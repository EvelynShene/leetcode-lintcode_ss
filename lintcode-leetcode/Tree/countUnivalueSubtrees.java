/** 250. Count Univalue Subtrees(leetcode - locked) / 921. Count Univalue Subtrees(lintcode)
 *    Given a binary tree, count the number of uni-value subtrees.
 *    A Uni-value subtree means all nodes of the subtree have the same value.
 *
 *    Example: Given root = {5,1,5,5,5,#,5}, return 4.
 *                       5
 *                      / \
 *                     1   5
 *                    / \   \
 *                   5   5   5
 */
 
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
 
//My Method: 
public class Solution {
    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        countUnival(root);
        return count;
    }
    
    public int countUnival(TreeNode root){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            count++;
            return 1;
        }
        int left = countUnival(root.left);
        int right = countUnival(root.right);
        if(left == -1 || right == -1){
            return -1;
        }
        if(left != 0 && right != 0){
            if(root.val == root.left.val && root.val == root.right.val){
                count++;
                return left + right + 1;
            }
            else{
                return -1;
            }
        }
        if((left != 0 && root.val == root.left.val) || (right != 0 && root.val == root.right.val)){
            count++;
            return left + right + 1;
        }
        else{
            return -1;
        }
    }
}
