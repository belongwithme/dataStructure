package graph.Prim;

import java.util.List;
import java.util.PriorityQueue;

/**
 * @description: Prim算法实现
 * @author: 王一
 * @date: 2024/12/13 13:25
 */

public class PrimDemo {
    // 核心数据结构，存储「横切边」的优先级队列
    private PriorityQueue<int[]> pq;
    // 类似 visited 数组的作用，记录哪些节点已经成为最小生成树的一部分
    private boolean[] inMST;
    // 记录最小生成树的权重和
    private int weightSum = 0;
    // graph 是用邻接表表示的一幅图，
    // graph[s] 记录节点 s 所有相邻的边，
    // 三元组 int[]{from, to, weight} 表示一条边
    private List<int[]>[] graph;


    public PrimDemo(List<int[]>[] graph) {
        this.graph = graph;
        this.pq = new PriorityQueue<>((a,b)->{
            return a[2]-b[2];
        });
        int n = graph.length;
        inMST = new boolean[n];
        //随便找一个点切分
        inMST[0] = true;
        cut(0);
        //不断切分.向最小生成树中添加边
        while(!pq.isEmpty()){
            int[] edge = pq.poll();
            int to = edge[1];
            int weight = edge[2];
            if(inMST[to]){
                continue;
            }
            weightSum+=weight;
            inMST[to] = true;
            cut(to);
        }
    }
    // 最小生成树的权重和
    public int weightSum() {
        return weightSum;
    }

    private void cut(int s){
        for(int[] edge:graph[s]){
            int to = edge[1];
            //如果相邻节点已经在最小生成树中,跳过
            //否则会产生环
            if(inMST[to]){
                continue;
            }
            pq.offer(edge);
        }
    }

    //判断最小生成树是否包含图中所有节点
    public boolean allConnected(){
        for(int i =0;i<inMST.length;i++){
            if(!inMST[i]){
                return false;
            }
        }
        return true;
    }
}
