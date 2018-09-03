/** 206. Reverse Linked List(leetcode) / 35. Reverse Linked List(lintcode)
 *    Reverse a singly linked list.
 *   
 *    Example: Input: 1->2->3->4->5->NULL
 *             Output: 5->4->3->2->1->NULL
 *    
 *    Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
 
 //Method: iteratively
 public ListNode reverseList(ListNode head) {
    if(head == null){
        return head;
    }
    ListNode prev = null;
    ListNode next = head;
    while(head != null){
        next = head.next;
        head.next = prev;
        prev = head;
        head = next;
    }
    return prev;
 }
 
 //Method: recursively
 public ListNode reverseList(ListNode head) {
    if(head == null || head.next == null){
        return head;
    }
    ListNode next = head.next;
    next = reverseList(next);
    head.next.next = head;
    head.next = null;
    return next;
 }
