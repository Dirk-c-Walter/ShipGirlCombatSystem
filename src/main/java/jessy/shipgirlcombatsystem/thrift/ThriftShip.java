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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2015-12-06")
public class ThriftShip implements org.apache.thrift.TBase<ThriftShip, ThriftShip._Fields>, java.io.Serializable, Cloneable, Comparable<ThriftShip> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ThriftShip");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField OWNER_FIELD_DESC = new org.apache.thrift.protocol.TField("owner", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PROPERTIES_FIELD_DESC = new org.apache.thrift.protocol.TField("properties", org.apache.thrift.protocol.TType.MAP, (short)4);
  private static final org.apache.thrift.protocol.TField POSITION_FIELD_DESC = new org.apache.thrift.protocol.TField("position", org.apache.thrift.protocol.TType.STRUCT, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ThriftShipStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ThriftShipTupleSchemeFactory());
  }

  public String id; // required
  public ThriftPlayer owner; // optional
  public String type; // required
  public Map<String,String> properties; // optional
  public ThriftHex position; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    OWNER((short)2, "owner"),
    TYPE((short)3, "type"),
    PROPERTIES((short)4, "properties"),
    POSITION((short)5, "position");

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
        case 1: // ID
          return ID;
        case 2: // OWNER
          return OWNER;
        case 3: // TYPE
          return TYPE;
        case 4: // PROPERTIES
          return PROPERTIES;
        case 5: // POSITION
          return POSITION;
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
  private static final _Fields optionals[] = {_Fields.OWNER,_Fields.PROPERTIES};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.OWNER, new org.apache.thrift.meta_data.FieldMetaData("owner", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ThriftPlayer.class)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PROPERTIES, new org.apache.thrift.meta_data.FieldMetaData("properties", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.POSITION, new org.apache.thrift.meta_data.FieldMetaData("position", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ThriftHex.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ThriftShip.class, metaDataMap);
  }

  public ThriftShip() {
  }

  public ThriftShip(
    String id,
    String type,
    ThriftHex position)
  {
    this();
    this.id = id;
    this.type = type;
    this.position = position;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ThriftShip(ThriftShip other) {
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetOwner()) {
      this.owner = new ThriftPlayer(other.owner);
    }
    if (other.isSetType()) {
      this.type = other.type;
    }
    if (other.isSetProperties()) {
      Map<String,String> __this__properties = new HashMap<String,String>(other.properties);
      this.properties = __this__properties;
    }
    if (other.isSetPosition()) {
      this.position = new ThriftHex(other.position);
    }
  }

  public ThriftShip deepCopy() {
    return new ThriftShip(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.owner = null;
    this.type = null;
    this.properties = null;
    this.position = null;
  }

  public String getId() {
    return this.id;
  }

  public ThriftShip setId(String id) {
    this.id = id;
    return this;
  }

  public void unsetId() {
    this.id = null;
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return this.id != null;
  }

  public void setIdIsSet(boolean value) {
    if (!value) {
      this.id = null;
    }
  }

  public ThriftPlayer getOwner() {
    return this.owner;
  }

  public ThriftShip setOwner(ThriftPlayer owner) {
    this.owner = owner;
    return this;
  }

  public void unsetOwner() {
    this.owner = null;
  }

  /** Returns true if field owner is set (has been assigned a value) and false otherwise */
  public boolean isSetOwner() {
    return this.owner != null;
  }

  public void setOwnerIsSet(boolean value) {
    if (!value) {
      this.owner = null;
    }
  }

  public String getType() {
    return this.type;
  }

  public ThriftShip setType(String type) {
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

  public int getPropertiesSize() {
    return (this.properties == null) ? 0 : this.properties.size();
  }

  public void putToProperties(String key, String val) {
    if (this.properties == null) {
      this.properties = new HashMap<String,String>();
    }
    this.properties.put(key, val);
  }

  public Map<String,String> getProperties() {
    return this.properties;
  }

  public ThriftShip setProperties(Map<String,String> properties) {
    this.properties = properties;
    return this;
  }

  public void unsetProperties() {
    this.properties = null;
  }

  /** Returns true if field properties is set (has been assigned a value) and false otherwise */
  public boolean isSetProperties() {
    return this.properties != null;
  }

  public void setPropertiesIsSet(boolean value) {
    if (!value) {
      this.properties = null;
    }
  }

  public ThriftHex getPosition() {
    return this.position;
  }

  public ThriftShip setPosition(ThriftHex position) {
    this.position = position;
    return this;
  }

  public void unsetPosition() {
    this.position = null;
  }

  /** Returns true if field position is set (has been assigned a value) and false otherwise */
  public boolean isSetPosition() {
    return this.position != null;
  }

  public void setPositionIsSet(boolean value) {
    if (!value) {
      this.position = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((String)value);
      }
      break;

    case OWNER:
      if (value == null) {
        unsetOwner();
      } else {
        setOwner((ThriftPlayer)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((String)value);
      }
      break;

    case PROPERTIES:
      if (value == null) {
        unsetProperties();
      } else {
        setProperties((Map<String,String>)value);
      }
      break;

    case POSITION:
      if (value == null) {
        unsetPosition();
      } else {
        setPosition((ThriftHex)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case OWNER:
      return getOwner();

    case TYPE:
      return getType();

    case PROPERTIES:
      return getProperties();

    case POSITION:
      return getPosition();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case OWNER:
      return isSetOwner();
    case TYPE:
      return isSetType();
    case PROPERTIES:
      return isSetProperties();
    case POSITION:
      return isSetPosition();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ThriftShip)
      return this.equals((ThriftShip)that);
    return false;
  }

  public boolean equals(ThriftShip that) {
    if (that == null)
      return false;

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (!this.id.equals(that.id))
        return false;
    }

    boolean this_present_owner = true && this.isSetOwner();
    boolean that_present_owner = true && that.isSetOwner();
    if (this_present_owner || that_present_owner) {
      if (!(this_present_owner && that_present_owner))
        return false;
      if (!this.owner.equals(that.owner))
        return false;
    }

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_properties = true && this.isSetProperties();
    boolean that_present_properties = true && that.isSetProperties();
    if (this_present_properties || that_present_properties) {
      if (!(this_present_properties && that_present_properties))
        return false;
      if (!this.properties.equals(that.properties))
        return false;
    }

    boolean this_present_position = true && this.isSetPosition();
    boolean that_present_position = true && that.isSetPosition();
    if (this_present_position || that_present_position) {
      if (!(this_present_position && that_present_position))
        return false;
      if (!this.position.equals(that.position))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true && (isSetId());
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_owner = true && (isSetOwner());
    list.add(present_owner);
    if (present_owner)
      list.add(owner);

    boolean present_type = true && (isSetType());
    list.add(present_type);
    if (present_type)
      list.add(type);

    boolean present_properties = true && (isSetProperties());
    list.add(present_properties);
    if (present_properties)
      list.add(properties);

    boolean present_position = true && (isSetPosition());
    list.add(present_position);
    if (present_position)
      list.add(position);

    return list.hashCode();
  }

  @Override
  public int compareTo(ThriftShip other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOwner()).compareTo(other.isSetOwner());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOwner()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.owner, other.owner);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
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
    lastComparison = Boolean.valueOf(isSetProperties()).compareTo(other.isSetProperties());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProperties()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.properties, other.properties);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPosition()).compareTo(other.isSetPosition());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPosition()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.position, other.position);
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
    StringBuilder sb = new StringBuilder("ThriftShip(");
    boolean first = true;

    sb.append("id:");
    if (this.id == null) {
      sb.append("null");
    } else {
      sb.append(this.id);
    }
    first = false;
    if (isSetOwner()) {
      if (!first) sb.append(", ");
      sb.append("owner:");
      if (this.owner == null) {
        sb.append("null");
      } else {
        sb.append(this.owner);
      }
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    if (isSetProperties()) {
      if (!first) sb.append(", ");
      sb.append("properties:");
      if (this.properties == null) {
        sb.append("null");
      } else {
        sb.append(this.properties);
      }
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("position:");
    if (this.position == null) {
      sb.append("null");
    } else {
      sb.append(this.position);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (owner != null) {
      owner.validate();
    }
    if (position != null) {
      position.validate();
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

  private static class ThriftShipStandardSchemeFactory implements SchemeFactory {
    public ThriftShipStandardScheme getScheme() {
      return new ThriftShipStandardScheme();
    }
  }

  private static class ThriftShipStandardScheme extends StandardScheme<ThriftShip> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ThriftShip struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.id = iprot.readString();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // OWNER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.owner = new ThriftPlayer();
              struct.owner.read(iprot);
              struct.setOwnerIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.type = iprot.readString();
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PROPERTIES
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map0 = iprot.readMapBegin();
                struct.properties = new HashMap<String,String>(2*_map0.size);
                String _key1;
                String _val2;
                for (int _i3 = 0; _i3 < _map0.size; ++_i3)
                {
                  _key1 = iprot.readString();
                  _val2 = iprot.readString();
                  struct.properties.put(_key1, _val2);
                }
                iprot.readMapEnd();
              }
              struct.setPropertiesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // POSITION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.position = new ThriftHex();
              struct.position.read(iprot);
              struct.setPositionIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ThriftShip struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.id != null) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        oprot.writeString(struct.id);
        oprot.writeFieldEnd();
      }
      if (struct.owner != null) {
        if (struct.isSetOwner()) {
          oprot.writeFieldBegin(OWNER_FIELD_DESC);
          struct.owner.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.type != null) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeString(struct.type);
        oprot.writeFieldEnd();
      }
      if (struct.properties != null) {
        if (struct.isSetProperties()) {
          oprot.writeFieldBegin(PROPERTIES_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.properties.size()));
            for (Map.Entry<String, String> _iter4 : struct.properties.entrySet())
            {
              oprot.writeString(_iter4.getKey());
              oprot.writeString(_iter4.getValue());
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.position != null) {
        oprot.writeFieldBegin(POSITION_FIELD_DESC);
        struct.position.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ThriftShipTupleSchemeFactory implements SchemeFactory {
    public ThriftShipTupleScheme getScheme() {
      return new ThriftShipTupleScheme();
    }
  }

  private static class ThriftShipTupleScheme extends TupleScheme<ThriftShip> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ThriftShip struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetOwner()) {
        optionals.set(1);
      }
      if (struct.isSetType()) {
        optionals.set(2);
      }
      if (struct.isSetProperties()) {
        optionals.set(3);
      }
      if (struct.isSetPosition()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetId()) {
        oprot.writeString(struct.id);
      }
      if (struct.isSetOwner()) {
        struct.owner.write(oprot);
      }
      if (struct.isSetType()) {
        oprot.writeString(struct.type);
      }
      if (struct.isSetProperties()) {
        {
          oprot.writeI32(struct.properties.size());
          for (Map.Entry<String, String> _iter5 : struct.properties.entrySet())
          {
            oprot.writeString(_iter5.getKey());
            oprot.writeString(_iter5.getValue());
          }
        }
      }
      if (struct.isSetPosition()) {
        struct.position.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ThriftShip struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.id = iprot.readString();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.owner = new ThriftPlayer();
        struct.owner.read(iprot);
        struct.setOwnerIsSet(true);
      }
      if (incoming.get(2)) {
        struct.type = iprot.readString();
        struct.setTypeIsSet(true);
      }
      if (incoming.get(3)) {
        {
          org.apache.thrift.protocol.TMap _map6 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.properties = new HashMap<String,String>(2*_map6.size);
          String _key7;
          String _val8;
          for (int _i9 = 0; _i9 < _map6.size; ++_i9)
          {
            _key7 = iprot.readString();
            _val8 = iprot.readString();
            struct.properties.put(_key7, _val8);
          }
        }
        struct.setPropertiesIsSet(true);
      }
      if (incoming.get(4)) {
        struct.position = new ThriftHex();
        struct.position.read(iprot);
        struct.setPositionIsSet(true);
      }
    }
  }

}

