package designmodel.proxyandchain.proxy03;

import designmodel.proxyandchain.MyUserInterface;

public class TransactionInterceptor implements Interceptor {
    @Override
    public Object interceptor(Invocation invocation) {
        System.out.println("事务方法");
        return invocation.process();
    }

    @Override
    public Object plugin(Object target) {
        return MyUserInvocationHandler02.wrap(target,this);
    }
}
