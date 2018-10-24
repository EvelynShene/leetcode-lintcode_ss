/** 543. Diameter of Binary Tree(leetcode) / 1181. Diameter of Binary Tree(lintcode)
 *      Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary 
 *  tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through 
 *  the root.
 *
 *      Example: Given a binary tree 
 *                     1
 *                    / \
 *                   2   3
 *                  / \     
 *                 4   5    
 *              Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *      
 *      Note: The length of path between two nodes is represented by the number of edges between them.
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
 
/* My Method: 
 *      Idea:对于每一个非叶子节点，经过这个节点的最长路径 = 左子树的高度 + 右子树的高度；
 *           所以遍历所有的非叶子节点，找到最大的最长路径
 */
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left_height = 0;
        if(root.left != null){
            left_height = 1 + findHeight(root.left);
        }
        
        int right_height = 0;
        if(root.right != null){
            right_height = 1 + findHeight(root.right);
        }
        
        int max = left_height + right_height;
        max = Math.max(max, diameterOfBinaryTree(root.left));
        max = Math.max(max, diameterOfBinaryTree(root.right));
        return max;
    }
    
    public int findHeight(TreeNode root){
        int left = 0;
        if(root.left != null){
            left = 1 + findHeight(root.left);
        }
        int right = 0;
        if(root.right != null){
            right = 1 + findHeight(root.right);
        }
        return Math.max(left, right);
    }
}


//Method 2: DFS - 思路同上，更简洁，运算更少，每个节点只会被访问一次: O(n) time and space complexity
class Solution {
    int max_path = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        findHeight(root);
        return max_path == 0? 0 : max_path - 1;
    }
    
    public int findHeight(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = findHeight(root.left);
        int right = findHeight(root.right);
        max_path = Math.max(max_path, left + right + 1);
        return Math.max(left + 1, right + 1);
    }
}
