package org.frpc.rpc.server;

import org.frpc.rpc.entity.RpcRequest;
import org.frpc.rpc.entity.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class WorkerThread implements Runnable {
    private static final Logger logger= LoggerFactory.getLogger(WorkerThread.class);

    private Socket socket;
    private  Object service;

    public WorkerThread(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try(ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream())){
            RpcRequest rpcRequest= (RpcRequest) objectInputStream.readObject();
            Method method=service.getClass().getMethod(rpcRequest.getMethodName(),rpcRequest.getParamTypes());
            Object returnObject=method.invoke(service,rpcRequest.getParameters());
            objectOutputStream.writeObject(RpcResponse.success(returnObject));
            objectOutputStream.flush();
        }catch (Exception e){
            logger.error("调用有错误发生:",e);
        }
    }
}
