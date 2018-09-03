/** 24. Swap Nodes in Pairs(leetcode) / 451. Swap Nodes in Pairs(lintcode)
 *      Given a linked list, swap every two adjacent nodes and return its head.
 *
 *      Example: Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 *      Note: 1) Your algorithm should use only constant extra space.
 *            2) You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 */
 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 //Method: 
 class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode prepre = null;
        ListNode pre = head;
        ListNode cur = null;
        ListNode next = null;
        while(pre != null && pre.next != null){
            cur = pre.next;
            next = cur.next;
            cur.next = pre;
            pre.next = next;
            if(prepre != null){
                prepre.next = cur;
            }
            else{
                head = cur;
            }
            prepre = pre;
            pre = next;
        }
        return head;
    }
}
