/** 685. Redundant Connection II(leetcode) / 1087. Redundant Connection II(lintcode)
 *     In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which
 *  all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node
 *  which has no parents.
 *      The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, 
 *  ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to 
 *  N, and was not an edge that already existed.
 *      The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that 
 *  represents a directed edge connecting nodes u and v, where u is a parent of child v.
 *      Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are
 *  multiple answers, return the answer that occurs last in the given 2D-array.
 *
 *      Example: 1) Input: [[1,2], [1,3], [2,3]] ; Output: [2,3]
 *                  Explanation: The given directed graph will be like this:
 *                                   1
 *                                  / \
 *                                 v   v
 *                                 2-->3
 *               2) Input: [[1,2], [2,3], [3,4], [4,1], [1,5]] ; Output: [4,1]
 *                  Explanation: The given directed graph will be like this:
 *                                 5 <- 1 -> 2
 *                                      ^    |
 *                                      |    v
 *                                      4 <- 3
 *   
 *      Note: 1) The size of the input 2D-array will be between 3 and 1000.
 *            2) Every integer represented in the 2D-array will be between 1 and N, where N is the size of the 
 *             input array.
 */
 
 // My Method: [idea from https://www.jiuzhang.com/solution/redundant-connection-ii/]
 class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] res = new int[2];
        if(edges == null || edges.length == 0){
            return res;
        }
        int n = edges.length;
        Map<Integer, Integer> map = new HashMap<>(); // map<node, its parent>
        int degree2 = 0; //是否有入度为2的点
        
        for(int[] e: edges){
            if(map.containsKey(e[1])){
                degree2++;
                if(degree2 == 1){
                    res[0] = e[0];
                    res[1] = e[1];
                }
                else{
                    res[0] = map.get(res[1]);
                    return res;
                }
            }
            else{
                map.put(e[1], e[0]);
            }
        }
        
        UnionFind f = new UnionFind(n);
        
        //checkTree - 有入度为2的点，查看删除指定边后的图是否能构成tree
        if(degree2 != 0){ 
            for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                if(!f.union(entry.getKey(), entry.getValue())){
                    res[0] = map.get(res[1]);
                    return res;
                }
            }
            if(f.count() != 1){
                res[0] = map.get(res[1]);
            }
            return res;
        }
        
        for(int[] e: edges){
            if(!f.union(e[1], e[0])){
                res[0] = e[0];
                res[1] = e[1];
                break;
            }
        }
        return res;
    }
    
    class UnionFind{
        int count;
        int[] parent;
        public UnionFind(int n){
            count = n;
            parent = new int[n + 1];
            for(int i = 1; i <= n; i++){
                parent[i] = i;
            }
        }
        
        public int count(){
            return count;
        }
        
        public int find(int a){
            while(parent[a] != a){
                a = parent[a];
            }
            return a;
        }
        
        public boolean union(int a, int b){
            int root_a = find(a);
            int root_b = find(b);
            if(root_a == root_b){
                return false;
            }
            parent[root_a] = root_b;
            count--;
            return true;
        }
    }
}
