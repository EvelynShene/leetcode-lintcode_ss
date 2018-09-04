/** 148. Sort List(leetcode) / 98. Sort List(lintcode)
 *      Sort a linked list in O(n log n) time using constant space complexity.
 *  
 *      Example: Given 1->3->2->null, sort it to 1->2->3->null.
 *
 *      Challenge: Solve it by merge sort & quick sort separately.
 */ 
 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 //Method 1: Merge Sort
 class Solution {
    public ListNode sortList(ListNode head) {
        head = mergeSortList(head);
        return head;
    }
    
    public ListNode mergeSortList(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        while(slow.next != null && fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // [head -> ... -> slow -> ... -> fast -> null / -> node -> null]
        
        ListNode h1 = mergeSortList(slow.next);
        slow.next = null;
        ListNode h2 = mergeSortList(head);
        
        return mergeList(h1,h2);
    }
    
    public ListNode mergeList(ListNode l1, ListNode l2){
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode h = dummy;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                h.next = l1;
                l1 = l1.next;
            }
            else{
                h.next = l2;
                l2 = l2.next;
            }
            h = h.next;
        }
        if(l1 != null){
            h.next = l1;
        }
        else{
            h.next = l2;
        }
        return dummy.next;
    }
}
