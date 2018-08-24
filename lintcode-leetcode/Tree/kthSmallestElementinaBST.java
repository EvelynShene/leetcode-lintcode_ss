/** 230. Kth Smallest Element in a BST(leetcode) / 902. Kth Smallest Element in a BST(lintcode)
 *      Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 *      Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 *      Example: 1) Input: root = [3,1,4,null,2], k = 1
 *                        3
 *                       / \
 *                      1   4
 *                       \
 *                        2
 *                  Output: 1
 *               2) Input: root = [5,3,6,2,4,null,null,1], k = 3
 *                        5
 *                       / \
 *                      3   6
 *                     / \
 *                    2   4
 *                   /
 *                  1
 *                  Output: 3
 *
 *      Follow up: What if the BST is modified (insert/delete operations) often and you need to find 
 *                    the kth smallest frequently? How would you optimize the kthSmallest routine?
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
 
//My Method: Inorder traverse
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode cur = root;
        while(cur != null){
            s.push(cur);
            cur = cur.left;
        }
        while(k > 0){
            cur = s.pop();
            k--;
            if(k == 0){
                break;
            }
            TreeNode right = cur.right;
            while(right != null){
                s.push(right);
                right = right.left;
            }
        }
        return cur.val;
    }
}

//Method 2: Divide and Conquer [from leetcode https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63743/Java-divide-and-conquer-solution-considering-augmenting-tree-structure-for-the-follow-up]
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int left = nodeCount(root.left);  // this value can be saved in the root node
        if(left + 1 == k) {
            return root.val;
        } else if (left + 1 < k) {
            return kthSmallest(root.right, k - left - 1);
        } else {
            return kthSmallest(root.left, k);
        }
    }

    private int nodeCount(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }
}
