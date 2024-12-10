package graph.traverseGraph;

import graph.Graph;
import graph.simpleGraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 广度优先搜索(BFS)
 * @author: 王一
 * @date: 2024/12/10 17:37
 */

public class traverseBFS {

    //图结构的BFS遍历
    //不记录遍历步数
    void bfs(Graph graph,int s){
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        visited[s] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            System.out.println("visit:"+cur);
            for(simpleGraph.Edge e:graph.neighbors(cur)){
                if(!visited[e.to]){
                    q.offer(e.to);
                    visited[e.to] = true;
                }
            }
        }
    }


    void bfsWithStep(Graph graph,int s){
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        visited[s] = true;
        int step = 0;
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i=0;i<sz;i++){
                int cur = q.poll();
                System.out.println("visit " + cur + " at step " + step);
                for(simpleGraph.Edge e:graph.neighbors(cur)){
                    if(!visited[e.to]){
                        q.offer(e.to);
                        visited[e.to] = true;
                    }
                }
            }
            step++;
        }
    }

    class State{
        int node ;
        int weight;

        public State(int node,int weight){
            this.node = node;
            this.weight = weight;
        }
    }

    void bfsWithState(Graph graph,int s){
        boolean[] visited = new boolean[graph.size()];
        Queue<State> q = new LinkedList<>();
        q.offer(new State(s,0));
        visited[s] = true;
        while(!q.isEmpty()){
            State state = q.poll();
            int cur = state.node;
            int weight = state.weight;
            System.out.println("visit " + cur + " with path weight " + weight);
            for(simpleGraph.Edge e :graph.neighbors(cur)){
                if(!visited[e.to]){
                    q.offer(new State(e.to,weight+e.weight));
                    visited[e.to] = true;
                }
            }
        }
    }
}
