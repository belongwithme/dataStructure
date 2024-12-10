package graph.traverseGraph;

import graph.Graph;
import graph.simpleGraph;

import java.util.LinkedList;
import java.util.List;


/**
 * @description: TODO
 * @author: 王一
 * @date: 2024/12/9 11:37
 */
//DFS深度优先遍历框架
public class traverseDFS {


    void traverse(simpleGraph.Vertex s, boolean[] visited) {
        if (s == null) {
            return;
        }
        //防止死循环
        if (visited[s.id]) {
            return;
        }
        //前序位置
        visited[s.id] = true;
        System.out.println("visit:" + s.id);
        for (simpleGraph.Vertex neighbor : s.neighbors) {
            traverse(neighbor, visited);
        }
    }

    void traverse(Graph graph, int s, boolean[] visited) {
        if (s < 0 || s >= graph.size()) {
            return;
        }
        if (visited[s]) {
            return;
        }
        //前序位置
        visited[s] = true;
        System.out.println("visit:" + s);
        for (simpleGraph.Edge edge : graph.neighbors(s)) {
            traverse(graph, edge.to, visited);
        }
        //后序位置
    }

    //使用OnPath数组
    boolean[] onPath = new boolean[new simpleGraph().size()];
    List<Integer> path = new LinkedList<>();

    void traverse(Graph graph,int src,int dest){
        if(src<0||src>= graph.size()){
            return;
        }
        //防止死循环
        if(onPath[src]){
            return;
        }
        //前序位置
        onPath[src] = true;
        path.add(src);
        if(src == dest){
            System.out.println("find path:"+path);
        }
        for(simpleGraph.Edge e:graph.neighbors(src)){
            traverse(graph,e.to,dest);
        }
        //
        path.remove(path.size()-1);
        onPath[src] = false;

    }

}

