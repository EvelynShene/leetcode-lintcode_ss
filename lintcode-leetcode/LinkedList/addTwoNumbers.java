/** 2. Add Two Numbers(leetcode) / 167. Add Two Numbers(lintcode)
 *      You are given two non-empty linked lists representing two non-negative integers. The digits are stored 
 *  in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked
 *  list.
 *
 *      You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *  
 *      Example: Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *               Output: 7 -> 0 -> 8
 *                      Explanation: 342 + 465 = 807.
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
 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if(l1 == null || (l1.next == null && l1.val == 0)){
        return l2;
    }
    if(l2 == null || (l2.next == null && l2.val == 0)){
        return l1;
    }
    int c = 0;
    ListNode cur1 = l1;
    ListNode cur2 = l2;
    ListNode l3 = null;
    ListNode cur3 = null;
    while(cur1 != null && cur2 != null){
        int num = cur1.val + cur2.val + c;
        c = num / 10;
        num %= 10;
        if(l3 == null){
            l3 = new ListNode(num);
            cur3 = l3;
        }
        else{
            cur3.next = new ListNode(num);
            cur3 = cur3.next;
        }
        cur1 = cur1.next;
        cur2 = cur2.next;
    }
    while(cur1 != null){
        int num = cur1.val + c;
        c = num / 10;
        num %= 10;
        cur3.next = new ListNode(num);
        cur3 = cur3.next;
        cur1 = cur1.next;
    }
    while(cur2 != null){
        int num = cur2.val + c;
        c = num / 10;
        num %= 10;
        cur3.next = new ListNode(num);
        cur3 = cur3.next;
        cur2 = cur2.next;
    }
    if(c == 1){
        cur3.next = new ListNode(1);
    }
    return l3;
 }
 
 //More concise code
 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int c = 0;
    ListNode cur1 = l1;
    ListNode cur2 = l2;
    ListNode l3 = null;
    ListNode cur3 = null;
    while(cur1 != null || cur2 != null){
        int num = cur1 != null? cur1.val: 0;
        num += cur2 != null? cur2.val: 0;
        num += c;
        c = num / 10;
        num %= 10;
        if(l3 == null){
            l3 = new ListNode(num);
            cur3 = l3;
        }
        else{
            cur3.next = new ListNode(num);
            cur3 = cur3.next;
        }
        cur1 = cur1 != null? cur1.next: null;
        if(cur2 != null)
        cur2 = cur2 != null? cur2.next: null;
    }
    if(c == 1){
        cur3.next = new ListNode(1);
    }
    return l3;
 }
