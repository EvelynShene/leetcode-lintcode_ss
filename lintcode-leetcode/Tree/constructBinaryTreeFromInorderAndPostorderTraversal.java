/** 106. Construct Binary Tree from Inorder and Postorder Traversal(leetcode)
 *                          72. Construct Binary Tree from Inorder and Postorder Traversal(lintcode)
 *     Given inorder and postorder traversal of a tree, construct the binary tree.
 *  
 *     Note: You may assume that duplicates do not exist in the tree.
 *
 *     For example, given
 *                inorder = [9,3,15,20,7]
 *                postorder = [9,15,7,20,3]
 *
 *     Return the following binary tree:
 *                   3
 *                  / \
 *                 9  20
 *                   /  \
 *                  15   7
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = build(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
        return root;
    }
    
    public TreeNode build(int[] inorder, int[] postorder, int ostart, int oend, int pstart, int pend){
        if(ostart > oend){
            return null;
        }
        TreeNode root = new TreeNode(postorder[pend]);
        if(pend == pstart){
            return root;
        }
        int i = ostart;
        for(; i <= oend; i++){
            if(inorder[i] == postorder[pend]){
                break;
            }
        }
        root.left = build(inorder, postorder, ostart, i - 1, pstart, pstart + i - ostart - 1);
        root.right = build(inorder, postorder, i + 1, oend, pstart + i - ostart, pend - 1);
        return root;
    }
}
