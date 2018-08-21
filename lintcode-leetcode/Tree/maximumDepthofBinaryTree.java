/** 104. Maximum Depth of Binary Tree(leetcode) / 97. Maximum Depth of Binary Tree(lintcode)
 *    Given a binary tree, find its maximum depth.
 *    The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf
 *  node.
 *
 *    Note: A leaf is a node with no children.
 *
 *    Example: Given binary tree [3,9,20,null,null,15,7],
 *                     3
 *                    / \
 *                   9  20
 *                     /  \
 *                    15   7
 *            return its depth = 3.
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
 
//Method 1: Recursive
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + Math.max(left,right);
    }
}

//Method 2: BFS - Use Queue
public int maxDepth(TreeNode root) {
    if(root == null){
        return 0;
    }
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.offer(root);
    int res = 0;
    while(!q.isEmpty()){
        int size = q.size();
        res++;
        while(size > 0){
            TreeNode cur = q.poll();
            if(cur.left != null){
                q.offer(cur.left);
            }
            if(cur.right != null){
                q.offer(cur.right);
            }
            size--;
        }
    }
    return res;
}
