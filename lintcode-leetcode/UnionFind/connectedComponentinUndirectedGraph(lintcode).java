/** 431. Connected Component in Undirected Graph(lintcode)
 *      Find the number connected component in the undirected graph. Each node in the graph contains a label and 
 *  a list of its neighbors. (a connected component (or just component) of an undirected graph is a subgraph in 
 *  which any two vertices are connected to each other by paths, and which is connected to no additional vertices 
 *  in the supergraph.)
 *
 *      Note: Each connected component should sort by label.
 *
 *      Example: Given graph:
 *                    A------B  C
 *                     \     |  | 
 *                      \    |  |
 *                       \   |  |
 *                        \  |  |
 *                          D   E
 *               Return {A,B,D}, {C,E}. Since there are two connected component which is {A,B,D}, {C,E}
 *
 *      注意：label可能是负数
 */

/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
 
 //My Method: 
 public class Solution {
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        List<List<Integer>> res = new ArrayList<>();
        if(nodes == null || nodes.size() == 0){
            return res;
        }
        int n = nodes.size();
        Set<Integer> labelset = new HashSet<>();
        for(int i = 0; i < n; i++){
            labelset.add(nodes.get(i).label);
            for(UndirectedGraphNode neighbor: nodes.get(i).neighbors){
                labelset.add(neighbor.label);
            }
        }
        
        UnionFind f = new UnionFind(labelset);
        
        for(int i = 0; i < n; i++){
            int label = nodes.get(i).label;
            ArrayList<UndirectedGraphNode> nei = nodes.get(i).neighbors;
            for(int j = 0; j < nei.size(); j++){
                if(!f.connected(label, nei.get(j).label)){
                    f.union(label, nei.get(j).label);
                }
            }
        }
        res = f.getComponents();
        return res;
    }
    
    class UnionFind{
        int count;
        Map<Integer, Integer> ids;
        Set<Integer> root;
        
        public UnionFind(Set<Integer> labelset){
            count = labelset.size();
            ids = new HashMap<Integer, Integer>();
            root = new HashSet<Integer>();
            for(Integer i: labelset){
                ids.put(i, i);
                root.add(i);
            }
        }
        
        public int find(int a){
            while(ids.get(a) != a){
                a = ids.get(a);
            }
            return a;
        }
        
        public boolean connected(int a, int b){
            return find(a) == find(b);
        }
        
        public void union(int a, int b){
            int root_a = find(a);
            int root_b = find(b);
            if(root_a == root_b){
                return;
            }
            
            ids.put(root_b, root_a);
            root.remove(root_b);

            count--;
        }
        
        public int count(){
            return count;
        }
        
        public List<List<Integer>> getComponents(){
            Map<Integer, List<Integer>> map = new HashMap<>();
            Iterator<Integer> itr = root.iterator();
            while(itr.hasNext()){
                Integer r = itr.next();
                map.put(r, new ArrayList<>());
            }
            for(Map.Entry<Integer, Integer> entry: ids.entrySet()){
                int root = find(entry.getKey());
                map.get(root).add(entry.getKey());
            }
            List<List<Integer>> res = new ArrayList<>();
            for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()){
                Collections.sort(entry.getValue());
                res.add(entry.getValue());
            }
            return res;
        }
        
    }
}
