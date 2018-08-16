/** 102. Binary Tree Level Order Traversal(leetcode) / 69. Binary Tree Level Order Traversal(lintcode)
 *      Given a binary tree, return the level order traversal of its nodes' values. 
 *  (ie, from left to right, level by level).
 *
 *      For example: Given binary tree [3,9,20,null,null,15,7],
 *                           3
 *                          / \
 *                         9  20
 *                           /  \
 *                          15   7
 *                   return its level order traversal as:
 *                         [
 *                           [3],
 *                           [9,20],
 *                           [15,7]
 *                         ]   
 *
 *      Challenge: 1) Challenge 1: Using only 1 queue to implement it.
 *                 2) Challenge 2: Use DFS algorithm to do it.
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
 
//Method 1: Use Two Queues
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if(root == null){
        return res;
    }
    Queue<TreeNode> q1 = new LinkedList<TreeNode>();
    q1.offer(root);
    while(!q1.isEmpty()){
        List<Integer> l = new ArrayList<Integer>();
        Queue<TreeNode> q2 = new LinkedList<TreeNode>();
        while(!q1.isEmpty()){
            TreeNode cur = q1.poll();
            l.add(cur.val);
            if(cur.left != null){
                q2.offer(cur.left);
            }
            if(cur.right != null){
                q2.offer(cur.right);
            }
        }
        res.add(l);
        q1 = q2;    
    }
    return res;
}

//Method 2: Use one queue
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if(root == null){
        return res;
    }
    Queue<TreeNode> q1 = new LinkedList<TreeNode>();
    q1.offer(root);
    int size = 1;
    while(size != 0){
        List<Integer> l = new ArrayList<Integer>();
        while(size != 0){
            TreeNode cur = q1.poll();
            size--;
            l.add(cur.val);
            if(cur.left != null){
                q1.offer(cur.left);
            }
            if(cur.right != null){
                q1.offer(cur.right);
            }
        }
        res.add(l);
        size = q1.size();   
    }
    return res;
}

//Method 3: Use DFS
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null){
            return res;
        }
        List<Integer> l = new ArrayList<Integer>();
        l.add(root.val);
        res.add(l);
        if(root.left != null){
            dfs(res, 1, root.left);
        }
        if(root.right != null){
            dfs(res, 1, root.right);
        }
        return res;
    }
    
    public void dfs(List<List<Integer>> res,int level, TreeNode root){
        List<Integer> l = new ArrayList<Integer>();
        if(level < res.size()){
            l = res.get(level);
        }
        l.add(root.val);
        if(level >= res.size()){
            res.add(l);
        }
        if(root.left != null){
            dfs(res, level + 1, root.left);
        }
        if(root.right != null){
            dfs(res, level + 1, root.right);
        }
    }
}
