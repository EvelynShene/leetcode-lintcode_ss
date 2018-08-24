/** 109. Convert Sorted List to Binary Search Tree(leetcode) / 106. Convert Sorted List to Binary Search Tree(lintcode)
 *    Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *    For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two 
 *  subtrees of every node never differ by more than 1.
 *
 *    Example: Given the sorted linked list: [-10,-3,0,5,9],
 *          One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *                       0
 *                      / \
 *                    -3   9
 *                    /   /
 *                  -10  5
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
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
 
//Method 1: O(n) time and space complexity
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null){
            return null;
        }
        List<Integer> value = new ArrayList<Integer>();
        while(head != null){
            value.add(head.val);
            head = head.next;
        }
        return toBST(value, 0, value.size());
    }
    
    public TreeNode toBST(List<Integer> value, int start, int end){
        if(start >= end){
            return null;  
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(value.get(mid));
        root.left = toBST(value, start, mid);
        root.right = toBST(value, mid+1, end);
        return root;
    }
}

//Method 2: O(n) time and O(1) space complexity
class Solution {
    private ListNode node;
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null){
            return null;
        }
        int size = 0;
        node = head;
        while(head != null){
            size++;
            head = head.next;
        }
        return toBST(0, size-1);
    }
    
    public TreeNode toBST(int start, int end){
        if(start > end){
            return null; 
        }
        int mid = (start + end) / 2;
        
        TreeNode left = toBST(start, mid-1);
        TreeNode root = new TreeNode(node.val);
        root.left = left;
        node = node.next;
        root.right = toBST(mid+1, end);
        return root;
    }
}
