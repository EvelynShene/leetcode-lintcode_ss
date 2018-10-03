/** 178. Graph Valid Tree(lintcode) / 261. Graph Valid Tree(leetcode - locked)
 *      Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 *  write a function to check whether these edges make up a valid tree.
 *
 *      Note: You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] 
 *  is the same as [1, 0] and thus will not appear together in edges.
 *
 *      Example:
 *            1) Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 *            2) Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 */
 
 //Method: 本质是查看是不是强连通和无环图 
 public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if(n < 2){
            return true; 
        }
        if(edges == null || edges.length == 0){
            return false;
        }
        UnionFind f = new UnionFind(n);
        for(int[] e: edges){
            int p = e[0];
            int q = e[1];
            if(f.connected(p, q)){
                return false;
            }
            f.union(p, q);
        }
        if(f.count() == 1){
            return true;
        }
        return false;
    }
    
    class UnionFind{
        int[] ids;
        int count;
        public UnionFind(int n){
            ids = new int[n];
            for(int i = 0; i < n; i++){
                ids[i] = i;
            }
            count = n;
        }
        
        // add connection between p and q; - use smaller one as new identifier
        public void union(int p, int q){
            if(ids[p] == ids[q]){
                return;
            }
            int oldid = 0;
            int newid = 0;
            if(ids[p] > ids[q]){
                oldid = ids[p];
                newid = ids[q];
            }
            else{
                oldid = ids[q];
                newid = ids[p];
            }
            for(int i = 0; i < ids.length; i++){
                if(ids[i] == oldid){
                    ids[i] = newid;
                }
            }
            count--;
        }
        
        public boolean connected(int p, int q){// return true if p and q in the same component
            return ids[p] == ids[q];
        }
        
        // public int find(int p){// find p's component identifier
        //     return ids[p];
        // }
        
        public int count(){// return the number of different component in the UF
            return count;
        }
    }
}
