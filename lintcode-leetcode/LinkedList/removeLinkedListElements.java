/** 203. Remove Linked List Elements(leetcode) / 452. Remove Linked List Elements(lintcode)
 *      Remove all elements from a linked list of integers that have value val.
 *
 *      Example: Given 1->2->3->3->4->5->3, val = 3, you should return the list as 1->2->4->5
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
 public ListNode removeElements(ListNode head, int val) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode pre = dummy;
    ListNode cur = head;
    while(cur != null){
        if(cur.val == val){
            pre.next = cur.next;
        }
        else{
            pre = cur;
        }
        cur = cur.next;
    }
    return dummy.next;
 }
