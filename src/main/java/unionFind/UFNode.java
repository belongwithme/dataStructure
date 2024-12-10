package unionFind;

public class UFNode {
    int id;
    UFNode parent;

    UFNode find(UFNode node){
        while(node.parent!=null){
            node =node.parent;
        }
        return node;
    }

    void union(UFNode p, UFNode q){
      find(p).parent = find(q);
    }

    boolean connected(UFNode p,UFNode q){
        return find(p).id == find(q).id;
    }
}
