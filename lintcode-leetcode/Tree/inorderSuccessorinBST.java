/** 448. Inorder Successor in BST(lintcode) / 285. Inorder Successor in BST(leetcode - locked)
 *      Given a binary search tree (See Definition) and a node in it, find the in-order successor of that node in 
 *  the BST.
 *      If the given node has no in-order successor in the tree, return null.
 *      
 *      Note: It's guaranteed p is one node in the given tree. 
 *              (You can directly compare the memory address to find p)
 *
 *      Example: 1) Given tree = [2,1] and node = 1:
 *                         2
 *                        /
 *                       1
 *                  return node 2.
 *               2) Given tree = [2,1,3] and node = 2:
 *                          2
 *                         / \
 *                        1   3
 *                  return node 3.
 *
 *      Challenge: O(h), where h is the height of the BST.
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
 
//My Method: 
public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if(root == null){
        return null;
    }
    if(p.right == null){
        TreeNode res = root;
        TreeNode cur = root;
        while(cur != p && cur != null){
            while(cur != null && cur.val > p.val){
                res = cur;
                cur = cur.left;
            }
            cur = cur.right;
        }
        if(res == p){
            return null;
        }
        return res;
    }
    TreeNode cur = p.right;
    while(cur.left != null){
        cur = cur.left;
    }
    return cur;
}

//Method 2: Recursive
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode left = inorderSuccessor(root.left, p);
            return (left != null) ? left : root;
        }
    }
}
