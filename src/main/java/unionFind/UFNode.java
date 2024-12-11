package unionFind;

public class UFNode {
    int id;
    UFNode parent;

    int count;

    //记录数的重量
    private int[] size;

    public UFNode(int n) {
        this.count = n;
        parent = new UFNode(n);
        // 最初每棵树只有一个节点
        // 重量应该初始化 1
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent.id = i;
            size[i] = 1;
        }
    }

    UFNode find(UFNode node){
        while(node.parent!=null){
            node =node.parent;
        }
        return node;
    }

    void union(UFNode p, UFNode q){
        //最基础的情况
//      find(p).parent = find(q);

        //平衡性优化
        int rootP =find(p).id;
        int rootQ = find(q).id;
        if(rootQ==rootP){
            return;
        }

        if(size[rootQ]>size[rootP]){
            find(p).id = find(q).id;
            size[rootQ]+=size[rootP];
        }else{
            find(q).id = find(p).id;
            size[rootP]+=size[rootQ];
        }
        count--;
    }

    boolean connected(UFNode p,UFNode q){
        return find(p).id == find(q).id;
    }

    int[] size(UFNode uf){

        return uf.size;
    }
}
