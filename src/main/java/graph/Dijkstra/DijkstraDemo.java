package graph.Dijkstra;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: Dijkstra伪代码
 * @author: 王一
 * @date: 2024/12/13 14:08
 */

public class DijkstraDemo {
    public int[] dijkstra(int start, List<Integer>[] graph) {
        int V = graph.length;
        //最短路径的权重,可以理解为dp table
        //distTo[i] ->节点start到节点i的最短路径权重
        int[] distTo = new int[V];
        //求最小值,初始化为正无穷
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.distFromStart - b.distFromStart;
        });
        pq.offer(new State(start,0));
        while(!pq.isEmpty()){
            State curState = pq.poll();
            int curNodeID = curState.id;
            int curDistFromStart = curState.distFromStart;
            if(curDistFromStart>distTo[curNodeID]){
                continue;
            }
            for(int nextNodeId : adj(curNodeID)){
                int distToNextNode = distTo[curNodeID]+ weight(curNodeID,nextNodeId);
                if(distTo[nextNodeId] > distToNextNode){
                    distTo[nextNodeId] = distToNextNode;
                    pq.offer(new State(nextNodeId,distToNextNode));
                }
            }
        }
        return distTo;
    }
    // 输入节点 s 返回 s 的相邻节点
    List<Integer> adj(int s){
        return null;
    }
    //返回节点from到节点to之间的边的权重
    int weight(int from,int to){
        return 0;
    }
}

class State {
    // 图节点的 id
    int id;
    // 从 start 节点到当前节点的距离
    int distFromStart;

    State(int id, int distFromStart) {
        this.id = id;
        this.distFromStart = distFromStart;
    }
}
