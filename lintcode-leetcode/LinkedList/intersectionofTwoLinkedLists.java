/** 160. Intersection of Two Linked Lists(leetcode) / 380. Intersection of Two Linked Lists(lintcode)
 *      Write a program to find the node at which the intersection of two singly linked lists begins.
 *      
 *      For example, the following two linked lists:
 *                 A:          a1 → a2
 *                                    ↘
 *                                      c1 → c2 → c3
 *                                    ↗            
 *                 B:     b1 → b2 → b3
 *          begin to intersect at node c1.
 *      
 *      Note:
 *          1) If the two linked lists have no intersection at all, return null.
 *          2) The linked lists must retain their original structure after the function returns.
 *          3) You may assume there are no cycles anywhere in the entire linked structure.
 *          4) Your code should preferably run in O(n) time and use only O(1) memory.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 
 //Method 1: O(m+n) time and O(m) or O(n) space complexity
 public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode cur1 = headA;
        Set<ListNode> set = new HashSet<ListNode>();
        while(cur1 != null){
            set.add(cur1);
            cur1 = cur1.next;
        }
        cur1 = headB;
        while(cur1 != null){
            if(set.contains(cur1)){
                return cur1;
            }
            cur1 = cur1.next;
        }
        
        return null;
    }
}

/* Method 2: O(mn) time and O(1) space complexity
 *     思路：先考虑如果两个链长度相同的话，那么对应的一个个比下去就能找到。所以只需要把长链表变短即可。
 *       =》分别遍历两个链表，得到分别对应的长度。然后求长度的差值，把较长的那个链表砍掉前面差值个节点，然后一一比较即可。
 */
 public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if(headA == null || headB == null){
        return null;
    }
    int len1 = 0;
    ListNode cur1 = headA;
    while(cur1 != null){
        len1++;
        cur1 = cur1.next;
    }
    int len2 = 0;
    ListNode cur2 = headB;
    while(cur2 != null){
        len2++;
        cur2 = cur2.next;
    }
    cur1 = headA;
    cur2 = headB;
    while(len1 > len2){
        cur1 = cur1.next;
        len1--;
    }
    while(len2 > len1){
        cur2 = cur2.next;
        len2--;
    }

    while(cur1 != null && cur1 != cur2){
        cur1 = cur1.next;
        cur2 = cur2.next;
    }
    return cur1;
 }

/* Method 3: Use Two Pointer - O(mn) time and O(1) space complexity
 *    用环的思路来做：让两条链表分别从各自的开头开始往后遍历，当其中一条遍历到末尾时，将它跳到另一个条链表的开头继续遍历。
 *    两个指针最终会相等，相遇有两种情况：a) 一种情况是在交点处相遇，b)另一种情况是在各自的末尾的空节点处相等。
 *   【 因为两个指针走过的路程相同，是两个链表的长度之和，所以一定会相等。】
 */
 
 public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if(headA == null || headB == null){
        return null;
    }

    ListNode cur1 = headA;
    ListNode cur2 = headB;
    while(cur1 != cur2){
        if(cur1 == null){
            cur1 = headB;
        }
        else{
            cur1 = cur1.next;
        }
        if(cur2 == null){
            cur2 = headA;
        }
        else{
            cur2 = cur2.next;
        }
    }
    return cur1;
 }
