/** 235. Lowest Common Ancestor of a Binary Search Tree(leetcode)
 *      Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 *      According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes 
 *  p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant
 *  of itself).”
 *      Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *                       _______6______
 *                      /              \
 *                   ___2__          ___8__
 *                  /      \        /      \
 *                 0       _4       7       9
 *                        /  \
 *                        3   5
 *
 *      Example:1) Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 *                 Output: 6
 *                      Explanation: The LCA of nodes 2 and 8 is 6.
 *              2) Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 *                 Output: 2
 *                      Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself 
 *                                according to the LCA definition.
 *
 *      Note: 1) All of the nodes' values will be unique.
 *            2) p and q are different and both values will exist in the BST.
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
 
//Method 1: Recursive - 充分利用二分查找树(BST)的性质: 左子树的值 < 根的值 < 右子树的值
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        else if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        else{
            return root;
        }
    }
}

//Method 2: Iterative
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if(p.val > q.val){
        return lowestCommonAncestor(root, q, p);
    }
    while(p.val > root.val || q.val < root.val){
        if(p.val > root.val && q.val > root.val){
            root = root.right;
        }
        else{
            root = root.left;
        }
    }
    return root;
}
