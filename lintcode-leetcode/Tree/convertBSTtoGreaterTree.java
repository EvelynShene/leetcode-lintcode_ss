/** 538. Convert BST to Greater Tree(leetcode) / 661. Convert BST to Greater Tree(lintcode)
 *      Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is 
 *  changed to the original key plus sum of all keys greater than the original key in BST.
 *
 *      Example: Input: The root of a Binary Search Tree like this:
 *                         5
 *                       /   \
 *                      2     13
 *               Output: The root of a Greater Tree like this:
 *                          18
 *                         /   \
 *                       20     13
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
 
//Method 1: 一开始没有注意到Binary search tree的特性，就当最普通tree处理了，用到了priorityQueue
class Solution {
    public TreeNode convertBST(TreeNode root) {
        if(root == null){
            return root;
        }
        
        PriorityQueue<TreeNode> queue = new PriorityQueue<TreeNode>(new Comparator<TreeNode>(){
            public int compare(TreeNode o1, TreeNode o2){
                return o2.val - o1.val;
            }
        });
        TreeNode cur = root;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        while(cur != null){
            stk.push(cur);
            cur = cur.left;
        }
        while(!stk.isEmpty()){
            cur = stk.pop();
            queue.offer(cur);
            cur = cur.right;
            while(cur != null){
                stk.push(cur);
                cur = cur.left;
            }
        }
        
        int add = 0;
        while(!queue.isEmpty()){
            cur = queue.poll();
            cur.val += add;
            add = cur.val;
        }
        return root;
    }
}

//Method 2: BST特性：一个节点的左子树节点的值比节点本身小；一个节点右子数的节点的值比节点本身大。 - O(n) time and space complexity
class Solution {
    public TreeNode convertBST(TreeNode root) {
        if(root == null){
            return root;
        }
        TreeNode cur = root;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        int add = 0;
        while(cur != null){
            stk.push(cur);
            cur = cur.right;
        }
        while(!stk.isEmpty()){
            cur = stk.pop();
            cur.val += add;
            add = cur.val;
            cur = cur.left;
            while(cur != null){
                stk.push(cur);
                cur = cur.right;
            }
        }
        return root;
    }
}
