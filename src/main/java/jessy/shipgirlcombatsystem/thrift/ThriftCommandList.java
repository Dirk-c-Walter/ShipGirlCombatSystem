/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package jessy.shipgirlcombatsystem.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2015-12-19")
public class ThriftCommandList implements org.apache.thrift.TBase<ThriftCommandList, ThriftCommandList._Fields>, java.io.Serializable, Cloneable, Comparable<ThriftCommandList> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ThriftCommandList");

  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField PLAYER_FIELD_DESC = new org.apache.thrift.protocol.TField("player", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField COMMANDS_FIELD_DESC = new org.apache.thrift.protocol.TField("commands", org.apache.thrift.protocol.TType.LIST, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ThriftCommandListStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ThriftCommandListTupleSchemeFactory());
  }

  /**
   * 
   * @see ThriftPacketType
   */
  public ThriftPacketType type; // required
  public ThriftPlayer player; // optional
  public List<ThriftCommand> commands; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see ThriftPacketType
     */
    TYPE((short)1, "type"),
    PLAYER((short)2, "player"),
    COMMANDS((short)3, "commands");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // TYPE
          return TYPE;
        case 2: // PLAYER
          return PLAYER;
        case 3: // COMMANDS
          return COMMANDS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.PLAYER,_Fields.COMMANDS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, ThriftPacketType.class)));
    tmpMap.put(_Fields.PLAYER, new org.apache.thrift.meta_data.FieldMetaData("player", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ThriftPlayer.class)));
    tmpMap.put(_Fields.COMMANDS, new org.apache.thrift.meta_data.FieldMetaData("commands", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ThriftCommand.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ThriftCommandList.class, metaDataMap);
  }

  public ThriftCommandList() {
  }

  public ThriftCommandList(
    ThriftPacketType type)
  {
    this();
    this.type = type;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ThriftCommandList(ThriftCommandList other) {
    if (other.isSetType()) {
      this.type = other.type;
    }
    if (other.isSetPlayer()) {
      this.player = new ThriftPlayer(other.player);
    }
    if (other.isSetCommands()) {
      List<ThriftCommand> __this__commands = new ArrayList<ThriftCommand>(other.commands.size());
      for (ThriftCommand other_element : other.commands) {
        __this__commands.add(new ThriftCommand(other_element));
      }
      this.commands = __this__commands;
    }
  }

  public ThriftCommandList deepCopy() {
    return new ThriftCommandList(this);
  }

  @Override
  public void clear() {
    this.type = null;
    this.player = null;
    this.commands = null;
  }

  /**
   * 
   * @see ThriftPacketType
   */
  public ThriftPacketType getType() {
    return this.type;
  }

  /**
   * 
   * @see ThriftPacketType
   */
  public ThriftCommandList setType(ThriftPacketType type) {
    this.type = type;
    return this;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  public ThriftPlayer getPlayer() {
    return this.player;
  }

  public ThriftCommandList setPlayer(ThriftPlayer player) {
    this.player = player;
    return this;
  }

  public void unsetPlayer() {
    this.player = null;
  }

  /** Returns true if field player is set (has been assigned a value) and false otherwise */
  public boolean isSetPlayer() {
    return this.player != null;
  }

  public void setPlayerIsSet(boolean value) {
    if (!value) {
      this.player = null;
    }
  }

  public int getCommandsSize() {
    return (this.commands == null) ? 0 : this.commands.size();
  }

  public java.util.Iterator<ThriftCommand> getCommandsIterator() {
    return (this.commands == null) ? null : this.commands.iterator();
  }

  public void addToCommands(ThriftCommand elem) {
    if (this.commands == null) {
      this.commands = new ArrayList<ThriftCommand>();
    }
    this.commands.add(elem);
  }

  public List<ThriftCommand> getCommands() {
    return this.commands;
  }

  public ThriftCommandList setCommands(List<ThriftCommand> commands) {
    this.commands = commands;
    return this;
  }

  public void unsetCommands() {
    this.commands = null;
  }

  /** Returns true if field commands is set (has been assigned a value) and false otherwise */
  public boolean isSetCommands() {
    return this.commands != null;
  }

  public void setCommandsIsSet(boolean value) {
    if (!value) {
      this.commands = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((ThriftPacketType)value);
      }
      break;

    case PLAYER:
      if (value == null) {
        unsetPlayer();
      } else {
        setPlayer((ThriftPlayer)value);
      }
      break;

    case COMMANDS:
      if (value == null) {
        unsetCommands();
      } else {
        setCommands((List<ThriftCommand>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TYPE:
      return getType();

    case PLAYER:
      return getPlayer();

    case COMMANDS:
      return getCommands();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TYPE:
      return isSetType();
    case PLAYER:
      return isSetPlayer();
    case COMMANDS:
      return isSetCommands();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ThriftCommandList)
      return this.equals((ThriftCommandList)that);
    return false;
  }

  public boolean equals(ThriftCommandList that) {
    if (that == null)
      return false;

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_player = true && this.isSetPlayer();
    boolean that_present_player = true && that.isSetPlayer();
    if (this_present_player || that_present_player) {
      if (!(this_present_player && that_present_player))
        return false;
      if (!this.player.equals(that.player))
        return false;
    }

    boolean this_present_commands = true && this.isSetCommands();
    boolean that_present_commands = true && that.isSetCommands();
    if (this_present_commands || that_present_commands) {
      if (!(this_present_commands && that_present_commands))
        return false;
      if (!this.commands.equals(that.commands))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_type = true && (isSetType());
    list.add(present_type);
    if (present_type)
      list.add(type.getValue());

    boolean present_player = true && (isSetPlayer());
    list.add(present_player);
    if (present_player)
      list.add(player);

    boolean present_commands = true && (isSetCommands());
    list.add(present_commands);
    if (present_commands)
      list.add(commands);

    return list.hashCode();
  }

  @Override
  public int compareTo(ThriftCommandList other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPlayer()).compareTo(other.isSetPlayer());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPlayer()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.player, other.player);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCommands()).compareTo(other.isSetCommands());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCommands()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.commands, other.commands);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ThriftCommandList(");
    boolean first = true;

    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    if (isSetPlayer()) {
      if (!first) sb.append(", ");
      sb.append("player:");
      if (this.player == null) {
        sb.append("null");
      } else {
        sb.append(this.player);
      }
      first = false;
    }
    if (isSetCommands()) {
      if (!first) sb.append(", ");
      sb.append("commands:");
      if (this.commands == null) {
        sb.append("null");
      } else {
        sb.append(this.commands);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (player != null) {
      player.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ThriftCommandListStandardSchemeFactory implements SchemeFactory {
    public ThriftCommandListStandardScheme getScheme() {
      return new ThriftCommandListStandardScheme();
    }
  }

  private static class ThriftCommandListStandardScheme extends StandardScheme<ThriftCommandList> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ThriftCommandList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.type = jessy.shipgirlcombatsystem.thrift.ThriftPacketType.findByValue(iprot.readI32());
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PLAYER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.player = new ThriftPlayer();
              struct.player.read(iprot);
              struct.setPlayerIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // COMMANDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list54 = iprot.readListBegin();
                struct.commands = new ArrayList<ThriftCommand>(_list54.size);
                ThriftCommand _elem55;
                for (int _i56 = 0; _i56 < _list54.size; ++_i56)
                {
                  _elem55 = new ThriftCommand();
                  _elem55.read(iprot);
                  struct.commands.add(_elem55);
                }
                iprot.readListEnd();
              }
              struct.setCommandsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ThriftCommandList struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.type != null) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeI32(struct.type.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.player != null) {
        if (struct.isSetPlayer()) {
          oprot.writeFieldBegin(PLAYER_FIELD_DESC);
          struct.player.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.commands != null) {
        if (struct.isSetCommands()) {
          oprot.writeFieldBegin(COMMANDS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.commands.size()));
            for (ThriftCommand _iter57 : struct.commands)
            {
              _iter57.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ThriftCommandListTupleSchemeFactory implements SchemeFactory {
    public ThriftCommandListTupleScheme getScheme() {
      return new ThriftCommandListTupleScheme();
    }
  }

  private static class ThriftCommandListTupleScheme extends TupleScheme<ThriftCommandList> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ThriftCommandList struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetType()) {
        optionals.set(0);
      }
      if (struct.isSetPlayer()) {
        optionals.set(1);
      }
      if (struct.isSetCommands()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetType()) {
        oprot.writeI32(struct.type.getValue());
      }
      if (struct.isSetPlayer()) {
        struct.player.write(oprot);
      }
      if (struct.isSetCommands()) {
        {
          oprot.writeI32(struct.commands.size());
          for (ThriftCommand _iter58 : struct.commands)
          {
            _iter58.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ThriftCommandList struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.type = jessy.shipgirlcombatsystem.thrift.ThriftPacketType.findByValue(iprot.readI32());
        struct.setTypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.player = new ThriftPlayer();
        struct.player.read(iprot);
        struct.setPlayerIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list59 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.commands = new ArrayList<ThriftCommand>(_list59.size);
          ThriftCommand _elem60;
          for (int _i61 = 0; _i61 < _list59.size; ++_i61)
          {
            _elem60 = new ThriftCommand();
            _elem60.read(iprot);
            struct.commands.add(_elem60);
          }
        }
        struct.setCommandsIsSet(true);
      }
    }
  }

}

