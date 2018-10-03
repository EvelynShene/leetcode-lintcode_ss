/** 590. Connecting Graph II(lintcode)
 *      Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.
 *      You need to support the following method:
 *         1) connect(a, b), an edge to connect node a and node b
 *         2) query(a), Returns the number of connected component nodes which include node a.
 */
 
//My Method:
public class ConnectingGraph2 {
    int[] ids; 
    Map<Integer, Integer> map;
    public ConnectingGraph2(int n) {
        ids = new int[n + 1]; 
        map = new HashMap<>(); // ids<root, num>
        for(int i = 1; i < n + 1; i++){
            ids[i] = i;
            map.put(i, 1);
        }
    }
     
    public int find(int a){
        while(ids[a] != a){
            a = ids[a];
        }
        return a;
    }
    
    public void connect(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        if(root_a == root_b){
            return;
        }
        int num_a = map.get(root_a);
        int num_b = map.get(root_b);
        ids[root_b] = root_a;
        map.put(root_a, num_a + num_b);
        map.remove(root_b);
    }

    public int query(int a) {
        // write your code here
        int root = find(a);
        return map.get(root);
    }
}
