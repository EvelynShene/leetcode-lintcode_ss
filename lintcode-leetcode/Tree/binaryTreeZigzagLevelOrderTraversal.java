/** 103. Binary Tree Zigzag Level Order Traversal(leetcode) / 71. Binary Tree Zigzag Level Order Traversal(lintcode)
 *      Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right,
 *  then right to left for the next level and alternate between).
 *
 *      For example:Given binary tree [3,9,20,null,null,15,7],
 *                           3
 *                          / \
 *                         9  20
 *                           /  \
 *                          15   7
 *                  return its zigzag level order traversal as:
 *                       [
 *                         [3],
 *                         [20,9],
 *                         [15,7]
 *                       ]
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//My Method:
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if(root == null){
        return res;
    }
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.offer(root);
    boolean left_to_right = true;
    while(!q.isEmpty()){
        List<Integer> l = new ArrayList<Integer>();
        int size = q.size();
        while(size > 0){
            TreeNode cur = q.poll();
            if(left_to_right){
                l.add(cur.val);
            }
            else{
                l.add(0,cur.val);
            }
            if(cur.left != null){
                q.offer(cur.left);
            }
            if(cur.right != null){
                q.offer(cur.right);
            }
            size--;
        }
        res.add(l);
        left_to_right = left_to_right? false: true;
    }
    return res;
}
