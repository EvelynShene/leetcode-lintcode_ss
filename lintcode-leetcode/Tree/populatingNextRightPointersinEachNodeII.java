/** 117. Populating Next Right Pointers in Each Node II(leetcode)
 *      Given a binary tree
 *           struct TreeLinkNode {
 *             TreeLinkNode *left;
 *             TreeLinkNode *right;
 *             TreeLinkNode *next;
 *           }
 *      Populate each next pointer to point to its next right node. If there is no next right node, 
 *  the next pointer should be set to NULL.
 *
 *      Initially, all next pointers are set to NULL.
 *
 *      Note: 1) You may only use constant extra space.
 *            2) Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 *
 *      Example: Given the following binary tree,
 *                        1
 *                      /  \
 *                     2    3
 *                    / \    \
 *                   4   5    7
 *               After calling your function, the tree should look like:
 *                        1 -> NULL
 *                      /  \
 *                     2 -> 3 -> NULL
 *                    / \    \
 *                   4-> 5 -> 7 -> NULL
 */
 
 //My Method: 树不再是完美二叉树了！！
 public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null){
            return;
        }
        TreeLinkNode level_node = root;
        while(level_node != null){
            TreeLinkNode cur = level_node;
            while(cur != null){
                if(cur.left != null){
                    if(cur.right != null){
                        cur.left.next = cur.right;
                    }
                    else{
                        TreeLinkNode n = cur.next;
                        while(n != null && (n.left == null && n.right == null)){
                            n = n.next;
                        }
                        if(n != null){
                            if(n.left != null){
                                cur.left.next = n.left;
                            }
                            else{
                                cur.left.next = n.right;
                            }
                        }
                    }
                }
                if(cur.right != null){
                    TreeLinkNode n = cur.next;
                    while(n != null && (n.left == null && n.right == null)){
                        n = n.next;
                    }
                    if(n != null){
                        if(n.left != null){
                            cur.right.next = n.left;
                        }
                        else{
                            cur.right.next = n.right;
                        }
                    }
                }
                cur = cur.next;
            }
            while(level_node.left == null && level_node.right == null && level_node.next != null){
                level_node = level_node.next;
            }
            if(level_node.left != null){
                level_node = level_node.left;
            }
            else{
                level_node = level_node.right;
            } 
        }
    }
}

//一开始用recursive方法，但是recursive方法导致同一层的next节点分配可能不下一层某些节点分配的晚，从而出现错误。
