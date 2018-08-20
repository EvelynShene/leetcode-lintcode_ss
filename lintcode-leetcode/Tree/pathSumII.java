/** 113. Path Sum II(leetcode) / 376. Binary Tree Path Sum(lintcode)
 *      Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *  
 *      Note: A leaf is a node with no children.
 *
 *      Example: Given the below binary tree and sum = 22,
 *                             5
 *                            / \
 *                           4   8
 *                          /   / \
 *                         11  13  4
 *                        /  \    / \
 *                       7    2  5   1
 *                Return:
 *                       [
 *                          [5,4,11,2],
 *                          [5,8,4,5]
 *                       ]
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
 
//My Method: DFS
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null){
            return res;
        }
        List<Integer> s = new ArrayList<Integer>();
        dfs(root, res, s, sum);
        return res;
    }
    
    public void dfs(TreeNode root, List<List<Integer>> res, List<Integer> s, int sum){
        s.add(root.val);
        if(root.left == null && root.right == null){
            if(sum - root.val == 0){
                res.add(new ArrayList<Integer>(s));
            }
            s.remove(s.size() - 1);
            return;
        }
        if(root.left != null){
            dfs(root.left, res, s, sum - root.val);
        }
        if(root.right != null){
            dfs(root.right, res, s, sum - root.val);
        }
        s.remove(s.size() - 1);
    }
}
