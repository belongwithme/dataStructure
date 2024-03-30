package com.wang.collection;

/**
 * 单链实现
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MySingleLinkedList<E> implements Iterable<E> {
    // 虚拟头尾节点
    final private Node<E> head,tail;
    private int size;

    // 双链表节点
    private static class Node<E> {
        E val;
        Node<E> next;


        Node(E val) {
            this.val = val;
        }
    }

    // 构造函数初始化头尾节点
    public MySingleLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        //建立关系
        head.next = tail;
        this.size = 0;
    }


    /***** 增 *****/

    public void addLast(E e) {
        Node<E> node = new Node<>(e);
        Node<E> lastNode;
        if(size-1>=0){
            lastNode  = getNode(size - 1);
        }else{
            lastNode = head;
        }


        node.next = tail;
        lastNode.next= node;
        size++;

        /**
         * 第一次写漏掉了边界条件和size
         */
//        Node<E> node = new Node<>(e);
//
//        Node<E> lastNode = getNode(size - 1);
//
//        node.next = tail;
//        lastNode.next= node;
    }

    public void addFirst(E e) {
        Node<E> node = new Node<>(e);
        Node<E> nextNode = head.next;

        node.next = nextNode;
        head.next = node;

        //第一次写漏掉了size
        size++;
    }

    public void add(int index, E element) {
        checkElementIndex(index);
        if(index==size){
            addLast(element);
            return;
        }else{

            Node<E> nextnode = getNode(index);
            Node<E> node = new Node<>(element);
            Node<E> preNode;
            if(index - 1>=0){
                preNode =getNode(index - 1);
            }else{
                preNode = head;
            }


            preNode.next = node.next;
            node.next = nextnode;
            size++;
        }
        /**
         * 第一次写
         * 边界情况少考虑了插入尾部的情况
         * 处理插入首部代码不是很好.
         * 漏掉了size
         */
//        checkElementIndex(index);
//        if(index==1){
//            addFirst(element);
//        }else{
//            Node<E> nextnode = getNode(index);
//            Node<E> preNode = getNode(index - 1);
//            Node<E> node = new Node<>(element);
//
//            preNode.next = node.next;
//            node.next = nextnode;
//
//        }
    }

    /***** 删 *****/

    public E removeFirst() {
        if(size <1){
            throw new NoSuchElementException();
        }
        Node<E> removeNode = head.next;
        head.next = removeNode.next;
        removeNode.next =null;
        //漏掉了size
        size--;
        return removeNode.val;
    }

    public E removeLast() {
        /**
         * 第一次写边界问题有点问题
         * 如果size-2<0的时候没考虑到
         */
        if(size<1){
            throw new NoSuchElementException();
        }

        Node<E> removeNode = getNode(size - 1);
        Node<E> node ;
        if(size-2>=0){
            node =  getNode(size - 2);
        }else{
            node = head;
        }

        node.next = tail;
        removeNode.next =null;
        size--;
        return removeNode.val;
    }

    public E remove(int index) {
        checkElementIndex(index);

        Node<E> node = getNode(index);
        Node<E> preNode;
        if(index-1>=0){
          preNode=getNode(index - 1);
        }else{
            preNode =head;
        }
        preNode.next = node.next;
        node.next =null;
        return node.val;

        /**
         * 第一次写边界问题有问题和size没考虑到
         */
//        checkElementIndex(index);
//        if(size ==1){
//           return removeFirst();
//        }else{
//            Node<E> node = getNode(index);
//            Node<E> preNode = getNode(index - 1);
//            preNode.next = node.next;
//            node.next =null;
//            return node.val;
//
//        }
    }

    /***** 查 *****/

    public E get(int index) {
        checkElementIndex(index);

        return getNode(index).val;
    }

    public E getFirst() {
        if(size<1){
            throw  new NoSuchElementException();
        }
        return head.next.val;
    }

    public E getLast() {
        if(size<1){
            throw  new NoSuchElementException();
        }
        Node<E> node = getNode(size - 1);
        return node.val;
    }

    /***** 改 *****/

    public E set(int index, E val) {
        checkElementIndex(index);
        Node<E> node = getNode(index );
        E oldVal = node.val;
        node.val = val;
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
        };
    }
}
