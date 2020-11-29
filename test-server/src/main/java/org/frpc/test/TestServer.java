package org.frpc.test;

import org.frpc.rpc.api.HelloService;
import org.frpc.rpc.server.RpcServer;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService=new HelloServiceImpl();
        RpcServer rpcServer=new RpcServer();
        rpcServer.register(helloService,9000);
    }
}
