import java.util.LinkedList;
import java.util.List;

public class demo1 {

    // 记录递归堆栈中的节点
    static boolean[] onPath;
    // 记录图中是否有环
    static boolean hasCycle = false;
    public static void main(String[] args) {
        int[][] de = new int[][]{{1,2},{1,5},{2,4},{4,3},{6,1}};
        List<Integer>[] graph = buildGraph(7, de);

        onPath = new boolean[7];

        for (int i = 0; i < 5; i++) {
            // 遍历图中的所有节点
            traverse(graph, i);
        }
        // 只要没有循环依赖可以完成所有课程

    }

    static List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            // 添加一条从 from 指向 to 的有向边
            // 边的方向是「被依赖」关系，即修完课程 from 才能修课程 to
            graph[from].add(to);
        }
        return graph;
    }
    // 图遍历函数，遍历所有路径
    static void traverse(List<Integer>[] graph, int s) {
        if (hasCycle) {
            // 如果已经找到了环，也不用再遍历了
            return;
        }

        if (onPath[s]) {
            // s 已经在递归路径上，说明成环了
            hasCycle = true;

            return;
        }

        // 前序代码位置
        onPath[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        // 后序代码位置
        onPath[s] = false;
    }

}
