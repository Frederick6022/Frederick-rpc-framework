package org.frpc.rpc.api;
//通用接口，客户端和服务端均能访问到该通用接口，但是只有服务端有该接口的具体实现类
public interface HelloService {
    String hello(HelloObject object);
}
