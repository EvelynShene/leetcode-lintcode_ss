/** 111. Minimum Depth of Binary Tree(leetcode) / 155. Minimum Depth of Binary Tree(lintcode)
 *      Given a binary tree, find its minimum depth.
 *      The minimum depth is the number of nodes along the shortest path from the root node down to the nearest 
 *   leaf node.
 *
 *      Note: A leaf is a node with no children.
 *
 *      Example: Given binary tree [3,9,20,null,null,15,7],
 *                     3
 *                    / \
 *                   9  20
 *                     /  \
 *                    15   7
 *                 return its minimum depth = 2.
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
 
//My Method: Use Queue - BFS
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int res = 0;
        Queue<TreeNode> path = new LinkedList<TreeNode>();
        path.offer(root);
        while(!path.isEmpty()){
            int size = path.size();
            res++;
            while(size > 0){
                TreeNode cur = path.poll();
                if(cur.left == null && cur.right == null){
                    return res;
                }
                if(cur.left != null){
                    path.offer(cur.left);
                }
                if(cur.right != null){
                    path.offer(cur.right);
                }
                size--;
            }
        }
        return res;
    }
}

//Method 2: Recursive
public int minDepth(TreeNode root) {
    if(root == null){
        return 0;
    }
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    if(left == 0 && right == 0){
        return 1;
    }
    if(left == 0 || right == 0){
        return left + right + 1;
    }
    else {
        return 1 + Math.min(left, right);
    }
}
