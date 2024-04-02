package com.wang.collection;

/**
 * 手动实现队列
 * @author wangyi
 */
public class MyLinkedQueue<E> {
   private MyLinkedList<E> list = new MyLinkedList<>();
   //入列
   public void enqueue(E e){
       list.addLast(e);
   }
   //出列
    public E dequeue(){
       return list.removeFirst();
    }

    //获取列尾元素
    public E getTail(){
       return list.getLast();
    }
    //获取列首元素

    public E getHead(){
       return list.getFirst();
    }
}
