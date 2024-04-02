package com.wang.collection;

import java.util.NoSuchElementException;

/**
 * 用数组实现栈
 * @author wangyi
 * @param <E>
 */
public class MyArrayStack<E> {
    MyArrayList<E> array = new MyArrayList<>();
    //入栈
    public void pop(E e){
        array.addLast(e);
    }
    //出栈
    public E push(){
        E last = array.removeLast();
        return last;
    }

    //获取栈顶元素
    public E peek(){
        if(array.isEmpty()){
            throw new NoSuchElementException();
        }
        return array.get(array.size()-1);
    }
}
