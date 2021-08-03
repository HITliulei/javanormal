package com.ll.designPattern.Simple;


import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

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

    public static void main(String[] args) {
        ConcurrentHashMap c = new ConcurrentHashMap();
    }

}