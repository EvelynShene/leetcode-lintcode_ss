/** 222.  Count Complete Tree Nodes(leetcode) / 1317.  Count Complete Tree Nodes(lintcode)
 *    Given a complete binary tree, count the number of nodes.
 *
 *    Note: Definition of a complete binary tree from Wikipedia:
 *          In a complete binary tree every level, except possibly the last, is completely filled, 
 *       and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive 
 *       at the last level h.
 *
 *    Example: Input: 
 *                     1
 *                    / \
 *                   2   3
 *                  / \  /
 *                 4  5 6
 *             Output: 6
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
 
 //Method: 
 class Solution {
    public int countNodes(TreeNode root) {
        int res = 0;
        Integer lheight = null, rheight = null;
        while(root != null){
            // 得到左节点的最左深度
            int leftH = getH(root.left);
            // 得到右节点的最左深度
            int rightH = getH(root.right);
            // 左节点的最左深度大，说明右边已经残缺，结束点在左边
            if(leftH > rightH){
                if(rightH != 0) res += 1 << rightH;
                else return res + 2;
                root = root.left;
            // 否则说明结束点在右边
            } else {
                if(leftH != 0) res += 1 << leftH;
                else return res + 1;
                root = root.right;
            }
        }
        return res;
    }
    
    private int getH(TreeNode root){
        int h = 0;
        while(root != null){
            ++h;
            root = root.left;
        }
        return h;
    }
}
