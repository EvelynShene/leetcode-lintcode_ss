/** 61. Rotate List(leetcode) / 170. Rotate List(lintcode)
 *      Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 *      Example: 1) Input: 1->2->3->4->5->NULL, k = 2
 *                  Output: 4->5->1->2->3->NULL
 *                  Explanation:
 *                    rotate 1 steps to the right: 5->1->2->3->4->NULL
 *                    rotate 2 steps to the right: 4->5->1->2->3->NULL
 *               2) Input: 0->1->2->NULL, k = 4
 *                  Output: 2->0->1->NULL
 *                  Explanation:
 *                    rotate 1 steps to the right: 2->0->1->NULL
 *                    rotate 2 steps to the right: 1->2->0->NULL
 *                    rotate 3 steps to the right: 0->1->2->NULL
 *                    rotate 4 steps to the right: 2->0->1->NULL      
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
 public ListNode rotateRight(ListNode head, int k) {
    if(head == null || head.next == null || k == 0){
        return head;
    }
    int count = 1;
    ListNode cur = head;
    while(count <= k && cur != null){
        cur = cur.next;
        count++;
    }
    if(cur == null){
        if(count == k + 1){
            return head;
        }
        else{
            k %= (count - 1);
            if(k == 0){
                return head;
            }
            count = 1;
            cur = head;
            while(count <= k && cur != null){
                cur = cur.next;
                count++;
            }
        }
    }

    ListNode pre = head;
    while(cur.next != null){
        pre = pre.next;
        cur = cur.next;
    }
    cur.next = head;
    head = pre.next;
    pre.next = null;
    return head;
 }
