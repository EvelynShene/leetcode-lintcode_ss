/** 25. Reverse Nodes in k-Group(leetcode) / 450. Reverse Nodes in k-Group(lintcode)
 *     Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *     k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes 
 *  is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 *     Example: Given this linked list: 1->2->3->4->5
 *                1) For k = 2, you should return: 2->1->4->3->5
 *                2) For k = 3, you should return: 3->2->1->4->5
 *
 *     Note: 1) Only constant extra memory is allowed.
 *           2) You may not alter the values in the list's nodes, only nodes itself may be changed.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 //My Method: k个一组处理，每个数最多会被遍历两次：分组时+反转时 =》 O(n^2) time and O(1) space complexity
 class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k <= 1){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = dummy;
        head = dummy;
        ListNode cur = head.next;
        while(cur != null){
            int count = 1;
            while(count <= k && cur != null){
                cur = cur.next;
                count++;
            }
            if(count == k + 1){
                tail = head.next;
                head.next = reverse(head.next, cur);
                head = tail;
            }
        }
        return dummy.next;
    }
    
    public ListNode reverse(ListNode head, ListNode end_node){
        if(head.next == end_node){
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != end_node){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = end_node;
        return pre;
    }
}
