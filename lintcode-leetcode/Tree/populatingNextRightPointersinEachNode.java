/** 116. Populating Next Right Pointers in Each Node(leetcode)
 *      Given a binary tree
 *           struct TreeLinkNode {
 *             TreeLinkNode *left;
 *             TreeLinkNode *right;
 *             TreeLinkNode *next;
 *           }
 *      Populate each next pointer to point to its next right node. If there is no next right node, 
 *  the next pointer should be set to NULL.
 *      Initially, all next pointers are set to NULL.
 *
 *      Note: 1. You may only use constant extra space.
 *            2. Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 *            3. You may assume that it is a perfect binary tree (ie, all leaves are at the same level, 
 *        and every parent has two children).
 *
 *      Example: Given the following perfect binary tree,
 *                          1
 *                        /  \
 *                       2    3
 *                      / \  / \
 *                     4  5  6  7
 *               After calling your function, the tree should look like:
 *                          1 -> NULL
 *                        /  \
 *                       2 -> 3 -> NULL
 *                      / \  / \
 *                     4->5->6->7 -> NULL
 */
 
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
 //My Method: Recursive:
 public void connect(TreeLinkNode root) {
    if(root == null){
        return;
    }
    if(root.left != null && root.right != null){
        root.left.next = root.right;
    }
    if(root.right != null && root.next != null){
        root.right.next = root.next.left;
    }
    connect(root.left);
    connect(root.right);
 }
 
 //Method 2: add next node level by level
 public void connect(TreeLinkNode root) {
    if(root == null){
        return;
    }
    TreeLinkNode level_node = root;
    while(level_node != null){
        TreeLinkNode cur =level_node;
        while(cur != null){
            if(cur.left != null && cur.right != null){
                cur.left.next = cur.right;
            }
            if(cur.right != null && cur.next != null){
                cur.right.next = cur.next.left;
            }
            cur = cur.next;
        }
        level_node = level_node.left;
    }    
 }
