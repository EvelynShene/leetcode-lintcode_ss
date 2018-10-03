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

/* Method 2: union - O(lgn) ; query - O(lgn)
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
