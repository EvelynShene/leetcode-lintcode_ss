/** 133. Clone Graph(leetcode) / 137. Clone Graph(lintcode)
 *      Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 *      How we serialize an undirected graph:
 *        Nodes are labeled uniquely.
 *        We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 *      
 *      As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 *      The graph has a total of three nodes, and therefore contains three parts as separated by #.
 *          1) First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 *          2) Second node is labeled as 1. Connect node 1 to node 2.
 *          3) Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 *      Visually, the graph looks like the following:
 *              1
 *             / \
 *            /   \
 *           0 --- 2
 *                / \
 *                \_/
 */
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
 
 //My Method: Use BFS + HashMap - Same as 138 Copy List with Random Pointer
 public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null){
            return null;
        }
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        q.offer(node);
        while(!q.isEmpty()){
            UndirectedGraphNode cur = q.poll();
            UndirectedGraphNode newNode = new UndirectedGraphNode(cur.label);
            if(!map.containsKey(cur)){
                map.put(cur, newNode);// Map<oldnode, newnode>
                List<UndirectedGraphNode> nei = cur.neighbors;
                for(int i = 0; i < nei.size(); i++){
                    q.offer(nei.get(i));
                }
            }
        }
        UndirectedGraphNode newGraph = map.get(node);
        for(Map.Entry<UndirectedGraphNode, UndirectedGraphNode> entry: map.entrySet()){
            UndirectedGraphNode oldNode = entry.getKey();
            UndirectedGraphNode newNode = entry.getValue();
            List<UndirectedGraphNode> nei = oldNode.neighbors;
            for(int i = 0; i < nei.size(); i++){
                newNode.neighbors.add(map.get(nei.get(i)));
            }
        }
        return newGraph;
    }
}
