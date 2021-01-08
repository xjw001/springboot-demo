package designmodel.proxyandchain.proxy03;

import designmodel.proxyandchain.MyUserInterface;
import designmodel.proxyandchain.proxy01.MyUser;
import designmodel.proxyandchain.proxy02.MyUserInvocationHandler;

import java.lang.annotation.Target;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class MyUser03 {

    public static void main(String[] args) {
//        List<Interceptor> interceptorList = new ArrayList<>();
//
//        MyUserInterface target = new MyUser();
//        MyUserInterface targetProxy = (MyUserInterface) MyUserInvocationHandler02.wrap(target,new LogInterceptor());
//        targetProxy.sayHello();

//        MyUserInterface target = new MyUser();
//        Interceptor transactionInterceptor = new TransactionInterceptor();
//        //把事务拦截器插入到目标类中
//        target = (MyUserInterface) transactionInterceptor.plugin(target);
//        LogInterceptor logInterceptor = new LogInterceptor();
//        target = (MyUserInterface)logInterceptor.plugin(target);
//        target.sayHello();
          InterceptorChain interceptorChain = new InterceptorChain();
          MyUserInterface target = new MyUser();
          interceptorChain.addInterceptor(new LogInterceptor());
          interceptorChain.addInterceptor(new TransactionInterceptor());
          target = (MyUserInterface) interceptorChain.pluginAll(target);
          target.sayHello();
    }
}
