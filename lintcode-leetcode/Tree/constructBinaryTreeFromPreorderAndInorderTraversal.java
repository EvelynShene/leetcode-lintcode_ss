/** 105. Construct Binary Tree from Preorder and Inorder Traversal(leetcode) / 
 *                                        73. Construct Binary Tree from Preorder and Inorder Traversal(lintcode)
 *    Given preorder and inorder traversal of a tree, construct the binary tree.
 *    
 *    Note: You may assume that duplicates do not exist in the tree.
 *
 *    For example, given
 *               preorder = [3,9,20,15,7]
 *               inorder = [9,3,15,20,7]
 *    Return the following binary tree:
 *               3
 *              / \
 *             9  20
 *               /  \
 *              15   7
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
 //My Method: Recursive
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) { 
        TreeNode root = build(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
        return root;
    }
    
    public TreeNode build(int[] preorder, int[] inorder, int pstart, int pend, int ostart, int oend){
        if(pend < pstart){
            return null;
        }
        TreeNode root = new TreeNode(preorder[pstart]);
        if(pend == pstart){
            return root;
        }
        int i = ostart;
        for(; i <= oend; i++){
            if(inorder[i] == preorder[pstart]){
                break;
            }
        }
        root.left = build(preorder, inorder, pstart + 1, pstart + i - ostart, ostart, i - 1);
        root.right = build(preorder, inorder, pstart + i - ostart + 1, pend, i + 1, oend);
        return root;
    }
}
