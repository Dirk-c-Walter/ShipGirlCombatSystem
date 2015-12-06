namespace java jessy.shipgirlcombatsystem.thrift

exception ShipGirlServiceError {
    1: string message;
}

struct ThriftColor {
    1: i32 red;
    2: i32 green;
    3: i32 blue;
}

struct ThriftPlayer {
    1: string name;
    2: ThriftColor color;
}

struct ThriftWelcome {
    1: string motd;
}

struct ThriftHex {
    1: i32 q;
    2: i32 r;
}

struct ThriftShip {
    1: string id;
    2: optional ThriftPlayer owner;
    3: string type;
    4: optional map<string, string> properties;
    5: ThriftHex position;
}

struct ThriftGameState {
    1: i32 mapRadious;
    2: i32 turn;
    3: i32 phaseCode;
    4: list<ThriftShip> items;
}

enum ThriftPacketType {
    DoneLobby;
    DoneMove;
    DoneFire;
}

enum ThriftCommandEnum {
    Advance;
    TurnLeft;
    TurnRight;
    DriftN;
    DriftNE;
    DriftSE;
    DriftS;
    DriftSW;
    DriftNW;
}

struct ThriftCommand {
    1: string type;
    2: optional string sourceId;
    3: optional map<string, string> properties;
    4: optional ThriftCommandEnum commandCode;
    5: optional ThriftShip newShip;
}

struct ThriftCommandList {
    1: ThriftPacketType type;
    2: optional ThriftPlayer player;
    3: list<ThriftCommand> commands;
}

service ShipGirlCombatSystemServer {
  ThriftWelcome joinPlayer(1: ThriftPlayer player) throws (1: ShipGirlServiceError ex);
  ThriftGameState donePhase(1: ThriftCommandList commands) throws (1: ShipGirlServiceError ex);
}
