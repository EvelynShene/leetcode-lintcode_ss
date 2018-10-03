/** 589. Connecting Graph(lintcode)
 *      Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.
 *      You need to support the following method:
 *        1) connect(a, b), add an edge to connect node a and node b`.
 *        2) query(a, b), check if two nodes are connected
 */
 
 //My Method 1: union - O(N) ; query - O(1)
 public class ConnectingGraph {
    /*
    * @param n: An integer
    */
    int[] ids;
    public ConnectingGraph(int n) {
        // do intialization if necessary
        ids = new int[n + 1];
        for(int i = 1; i <= n; i++){
            ids[i] = i;
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        // write your code here
        if(ids[a] == ids[b]){
            return;
        }
        int newid = ids[a];
        int oldid = ids[b];
        for(int i = 1; i < ids.length; i++){
            if(ids[i] == oldid){
                ids[i] = newid;
            }
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {
        // write your code here
        return ids[a] == ids[b];
    }
}

/* Method 2: union - O(lgn)-O(n) ; query - O(lgn)
 *      引入了一个抽象的“树”结构，即初始时每个点都是一棵独立的树，所有的点构成了一个大森林。
 *    每一次连接，实际上就是两棵树的合并。通过，不断的合并，合并，再合并最后长成了一棵棵的大树。
 */
public class ConnectingGraph {
    int[] ids;
    public ConnectingGraph(int n) {
        // do intialization if necessary
        ids = new int[n + 1];
        for(int i = 1; i <= n; i++){
            ids[i] = i;
        }
    }
    
    public int find(int a){ // 只有根节点root才可能是ids[root] = root
        while(ids[a] != a){
            a = ids[a];
        }
        return a;
    }
    
    public void connect(int a, int b) {
        // write your code here
        int id_a = find(a);
        int id_b = find(b);
        if(id_a != id_b){
            ids[id_a] = id_b;
        }
    }

    public boolean query(int a, int b) {
        // write your code here
        return find(a) == find(b);
    }
}

/** Method 3:
 *   method2的问题：一种极端情况，即每连接一个点之后，树在不断往下生长，最后长成一棵“秃树”（没有任何树枝）
 *   所以需要在两棵树进行连接之前做一个判断：每一次都优先选择将小树合并到大树下面，这样子树的高度不变，能避免树一直往下增长了
 */
public class ConnectingGraph {
    int[] ids;
    int[] height;
    public ConnectingGraph(int n) {
        // do intialization if necessary
        ids = new int[n + 1];
        height = new int[n + 1];
        for(int i = 1; i <= n; i++){
            ids[i] = i;
            height[i] = 1;
        }
    }
    
    public int find(int a){
        while(ids[a] != a){
            a = ids[a];
        }
        return a;
    }
    
    public void connect(int a, int b) {
        // write your code here
        int root_a = find(a);
        int root_b = find(b);
        if(root_a == root_b){
            return;
        }
        if(height[root_a] > height[root_b]){
            ids[root_b] = root_a;
            height[root_b] = height[root_b];
        }
        else if(height[root_a] < height[root_b]){
            ids[root_a] = root_b;
            height[root_a] = height[root_b];
        }
        else{
            ids[root_a] = root_b;
            height[root_a]++;
            height[root_b]++;
        }
    }

    public boolean query(int a, int b) {
        // write your code here
        return find(a) == find(b);
    }
}
