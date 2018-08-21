/** 337.  House Robber III(leetcode) / 535.  House Robber III(lintcode)
 *     The thief has found himself a new place for his thievery again. There is only one entrance to this area, 
 *  called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart 
 *  thief realized that "all houses in this place forms a binary tree". It will automatically contact the police 
 *  if two directly-linked houses were broken into on the same night.
 *     Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 *     Example:1)Input: [3,2,3,null,3,null,1]
 *                        3
 *                       / \
 *                      2   3
 *                       \   \ 
 *                        3   1
 *               Output: 7 
 *                    Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *             2)Input:[2,1,3,null,4,null,null]
 *                        2
 *                       / \
 *                      1   3
 *                       \    
 *                        4   
 *               Output: 7 
 *                    Explanation: Maximum amount of money the thief can rob = 3 + 4 = 7.
 *            
 */
[leetcode idea: https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem]
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */ 
 
//Method 1: Very slow - AC in leetcode but TLE in lintcode
class Solution {
    public int rob(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(robRoot(root), notRobRoot(root));
    }
    
    public int robRoot(TreeNode root){
        if(root == null){
            return 0;
        }
        return notRobRoot(root.left) + notRobRoot(root.right) + root.val;
    }
    
    public int notRobRoot(TreeNode root){
        if(root == null){
            return 0;
        }
        return rob(root.left) + rob(root.right);
    } 
}
 
//Method 2: Use HashMap
class Solution {
    public int rob(TreeNode root) {
        if(root == null){
            return 0;
        }
        Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
        return subRob(root,map);
    }
    
    public int subRob(TreeNode root, Map<TreeNode, Integer> map){
        if(root == null){
            return 0;
        }
        if(map.containsKey(root)){
            return map.get(root);
        }
        int val = 0;
        if(root.left != null){
            val += subRob(root.left.left, map) + subRob(root.left.right, map);
        }
        if(root.right != null){
            val += subRob(root.right.left, map) + subRob(root.right.right, map);
        }
        val = Math.max(val + root.val, subRob(root.left, map) + subRob(root.right, map));
        map.put(root,val);
        return val;
    }
}
