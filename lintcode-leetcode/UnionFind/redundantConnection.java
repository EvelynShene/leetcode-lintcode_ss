/** 684. Redundant Connection(leetcode) / 1088. Redundant Connection(lintcode)
 *    In this problem, a tree is an undirected graph that is connected and has no cycles.
 *    The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one
 *  additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that
 *  already existed.
 *    The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that
 *  represents an undirected edge connecting nodes u and v.
 *    Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple 
 *  answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the 
 *  same format, with u < v.
 *
 *    Example:1) Input: [[1,2], [1,3], [2,3]] ; Output: [2,3]
 *               Explanation: The given undirected graph will be like this:
 *                       1
 *                      / \
 *                     2 - 3
 *
 *            2) Input: [[1,2], [2,3], [3,4], [1,4], [1,5]] ; Output: [1,4]
 *               Explanation: The given undirected graph will be like this:
 *                       5 - 1 - 2
 *                           |   |
 *                           4 - 3
 *
 *    Note: 1) The size of the input 2D-array will be between 3 and 1000.
 *          2) Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input
 *            array.
 */

//My Method:
class Solution {
    class UnionFind{
        int[] ids;
        public UnionFind(int n){
            ids = new int[n + 1];
            for(int i = 1; i <= n; i++){
                ids[i] = i;
            }
        }
        
        public int find(int a){
            while(ids[a] != a){
                a = ids[a];
            }
            return a;
        }
        
        public boolean union(int a, int b){
            int root_a = find(a);
            int root_b = find(b);
            if(root_a == root_b){
                return false;
            }
            ids[root_a] = root_b;
            return true;
        }
    }
    
    public int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];
        if(edges == null || edges.length == 0){
            return res;
        }
        int n = edges.length;
        UnionFind f = new UnionFind(n);
        
        for(int[] e: edges){
            if(!f.union(e[0], e[1])){
                res[0] = e[0];
                res[1] = e[1];
                break;
            }
        }
        return res;
    }
}
