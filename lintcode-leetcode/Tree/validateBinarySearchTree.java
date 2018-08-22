/** 98. Validate Binary Search Tree(leetcode) / 95. Validate Binary Search Tree(lintcode)
 *     Given a binary tree, determine if it is a valid binary search tree (BST).
 *     Assume a BST is defined as follows:
 *        1) The left subtree of a node contains only nodes with keys less than the node's key.
 *        2) The right subtree of a node contains only nodes with keys greater than the node's key.
 *        3) Both the left and right subtrees must also be binary search trees.
 *
 *     Example 1:Input:
 *                     2
 *                    / \
 *                   1   3
 *               Output: true
 *     Example 2:
 *                     5
 *                    / \
 *                   1   4
 *                      / \
 *                     3   6
 *               Output: false
 *                      Explanation: The input is: [5,1,4,null,null,3,6]. 
 *                          The root node's value is 5 but its right child's value is 4.
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
 
//My Method: Recursive
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValid(TreeNode root,long min, long max) {
        if(root == null){
            return true;
        }
        if(root.val <= min || root.val >= max){
            return false;
        }
        return isValid(root.left,min,root.val) && isValid(root.right, root.val, max);
    }
}

//Method 2: Iterative - 中序遍历法 [from leetcode discuss]
public boolean isValidBST(TreeNode root) {
    if(root == null){
        return true;
    }
    Stack<TreeNode> s = new Stack<TreeNode>();
    TreeNode pre = null;
    while(root != null || !s.isEmpty()){
        while(root != null){
            s.push(root);
            root = root.left;
        }
        root = s.pop();
        if(pre != null && root.val <= pre.val){
            return false;
        }
        pre = root;
        root = root.right;
    }
    return true;
}
