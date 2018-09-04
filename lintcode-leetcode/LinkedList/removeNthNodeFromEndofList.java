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
 
 /* Method 2: O(n) time and space complexity - one pass
  *      思路：1) 先从前往后顺序遍历到第n + 1个node, 如果 (n + 1) node 是 null,说明list里只有n个node，那么从后往前逆序,
  *           第n个node就是head; 删除第一个节点只需直接返回head.next;
  *           2) 如果不是(n + 1) node 不是 null, 设置pre从head开始，同时向后一步一步移动cur 和 pre指针，当cur == null时，
  *           pre指向的节点就是从后往前数第n个节点，删除pre并返回head即可.
  */
 public ListNode removeNthFromEnd(ListNode head, int n) {
     ListNode cur = head;
     int count = 1;
     while(count <= n){
         cur = cur.next;
         count++;
     }
     if(cur == null){
         return head.next;
     }
     ListNode pp = head;
     ListNode pre = head;
     while(cur != null){
         cur = cur.next;
         pp = pre;
         pre = pre.next;
     }
     pp.next = pre.next;      
     return head;
 }
