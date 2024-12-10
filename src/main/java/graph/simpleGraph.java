package graph;

import java.util.List;

/**
 * @description: 最简单的图结构
 * @author: 王一
 * @date: 2024/12/9 11:13
 */

public class simpleGraph {
    //图节点的逻辑结构
    public class Vertex {
        public int id;
        public Vertex[] neighbors;
    }

    public class Edge {
        public int to;
        public int weight;
    }

    //邻接表
    List<Edge>[] graph;
    //临接矩阵
    int[][] matrix;

    public int size(){
        return this.graph.length;
    }
}
