// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: services/addressbook.proto

package service;

/**
 * <pre>
 * The response message when reading the entries, either by read or search
 * </pre>
 *
 * Protobuf type {@code services.AddressBookResponse}
 */
public final class AddressBookResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:services.AddressBookResponse)
    AddressBookResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AddressBookResponse.newBuilder() to construct.
  private AddressBookResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AddressBookResponse() {
    message_ = "";
    book_ = java.util.Collections.emptyList();
    error_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new AddressBookResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AddressBookResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            isSuccess_ = input.readBool();
            break;
          }
          case 18: {
            service.Address.Builder subBuilder = null;
            if (address_ != null) {
              subBuilder = address_.toBuilder();
            }
            address_ = input.readMessage(service.Address.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(address_);
              address_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            message_ = s;
            break;
          }
          case 34: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              book_ = new java.util.ArrayList<service.Address>();
              mutable_bitField0_ |= 0x00000001;
            }
            book_.add(
                input.readMessage(service.Address.parser(), extensionRegistry));
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            error_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        book_ = java.util.Collections.unmodifiableList(book_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return service.AddressBookProto.internal_static_services_AddressBookResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return service.AddressBookProto.internal_static_services_AddressBookResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            service.AddressBookResponse.class, service.AddressBookResponse.Builder.class);
  }

  public static final int ISSUCCESS_FIELD_NUMBER = 1;
  private boolean isSuccess_;
  /**
   * <code>bool isSuccess = 1;</code>
   * @return The isSuccess.
   */
  @java.lang.Override
  public boolean getIsSuccess() {
    return isSuccess_;
  }

  public static final int ADDRESS_FIELD_NUMBER = 2;
  private service.Address address_;
  /**
   * <code>.services.Address address = 2;</code>
   * @return Whether the address field is set.
   */
  @java.lang.Override
  public boolean hasAddress() {
    return address_ != null;
  }
  /**
   * <code>.services.Address address = 2;</code>
   * @return The address.
   */
  @java.lang.Override
  public service.Address getAddress() {
    return address_ == null ? service.Address.getDefaultInstance() : address_;
  }
  /**
   * <code>.services.Address address = 2;</code>
   */
  @java.lang.Override
  public service.AddressOrBuilder getAddressOrBuilder() {
    return getAddress();
  }

  public static final int MESSAGE_FIELD_NUMBER = 3;
  private volatile java.lang.Object message_;
  /**
   * <code>string message = 3;</code>
   * @return The message.
   */
  @java.lang.Override
  public java.lang.String getMessage() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      message_ = s;
      return s;
    }
  }
  /**
   * <code>string message = 3;</code>
   * @return The bytes for message.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getMessageBytes() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      message_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BOOK_FIELD_NUMBER = 4;
  private java.util.List<service.Address> book_;
  /**
   * <code>repeated .services.Address book = 4;</code>
   */
  @java.lang.Override
  public java.util.List<service.Address> getBookList() {
    return book_;
  }
  /**
   * <code>repeated .services.Address book = 4;</code>
   */
  @java.lang.Override
  public java.util.List<? extends service.AddressOrBuilder> 
      getBookOrBuilderList() {
    return book_;
  }
  /**
   * <code>repeated .services.Address book = 4;</code>
   */
  @java.lang.Override
  public int getBookCount() {
    return book_.size();
  }
  /**
   * <code>repeated .services.Address book = 4;</code>
   */
  @java.lang.Override
  public service.Address getBook(int index) {
    return book_.get(index);
  }
  /**
   * <code>repeated .services.Address book = 4;</code>
   */
  @java.lang.Override
  public service.AddressOrBuilder getBookOrBuilder(
      int index) {
    return book_.get(index);
  }

  public static final int ERROR_FIELD_NUMBER = 5;
  private volatile java.lang.Object error_;
  /**
   * <code>string error = 5;</code>
   * @return The error.
   */
  @java.lang.Override
  public java.lang.String getError() {
    java.lang.Object ref = error_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      error_ = s;
      return s;
    }
  }
  /**
   * <code>string error = 5;</code>
   * @return The bytes for error.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getErrorBytes() {
    java.lang.Object ref = error_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      error_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (isSuccess_ != false) {
      output.writeBool(1, isSuccess_);
    }
    if (address_ != null) {
      output.writeMessage(2, getAddress());
    }
    if (!getMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, message_);
    }
    for (int i = 0; i < book_.size(); i++) {
      output.writeMessage(4, book_.get(i));
    }
    if (!getErrorBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, error_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (isSuccess_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, isSuccess_);
    }
    if (address_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getAddress());
    }
    if (!getMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, message_);
    }
    for (int i = 0; i < book_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, book_.get(i));
    }
    if (!getErrorBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, error_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof service.AddressBookResponse)) {
      return super.equals(obj);
    }
    service.AddressBookResponse other = (service.AddressBookResponse) obj;

    if (getIsSuccess()
        != other.getIsSuccess()) return false;
    if (hasAddress() != other.hasAddress()) return false;
    if (hasAddress()) {
      if (!getAddress()
          .equals(other.getAddress())) return false;
    }
    if (!getMessage()
        .equals(other.getMessage())) return false;
    if (!getBookList()
        .equals(other.getBookList())) return false;
    if (!getError()
        .equals(other.getError())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ISSUCCESS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getIsSuccess());
    if (hasAddress()) {
      hash = (37 * hash) + ADDRESS_FIELD_NUMBER;
      hash = (53 * hash) + getAddress().hashCode();
    }
    hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getMessage().hashCode();
    if (getBookCount() > 0) {
      hash = (37 * hash) + BOOK_FIELD_NUMBER;
      hash = (53 * hash) + getBookList().hashCode();
    }
    hash = (37 * hash) + ERROR_FIELD_NUMBER;
    hash = (53 * hash) + getError().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static service.AddressBookResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static service.AddressBookResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static service.AddressBookResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static service.AddressBookResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static service.AddressBookResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static service.AddressBookResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static service.AddressBookResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static service.AddressBookResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static service.AddressBookResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static service.AddressBookResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static service.AddressBookResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static service.AddressBookResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(service.AddressBookResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * The response message when reading the entries, either by read or search
   * </pre>
   *
   * Protobuf type {@code services.AddressBookResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:services.AddressBookResponse)
      service.AddressBookResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return service.AddressBookProto.internal_static_services_AddressBookResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return service.AddressBookProto.internal_static_services_AddressBookResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              service.AddressBookResponse.class, service.AddressBookResponse.Builder.class);
    }

    // Construct using service.AddressBookResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getBookFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      isSuccess_ = false;

      if (addressBuilder_ == null) {
        address_ = null;
      } else {
        address_ = null;
        addressBuilder_ = null;
      }
      message_ = "";

      if (bookBuilder_ == null) {
        book_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        bookBuilder_.clear();
      }
      error_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return service.AddressBookProto.internal_static_services_AddressBookResponse_descriptor;
    }

    @java.lang.Override
    public service.AddressBookResponse getDefaultInstanceForType() {
      return service.AddressBookResponse.getDefaultInstance();
    }

    @java.lang.Override
    public service.AddressBookResponse build() {
      service.AddressBookResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public service.AddressBookResponse buildPartial() {
      service.AddressBookResponse result = new service.AddressBookResponse(this);
      int from_bitField0_ = bitField0_;
      result.isSuccess_ = isSuccess_;
      if (addressBuilder_ == null) {
        result.address_ = address_;
      } else {
        result.address_ = addressBuilder_.build();
      }
      result.message_ = message_;
      if (bookBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          book_ = java.util.Collections.unmodifiableList(book_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.book_ = book_;
      } else {
        result.book_ = bookBuilder_.build();
      }
      result.error_ = error_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof service.AddressBookResponse) {
        return mergeFrom((service.AddressBookResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(service.AddressBookResponse other) {
      if (other == service.AddressBookResponse.getDefaultInstance()) return this;
      if (other.getIsSuccess() != false) {
        setIsSuccess(other.getIsSuccess());
      }
      if (other.hasAddress()) {
        mergeAddress(other.getAddress());
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      if (bookBuilder_ == null) {
        if (!other.book_.isEmpty()) {
          if (book_.isEmpty()) {
            book_ = other.book_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureBookIsMutable();
            book_.addAll(other.book_);
          }
          onChanged();
        }
      } else {
        if (!other.book_.isEmpty()) {
          if (bookBuilder_.isEmpty()) {
            bookBuilder_.dispose();
            bookBuilder_ = null;
            book_ = other.book_;
            bitField0_ = (bitField0_ & ~0x00000001);
            bookBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getBookFieldBuilder() : null;
          } else {
            bookBuilder_.addAllMessages(other.book_);
          }
        }
      }
      if (!other.getError().isEmpty()) {
        error_ = other.error_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      service.AddressBookResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (service.AddressBookResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private boolean isSuccess_ ;
    /**
     * <code>bool isSuccess = 1;</code>
     * @return The isSuccess.
     */
    @java.lang.Override
    public boolean getIsSuccess() {
      return isSuccess_;
    }
    /**
     * <code>bool isSuccess = 1;</code>
     * @param value The isSuccess to set.
     * @return This builder for chaining.
     */
    public Builder setIsSuccess(boolean value) {
      
      isSuccess_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool isSuccess = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearIsSuccess() {
      
      isSuccess_ = false;
      onChanged();
      return this;
    }

    private service.Address address_;
    private com.google.protobuf.SingleFieldBuilderV3<
        service.Address, service.Address.Builder, service.AddressOrBuilder> addressBuilder_;
    /**
     * <code>.services.Address address = 2;</code>
     * @return Whether the address field is set.
     */
    public boolean hasAddress() {
      return addressBuilder_ != null || address_ != null;
    }
    /**
     * <code>.services.Address address = 2;</code>
     * @return The address.
     */
    public service.Address getAddress() {
      if (addressBuilder_ == null) {
        return address_ == null ? service.Address.getDefaultInstance() : address_;
      } else {
        return addressBuilder_.getMessage();
      }
    }
    /**
     * <code>.services.Address address = 2;</code>
     */
    public Builder setAddress(service.Address value) {
      if (addressBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        address_ = value;
        onChanged();
      } else {
        addressBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.services.Address address = 2;</code>
     */
    public Builder setAddress(
        service.Address.Builder builderForValue) {
      if (addressBuilder_ == null) {
        address_ = builderForValue.build();
        onChanged();
      } else {
        addressBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.services.Address address = 2;</code>
     */
    public Builder mergeAddress(service.Address value) {
      if (addressBuilder_ == null) {
        if (address_ != null) {
          address_ =
            service.Address.newBuilder(address_).mergeFrom(value).buildPartial();
        } else {
          address_ = value;
        }
        onChanged();
      } else {
        addressBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.services.Address address = 2;</code>
     */
    public Builder clearAddress() {
      if (addressBuilder_ == null) {
        address_ = null;
        onChanged();
      } else {
        address_ = null;
        addressBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.services.Address address = 2;</code>
     */
    public service.Address.Builder getAddressBuilder() {
      
      onChanged();
      return getAddressFieldBuilder().getBuilder();
    }
    /**
     * <code>.services.Address address = 2;</code>
     */
    public service.AddressOrBuilder getAddressOrBuilder() {
      if (addressBuilder_ != null) {
        return addressBuilder_.getMessageOrBuilder();
      } else {
        return address_ == null ?
            service.Address.getDefaultInstance() : address_;
      }
    }
    /**
     * <code>.services.Address address = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        service.Address, service.Address.Builder, service.AddressOrBuilder> 
        getAddressFieldBuilder() {
      if (addressBuilder_ == null) {
        addressBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            service.Address, service.Address.Builder, service.AddressOrBuilder>(
                getAddress(),
                getParentForChildren(),
                isClean());
        address_ = null;
      }
      return addressBuilder_;
    }

    private java.lang.Object message_ = "";
    /**
     * <code>string message = 3;</code>
     * @return The message.
     */
    public java.lang.String getMessage() {
      java.lang.Object ref = message_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        message_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string message = 3;</code>
     * @return The bytes for message.
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string message = 3;</code>
     * @param value The message to set.
     * @return This builder for chaining.
     */
    public Builder setMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      message_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string message = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearMessage() {
      
      message_ = getDefaultInstance().getMessage();
      onChanged();
      return this;
    }
    /**
     * <code>string message = 3;</code>
     * @param value The bytes for message to set.
     * @return This builder for chaining.
     */
    public Builder setMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      message_ = value;
      onChanged();
      return this;
    }

    private java.util.List<service.Address> book_ =
      java.util.Collections.emptyList();
    private void ensureBookIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        book_ = new java.util.ArrayList<service.Address>(book_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        service.Address, service.Address.Builder, service.AddressOrBuilder> bookBuilder_;

    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public java.util.List<service.Address> getBookList() {
      if (bookBuilder_ == null) {
        return java.util.Collections.unmodifiableList(book_);
      } else {
        return bookBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public int getBookCount() {
      if (bookBuilder_ == null) {
        return book_.size();
      } else {
        return bookBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public service.Address getBook(int index) {
      if (bookBuilder_ == null) {
        return book_.get(index);
      } else {
        return bookBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public Builder setBook(
        int index, service.Address value) {
      if (bookBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBookIsMutable();
        book_.set(index, value);
        onChanged();
      } else {
        bookBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public Builder setBook(
        int index, service.Address.Builder builderForValue) {
      if (bookBuilder_ == null) {
        ensureBookIsMutable();
        book_.set(index, builderForValue.build());
        onChanged();
      } else {
        bookBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public Builder addBook(service.Address value) {
      if (bookBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBookIsMutable();
        book_.add(value);
        onChanged();
      } else {
        bookBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public Builder addBook(
        int index, service.Address value) {
      if (bookBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBookIsMutable();
        book_.add(index, value);
        onChanged();
      } else {
        bookBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public Builder addBook(
        service.Address.Builder builderForValue) {
      if (bookBuilder_ == null) {
        ensureBookIsMutable();
        book_.add(builderForValue.build());
        onChanged();
      } else {
        bookBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public Builder addBook(
        int index, service.Address.Builder builderForValue) {
      if (bookBuilder_ == null) {
        ensureBookIsMutable();
        book_.add(index, builderForValue.build());
        onChanged();
      } else {
        bookBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public Builder addAllBook(
        java.lang.Iterable<? extends service.Address> values) {
      if (bookBuilder_ == null) {
        ensureBookIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, book_);
        onChanged();
      } else {
        bookBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public Builder clearBook() {
      if (bookBuilder_ == null) {
        book_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        bookBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public Builder removeBook(int index) {
      if (bookBuilder_ == null) {
        ensureBookIsMutable();
        book_.remove(index);
        onChanged();
      } else {
        bookBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public service.Address.Builder getBookBuilder(
        int index) {
      return getBookFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public service.AddressOrBuilder getBookOrBuilder(
        int index) {
      if (bookBuilder_ == null) {
        return book_.get(index);  } else {
        return bookBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public java.util.List<? extends service.AddressOrBuilder> 
         getBookOrBuilderList() {
      if (bookBuilder_ != null) {
        return bookBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(book_);
      }
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public service.Address.Builder addBookBuilder() {
      return getBookFieldBuilder().addBuilder(
          service.Address.getDefaultInstance());
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public service.Address.Builder addBookBuilder(
        int index) {
      return getBookFieldBuilder().addBuilder(
          index, service.Address.getDefaultInstance());
    }
    /**
     * <code>repeated .services.Address book = 4;</code>
     */
    public java.util.List<service.Address.Builder> 
         getBookBuilderList() {
      return getBookFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        service.Address, service.Address.Builder, service.AddressOrBuilder> 
        getBookFieldBuilder() {
      if (bookBuilder_ == null) {
        bookBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            service.Address, service.Address.Builder, service.AddressOrBuilder>(
                book_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        book_ = null;
      }
      return bookBuilder_;
    }

    private java.lang.Object error_ = "";
    /**
     * <code>string error = 5;</code>
     * @return The error.
     */
    public java.lang.String getError() {
      java.lang.Object ref = error_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        error_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string error = 5;</code>
     * @return The bytes for error.
     */
    public com.google.protobuf.ByteString
        getErrorBytes() {
      java.lang.Object ref = error_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        error_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string error = 5;</code>
     * @param value The error to set.
     * @return This builder for chaining.
     */
    public Builder setError(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      error_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string error = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearError() {
      
      error_ = getDefaultInstance().getError();
      onChanged();
      return this;
    }
    /**
     * <code>string error = 5;</code>
     * @param value The bytes for error to set.
     * @return This builder for chaining.
     */
    public Builder setErrorBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      error_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:services.AddressBookResponse)
  }

  // @@protoc_insertion_point(class_scope:services.AddressBookResponse)
  private static final service.AddressBookResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new service.AddressBookResponse();
  }

  public static service.AddressBookResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AddressBookResponse>
      PARSER = new com.google.protobuf.AbstractParser<AddressBookResponse>() {
    @java.lang.Override
    public AddressBookResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AddressBookResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AddressBookResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AddressBookResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public service.AddressBookResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
