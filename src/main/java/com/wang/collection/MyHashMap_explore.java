package com.wang.collection;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @description: 线性探查实现HashMap
 * @author: 王一
 * @date: 2024/4/16 18:04
 */
public class MyHashMap_explore<K,V> {
    public static class Node<K,V> implements Map.Entry<K,V>{
        K key;
        V val;
        Node(K key,V val){
            this.key = key;
            this.val = val;
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return val;
        }

        public V setValue(V val){
            V oldVal = this.val;
            this.val = val;
            return oldVal;
        }
    }

    //真正存储键值对的数据
    private Node<K,V>[] table;
    //HashMap 中的键值对个数
    private int size;
    //HashMap的容量,即keys和values的length
    private int cap;
    //默认的初始化容量
    private static final int INIT_CAP =4;

    public MyHashMap_explore(){
        this(INIT_CAP);
    }

    public MyHashMap_explore(int initCapacity){
        size = 0;
        cap = initCapacity;
        table = (Node<K,V>[]) new Node[initCapacity];
    }

    /**
     * 增/改
     * 添加key ->val 键值对
     * 如果键key已存在,则将值修改为val
     * @param key
     * @param val
     * @return
     */
    public V put(K key ,V val){
        if(key == null){
            throw new IllegalArgumentException("key is null");
        }

        if(size >= cap/2){
            resize(cap *2);
        }

        int i = getNodeIndex(key);

        //若key已存在,修改对应的val
        if(i!=-1){
            Node<K,V> entry = table[i];
            V oldVal = entry.val;
            entry.val = val;
            return oldVal;
        }

        //key不存在,找个空位插入
        i = hash(key);
        while(table[i] != null){
            i = (i +1 )% cap;
        }
        //此时table[i]为一个空位
        Node<K,V> x = new Node<>(key,val);
        table[i] = x;
        size++;

        return null;
    }


    public V remove(K key){
        if(key == null){
            throw new IllegalArgumentException("key is null");
        }

        if(size<cap/8){
            resize(cap/2);
        }

        int i = getNodeIndex(key);

        if(i == -1){
            //如果key不存在,不需要remove
            return null;
        }

        //开始remove
        V deleteVal = table[i].val;
        table[i] =null;
        size--;
        //保持连续性
        i = (i+1)%cap;
        for(;table[i]!=null;i=(i+1)%cap){
            Node<K,V> entry = table[i];
            table[i] = null;
            //put里面会加一
            size--;
            put(entry.key,entry.val);
        }
        return deleteVal;
    }

    public V get(K key){
        if(key ==null){
            throw new IllegalArgumentException("key is null");
        }
        int i = getNodeIndex(key);

        if(i == -1){
            return null;
        }

        return table[i].val;
    }

    //判断key是否存在Map中
    public boolean containsKey(K key){
        if(key ==null){
            throw new IllegalArgumentException("key is null");
        }
        return getNodeIndex(key) != -1;
    }

    //其他工具函数
    public List<K> keys(){
        LinkedList<K> keyList = new LinkedList<>();
        for(Node<K,V> entry : table){
            if(entry !=null){
                keyList.addLast(entry.key);
            }
        }
        return keyList;
    }

    public List<Map.Entry<K,V>> entries(){
        LinkedList<Map.Entry<K,V>> entryList = new LinkedList<>();
        for(Node<K,V> entry: table){
            if(entry !=null){
                entryList.addLast(entry);
            }
        }
        return entryList;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 对key进行线性探查,返回一个索引
     * 若返回-1,说明没有找到
     * @param key
     * @return
     */
    private int getNodeIndex(K key){
        int i;
        for(i = hash(key); table[i]!=null;i= (i+1) % cap){
            if(table[i].key.equals(key))
                return i;
        }
        return -1;
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) %cap;
    }

    private void resize(int newCap){
        MyHashMap_explore<K, V> newMap = new MyHashMap_explore<>(newCap);
        for(Node<K,V>entry: table){
            if(entry != null){
                newMap.put(entry.key, entry.val);
            }
        }
        this.table = newMap.table;
        this.cap = newMap.cap;
    }
}
