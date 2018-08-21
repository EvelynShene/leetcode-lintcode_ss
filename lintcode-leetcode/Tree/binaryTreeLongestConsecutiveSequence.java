/** 298. Binary Tree Longest Consecutive Sequence(leetcode - locked) / 595. Binary Tree Longest Consecutive Sequence (lintcode)
 *    Given a binary tree, find the length of the longest consecutive sequence path.
 *    The path refers to any sequence of nodes from some starting node to any node in the tree along the 
 *  parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 *
 *    Example: 1) Input:
 *                    1
 *                     \
 *                      3
 *                     / \
 *                    2   4
 *                         \
 *                          5
 *              Longest consecutive sequence path is 3-4-5, so return 3.
 *              2) Input:
 *                    2
 *                     \
 *                      3
 *                     / 
 *                    2    
 *                   / 
 *                  1
 *              Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
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
 
//Method:
public class Solution {
    public int longestConsecutive(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        int res = 0;
        if(root.left != null){
            res = Math.max(res, dfs(root.left, root, 1));
        }
        if(root.right != null){
            res = Math.max(res, dfs(root.right, root, 1));
        }
        return res;
    }
    
    public int dfs(TreeNode cur,TreeNode parent, int max){
        int res = max;
        if(cur.val == parent.val + 1){
            max++;
        }
        else{
            max = 1;
        }
        res = Math.max(res,max);
        if(cur.left != null){
            res = Math.max(res, dfs(cur.left, cur, max));
        }
        if(cur.right != null){
            res = Math.max(res, dfs(cur.right, cur, max));
        }
        return res;
    }
}

//More concise:
public class Solution {
    public int longestConsecutive(TreeNode root) {
        return dfs(root, null, 0);
    }
    
    public int dfs(TreeNode cur,TreeNode parent, int max){
        if(cur == null){
            return max;
        }
        int res = max;
        if(parent != null && cur.val == parent.val + 1){
            max++;
        }
        else{
            max = 1;
        }
        res = Math.max(max, dfs(cur.left, cur, max));
        res = Math.max(res, dfs(cur.right, cur, max));
        return res;
    }
}
