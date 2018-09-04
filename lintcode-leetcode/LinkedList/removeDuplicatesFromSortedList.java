/** 83. Remove Duplicates from Sorted List(leetcode) / 112. Remove Duplicates from Sorted List(lintcode)
 *      Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 *      Example: 2) Given 1->1->2, return 1->2.
 *               1) Given 1->1->2->3->3, return 1->2->3.
 */
 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 //Method 1:
 public ListNode deleteDuplicates(ListNode head) {
    if(head == null || head.next == null){
        return head;
    }
    ListNode cur = head;
    while(cur != null){
        ListNode next = cur.next;
        while(next != null && next.val == cur.val){
            cur.next = next.next;
            next = next.next;
        }
        cur = cur.next;
    }
    return head;
 }
 
 //Other:
 public ListNode deleteDuplicates(ListNode head) {
    if(head == null || head.next == null){
        return head;
    }
    ListNode cur = head;
    while(cur != null){
        ListNode next = cur.next;
        if(next != null && next.val == cur.val){
            cur.next = next.next;
            next = next.next;
        }
        else{
            cur = cur.next;
        }
    }
    return head;
 }
