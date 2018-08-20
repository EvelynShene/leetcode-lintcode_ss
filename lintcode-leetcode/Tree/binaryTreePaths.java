/** 257. Binary Tree Paths(leetcode) / 480. Binary Tree Paths(lintcode)
 *    Given a binary tree, return all root-to-leaf paths.
 *
 *    Note: A leaf is a node with no children.
 *
 *    Example: Input:
 *                      1
 *                    /   \
 *                   2     3
 *                    \
 *                     5
 *
 *    Output: ["1->2->5", "1->3"]
 *            Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
 
//Method: DFS
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        if(root == null){
            return res;
        }
        String path = ""+ root.val;
        if(root.left == null && root.right == null){
            res.add(path);
            return res;
        }
        if(root.left != null){
            treePaths(root.left, path, res);
        }
        if(root.right != null){
            treePaths(root.right, path, res);
        }
        return res;
    }
    
    public void treePaths(TreeNode root, String path, List<String> res){
        StringBuilder s = new StringBuilder(path);
        s.append("->");
        s.append(""+root.val);
        if(root.left == null && root.right == null){
            res.add(s.toString());
            return;
        }
        if(root.left != null){
            treePaths(root.left, s.toString(), res);
        }
        if(root.right != null){
            treePaths(root.right, s.toString(), res);
        }
    }
}
