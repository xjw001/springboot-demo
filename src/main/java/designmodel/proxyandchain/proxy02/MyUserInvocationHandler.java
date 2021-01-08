package designmodel.proxyandchain.proxy02;

import designmodel.proxyandchain.MyUserInterface;
import designmodel.proxyandchain.proxy01.MyUser;

import javax.sound.midi.Soundbank;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * InvocationHandler实现类
 */
public class MyUserInvocationHandler implements InvocationHandler {
    Object target;

    public MyUserInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用前");
        Object result = method.invoke(target,args);
        System.out.println("调用后");
        return result;
    }

    public static Object wrap(Object tar){
        return Proxy.newProxyInstance(MyUserInterface.class.getClassLoader(),
                new Class[]{MyUserInterface.class},new MyUserInvocationHandler(tar));
    }


    public static void main(String[] args) {

        MyUserInterface myUserInterface = (MyUserInterface) MyUserInvocationHandler.wrap(new MyUser());
        myUserInterface.sayHello();
    }
}
