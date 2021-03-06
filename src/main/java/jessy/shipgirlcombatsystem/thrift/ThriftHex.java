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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2015-12-27")
public class ThriftHex implements org.apache.thrift.TBase<ThriftHex, ThriftHex._Fields>, java.io.Serializable, Cloneable, Comparable<ThriftHex> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ThriftHex");

  private static final org.apache.thrift.protocol.TField Q_FIELD_DESC = new org.apache.thrift.protocol.TField("q", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField R_FIELD_DESC = new org.apache.thrift.protocol.TField("r", org.apache.thrift.protocol.TType.I32, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ThriftHexStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ThriftHexTupleSchemeFactory());
  }

  public int q; // required
  public int r; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    Q((short)1, "q"),
    R((short)2, "r");

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
        case 1: // Q
          return Q;
        case 2: // R
          return R;
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
  private static final int __Q_ISSET_ID = 0;
  private static final int __R_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.Q, new org.apache.thrift.meta_data.FieldMetaData("q", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.R, new org.apache.thrift.meta_data.FieldMetaData("r", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ThriftHex.class, metaDataMap);
  }

  public ThriftHex() {
  }

  public ThriftHex(
    int q,
    int r)
  {
    this();
    this.q = q;
    setQIsSet(true);
    this.r = r;
    setRIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ThriftHex(ThriftHex other) {
    __isset_bitfield = other.__isset_bitfield;
    this.q = other.q;
    this.r = other.r;
  }

  public ThriftHex deepCopy() {
    return new ThriftHex(this);
  }

  @Override
  public void clear() {
    setQIsSet(false);
    this.q = 0;
    setRIsSet(false);
    this.r = 0;
  }

  public int getQ() {
    return this.q;
  }

  public ThriftHex setQ(int q) {
    this.q = q;
    setQIsSet(true);
    return this;
  }

  public void unsetQ() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __Q_ISSET_ID);
  }

  /** Returns true if field q is set (has been assigned a value) and false otherwise */
  public boolean isSetQ() {
    return EncodingUtils.testBit(__isset_bitfield, __Q_ISSET_ID);
  }

  public void setQIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __Q_ISSET_ID, value);
  }

  public int getR() {
    return this.r;
  }

  public ThriftHex setR(int r) {
    this.r = r;
    setRIsSet(true);
    return this;
  }

  public void unsetR() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __R_ISSET_ID);
  }

  /** Returns true if field r is set (has been assigned a value) and false otherwise */
  public boolean isSetR() {
    return EncodingUtils.testBit(__isset_bitfield, __R_ISSET_ID);
  }

  public void setRIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __R_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case Q:
      if (value == null) {
        unsetQ();
      } else {
        setQ((Integer)value);
      }
      break;

    case R:
      if (value == null) {
        unsetR();
      } else {
        setR((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case Q:
      return getQ();

    case R:
      return getR();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case Q:
      return isSetQ();
    case R:
      return isSetR();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ThriftHex)
      return this.equals((ThriftHex)that);
    return false;
  }

  public boolean equals(ThriftHex that) {
    if (that == null)
      return false;

    boolean this_present_q = true;
    boolean that_present_q = true;
    if (this_present_q || that_present_q) {
      if (!(this_present_q && that_present_q))
        return false;
      if (this.q != that.q)
        return false;
    }

    boolean this_present_r = true;
    boolean that_present_r = true;
    if (this_present_r || that_present_r) {
      if (!(this_present_r && that_present_r))
        return false;
      if (this.r != that.r)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_q = true;
    list.add(present_q);
    if (present_q)
      list.add(q);

    boolean present_r = true;
    list.add(present_r);
    if (present_r)
      list.add(r);

    return list.hashCode();
  }

  @Override
  public int compareTo(ThriftHex other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetQ()).compareTo(other.isSetQ());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetQ()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.q, other.q);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetR()).compareTo(other.isSetR());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetR()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.r, other.r);
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
    StringBuilder sb = new StringBuilder("ThriftHex(");
    boolean first = true;

    sb.append("q:");
    sb.append(this.q);
    first = false;
    if (!first) sb.append(", ");
    sb.append("r:");
    sb.append(this.r);
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

  private static class ThriftHexStandardSchemeFactory implements SchemeFactory {
    public ThriftHexStandardScheme getScheme() {
      return new ThriftHexStandardScheme();
    }
  }

  private static class ThriftHexStandardScheme extends StandardScheme<ThriftHex> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ThriftHex struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // Q
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.q = iprot.readI32();
              struct.setQIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // R
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.r = iprot.readI32();
              struct.setRIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ThriftHex struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(Q_FIELD_DESC);
      oprot.writeI32(struct.q);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(R_FIELD_DESC);
      oprot.writeI32(struct.r);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ThriftHexTupleSchemeFactory implements SchemeFactory {
    public ThriftHexTupleScheme getScheme() {
      return new ThriftHexTupleScheme();
    }
  }

  private static class ThriftHexTupleScheme extends TupleScheme<ThriftHex> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ThriftHex struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetQ()) {
        optionals.set(0);
      }
      if (struct.isSetR()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetQ()) {
        oprot.writeI32(struct.q);
      }
      if (struct.isSetR()) {
        oprot.writeI32(struct.r);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ThriftHex struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.q = iprot.readI32();
        struct.setQIsSet(true);
      }
      if (incoming.get(1)) {
        struct.r = iprot.readI32();
        struct.setRIsSet(true);
      }
    }
  }

}

