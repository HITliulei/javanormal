package com.ll.designPattern.Simple;

/**
 * @author Lei
 * @version 0.1
 * @date 2021/7/15
 */
public class Simple {


    private static volatile Simple simple;

    private Simple() {
    }


    public static Simple get(){
        if (simple == null) {
            synchronized (Simple.class){
                if (simple ==null){
                    return new Simple();
                }
            }
        }
        return simple;
    }
}
