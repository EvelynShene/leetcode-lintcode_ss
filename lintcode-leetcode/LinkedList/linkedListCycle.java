/** 141. Linked List Cycle (leetcode)/ 102. Linked List Cycle(lintcode)
 *      Given a linked list, determine if it has a cycle in it.
 *
 *      Example: Given -21->10->4->5, tail connects to node index 1, return true
 *
 *      Follow up: Can you solve it without using extra space?
 */
 
 //Method 1: Use Set - O(n) time and space complexity
 public boolean hasCycle(ListNode head) {
    if(head == null || head.next == null){
        return false;
    }
    Set<ListNode> nodes = new HashSet<ListNode>();
    ListNode cur = head;
    while(cur != null){
        if(nodes.contains(cur)){
            return true;
        }
        nodes.add(cur);
        cur = cur.next;
    }
    return false;
 }
 
 /* Method 2: Two pointers - O(n) time and O(1) space complexity
  *      The fast runner moves 2 steps while the slow runner moves 1 steps at a time.
  */
 
 public boolean hasCycle(ListNode head) {
    if(head == null || head.next == null){
        return false;
    }

    ListNode p1 = head.next;
    ListNode p2 = head.next.next;
    while(p1 != null && p2 != null){
        if(p1 == p2){
            return true;
        }
        p1 = p1.next;
        p2 = p2.next;
        if(p2 != null){
            p2 = p2.next;
        }
    }
    return false;
 }
