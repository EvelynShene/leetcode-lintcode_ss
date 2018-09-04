/** 234. Palindrome Linked List(leetcode) / 223. Palindrome Linked List(lintcode)
 *      Given a singly linked list, determine if it is a palindrome.
 *      
 *      Example: 1) Given 1->2->1, return true
 *               2) Given 1->2, return false
 *
 *      Follow up: Could you do it in O(n) time and O(1) space?
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 //Method 1: O(n) time and space complexity
 public boolean isPalindrome(ListNode head) {
    if(head == null || head.next == null){
        return true;
    }
    List<Integer> vals = new ArrayList<Integer>();
    while(head != null){
        vals.add(head.val);
        head = head.next;
    }

    int left = 0;
    int right = vals.size() - 1;
    while(left < right){
        if(!vals.get(left).equals(vals.get(right))){
            return false;
        }
        left++;
        right--;
    }
    return true;
 }
 
 /* Method 2: O(n) time and O(1) space complexity
  *    思路：1) 先找到链表的中点：设置快慢两个指针，慢指针每次走1步，快指针每次走2步，等到快指针走完链表时，慢指针指向了中点位置；
  *         2) 将终点后的链表反转reverse，然后比较（开头->中点）和（中点->结尾）这两部分是否相同，相同则返回true；否则返回false 
  */
 class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(slow.next != null && fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        slow.next = reverse(slow.next);
        
        fast = slow.next;
        slow = head;
        while(fast != null){
            if(fast.val != slow.val){
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
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
