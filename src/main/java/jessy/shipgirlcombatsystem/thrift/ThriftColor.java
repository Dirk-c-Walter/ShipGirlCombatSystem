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
public class ThriftColor implements org.apache.thrift.TBase<ThriftColor, ThriftColor._Fields>, java.io.Serializable, Cloneable, Comparable<ThriftColor> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ThriftColor");

  private static final org.apache.thrift.protocol.TField RED_FIELD_DESC = new org.apache.thrift.protocol.TField("red", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField GREEN_FIELD_DESC = new org.apache.thrift.protocol.TField("green", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField BLUE_FIELD_DESC = new org.apache.thrift.protocol.TField("blue", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ThriftColorStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ThriftColorTupleSchemeFactory());
  }

  public int red; // required
  public int green; // required
  public int blue; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RED((short)1, "red"),
    GREEN((short)2, "green"),
    BLUE((short)3, "blue");

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
        case 1: // RED
          return RED;
        case 2: // GREEN
          return GREEN;
        case 3: // BLUE
          return BLUE;
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
  private static final int __RED_ISSET_ID = 0;
  private static final int __GREEN_ISSET_ID = 1;
  private static final int __BLUE_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RED, new org.apache.thrift.meta_data.FieldMetaData("red", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.GREEN, new org.apache.thrift.meta_data.FieldMetaData("green", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.BLUE, new org.apache.thrift.meta_data.FieldMetaData("blue", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ThriftColor.class, metaDataMap);
  }

  public ThriftColor() {
  }

  public ThriftColor(
    int red,
    int green,
    int blue)
  {
    this();
    this.red = red;
    setRedIsSet(true);
    this.green = green;
    setGreenIsSet(true);
    this.blue = blue;
    setBlueIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ThriftColor(ThriftColor other) {
    __isset_bitfield = other.__isset_bitfield;
    this.red = other.red;
    this.green = other.green;
    this.blue = other.blue;
  }

  public ThriftColor deepCopy() {
    return new ThriftColor(this);
  }

  @Override
  public void clear() {
    setRedIsSet(false);
    this.red = 0;
    setGreenIsSet(false);
    this.green = 0;
    setBlueIsSet(false);
    this.blue = 0;
  }

  public int getRed() {
    return this.red;
  }

  public ThriftColor setRed(int red) {
    this.red = red;
    setRedIsSet(true);
    return this;
  }

  public void unsetRed() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RED_ISSET_ID);
  }

  /** Returns true if field red is set (has been assigned a value) and false otherwise */
  public boolean isSetRed() {
    return EncodingUtils.testBit(__isset_bitfield, __RED_ISSET_ID);
  }

  public void setRedIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RED_ISSET_ID, value);
  }

  public int getGreen() {
    return this.green;
  }

  public ThriftColor setGreen(int green) {
    this.green = green;
    setGreenIsSet(true);
    return this;
  }

  public void unsetGreen() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __GREEN_ISSET_ID);
  }

  /** Returns true if field green is set (has been assigned a value) and false otherwise */
  public boolean isSetGreen() {
    return EncodingUtils.testBit(__isset_bitfield, __GREEN_ISSET_ID);
  }

  public void setGreenIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __GREEN_ISSET_ID, value);
  }

  public int getBlue() {
    return this.blue;
  }

  public ThriftColor setBlue(int blue) {
    this.blue = blue;
    setBlueIsSet(true);
    return this;
  }

  public void unsetBlue() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __BLUE_ISSET_ID);
  }

  /** Returns true if field blue is set (has been assigned a value) and false otherwise */
  public boolean isSetBlue() {
    return EncodingUtils.testBit(__isset_bitfield, __BLUE_ISSET_ID);
  }

  public void setBlueIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __BLUE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case RED:
      if (value == null) {
        unsetRed();
      } else {
        setRed((Integer)value);
      }
      break;

    case GREEN:
      if (value == null) {
        unsetGreen();
      } else {
        setGreen((Integer)value);
      }
      break;

    case BLUE:
      if (value == null) {
        unsetBlue();
      } else {
        setBlue((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case RED:
      return getRed();

    case GREEN:
      return getGreen();

    case BLUE:
      return getBlue();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case RED:
      return isSetRed();
    case GREEN:
      return isSetGreen();
    case BLUE:
      return isSetBlue();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ThriftColor)
      return this.equals((ThriftColor)that);
    return false;
  }

  public boolean equals(ThriftColor that) {
    if (that == null)
      return false;

    boolean this_present_red = true;
    boolean that_present_red = true;
    if (this_present_red || that_present_red) {
      if (!(this_present_red && that_present_red))
        return false;
      if (this.red != that.red)
        return false;
    }

    boolean this_present_green = true;
    boolean that_present_green = true;
    if (this_present_green || that_present_green) {
      if (!(this_present_green && that_present_green))
        return false;
      if (this.green != that.green)
        return false;
    }

    boolean this_present_blue = true;
    boolean that_present_blue = true;
    if (this_present_blue || that_present_blue) {
      if (!(this_present_blue && that_present_blue))
        return false;
      if (this.blue != that.blue)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_red = true;
    list.add(present_red);
    if (present_red)
      list.add(red);

    boolean present_green = true;
    list.add(present_green);
    if (present_green)
      list.add(green);

    boolean present_blue = true;
    list.add(present_blue);
    if (present_blue)
      list.add(blue);

    return list.hashCode();
  }

  @Override
  public int compareTo(ThriftColor other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetRed()).compareTo(other.isSetRed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRed()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.red, other.red);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetGreen()).compareTo(other.isSetGreen());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGreen()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.green, other.green);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBlue()).compareTo(other.isSetBlue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBlue()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.blue, other.blue);
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
    StringBuilder sb = new StringBuilder("ThriftColor(");
    boolean first = true;

    sb.append("red:");
    sb.append(this.red);
    first = false;
    if (!first) sb.append(", ");
    sb.append("green:");
    sb.append(this.green);
    first = false;
    if (!first) sb.append(", ");
    sb.append("blue:");
    sb.append(this.blue);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ThriftColorStandardSchemeFactory implements SchemeFactory {
    public ThriftColorStandardScheme getScheme() {
      return new ThriftColorStandardScheme();
    }
  }

  private static class ThriftColorStandardScheme extends StandardScheme<ThriftColor> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ThriftColor struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RED
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.red = iprot.readI32();
              struct.setRedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // GREEN
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.green = iprot.readI32();
              struct.setGreenIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // BLUE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.blue = iprot.readI32();
              struct.setBlueIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ThriftColor struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(RED_FIELD_DESC);
      oprot.writeI32(struct.red);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(GREEN_FIELD_DESC);
      oprot.writeI32(struct.green);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(BLUE_FIELD_DESC);
      oprot.writeI32(struct.blue);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ThriftColorTupleSchemeFactory implements SchemeFactory {
    public ThriftColorTupleScheme getScheme() {
      return new ThriftColorTupleScheme();
    }
  }

  private static class ThriftColorTupleScheme extends TupleScheme<ThriftColor> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ThriftColor struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetRed()) {
        optionals.set(0);
      }
      if (struct.isSetGreen()) {
        optionals.set(1);
      }
      if (struct.isSetBlue()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetRed()) {
        oprot.writeI32(struct.red);
      }
      if (struct.isSetGreen()) {
        oprot.writeI32(struct.green);
      }
      if (struct.isSetBlue()) {
        oprot.writeI32(struct.blue);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ThriftColor struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.red = iprot.readI32();
        struct.setRedIsSet(true);
      }
      if (incoming.get(1)) {
        struct.green = iprot.readI32();
        struct.setGreenIsSet(true);
      }
      if (incoming.get(2)) {
        struct.blue = iprot.readI32();
        struct.setBlueIsSet(true);
      }
    }
  }

}

