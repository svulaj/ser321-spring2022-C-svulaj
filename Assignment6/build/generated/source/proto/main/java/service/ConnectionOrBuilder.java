// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: services/registry.proto

package service;

public interface ConnectionOrBuilder extends
    // @@protoc_insertion_point(interface_extends:services.Connection)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string uri = 1;</code>
   * @return The uri.
   */
  java.lang.String getUri();
  /**
   * <code>string uri = 1;</code>
   * @return The bytes for uri.
   */
  com.google.protobuf.ByteString
      getUriBytes();

  /**
   * <code>int32 discoveryPort = 2;</code>
   * @return The discoveryPort.
   */
  int getDiscoveryPort();

  /**
   * <code>int32 port = 3;</code>
   * @return The port.
   */
  int getPort();

  /**
   * <code>string protocol = 4;</code>
   * @return The protocol.
   */
  java.lang.String getProtocol();
  /**
   * <code>string protocol = 4;</code>
   * @return The bytes for protocol.
   */
  com.google.protobuf.ByteString
      getProtocolBytes();

  /**
   * <code>string name = 5;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 5;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();
}
