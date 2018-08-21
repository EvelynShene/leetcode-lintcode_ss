/** 124. Binary Tree Maximum Path Sum(leetcode) / 94. Binary Tree Maximum Path Sum(lintcode)
 *     Given a non-empty binary tree, find the maximum path sum.
 *     For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree
 *  along the parent-child connections. The path must contain at least one node and does not need to go through 
 *  the root.
 *
 *     Example: 1)Input: [1,2,3]
 *                    1
 *                   / \
 *                  2   3
 *                Output: 6
 *              2) Input: [-10,9,20,null,null,15,7]
 *                    -10
 *                    / \
 *                   9  20
 *                     /  \
 *                    15   7
 *                Output: 42
 *
 *     理解题意：求二叉树的最大路径和，这条路径的起始和终点位置任意。如例2中的路径可以是 9-(-10)-20-15，也可以是15-20-7。
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
 
// Method: [idea://www.cnblogs.com/grandyang/p/4280120.html]   
class Solution {
    int maxValue;
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPath(root);
        return maxValue;
    }
    
    public int maxPath(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = Math.max(0, maxPath(root.left));
        int right =Math.max(0, maxPath(root.right));
        maxValue = Math.max(maxValue, left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}
