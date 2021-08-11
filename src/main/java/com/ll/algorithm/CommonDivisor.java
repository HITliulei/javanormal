package com.ll.algorithm;

/**
 * @author Lei
 * @version 0.1
 * @date 2021/8/6
 */
public class CommonDivisor {
    // 求解最小公约数，
    private static int gcd(int a, int b){
        if(a < b){
            int temp = a;
            a = b;
            b = temp;
        }
        while(b > 0){
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    // 最大公倍数
    private static int get(int a, int b){
        return a*b / gcd(a,b);
    }
}
