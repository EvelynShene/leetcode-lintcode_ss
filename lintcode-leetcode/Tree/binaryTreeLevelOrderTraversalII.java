/** 107. Binary Tree Level Order Traversal II(leetcode) / 70. Binary Tree Level Order Traversal II(lintcode)
 *      Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to 
 *  right, level by level from leaf to root).
 *
 *      For example: Given binary tree [3,9,20,null,null,15,7],
 *                             3
 *                            / \
 *                           9  20
 *                             /  \
 *                            15   7
 *                   return its bottom-up level order traversal as:
 *                           [
 *                             [15,7],
 *                             [9,20],
 *                             [3]
 *                           ]
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
 
//My Method: BFS
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty()){
            List<Integer> l = new ArrayList<Integer>();
            int size = q.size();
            while(size > 0){
                TreeNode cur = q.poll();
                l.add(cur.val);
                if(cur.left != null){
                    q.offer(cur.left);
                }
                if(cur.right != null){
                    q.offer(cur.right);
                }
                size--;
            }
            res.add(0,l);
        }
        return res;
    }
}
