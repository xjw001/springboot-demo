package designmodel.proxyandchain.proxy03;

import designmodel.proxyandchain.MyUserInterface;

/**
 * @author xiongjw
 * @date 2020/12/11 16:49
 * 拦截的接口
 */
public interface Interceptor {

    /**
     * 对方法拦截实现增强
     * @param invocation
     * @return
     */
    Object interceptor(Invocation invocation);


    /**
     * 类似包装器，用于返回代理target的对象
     * @param target
     * @return
     */
    Object plugin(Object target);
}
