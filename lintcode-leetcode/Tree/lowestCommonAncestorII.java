/** 474. Lowest Common Ancestor II(lintcode) - leetcode 236 简化版
 *      Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
 *      The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
 *      The node has an extra attribute parent which point to the father of itself. The root's parent is null.
 *
 *      Example: For the following binary tree:
 *                       4
 *                      / \
 *                     3   7
 *                        / \
 *                       5   6
 *               LCA(3, 5) = 4
 *               LCA(5, 6) = 7
 *               LCA(6, 7) = 7
 */

/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */
 
//My Method: Use HashSet
public class Solution {
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        if(root == null || A == root || B == root){
            return root;
        }
        Set<ParentTreeNode> set = new HashSet<ParentTreeNode>();
        while(A != null){
            set.add(A);
            A = A.parent;
        }
        while(!set.contains(B)){
            B = B.parent;
        }
        return B;
    }
}
