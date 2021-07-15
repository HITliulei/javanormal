package com.ll.designPattern.Simple;


/**
 * @author Lei
 * @version 0.1
 * @date 2021/7/15
 */
public class HungrySimplePattern {

    private static final HungrySimplePattern instance = new HungrySimplePattern();

    private HungrySimplePattern(){}

    public static HungrySimplePattern getInstance(){
        return instance;
    }

}