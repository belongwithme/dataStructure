package com.wang.collection;

/**
 * 用链表手动实现栈
 * @author wangyi
 */
public class MyLinkedStack<E> {
  public MyLinkedList<E> list =  new MyLinkedList<>();

   //入栈
    public void push(E e){
      list.addLast(e);
    }

    //出栈
    public E pop(){
      return list.removeLast();
    }
    //查看栈顶元素
    public E peek(){
      return list.getLast();
    }

}
