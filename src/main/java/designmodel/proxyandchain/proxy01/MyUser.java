package designmodel.proxyandchain.proxy01;

import designmodel.proxyandchain.MyUserInterface;

/**
 * @author xiongjw
 * @date 2020/12/11 16:11
 * 被代理类
 */
public class MyUser implements MyUserInterface {

    @Override
    public void sayHello(){
        System.out.println("sayHello");
    }

    //代理类
    static class MyUserProxy implements MyUserInterface{

        MyUser myUser;

        public MyUserProxy(MyUser myUser) {
            this.myUser = myUser;
        }

        @Override
        public void sayHello() {
            System.out.println("调用前");
            myUser.sayHello();
            System.out.println("调用后");
        }
    }

    public static void main(String[] args) {
        MyUserInterface myUserInterface = new MyUserProxy(new MyUser());
        myUserInterface.sayHello();
    }
}
