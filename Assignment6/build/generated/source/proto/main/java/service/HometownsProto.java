// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: services/hometowns.proto

package service;

public final class HometownsProto {
  private HometownsProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_services_HometownsReadResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_services_HometownsReadResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_services_HometownsSearchRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_services_HometownsSearchRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_services_HometownsWriteRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_services_HometownsWriteRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_services_HometownsWriteResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_services_HometownsWriteResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_services_Hometown_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_services_Hometown_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030services/hometowns.proto\022\010services\032\033go" +
      "ogle/protobuf/empty.proto\"`\n\025HometownsRe" +
      "adResponse\022\021\n\tisSuccess\030\001 \001(\010\022%\n\thometow" +
      "ns\030\002 \003(\0132\022.services.Hometown\022\r\n\005error\030\003 " +
      "\001(\t\"&\n\026HometownsSearchRequest\022\014\n\004city\030\001 " +
      "\001(\t\"=\n\025HometownsWriteRequest\022$\n\010hometown" +
      "\030\001 \001(\0132\022.services.Hometown\":\n\026HometownsW" +
      "riteResponse\022\021\n\tisSuccess\030\001 \001(\010\022\r\n\005error" +
      "\030\002 \001(\t\"6\n\010Hometown\022\014\n\004name\030\001 \001(\t\022\014\n\004city" +
      "\030\002 \001(\t\022\016\n\006region\030\003 \001(\t2\353\001\n\tHometowns\022A\n\004" +
      "read\022\026.google.protobuf.Empty\032\037.services." +
      "HometownsReadResponse\"\000\022M\n\006search\022 .serv" +
      "ices.HometownsSearchRequest\032\037.services.H" +
      "ometownsReadResponse\"\000\022L\n\005write\022\037.servic" +
      "es.HometownsWriteRequest\032 .services.Home" +
      "townsWriteResponse\"\000B\033\n\007serviceB\016Hometow" +
      "nsProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.EmptyProto.getDescriptor(),
        });
    internal_static_services_HometownsReadResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_services_HometownsReadResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_services_HometownsReadResponse_descriptor,
        new java.lang.String[] { "IsSuccess", "Hometowns", "Error", });
    internal_static_services_HometownsSearchRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_services_HometownsSearchRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_services_HometownsSearchRequest_descriptor,
        new java.lang.String[] { "City", });
    internal_static_services_HometownsWriteRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_services_HometownsWriteRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_services_HometownsWriteRequest_descriptor,
        new java.lang.String[] { "Hometown", });
    internal_static_services_HometownsWriteResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_services_HometownsWriteResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_services_HometownsWriteResponse_descriptor,
        new java.lang.String[] { "IsSuccess", "Error", });
    internal_static_services_Hometown_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_services_Hometown_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_services_Hometown_descriptor,
        new java.lang.String[] { "Name", "City", "Region", });
    com.google.protobuf.EmptyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
