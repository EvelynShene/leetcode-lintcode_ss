/** 900. Closest Binary Search Tree Value(lintcode) / 270. Closest Binary Search Tree Value(leetcode)
 *      Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the 
 *  target.
 *
 *      Note: 1) Given target value is a floating point.
 *            2) You are guaranteed to have only one unique value in the BST that is closest to the target.
 *
 *      Example: Given root = {1}, target = 4.428571, return 1.
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
 
 //My Method: Recursive
 public int closestValue(TreeNode root, double target) {
    if(root.left == null && root.right == null){
        return root.val;
    }
    int left = 0;
    int right = 0;
    if(root.left != null){
        left = closestValue(root.left, target);
    }
    if(root.right != null){
        right = closestValue(root.right, target);
    }

    if(Math.abs(root.val - target) < Math.abs(left - target) && Math.abs(root.val - target) < Math.abs(right - target)){
        return root.val;
    }
    else if(Math.abs(left - target) < Math.abs(root.val - target) && Math.abs(left - target) < Math.abs(right - target)){
        return left;
    }
    else{
        return right;
    }
 }
 
 //Method 2: O(h) complexity - 找target的上下界
 public int closestValue(TreeNode root, double target) {
    if(root.left == null && root.right == null){
        return root.val;
    }
    TreeNode left = root;
    TreeNode right = root;
    if(root.val > target){
        while(left != null){
            while(left != null && left.val > target){
                right = left;
                left = left.left;
            }
            if(left != null && left.right != null ){
                left = left.right;
            }
            else{
                break;
            }
        }
    }
    else{
        while(right != null){
            while(right != null && right.val <= target){
                left = right;
                right = right.right;
            }
            if(right != null && right.left != null){
                right = right.left;
            }
            else{
                break;
            }
        }
    }

    if(right == null){
        return left.val;
    }
    if(left == null){
        return right.val;
    }
    if(Math.abs(left.val - target) < Math.abs(right.val - target)){
        return left.val;
    }
    else{
        return right.val;
    }
 }
