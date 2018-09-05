/** 147. Insertion Sort List(leetcode) / 173. Insertion Sort List(lintcode)
 *      Sort a linked list using insertion sort.
 *
 *      Example: Given 1->3->2->0->null, return 0->1->2->3->null.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 //My Method:
 class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode node = head.next;
        head.next = null;
        ListNode pre = dummy;
        while(node != null){
            ListNode next = node.next;
            pre = dummy;
            head = dummy.next;
            while(head != null && head.val < node.val){
                pre = head;
                head = head.next;
            }
            pre.next = node;
            node.next = head;
            node = next;
        }
        return dummy.next;
    }
}
