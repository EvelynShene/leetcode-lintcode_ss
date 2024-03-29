/** 129. Sum Root to Leaf Numbers(leetcode) / 1353. Sum Root to Leaf Numbers(lintcode) 
 *    Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *    An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *    Find the total sum of all root-to-leaf numbers.
 *
 *    Note: A leaf is a node with no children.
 *
 *    Example: 1)Input: [1,2,3]
 *                 1
 *                / \
 *               2   3
 *              Output: 25
 *              Explanation: The root-to-leaf path 1->2 represents the number 12.
 *                           The root-to-leaf path 1->3 represents the number 13.
 *                           Therefore, sum = 12 + 13 = 25.
 *             2)Input: [4,9,0,5,1]
 *                   4
 *                  / \
 *                 9   0
 *                / \
 *               5   1
 *               Output: 1026
 *               Explanation: The root-to-leaf path 4->9->5 represents the number 495.
 *                            The root-to-leaf path 4->9->1 represents the number 491.
 *                            The root-to-leaf path 4->0 represents the number 40.
 *                            Therefore, sum = 495 + 491 + 40 = 1026.
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
    public int sumNumbers(TreeNode root) {
        if(root == null){
            return 0;
        }
        List<String> path = new ArrayList<String>();
        dfs(root, path, "");
        
        int res = 0;
        for(int i = 0; i < path.size(); i++){
            res += Integer.valueOf(path.get(i));
        }
        return res;
    }
    
    public void dfs(TreeNode root, List<String> paths, String prefix){
        StringBuilder str = new StringBuilder(prefix);
        str.append(""+root.val);
        if(root.left == null && root.right == null){
            paths.add(str.toString());
            return;
        }
        if(root.left != null){
            dfs(root.left, paths, str.toString());
        }
        if(root.right != null){
            dfs(root.right, paths, str.toString());
        }
    }
}

//Method 2: DFS - 直接加法
class Solution {
    public int sumNumbers(TreeNode root) {
        if(root == null){
            return 0;
        }        
        int res = 0;
        res += dfs(root, 0);
        return res;
    }
    
    public int dfs(TreeNode root, int sum){
        if(root.left == null && root.right == null){
            return sum + root.val;
        }
        int res = 0;
        if(root.left != null){
            res += dfs(root.left, (sum + root.val) * 10);
        }
        if(root.right != null){
            res += dfs(root.right, (sum + root.val) * 10);
        }
        return res;
    }
}
