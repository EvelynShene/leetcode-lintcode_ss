/** 110. Balanced Binary Tree(leetcode) / 93. Balanced Binary Tree(lintcode)
 *     Given a binary tree, determine if it is height-balanced.
 *     For this problem, a height-balanced binary tree is defined as:
 *          a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 *      Example: 1) Given the following tree [3,9,20,null,null,15,7]:
 *                           3
 *                          / \
 *                         9  20
 *                           /  \
 *                          15   7
 *                  Return true.
 *               2) Given the following tree [1,2,2,3,3,null,null,4,4]:
 *                              1
 *                             / \
 *                            2   2
 *                           / \
 *                          3   3
 *                         / \
 *                        4   4
 *                  Return false.
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
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        int left = depthOfTree(root.left);
        int right = depthOfTree(root.right);
        if(Math.abs(left-right) <= 1){
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;
    }
    public int depthOfTree(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = depthOfTree(root.left);
        int right = depthOfTree(root.right);
        return 1 + Math.max(left,right);
    }
}

//Method 2: 思路同Method 1, 只是一旦发现不平衡，就返回-1，不计算具体深度，只有平衡时，才返回具体的节点深度
class Solution {
    public boolean isBalanced(TreeNode root) {
        return depthOfTree(root) != -1;
    }
    
    public int depthOfTree(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = depthOfTree(root.left);
        int right = depthOfTree(root.right);
        if(left == -1 || right == -1 || Math.abs(left - right) > 1){
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
