package com.ll.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 主要记录一下十个排序算法的实现过程,实现的都是从小到大的排序
 * @author Lei
 * @version 0.1
 * @date 2021/4/22
 */
public class Sort {

    /**
     *
     * @param a 数组a
     * @param i 位置i
     * @param j 位置j
     */
    public static void swap(int[] a, int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 冒泡排序
     * @param a 数组a
     */
    public static void bubbleSort(int[] a){
        int length = a.length;
        for (int i = 0; i < length-1; i++){
            for (int j = i+1; j<length;j++){
                if (a[i] >= a[j]){
                    swap(a, i, j);
                }
            }
        }
    }

    /**
     * 选择排序
     * @param a 数组a
     */
    public static void selectionSort(int[] a){
        int length = a.length;
        for (int i = 0; i < length-1; i++) {
            int minindex = i;
            for (int j = i+1; j < length; j++) {
                if (a[j] < a[minindex]){
                    minindex = j;
                }
            }
            swap(a, i, minindex);
        }
    }

    /**
     * 插入排序
     * @param a 数组a
     */
    public static void insertionSort(int[] a){
        int length = a.length;
        for (int i = 1; i < length; i++) {
            int index = i-1;
            int current = a[i];
            while (index >=0 && a[index] > current){
                a[index+1] = a[index];
                index--;
            }
            a[index+1]=current;
        }
    }


    /**
     * 希尔排序
     * @param a
     */
    public static void shellSort(int[] a){
        int length = a.length;
        for (int i = length/2; i>0; i = i/2){
            for (int j = i; j <length ; j++) {
                int current = a[j];
                int index= j-i;
                while (index>=0 && a[index] > current){
                    a[index+i] = a[index];
                    index = index - i;
                }
                a[index+i] = current;
            }
        }
    }

    /**
     * 归并排序
     * @param a
     */
    public static void mergeSort(int[] a){
        mergeSort(a, 0, a.length-1);
    }
    private static void mergeSort(int[] arr,int left,int right){
        if(left<right){
            int mid = (left+right)/2;
            mergeSort(arr,left,mid); // 左侧
            mergeSort(arr,mid+1,right);  // 右侧
            mergeSort(arr,left,mid,right);  // 合并
        }
    }
    private static void mergeSort(int[] arr,int left,int mid,int right){
        int i = left;
        int j = mid+1;
        int t = 0;
        int[] temp = new int[right - left + 1];
        while (i<=mid && j<=right){
            temp[t++] = arr[i]<=arr[j]?arr[i++]:arr[j++];
        }
        while(i<=mid){
            temp[t++] = arr[i++];
        }
        while(j<=right){
            temp[t++] = arr[j++];
        }
        t = 0;
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }

    /**
     * 快速排序
     * @param a
     */
    public static void quickSort(int[] a){
        quickSort(a, 0, a.length-1);
    }
    private static void quickSort(int[] a, int left, int right){
        if (left > right){
            return;
        }
        int temp = a[left];
        int i = left, j=right;
        while (i<j){
            while (temp <= a[j] && i<j){
                j--;
            }
            while (temp >= a[i] && i<j){
                i++;
            }
            if (i < j){
                swap(a, i, j);
            }
        }
        a[left] = a[i];
        a[i] = temp;
        quickSort(a, left, i-1);
        quickSort(a, i+1, right);
    }


    private static void quickSort1(int[] a, int left, int right){
        int i = left;
        int j = right;
        int mark = a[left];

        while (i < j) {
            while (i < j && a[j] >= mark)
                --j;
            if (i < j)
                a[i++] = a[j];

            while (i < j && a[i] <= mark)
                ++i;
            if (i < j)
                a[j--] = a[i];
        }
        quickSort(a, left , i-1);
        quickSort(a, i+1, right);
    }

    public static void main(String[] args) {
        int[] a  =new int[]{1,4,2,1,5,3};
        quickSort1(a, 0, a.length-1);
        System.out.println( Arrays.toString(a));

    }

    /**
     * 快速排序的list实现 方便一点
     * @param list
     */
    public static void quickSort(List<Integer> list){
        if (list.size() > 1){
            List<Integer> small = new ArrayList<>();
            List<Integer> same = new ArrayList<>();
            List<Integer> big = new ArrayList<>();

            Integer item = list.get(list.size()/2);
            for (Integer i: list){
                if (i<item){
                    small.add(i);
                }
                if (i.equals(item)){
                    same.add(i);
                }
                if (i > item){
                    big.add(i);
                }
            }
            quickSort(small);
            quickSort(big);
            list.clear();
            list.addAll(small);
            list.addAll(same);
            list.addAll(big);
        }
    }



    /**
     * 堆排序 升序采用大顶堆
     * @param arr
     */
    public static void heapSort(int[] arr){
        //1.构建大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr,i,arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr,0,j);//重新对堆进行调整
        }
    }
    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int []arr,int i,int length){
        int temp = arr[i];//先取出当前元素i
        for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
            if(k+1<length && arr[k]<arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(arr[k] >temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }



    public static int[] buildHead(int[] a){
        int[] b = new int[a.length+1];
        b[0] = Integer.MAX_VALUE;
        int current = 0;
        for (Integer h : a){
            System.out.println(Arrays.toString(b));
            current = current +1;
            int now = current;
            for (; now > 1 && h > b[now/2]; now = now /2){
                b[now] = b[now/2];
            }
            b[now] = h;
        }
        return b;
    }


//    public static void main(String[] args) {
//        int[] a = new int[]{1,4,3,76,2};
//        quickSort(a);
//        for (Integer integer : a){
//            System.out.println(integer);
//        }
//    }


}

