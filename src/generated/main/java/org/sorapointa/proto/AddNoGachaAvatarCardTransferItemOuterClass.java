// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AddNoGachaAvatarCardTransferItem.proto

package org.sorapointa.proto;

public final class AddNoGachaAvatarCardTransferItemOuterClass {
  private AddNoGachaAvatarCardTransferItemOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface AddNoGachaAvatarCardTransferItemOrBuilder extends
      // @@protoc_insertion_point(interface_extends:AddNoGachaAvatarCardTransferItem)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>uint32 count = 9;</code>
     * @return The count.
     */
    int getCount();

    /**
     * <code>uint32 item_id = 6;</code>
     * @return The itemId.
     */
    int getItemId();

    /**
     * <code>bool is_new = 15;</code>
     * @return The isNew.
     */
    boolean getIsNew();
  }
  /**
   * Protobuf type {@code AddNoGachaAvatarCardTransferItem}
   */
  public static final class AddNoGachaAvatarCardTransferItem extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:AddNoGachaAvatarCardTransferItem)
      AddNoGachaAvatarCardTransferItemOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use AddNoGachaAvatarCardTransferItem.newBuilder() to construct.
    private AddNoGachaAvatarCardTransferItem(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private AddNoGachaAvatarCardTransferItem() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new AddNoGachaAvatarCardTransferItem();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private AddNoGachaAvatarCardTransferItem(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
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
            case 48: {

              itemId_ = input.readUInt32();
              break;
            }
            case 72: {

              count_ = input.readUInt32();
              break;
            }
            case 120: {

              isNew_ = input.readBool();
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
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.internal_static_AddNoGachaAvatarCardTransferItem_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.internal_static_AddNoGachaAvatarCardTransferItem_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem.class, org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem.Builder.class);
    }

    public static final int COUNT_FIELD_NUMBER = 9;
    private int count_;
    /**
     * <code>uint32 count = 9;</code>
     * @return The count.
     */
    @java.lang.Override
    public int getCount() {
      return count_;
    }

    public static final int ITEM_ID_FIELD_NUMBER = 6;
    private int itemId_;
    /**
     * <code>uint32 item_id = 6;</code>
     * @return The itemId.
     */
    @java.lang.Override
    public int getItemId() {
      return itemId_;
    }

    public static final int IS_NEW_FIELD_NUMBER = 15;
    private boolean isNew_;
    /**
     * <code>bool is_new = 15;</code>
     * @return The isNew.
     */
    @java.lang.Override
    public boolean getIsNew() {
      return isNew_;
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
      if (itemId_ != 0) {
        output.writeUInt32(6, itemId_);
      }
      if (count_ != 0) {
        output.writeUInt32(9, count_);
      }
      if (isNew_ != false) {
        output.writeBool(15, isNew_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (itemId_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(6, itemId_);
      }
      if (count_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(9, count_);
      }
      if (isNew_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(15, isNew_);
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
      if (!(obj instanceof org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem)) {
        return super.equals(obj);
      }
      org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem other = (org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem) obj;

      if (getCount()
          != other.getCount()) return false;
      if (getItemId()
          != other.getItemId()) return false;
      if (getIsNew()
          != other.getIsNew()) return false;
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
      hash = (37 * hash) + COUNT_FIELD_NUMBER;
      hash = (53 * hash) + getCount();
      hash = (37 * hash) + ITEM_ID_FIELD_NUMBER;
      hash = (53 * hash) + getItemId();
      hash = (37 * hash) + IS_NEW_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getIsNew());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parseFrom(
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
    public static Builder newBuilder(org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem prototype) {
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
     * Protobuf type {@code AddNoGachaAvatarCardTransferItem}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:AddNoGachaAvatarCardTransferItem)
        org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItemOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.internal_static_AddNoGachaAvatarCardTransferItem_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.internal_static_AddNoGachaAvatarCardTransferItem_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem.class, org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem.Builder.class);
      }

      // Construct using org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem.newBuilder()
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
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        count_ = 0;

        itemId_ = 0;

        isNew_ = false;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.internal_static_AddNoGachaAvatarCardTransferItem_descriptor;
      }

      @java.lang.Override
      public org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem getDefaultInstanceForType() {
        return org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem.getDefaultInstance();
      }

      @java.lang.Override
      public org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem build() {
        org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem buildPartial() {
        org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem result = new org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem(this);
        result.count_ = count_;
        result.itemId_ = itemId_;
        result.isNew_ = isNew_;
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
        if (other instanceof org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem) {
          return mergeFrom((org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem other) {
        if (other == org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem.getDefaultInstance()) return this;
        if (other.getCount() != 0) {
          setCount(other.getCount());
        }
        if (other.getItemId() != 0) {
          setItemId(other.getItemId());
        }
        if (other.getIsNew() != false) {
          setIsNew(other.getIsNew());
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
        org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int count_ ;
      /**
       * <code>uint32 count = 9;</code>
       * @return The count.
       */
      @java.lang.Override
      public int getCount() {
        return count_;
      }
      /**
       * <code>uint32 count = 9;</code>
       * @param value The count to set.
       * @return This builder for chaining.
       */
      public Builder setCount(int value) {
        
        count_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 count = 9;</code>
       * @return This builder for chaining.
       */
      public Builder clearCount() {
        
        count_ = 0;
        onChanged();
        return this;
      }

      private int itemId_ ;
      /**
       * <code>uint32 item_id = 6;</code>
       * @return The itemId.
       */
      @java.lang.Override
      public int getItemId() {
        return itemId_;
      }
      /**
       * <code>uint32 item_id = 6;</code>
       * @param value The itemId to set.
       * @return This builder for chaining.
       */
      public Builder setItemId(int value) {
        
        itemId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 item_id = 6;</code>
       * @return This builder for chaining.
       */
      public Builder clearItemId() {
        
        itemId_ = 0;
        onChanged();
        return this;
      }

      private boolean isNew_ ;
      /**
       * <code>bool is_new = 15;</code>
       * @return The isNew.
       */
      @java.lang.Override
      public boolean getIsNew() {
        return isNew_;
      }
      /**
       * <code>bool is_new = 15;</code>
       * @param value The isNew to set.
       * @return This builder for chaining.
       */
      public Builder setIsNew(boolean value) {
        
        isNew_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>bool is_new = 15;</code>
       * @return This builder for chaining.
       */
      public Builder clearIsNew() {
        
        isNew_ = false;
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


      // @@protoc_insertion_point(builder_scope:AddNoGachaAvatarCardTransferItem)
    }

    // @@protoc_insertion_point(class_scope:AddNoGachaAvatarCardTransferItem)
    private static final org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem();
    }

    public static org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<AddNoGachaAvatarCardTransferItem>
        PARSER = new com.google.protobuf.AbstractParser<AddNoGachaAvatarCardTransferItem>() {
      @java.lang.Override
      public AddNoGachaAvatarCardTransferItem parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new AddNoGachaAvatarCardTransferItem(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<AddNoGachaAvatarCardTransferItem> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<AddNoGachaAvatarCardTransferItem> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public org.sorapointa.proto.AddNoGachaAvatarCardTransferItemOuterClass.AddNoGachaAvatarCardTransferItem getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AddNoGachaAvatarCardTransferItem_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AddNoGachaAvatarCardTransferItem_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n&AddNoGachaAvatarCardTransferItem.proto" +
      "\"R\n AddNoGachaAvatarCardTransferItem\022\r\n\005" +
      "count\030\t \001(\r\022\017\n\007item_id\030\006 \001(\r\022\016\n\006is_new\030\017" +
      " \001(\010B\026\n\024org.sorapointa.protob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_AddNoGachaAvatarCardTransferItem_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_AddNoGachaAvatarCardTransferItem_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AddNoGachaAvatarCardTransferItem_descriptor,
        new java.lang.String[] { "Count", "ItemId", "IsNew", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
