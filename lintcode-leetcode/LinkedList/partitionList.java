/** 86. Partition List(leetcode) / 96. Partition List(lintcode)
 *      Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater 
 *  than or equal to x.
 *
 *      You should preserve the original relative order of the nodes in each of the two partitions.
 *
 *      Example: Input: head = 1->4->3->2->5->2, x = 3
 *               Output: 1->2->2->4->3->5
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
 class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode cur = head;
        ListNode dummy = new ListNode(x - 1);
        dummy.next = head;
        ListNode mpre = dummy;
        ListNode mid = null;
        
        while(cur != null){
            if(cur.val < x){
                if(cur == mpre.next){
                    mpre = cur;
                    cur = cur.next;
                }
                else{
                    mid.next = cur.next;
                    cur.next = mpre.next;
                    mpre.next = cur;
                    mpre = mpre.next;
                    cur = mid.next;
                }
            }
            else{
                mid = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}

//Method 2: More easy and concise
class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode h1 = new ListNode(x-1);
        ListNode h2 = new ListNode(x);
        ListNode p1 = h1;
        ListNode p2 = h2;
        while(head != null){
            if(head.val < x){
                p1.next = head;
                p1 = head;
            }
            else{
                p2.next = head;
                p2 = head;
            }
            head = head.next;
        }
        p2.next = null;
        p1.next = h2.next;
        return h1.next;
    }
}
