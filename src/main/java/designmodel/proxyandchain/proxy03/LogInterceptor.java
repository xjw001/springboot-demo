package designmodel.proxyandchain.proxy03;

import designmodel.proxyandchain.MyUserInterface;

public class LogInterceptor implements Interceptor {


    @Override
    public Object interceptor(Invocation invocation) {
        System.out.println("日志方法前");
        Object o = invocation.process();
        System.out.println("日志方法后");
        return o;
    }

    @Override
    public Object plugin(Object target) {
        return MyUserInvocationHandler02.wrap(target,this);
    }
}
