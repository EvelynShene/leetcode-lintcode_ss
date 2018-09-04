/** 143. Reorder List(leetcode) / 99. Reorder List(lintcode)
 *      Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 *          reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 *      Note: You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 *      Example: Given 1->2->3->4->null, reorder it to 1->4->2->3->null.      
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 /* My Method: - 与 234. Palindrome Linked List 题 Method 2思路一致
  *     1) 先找到链表的中点：设置快慢两个指针，慢指针每次走1步，快指针每次走2步，等到快指针走完链表时，慢指针指向了中点位置；
  *     2) 将终点后的链表反转reverse，然后合并（开头->中点）和（中点->结尾）这两个链表。 
  *／
 class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(slow.next != null && fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode l2 = reverse(slow.next);
        slow.next = null;
        
        ListNode l1 = head;
        
        while(l2 != null){
            ListNode tmp = l1.next;
            l1.next = l2;
            l2 = l2.next;
            l1.next.next = tmp;
            l1 = tmp;
        }
    }
    
    public ListNode reverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = null;
        return pre;
    }
}
