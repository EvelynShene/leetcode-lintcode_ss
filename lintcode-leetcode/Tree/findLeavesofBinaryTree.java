/** 366. Find Leaves of Binary Tree(leetcode - locked) / 650. Find Leaves of Binary Tree(lintcode)
 *      Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, 
 *   repeat until the tree is empty.
 *
 *      Example: Given binary tree:
 *                     1
 *                    / \
 *                   2   3
 *                  / \     
 *                 4   5    
 *               Returns [[4, 5, 3], [2], [1]].
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

//Method 1: 根据每个节点的高度/深度h可以得到这个节点应该属于List<List<Integer>>中的第h个list
public class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null){
            return res;
        }
        int depth = maxDepth(root, res);
        res.add(new ArrayList<Integer>());
        res.get(depth-1).add(root.val);
        return res;
    }
    
    public int maxDepth(TreeNode root, List<List<Integer>> res){
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left,res);
        int right = maxDepth(root.right,res);
        if(left > 0){
            if(res.size() < left){
                res.add(new ArrayList<Integer>());
            }
            res.get(left-1).add(root.left.val);
        }
        if(right > 0){
            if(res.size() < right){
                res.add(new ArrayList<Integer>());
            }
            res.get(right-1).add(root.right.val);
        }
        return Math.max(left, right) + 1;
    }
}

//More concise code
public class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        maxDepth(res, root);
        return res;
    }

    private int maxDepth(List<List<Integer>> list, TreeNode root) {
        if (root == null){
            return -1;
        }
        int left = maxDepth(list, root.left);
        int right = maxDepth(list, root.right);
        int cur = Math.max(left, right) + 1;
        if (list.size() == cur){
            list.add(new ArrayList<Integer>());
        }
        list.get(cur).add(root.val);
        return cur;
    }
}
