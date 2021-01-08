package designmodel.proxyandchain.proxy02;

import designmodel.proxyandchain.MyUserInterface;
import designmodel.proxyandchain.proxy01.MyUser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author xiongjw
 * @date 2020/12/11 16:35
 * 动态代理
 */
public class MyJDKProxy {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        MyUserInterface target = new MyUser();
        //Class[] classes = {MyUserInterface.class};
        //通过一个方法实现以下三步
        MyUserInterface proxyInstance = (MyUserInterface) Proxy.newProxyInstance(MyUserInterface.class.getClassLoader(),
                new Class[]{MyUserInterface.class}, new MyUserInvocationHandler(target));

        //1获取动态代理类
        //Class<?> proxyClass = Proxy.getProxyClass(MyUserInterface.class.getClassLoader(), MyUserInterface.class);
        //2获取构造函数
        //Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
       //43通过构造函数生成代理类
       // MyUserInterface myUserInterface = (MyUserInterface) constructor.newInstance(new MyUserInvocationHandler(target));

        proxyInstance.sayHello();
    }
}
