/** 591. Connecting Graph III(lintcode)
 *      Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.
 *      You need to support the following method:
 *          1) connect(a, b), an edge to connect node a and node b
 *          2) query(), Returns the number of connected component in the graph
 *
 *      Example: 5 // n = 5
 *               query() return 5
 *               connect(1, 2)
 *               query() return 4
 *               connect(2, 4)
 *               query() return 3
 *               connect(1, 4)
 *               query() return 3
 */
 
 //My Method:
 public class ConnectingGraph3 {
    int count;
    int[] ids;
    public ConnectingGraph3(int n){
        count = n;
        ids = new int[n];
        for(int i = 0; i < n; i++){
            ids[i] = i;
        }
    }
    public void connect(int a, int b) {
        // write your code here
        if(ids[a - 1] == ids[b - 1]){
            return;
        }
        int newid = ids[a - 1] > ids[b - 1]? ids[b - 1]: ids[a - 1];
        int oldid = ids[a - 1] > ids[b - 1]? ids[a - 1]: ids[b - 1];
        for(int i = 0; i < ids.length; i++){
            if(ids[i] == oldid){
                ids[i] = newid;
            }
        }
        count--;
    }

    /**
     * @return: An integer
     */
    public int query() {
        // write your code here
        return count;
    }
}
