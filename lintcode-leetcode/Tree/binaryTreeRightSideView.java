/** 199. Binary Tree Right Side View(leetcode)
 *      Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you
 *  can see ordered from top to bottom.
 *
 *      Example:1)Input: [1,2,3,null,5,null,4]
 *                Output: [1, 3, 4]
 *                  Explanation:
 *                              1            <---
 *                            /   \
 *                           2     3         <---
 *                            \     \
 *                             5     4       <---
 *              2)Input: [1,2,3,null,4]
 *                Output: [1, 3, 4]
 *                  Explanation:
 *                              1            <---
 *                            /   \
 *                           2     3         <---
 *                            \     
 *                             4             <---
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

//My Method: BFS - O(n) time and space complexity
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            TreeNode cur = new TreeNode(0);
            while(size > 0){
                cur = q.poll();
                if(cur.left != null){
                    q.offer(cur.left);
                }
                if(cur.right != null){
                    q.offer(cur.right);
                }
                size--;
            }
            res.add(cur.val);
        }
        return res;
    }
}
