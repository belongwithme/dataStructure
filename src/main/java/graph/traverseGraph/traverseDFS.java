package graph.traverseGraph;

import graph.Graph;
import graph.simpleGraph;


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


}

