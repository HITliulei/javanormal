package com.ll.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lei
 * @version 0.1
 * @date 2021/8/3
 */
public class Netty {
    public int findKth(int[] a, int n, int K) {
        // write code here
        return quickSort(a, 0, n-1, K);
    }


    private int quickSort(int[] a, int left, int right, int k){
        System.out.println(left);
        System.out.println(right);
        int i = left;
        int j = right;
        int mark = a[i];
        while(i < j){
            while(i < j && a[j] >= mark){
                --j;
            }
            while(i < j && a[i] <= mark){
                ++i;
            }
            if(i < j){
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        a[left] = a[i];
        a[i] = mark;
        int big_num = right - i;
        if(big_num + 1 == k){
            return mark;
        }else if( big_num + 1 < k){
            return quickSort(a, left, i-1, k-big_num -1);
        }else{
            return quickSort(a, i+1, right,k);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1332802,1177178,1514891,871248,753214,123866,1615405,328656,1540395,968891,1884022,252932,1034406,1455178,821713,486232,860175,1896237,852300,566715,1285209,1845742,883142,259266,520911,1844960,218188,1528217,332380,261485,1111670,16920,1249664,1199799,1959818,1546744,1904944,51047,1176397,190970,48715,349690,673887,1648782,1010556,1165786,937247,986578,798663};
        System.out.println(new Netty().findKth(a, 49, 24));
    }

}
