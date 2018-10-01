/** 310. Minimum Height Trees(leetcode) / 1298. Minimum Height Trees(lintcode)
 *      For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is
 *  then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees
 *  (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 *    Format:
 *      The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of
 *  undirected edges (each edge is a pair of labels).
 *      You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the
 *  same as [1, 0] and thus will not appear together in edges.
 *
 *      Example: 1) Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *                           0
 *                           |
 *                           1
 *                          / \
 *                         2   3 
 *                  Output: [1]
 *               2) Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *                         0  1  2
 *                          \ | /
 *                            3
 *                            |
 *                            4
 *                            |
 *                            5 
 *                  Output: [3, 4]
 *
 *       Note: 1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any 
 *                two vertices are connected by exactly one path. In other words, any connected graph without 
 *                simple cycles is a tree.”
 *             2) The height of a rooted tree is the number of edges on the longest downward path between the root
 *                and a leaf.
 */
 
 //Method 1: 先用邻接表表示图，然后对每个节点求树高（BFS or DFS），每次更新最小高度。 —— TLE
 
 /* Method 2: [AC in leetcode but MLE in lintcode]
  *   Idea:从叶子节点开始进行 BFS。所有入度（即相连边数）为 1 的节点即是叶子节点。找高度最小的节点，即找离所有叶子节点最远的节点，
  *       也即找最中心的节点。找最中心的节点的思路：每次去掉当前图的所有叶子节点，重复此操作直到只剩下最后的根。
  *       根据这个思路可以回答题目中的 
  *   [ hint : How many MHTs can a graph have at most? ]Ans: 只能有一个或者两个最小高度树树根
  */
 class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if(edges == null || edges.length == 0){
            for(int i = 0; i < n; i++){
                res.add(i);
            }
            return res;
        }
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<Integer>();
        }
        
        int[] degree = new int[n];
        for(int i = 0; i < edges.length; i++){
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if(degree[i] == 1){
                q.offer(i);
            }
        }
        Set<Integer> visited = new HashSet<Integer>();
        while(!q.isEmpty() && q.size() + visited.size() < n){
            int size = q.size();
            while(size > 0){
                int leaf = q.poll();
                List<Integer> neighbors = graph[leaf];
                for(int i = 0; i < neighbors.size(); i++){
                    if(!visited.contains(neighbors.get(i))){
                        degree[neighbors.get(i)]--;
                    }
                }
                visited.add(leaf);
                size--;
            }
            for(int i = 0; i < n; i++){
                if(!visited.contains(i) && degree[i] == 1){
                    q.offer(i);
                }
            }
        }
        while(!q.isEmpty()){
            res.add(q.poll());
        }
        if(q.isEmpty()){
            for(int i = 0; i < n; i++){
                if(degree[i] == 0){
                    res.add(i);
                }
            }
        }
        return res;
    }
}

//Method 3: 
public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    List<Integer> res = new ArrayList<>();
    if(edges == null || edges.length == 0){
        for(int i = 0; i < n; i++){
            res.add(i);
        }
        return res;
    }
    List<Integer>[] graph = new ArrayList[n];
    for(int i = 0; i < n; i++){
        graph[i] = new ArrayList<Integer>();
    }

    int[] degree = new int[n];
    for(int i = 0; i < edges.length; i++){
        graph[edges[i][0]].add(edges[i][1]);
        graph[edges[i][1]].add(edges[i][0]);
        degree[edges[i][0]]++;
        degree[edges[i][1]]++;
    }

    Queue<Integer> q = new LinkedList<>();
    for(int i = 0; i < n; i++){
        if(degree[i] == 1){
            q.offer(i);
        }
    }
    while(!q.isEmpty()){
        res = new ArrayList<>();
        int size = q.size();
        for(int i = 0; i < size; i++){
            int leaf = q.poll();
            res.add(leaf);
            degree[leaf]--;
            for(int j = 0; j < graph[leaf].size(); j++){
                int parent = graph[leaf].get(j);
                degree[parent]--;
                if(degree[parent] == 1){
                    q.offer(parent);
                }
            }
        }
    }
    return res;
}
