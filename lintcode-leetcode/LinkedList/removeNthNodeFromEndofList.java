/** 19. Remove Nth Node From End of List(leetcode) / 174. Remove Nth Node From End of List(lintcode)
 *      Given a linked list, remove the n-th node from the end of list and return its head.
 *
 *      Example: Given linked list: 1->2->3->4->5, and n = 2.
 *               After removing the second node from the end, the linked list becomes 1->2->3->5.
 *      
 *      Note: Given n will always be valid.
 *
 *      Follow up: Could you do this in one pass?
 */
 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 //Method 1: O(n) time complexity
 public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode cur = head;
    int len = 0;
    while(cur != null){
        len++;
        cur = cur.next;
    }
    int index = len - n + 1;
    ListNode pre = head;
    cur = head;
    while(index > 1){
        pre = cur;
        cur = cur.next;
        index--;
    }
    if(pre == cur){
        return pre.next;
    }
    pre.next = cur.next;
    return head;
 }
 
 //Method 2:
 
