package com.wang.collection;

import java.util.Map;

public class MyListMap<K,V> {
    public final Node<K,V> head,tail;
    private int size;
    //构造函数初始化头尾节点
    public MyListMap(){
        this.head = new Node<>(null ,null);
        this.tail = new Node<>(null,null);
        //将头结点和尾节点相连
        head.next = tail;
        this.size = 0;

    }


    //单链表节点
    private static class Node<K,V> implements Map.Entry<K,V>{
        K key;
        V value;
        Node<K,V> next;

        public Node(K key, V value, Node<K,V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        Node(K key, V value) {
            this(key, value, null);
        }
        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
    //在链表中添加key-value键值对,如果键key已存在,则将值修改为val
    public V put (K key ,V val){
        if(key ==null){
            throw new IllegalArgumentException("key is null");
        }
        Node<K,V> p =getNode(key);
        //Key 已存在
        if(p != null){
            V oldVal = p.value;
            //修改已有节点
            p.value = val;
            return oldVal;
        }
        //将新节点插入到链表开头
        Node<K,V> x = new Node<>(key,val);
        x.next = head.next;
        head.next = x;
        size++;
        return null;
    }

    //在链表中删除key和对应的val,并返回val,若key不存在,则返回null
    public V remove(K key){
        if(key ==null){
            throw new IllegalArgumentException("key is null");
        }
        Node<K,V> prev = head;
        for(Node<K,V> p = head.next; p!=tail;p=p.next){
            if(key.equals(p.key)){
                prev.next = p.next;
                size--;
                return p.value;
            }
            prev =p;
        }
        return null;
    }

    public V get(K key){
        if(key == null){
            throw new IllegalArgumentException("key is nul");
        }
        Node<K,V> p =getNode(key);
        if(p ==null){
            return null;
        }
        return p.value;
    }
    //找到key对应的单链表节点
    private Node<K,V> getNode(K key){
        for(Node<K,V> p = head.next; p!=tail;p=p.next){
            if(key.equals(p.key)){
                return p;
            }
        }
        return null;
    }

    //判断key是否存在Map中
    public boolean containKey(K key){
        if(key == null){
            throw new IllegalArgumentException("key is null");
        }
        return getNode(key)!=null;
    }

}
