package designmodel.proxyandchain.proxy03;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过Invocation封装可以在Interceptor中调用目标对象方法
 */
public class Invocation {
    /**
     * 执行方法
     */
    Method method;
    /**
     * 目前对象
     */
    Object target;
    /**
     * 方法的参数
     */
    private Object[] args;

    public Invocation(Method method, Object target, Object[] args) {
        this.method = method;
        this.target = target;
        this.args = args;
    }

    /**
     * 调用方法
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object process(){
        try {
            return method.invoke(target, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
