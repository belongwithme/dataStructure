package com.wang.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 手动实现链表
 * @author wangyi
 * @param <E>
 */
public class MyLinkedList<E> implements Iterable<E> {
    // 虚拟头尾节点
    final private Node<E> head, tail;
    private int size;

    // 双链表节点
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    // 构造函数初始化头尾节点
    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }


    /***** 增 *****/

    public void addLast(E e) {
        Node<E> node = new Node<>(e);
        Node<E> preNode = tail.prev;

        //先处理添加节点
        node.prev=preNode;
        node.next = tail;
        //然后处理旁边节点
        preNode.next = node;
        tail.prev = node;

        //第一次写,漏了一个size++
        size++;
    }

    public void addFirst(E e) {
        Node<E> node = new Node<>(e);
        Node<E> nextNode = head.next;

        node.prev = head;
        node.next = nextNode;

        head.next = node;
        nextNode.prev = node;

        //第一次写,漏了一个size++
        size++;

    }

    public void add(int index, E element) {
        checkPositionIndex(index);
        if(index ==size){
            addLast(element);
            return;
        }
        Node<E> node = getNode(index);
        Node<E> prevNode = node.prev;
        Node<E> addNode = new Node<>(element);

        addNode.prev=prevNode;
        addNode.next=node;
        prevNode.next=addNode;
        node.prev=addNode;

        size++;
        /**
         * 第一次写
         * 1.没做边界判断(没意识到链表也是有序的,脑子坏了)
         * 2.没考虑size
         */
//        Node<E> node = getNode(index);
//        Node<E> prevNode = node.prev;
//        Node<E> addNode = new Node<>(element);
//
//        addNode.prev=prevNode;
//        addNode.next=node;
//        prevNode.next=addNode;
//        node.prev=addNode;
    }

    /***** 删 *****/

    public E removeFirst() {
        /**
         * 第一次写没考虑到边界条件和size问题
         */
        if(size<1){
            throw new NoSuchElementException();
        }
        Node<E> node = head.next;
        Node<E> nextNode = node.next;

        node.prev = null;
        node.next = null;

        head.next = nextNode;
        nextNode.prev = head;

        E value = node.val;
        size--;
        return value;
    }

    public E removeLast() {
        /**
         * 第一次写没考虑到边界条件和size问题
         */
        if(size<1){
            throw new NoSuchElementException();
        }
        Node<E> node = tail.prev;
        Node<E> preNode = node.prev;
        E value = node.val;

        node.prev = null;
        node.next = null;

        preNode.next = tail;
        tail.prev = preNode;
        size--;
        return value;
    }

    public E remove(int index) {
        /**
         * 第一次写没考虑到边界条件和size问题
         */
        checkElementIndex(index);
        Node<E> node = getNode(index);
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;

        node.prev =null;
        node.next =null;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;
        return node.val;
    }

    /***** 查 *****/

    public E get(int index) {
        /**
         * 第一次写没考虑到边界条件
         */
        checkElementIndex(index);
        Node<E> node = getNode(index);
        return node.val;
    }

    public E getFirst() {
        /**
         * 第一次写没考虑到边界条件
         */
        if(size<1){
            throw new NoSuchElementException();
        }
        Node<E> node = head.next;
        return node.val;
    }

    public E getLast() {
        /**
         * 第一次写没考虑到边界条件
         */
        if(size<1){
            throw new NoSuchElementException();
        }
        Node<E> node = tail.prev;
        return node.val;
    }

    /***** 改 *****/

    public E set(int index, E val) {
        /**
         * 第一次写没考虑到边界条件
         * 而且让返回的是修改前的数据
         */
       checkElementIndex(index);
        Node<E> node = getNode(index);
        E oldVal = node.val;
        node.val = val;
//        Node<E> node = getNode(index);
//        node.val = val;
        return oldVal;
    }

    /***** 其他工具函数 *****/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> p = head.next;
        // TODO: 可以优化，通过 index 判断从 head 还是 tail 开始遍历
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 检查 index 索引位置是否可以存在元素
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * 检查 index 索引位置是否可以添加元素
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E val = p.val;
                p = p.next;
                return val;
            }

            @Override
            public void remove() {

            }
        };
    }
}
