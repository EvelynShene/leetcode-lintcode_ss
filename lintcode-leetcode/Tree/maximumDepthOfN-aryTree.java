/** 559. Maximum Depth of N-ary Tree(leetcode)
 *    Given a n-ary tree, find its maximum depth.
 *    The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf
 *  node.
 *
 *    Note: 1) The depth of the tree is at most 1000.
 *          2) The total number of nodes is at most 5000.
 */
 
 /*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public int maxDepth(Node root) {
        if(root == null){
            return 0;
        }
        int max_depth = 0;
        Queue<Node> q = new LinkedList<Node>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            max_depth++;
            while(size > 0){
                Node cur = q.poll();
                List<Node> children = cur.children;
                for(int i = 0; i < children.size(); i++){
                    q.offer(children.get(i));
                }
                size--;
            }
        }
        return max_depth;
    }
}
