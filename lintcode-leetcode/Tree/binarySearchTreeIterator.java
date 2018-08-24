/** 173. Binary Search Tree Iterator(leetcode)/ 86. Binary Search Tree Iterator(lintcode)
 *     Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node 
 *  of a BST.
 *     Calling next() will return the next smallest number in the BST.
 *
 *     Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of 
 *  the tree.
 */
 
 //My Method: Inorder traverse
 /**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    Stack<TreeNode> s;
    public BSTIterator(TreeNode root) {
        s = new Stack<TreeNode>();
        TreeNode cur = root;
        while(cur != null){
            s.push(cur);
            cur = cur.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(s.isEmpty()){
            return false;
        }
        return true;
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode cur = s.pop();
        TreeNode right = cur.right;
        while(right != null){
            s.push(right);
            right = right.left;
        }
        return cur.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
