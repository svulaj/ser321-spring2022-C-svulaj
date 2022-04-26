package service;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: services/addressbook.proto")
public final class AddressBookGrpc {

  private AddressBookGrpc() {}

  public static final String SERVICE_NAME = "services.AddressBook";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      service.AddressBookResponse> getListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "list",
      requestType = com.google.protobuf.Empty.class,
      responseType = service.AddressBookResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      service.AddressBookResponse> getListMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, service.AddressBookResponse> getListMethod;
    if ((getListMethod = AddressBookGrpc.getListMethod) == null) {
      synchronized (AddressBookGrpc.class) {
        if ((getListMethod = AddressBookGrpc.getListMethod) == null) {
          AddressBookGrpc.getListMethod = getListMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, service.AddressBookResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "list"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.AddressBookResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AddressBookMethodDescriptorSupplier("list"))
              .build();
        }
      }
    }
    return getListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<service.AddressWriteRequest,
      service.AddressBookResponse> getAddMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "add",
      requestType = service.AddressWriteRequest.class,
      responseType = service.AddressBookResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<service.AddressWriteRequest,
      service.AddressBookResponse> getAddMethod() {
    io.grpc.MethodDescriptor<service.AddressWriteRequest, service.AddressBookResponse> getAddMethod;
    if ((getAddMethod = AddressBookGrpc.getAddMethod) == null) {
      synchronized (AddressBookGrpc.class) {
        if ((getAddMethod = AddressBookGrpc.getAddMethod) == null) {
          AddressBookGrpc.getAddMethod = getAddMethod =
              io.grpc.MethodDescriptor.<service.AddressWriteRequest, service.AddressBookResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "add"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.AddressWriteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.AddressBookResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AddressBookMethodDescriptorSupplier("add"))
              .build();
        }
      }
    }
    return getAddMethod;
  }

  private static volatile io.grpc.MethodDescriptor<service.AddressSearchRequest,
      service.AddressBookResponse> getFindMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "find",
      requestType = service.AddressSearchRequest.class,
      responseType = service.AddressBookResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<service.AddressSearchRequest,
      service.AddressBookResponse> getFindMethod() {
    io.grpc.MethodDescriptor<service.AddressSearchRequest, service.AddressBookResponse> getFindMethod;
    if ((getFindMethod = AddressBookGrpc.getFindMethod) == null) {
      synchronized (AddressBookGrpc.class) {
        if ((getFindMethod = AddressBookGrpc.getFindMethod) == null) {
          AddressBookGrpc.getFindMethod = getFindMethod =
              io.grpc.MethodDescriptor.<service.AddressSearchRequest, service.AddressBookResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "find"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.AddressSearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.AddressBookResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AddressBookMethodDescriptorSupplier("find"))
              .build();
        }
      }
    }
    return getFindMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AddressBookStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddressBookStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddressBookStub>() {
        @java.lang.Override
        public AddressBookStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddressBookStub(channel, callOptions);
        }
      };
    return AddressBookStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AddressBookBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddressBookBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddressBookBlockingStub>() {
        @java.lang.Override
        public AddressBookBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddressBookBlockingStub(channel, callOptions);
        }
      };
    return AddressBookBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AddressBookFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddressBookFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddressBookFutureStub>() {
        @java.lang.Override
        public AddressBookFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddressBookFutureStub(channel, callOptions);
        }
      };
    return AddressBookFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AddressBookImplBase implements io.grpc.BindableService {

    /**
     */
    public void list(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<service.AddressBookResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getListMethod(), responseObserver);
    }

    /**
     */
    public void add(service.AddressWriteRequest request,
        io.grpc.stub.StreamObserver<service.AddressBookResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddMethod(), responseObserver);
    }

    /**
     */
    public void find(service.AddressSearchRequest request,
        io.grpc.stub.StreamObserver<service.AddressBookResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFindMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                service.AddressBookResponse>(
                  this, METHODID_LIST)))
          .addMethod(
            getAddMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                service.AddressWriteRequest,
                service.AddressBookResponse>(
                  this, METHODID_ADD)))
          .addMethod(
            getFindMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                service.AddressSearchRequest,
                service.AddressBookResponse>(
                  this, METHODID_FIND)))
          .build();
    }
  }

  /**
   */
  public static final class AddressBookStub extends io.grpc.stub.AbstractAsyncStub<AddressBookStub> {
    private AddressBookStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddressBookStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddressBookStub(channel, callOptions);
    }

    /**
     */
    public void list(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<service.AddressBookResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void add(service.AddressWriteRequest request,
        io.grpc.stub.StreamObserver<service.AddressBookResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void find(service.AddressSearchRequest request,
        io.grpc.stub.StreamObserver<service.AddressBookResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AddressBookBlockingStub extends io.grpc.stub.AbstractBlockingStub<AddressBookBlockingStub> {
    private AddressBookBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddressBookBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddressBookBlockingStub(channel, callOptions);
    }

    /**
     */
    public service.AddressBookResponse list(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getListMethod(), getCallOptions(), request);
    }

    /**
     */
    public service.AddressBookResponse add(service.AddressWriteRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddMethod(), getCallOptions(), request);
    }

    /**
     */
    public service.AddressBookResponse find(service.AddressSearchRequest request) {
      return blockingUnaryCall(
          getChannel(), getFindMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AddressBookFutureStub extends io.grpc.stub.AbstractFutureStub<AddressBookFutureStub> {
    private AddressBookFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddressBookFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddressBookFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<service.AddressBookResponse> list(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<service.AddressBookResponse> add(
        service.AddressWriteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<service.AddressBookResponse> find(
        service.AddressSearchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFindMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST = 0;
  private static final int METHODID_ADD = 1;
  private static final int METHODID_FIND = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AddressBookImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AddressBookImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIST:
          serviceImpl.list((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<service.AddressBookResponse>) responseObserver);
          break;
        case METHODID_ADD:
          serviceImpl.add((service.AddressWriteRequest) request,
              (io.grpc.stub.StreamObserver<service.AddressBookResponse>) responseObserver);
          break;
        case METHODID_FIND:
          serviceImpl.find((service.AddressSearchRequest) request,
              (io.grpc.stub.StreamObserver<service.AddressBookResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AddressBookBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AddressBookBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return service.AddressBookProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AddressBook");
    }
  }

  private static final class AddressBookFileDescriptorSupplier
      extends AddressBookBaseDescriptorSupplier {
    AddressBookFileDescriptorSupplier() {}
  }

  private static final class AddressBookMethodDescriptorSupplier
      extends AddressBookBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AddressBookMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AddressBookGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AddressBookFileDescriptorSupplier())
              .addMethod(getListMethod())
              .addMethod(getAddMethod())
              .addMethod(getFindMethod())
              .build();
        }
      }
    }
    return result;
  }
}
