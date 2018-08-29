/** 156. Binary Tree Upside Down(leetcode - locked) / 649. Binary Tree Upside Down(lintcode)
 *      Given a binary tree where all the right nodes are either leaf nodes with a sibling 
 *  (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where 
 *  the original right nodes turned into left leaf nodes. Return the new root.
 *
 *      Example: Given a binary tree {1,2,3,4,5}
 *                         1
 *                        / \
 *                       2   3
 *                      / \
 *                     4   5
 *               return the root of the binary tree {4,5,2,#,#,3,1}.
 *                        4
 *                       / \
 *                      5   2
 *                         / \
 *                        3   1  
 */
 
 /** 理解题意：
  *       右节点一定是有左兄弟节点的叶子节点或者为空，说明整个树偏左，限制了右节点要么为空要么一定会有对应的左节点。
  *   上下颠倒后原来二叉树的最左子节点变成了根节点，其对应的右节点变成了其左子节点，其父节点变成了其右子节点，相当于顺时针旋转了一下。
  *     递归：对于一个根节点来说，目标是将其左子节点变为根节点，右子节点变为左子节点，原根节点变为右子节点。
  *         1）那么首先判断这个根节点是否存在，且其有没有左子节点，如果不满足这两个条件的话，直接返回即可，不需要翻转操作。
  *         2）不停的对左子节点调用递归函数，直到到达最左子节点开始翻转，翻转好最左子节点后，开始回到上一个左子节点继续翻转即可，
  *        直至翻转完整棵树，
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

 //Method:
 public TreeNode upsideDownBinaryTree(TreeNode root) {
    if(root == null || root.left == null){
        return root;
    }
    TreeNode left = root.left;
    TreeNode right = root.right;
    TreeNode new_root = upsideDownBinaryTree(left);
    left.left = right; 
    left.right = root;
    root.left = null;
    root.right = null;
    return new_root;
 }
