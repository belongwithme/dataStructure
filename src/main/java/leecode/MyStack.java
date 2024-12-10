package leecode;

/**
 * @description: TODO
 * @author: 王一
 * @date: 2024/8/23 11:19
 */

class MyStack{
    /**
     * 用数组实现栈
     */
    private int size = 0;
    private int[] table = new int[100];

    public void push(int x){
        //如果要扩容捏
        table[size] = x;
        size++;
    }
    public int pop(){
        //好像要缩容呀
        if(size==0){
            return -1;
        }
        int res = table[size-1];
        table[size-1] = 0;
        return res;
    }


    public int peek(){
        if(size ==0){
            return -1;
        }
        return table[size-1];
    }

    public Boolean empty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }

}
