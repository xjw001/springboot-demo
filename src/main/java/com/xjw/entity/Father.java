package com.xjw.entity;

public class Father {

    public void domSomething(){
        System.out.println("father");
    }

    public static void main(String[] args) {
        System.out.println(9%6);
    }
}

class Son extends  Father {
    public void doSomething(){
        System.out.println("son");
    }
}
