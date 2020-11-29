package org.frpc.test;

import org.frpc.rpc.api.HelloObject;
import org.frpc.rpc.api.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//服务端接口的实现类
public class HelloServiceImpl implements HelloService {
    //日志文件
    private static final Logger logger=LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public String hello(HelloObject object) {
        logger.info("接收到：{}",object.getId());
        return "这是用掉的返回值，id="+object.getMessage();
    }
}
