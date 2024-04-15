package com.wang.collection;

/**
 * 手动实现环形缓冲区
 */
public class MyRingBuffer {

    private byte[] buffer;
    //用于防止索引越界
    private int mask;
    //读写指针的位置
    private int r,w;
    //记录可以读取的字节个数
    private int size;
    //默认初始化的buffer大小
    private static final int INIT_CAP = 1024;

    public MyRingBuffer(){
        this(INIT_CAP);
    }


    public MyRingBuffer(int cap){
        //将输入的cap变成2的指数
        cap = ceilToPowerOfTwo(cap);

        //保证capacity是2的指数
        //(i+n)%capacity 等价于 (i+n)&mask
        mask = cap -1;
        buffer = new byte[cap];

        //读/写指针初始化在索引0
        r=w=0;
        //还没有写入任何数据,可读取字节数为0
        size=0;
    }

    //从RingBuffer中读取元素到out中,返回读取的字节数
    public int read(byte[] out){
        if(out ==null ||out.length ==0 || isEmpty())
            return 0;

        //读取字节数
        int len = Math.min(size,out.length);

        //情况一: r-----w
        if(w>r){
            System.arraycopy(buffer,r,out,0,len);
            r =len+r;
            //可读取的字节数减少n
            size = len-size;
            return  len;
        }

        //情况二: --w -r---
        //情况2.1: --w ***r-- r移动后还在w的右边
        if(r+len<=buffer.length){
            System.arraycopy(buffer,r,out,0,len);
        }else {
            //情况2.2: 读取后变为***r ---w
            int n1 = buffer.length - r;
            int n2 = len - n1;
            System.arraycopy(buffer, r, out, 0, n1);
             System.arraycopy(buffer, 0, out, n1, n2);
        }
        r=(r+len)&mask;
        size-=len;
        return len;
    }

    //将in中的数据写入RingBuffer,返回写入字节的个数
    public  int write(byte[] in){
        if(in ==null || in.length ==0){
            return 0;
        }
        final int len =in.length;

        //还未使用的字节数量
        int free = buffer.length - size;

        if(len>free){
            //确保buffer容量足够
            ensureCapacity(length()+len);
        }
        //情况一: r---w
        if(w>=r){
            //情况1.1: 写入后为 r----**w
            if(w+len<=buffer.length){
                System.arraycopy(in,0,buffer,w,len);
            }else{
                //情况1.2: 写入后为***w---r**
                int n1 = buffer.length-w;
                int n2 = len -n1;
                System.arraycopy(in,0,buffer,w,n1);
                System.arraycopy(in,n1,buffer,0,n2);
            }
        }else{
            //情况二: --w r--
            //buffer容量肯定是够的,直接copy就行了
            System.arraycopy(in,0,buffer,w,len);
        }

        w = (w+len)&mask;

        size+=len;
        return len;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public int length(){
        return size;
    }
    public void ensureCapacity(int newCap){
        newCap = ceilToPowerOfTwo(newCap);
        //将data中的数据读入temp中
        byte[] temp = new byte[newCap];
        int n = read(temp);
        //更新其他字段的值
        this.buffer = temp;
        this.r =0;
        this.w =n;
        this.mask = newCap-1;
    }
    private static int ceilToPowerOfTwo(int cap) {
        if(cap<0){
            //一定不能小于0
            cap = 2;
        }
        if(cap>(1<<30)){
            /**
             *  int型最大值为2^31-1
             *  所以无法向上取整到2^31
             */
            cap = 1<<30;
        }
        cap--;
        cap |= cap >> 1;
        cap |= cap >> 2;
        cap |= cap >> 4;
        cap |= cap >> 8;
        cap |= cap >> 16;
        cap++;
        return cap;
    }

}
