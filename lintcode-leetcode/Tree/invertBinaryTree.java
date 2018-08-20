/** 226. Invert Binary Tree(leetcode) / 175. Invert Binary Tree(lintcode)
 *      Invert a binary tree.
 *
 *      Example: Input:
 *                        4
 *                      /   \
 *                     2     7
 *                    / \   / \
 *                   1   3 6   9
 *               Output:
 *                       4
 *                     /   \
 *                    7     2
 *                   / \   / \
 *                  9   6 3   1
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
  
//Method 1: Recursive - [leetcode 上 recursive method runtime 优于 iterative method]
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)){
            return root;
        }
        TreeNode tmp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = tmp;
        return root;
    }
}

//Method 2: Iterative 
public TreeNode invertTree(TreeNode root) {
    if(root == null){
        return root;
    }
    Stack<TreeNode> s = new Stack<TreeNode>();
    s.push(root);
    while(!s.isEmpty()){
        TreeNode cur = s.pop();
        if(cur == null || (cur.left == null && cur.right == null)){
            continue;
        }
        TreeNode tmp = cur.left;
        cur.left = cur.right;
        cur.right = tmp;
        s.push(cur.left);
        s.push(cur.right);
    }
    return root;
}
