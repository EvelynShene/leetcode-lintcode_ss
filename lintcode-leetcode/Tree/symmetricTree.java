/** 101. Symmetric Tree(leetcode) / 1360. Symmetric Tree(lintcode)
 *    Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *    
 *    For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *           1
 *          / \
 *         2   2
 *        / \ / \
 *       3  4 4  3
 *    But the following [1,2,2,null,3,null,3] is not:
 *           1
 *          / \
 *         2   2
 *          \   \
 *          3    3
 *
 *    Note: Bonus points if you could solve it both recursively and iteratively.
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
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        
        return symmrtricChild(root.left, root.right);
    }
    
    public boolean symmrtricChild(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }
        if(left == null || right == null){
            return false;
        }
        if(left.val != right .val){
            return false;
        }
        
        return (symmrtricChild(left.left, right.right) && symmrtricChild(left.right, right.left));
    }
}

//Method 2: Iterative
public boolean isSymmetric(TreeNode root) {
    if(root == null){
        return true;
    }
    if(root.left == null && root.right == null){
        return true;
    }
    if(root.left == null || root.right == null){
        return false;
    }
    Stack<TreeNode> s = new Stack<TreeNode>();
    s.push(root.left);
    s.push(root.right);
    while(!s.isEmpty()){
        TreeNode r = s.pop();
        TreeNode l = s.pop();
        if(l == null && r == null){
            continue;
        }
        if(l == null || r == null){
            return false;
        }
        if(l.val != r.val){
            return false;
        }
        else{
            s.push(l.left);
            s.push(r.right);
            s.push(r.left);
            s.push(l.right);
        }
    }
    return true;
}
