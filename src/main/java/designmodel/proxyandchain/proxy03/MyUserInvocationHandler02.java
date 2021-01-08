package designmodel.proxyandchain.proxy03;

import designmodel.proxyandchain.MyUserInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongjw
 * @date 2020/12/11 16:58
 * 抽取业务逻辑的动态代理
 */
public class MyUserInvocationHandler02 implements InvocationHandler {
    Object target;
    List<Interceptor> list = new ArrayList();
    private Interceptor interceptor;

    public MyUserInvocationHandler02(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Invocation invocation = new Invocation(method,target,args);
            return interceptor.interceptor(invocation);
    }

    public static Object wrap(Object target,Interceptor interceptor){
        return Proxy.newProxyInstance(MyUserInterface.class.getClassLoader(),new Class[]{MyUserInterface.class},
                new MyUserInvocationHandler02(target,interceptor));
    }
}
