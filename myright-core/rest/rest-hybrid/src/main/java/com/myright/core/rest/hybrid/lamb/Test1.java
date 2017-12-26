package com.myright.core.rest.hybrid.lamb;

import java.util.concurrent.Callable;

public class Test1 {

    public static void main(String[] args) throws Exception {

        //看参数 看返回值

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("直接new");
            }
        };

        Runnable runnable2 = () -> {System.out.println("lamb表达式");str();};
        new Thread(runnable).start();
        new Thread(runnable2).start();



        Callable callable = () -> {return "";};
        callable.call();
    }
    public static void str(){
        System.out.println("------------str---------");
    }
}
