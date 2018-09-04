/** 142. Linked List Cycle II(leetcode) / 103. Linked List Cycle II(lintcode)
 *      Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 *      Note: Do not modify the linked list.
 *
 *      Follow up: Can you solve it without using extra space?
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 
 /* My Method:
  *   1) 先用双指针判断是否有环；
  *   2) 有环，则考虑fast和slow指针相遇时，假设从head到环起点的非环路径长为k，slow走过的环内路径为t, slow走的总路径长为(k + t);
  *   那么fast走过的路径长为【因为slow每次走一步，而fast走两步】；假设环周长为y, 那么fast实际走过的路径又可以表示为(k + t + y):
  *                   (k + t + y) = 2*(k + t) => y = k + t
  *   3) slow在环内还剩(y - t)路径又回到环起点，而由上公式：y - t = k; 正好是从head到环起点的非环路径长，所以此时把fast重新设为
  *   head，让fast和slow指针均每次走一步，最后它们会在环的起点相遇；返回相遇起点。
  */
 public ListNode detectCycle(ListNode head) {
    if(head == null || head.next == null){
        return null;
    }
    ListNode slow = head.next;
    ListNode fast = head.next.next;
    while(slow != null && fast != null){
        if(slow == fast){
            break;
        }
        slow = slow.next;
        fast = fast.next;
        if(fast != null){
            fast = fast.next;
        }
    }
    if(fast == null){
        return null;
    }
    fast = head;
    while(fast != slow){
        fast = fast.next;
        slow = slow.next;
    }
    return fast;
 }
