package com.wang.collection;

import java.util.Map;

/**
 * @description:  拉链法解决哈希冲突
 * @author: 王一
 * @date: 2024/4/15 17:25
 */

public class MyHashMap_zips<K,V> {
    // 哈希表中存入的键值对个数
    private int size;
    // 底层存储链表的数组
    private MyListMap<K, V>[] table;
    // 底层数组的初始容量
    private static final int INIT_CAP = 4;

    public MyHashMap_zips(){
        this(INIT_CAP);
    }

    public MyHashMap_zips(int initCapacity){
        size = 0;
        //初始化哈希表
        table = (MyListMap<K, V>[]) new MyListMap[initCapacity];
        for(int i =0; i<table.length;i++){
            table[i] = new MyListMap<>();
        }
    }

    //增*改//

    /**
     * 添加key ->value键值对
     * 如果键key 已存在, 则将值修改为val
     * @param key
     * @param val
     * @return
     */
    public V put (K key, V val){
        if(key ==null){
            throw new IllegalArgumentException("key is null");
        }
        if(size >= table.length * 0.75){
            resize(table.length * 2);
        }
        MyListMap<K, V> myListMap = table[hash(key)];
        //如果key之前不存在,则插入
        if(!myListMap.containKey(key)){

        }
       return myListMap.put(key,val);
    }

    /**
     * 删
     * 删除key和对应的val,并返回val
     * 若key不存在,则返回null
     * @param key
     * @return
     */
    public V remove(K key){
        if(key == null){
            throw new IllegalArgumentException("key is null");
        }
        MyListMap<K, V> myListMap = table[hash(key)];
        //如果key之前存在,则删除,size减少
        if(myListMap.containKey(key)){
            size--;
            return myListMap.remove(key);
        }
        return null;
    }

    /**
     * 查
     * 返回key 对应的val
     * 如果key 不存在,则返回null
     * @param key
     * @return
     */
    public V get(K key){
        if(key ==null){
            throw new IllegalArgumentException("key is null");
        }
        MyListMap<K, V> myListMap = table[hash(key)];
        return myListMap.get(key);
    }

    //判断key是否存在Map中
    public boolean containsKey(K key){
        if(key == null){
            throw new IllegalArgumentException("key is null");
        }
        MyListMap<K, V> myListMap = table[hash(key)];
        return myListMap.containKey(key);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size ==0;
    }

    //哈希函数,将键映射到table的索引
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    private void resize (int newCap){
        //构造一个更大容量的HashMap
        MyHashMap_zips<K, V> newMap = new MyHashMap_zips<>(newCap);
        //穷举当前HashMap中的所有键值对
        for(MyListMap<K,V> myListMap : table){
            for(Map.Entry<K,V> entry: myListMap.entries()){
                K key = entry.getKey();
                V value = entry.getValue();
                //将键值对转移到新的HashMap中
                newMap.put(key,value);
            }
        }

        //将当前HashMap的底层table换掉
        this.table = newMap.table;
    }

}
