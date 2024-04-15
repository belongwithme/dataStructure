package com.wang.collection;

/**
 * @description: 数组实现双端队列
 * @author: wangyi
 * @date: 2024/4/3 18:03
 */
public class MyArrayDeque<E> {
    private int size;

    private E[] data;

    //data的索引区间[first,last)存储着添加的元素
    private int first,last;

    private final static int INIT_CAP = 2;


    public MyArrayDeque(){
        this(INIT_CAP);
    }

    public MyArrayDeque(int initCap){
        size = 0;
        data = (E[]) new Object[initCap];
        first = last = 0;
    }


}
