package unionFind;

public interface UF {
    //初始化并查集，包含n个节点，时间复杂度O(n)
    void UF(int n);

    //查询节点p和节点q是否连通(是否在同一个连通分量内），时间复杂度O(1)
    boolean connected(int p ,int q);

    //查询当前的连通分量数量，时间复杂度O(1)
    int count();

}
