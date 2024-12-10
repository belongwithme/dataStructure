package graph.traverseGraph;

import graph.Graph;
import graph.WeightedDigraph;

/**
 * @description: TODO
 * @author: 王一
 * @date: 2024/12/9 11:37
 */

public class traverseDemo {
    void traverse(Graph graph,int s,boolean[] visited){
        if(s<0||s>=graph.size()){
            return;
        }
        //防止死循环
        if(visited[s]){
            return;
        }
        //前序位置
        visited[s] = true;
        System.out.println("visit"+s);
        for(WeightedDigraph.Edge e:graph.neighbors(s)){

        }
    }
}
