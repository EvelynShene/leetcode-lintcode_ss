/** 21. Merge Two Sorted Lists(leetcode) / 165. Merge Two Sorted Lists(lintcode)
 *      Merge two sorted linked lists and return it as a new list. The new list should be made by splicing 
 *  together the nodes of the first two lists.
 *
 *      Example: Input: 1->2->4, 1->3->4
 *               Output: 1->1->2->3->4->4
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
 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if(l1 == null){
        return l2;
    }
    if(l2 == null){
        return l1;
    }
    ListNode c1 = l1;
    ListNode c2 = l2;
    ListNode dummy = new ListNode(0);
    ListNode c3 = dummy;
    while(c1 != null && c2 != null){
        if(c1.val > c2.val){
            c3.next = c2;
            c2 = c2.next;
        }
        else{
            c3.next = c1;
            c1 = c1.next;
        } 
        c3 = c3.next;
    }
    if(c1 == null){
        c3.next = c2;
    }
    else{
        c3.next = c1;
    }
    return dummy.next;
 }
 
 //Method 2: Recursive
 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if(l1 == null){
        return l2;
    }
    if(l2 == null){
        return l1;
    }
    if(l1.val < l2.val){
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    }
    else{
        l2.next = mergeTwoLists(l1,l2.next);
        return l2;
    }
 }
