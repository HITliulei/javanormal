package com.ll.designPattern.Simple;

/**
 * @author Lei
 * @version 0.1
 * @date 2021/7/15
 */
public class LazySimglePattern {

    private static volatile LazySimglePattern instance = null;

    private LazySimglePattern(){}

    public static synchronized LazySimglePattern getInstance(){
        if (instance == null){
            instance = new LazySimglePattern();
        }
        return instance;
    }
}