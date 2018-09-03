/** 92. Reverse Linked List II(leetcode) / 36. Reverse Linked List II(lintcode)
 *     Reverse a linked list from position m to n. Do it in one-pass.
 *
 *     Note: 1 ≤ m ≤ n ≤ length of list.
 *
 *     Example: Input: 1->2->3->4->5->NULL, m = 2, n = 4
 *              Output: 1->4->3->2->5->NULL
 *
 *     Challenge: Reverse it in-place and in one-pass
 */
 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
//Method: O(n) time and O(1) space complexity
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m == n){
            return head;
        }

        ListNode cur = head;
        ListNode pre = new ListNode(-1);
        pre.next = head;
        int index = 1;
        while(cur != null && index < m){
            pre = cur;
            cur = cur.next;
            index++;
        }
        System.out.println(cur.val);
        ListNode left = cur;
        ListNode right = null;
        cur = cur.next;
        index++;
        while(cur != null && index <= n){
            right = cur.next;
            cur.next = left;
            left = cur;
            cur = right;
            index++;
        }
        
        pre.next.next = right;
        if(pre.next == head){
            return left;
        }
        pre.next = left;
        return head;
    }
}
 
