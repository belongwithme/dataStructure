package com.wang.collection;

import java.util.NoSuchElementException;

/**
 * 手动实现双向数组队列
 * @author wangyi
 * @param <E>
 */
public class MyArrayDeque<E> {

    private int size;

    private E[] data;
    private final static int INIT_CAP = 2;

    //data的索引区间[first,last) 存储着添加的元素
    private int first,last;
    public MyArrayDeque(int initCap){
        size = 0;
        data = (E[]) new Object[initCap];
        //都初始化为0,区间[0.0)是空集
        first = last = 0;
    }
    public MyArrayDeque(){
        this(INIT_CAP);
    }

    //从队首入队
    public void addFirst(E e){
        if(size == data.length){
            resize(2*data.length);
        }

        /**
         *   情况一: first --- last
         *   情况二: ---last ---- first ---
         */
        if(first==0){
            first = data.length -1;
        }else{
            first--;
        }
        data[first] = e;
        size ++;

        /**
         * 第一次写
         */
//        if(first == data.length-1){
//            first=0;
//        }
//        data[first] = e;
//        first++;
//        size++;
    }

    //从队尾入队
    public void addLast(E e){
        if (size == data.length){
            resize(2*data.length);
        }
        data[last] =e;
        last++;
        if(last == data.length){
            last=0;
        }
        size++;
    }

    //从队首出队
    public E removeFirst(){
        if(size==0){
            throw new NoSuchElementException();
        }
        if(size < data.length/4){
            resize(data.length/2);
        }

        E removeValue = data[first];
        data[first] = null;
        first++;
        if(first==data.length){
            first =0;
        }
        /**
         * 第一次写
         */
//        if(first ==data.length-1){
//            first=0;
//        }else {
//            first++;
//        }
        size--;
        return removeValue;
    }
    //从队尾出队
    public E removeLast(){
        if(size==0){
            throw new NoSuchElementException();
        }

        if(size < data.length/4){
            resize(data.length/2);
        }
        /**
         * 两种情况
         * 1. first --- last
         * 2. last --- first ---
         */
        if(last == 0){
            last = data.length-1;
        }else{
            last--;
        }
        E removeValue = data[last];
        data[last] = null;
        size--;
        return removeValue;
    }

    //获取队首元素
    public E getFirst(){
        if(size==0){
            throw new NoSuchElementException();
        }

        return data[first];
    }
    //获取队尾元素
    public E getLast(){
        if(size ==0){
            throw new NoSuchElementException();
        }
        if(last ==0){
            return data[data.length-1];
        }
        return data[last -1];
    }

    public int getSize(){
        return size;
    }
    //扩容
    public void resize(int newCap){
         if(size==0){
             data = (E[]) new Object[INIT_CAP];
             return;
         }

         E[] newData = (E[]) new Object[newCap];
         for(int i = 0 ;i<size;i++){
             newData[i] = data[(first+i)%data.length];
         }

         data = newData;
         first =0;
         last = size;

    }
}
