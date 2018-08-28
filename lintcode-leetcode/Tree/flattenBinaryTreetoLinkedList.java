/** 114. Flatten Binary Tree to Linked List(leetcode) / 453. Flatten Binary Tree to Linked List(lintcode)
 *      Given a binary tree, flatten it to a linked list in-place.
 *      For example, given the following tree:
 *                 1
 *                / \
 *               2   5
 *              / \   \
 *             3   4   6
 *         The flattened tree should look like:
 *             1
 *              \
 *               2
 *                \
 *                 3
 *                  \
 *                   4
 *                    \
 *                     5
 *                      \
 *                       6
 *
 *      Hint: If you notice carefully in the flattened tree, each node's right child points to the next node of 
 *          a pre-order traversal.
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
 public void flatten(TreeNode root) {
    if(root == null){
        return;
    }
    Stack<TreeNode> s = new Stack<TreeNode>();
    TreeNode cur = root;
    while(cur != null){
        if(cur.right != null){
            s.push(cur.right);
        }
        if(cur.left != null){
            cur.right = cur.left;
            cur.left = null;
        }
        else if(!s.isEmpty()){
            cur.right = s.pop();
        }
        cur = cur.right;
    }
 }
 
 //Method 2: Recursive
 class Solution {
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        flatten(left);
        flatten(right);
        root.left = null;
        root.right = left;
        
        TreeNode cur = root;
        while(cur.right != null){
            cur = cur.right;
        }
        cur.right = right;
    }
}
