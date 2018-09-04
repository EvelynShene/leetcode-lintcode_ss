/** 369. Plus One Linked List(leetcode) / 904. Plus One Linked List(lintcode)
 *      Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the 
 *  integer.
 *      You may assume the integer do not contain any leading zero, except the number 0 itself.
 *      The digits are stored such that the most significant digit is at the head of the list.
 *
 *      Example: Given head = 1 -> 2 -> 3 -> null, return 1 -> 2 -> 4 -> null.
 *
 *      理解题意：用一个列表表示一个数，如例子用链表表示数字123，加1变成124.
 */

/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

 /* My Method:
  *   1）先找到第一个不为9的数字（pre指向），如果找不到，可以直接将最后一位数+1；
  *   2）找到后，pre记录第一个不为9的数字位置，然后cur向后查看到第一个不为9的数，此时pre.next -> cur.parent之间都是9：
  *    a) 若cur == null, 说明这个数以..9结尾，把pre的值+1，pre后面所有的数变成0；
  *     【这里返回值时，要考虑这个数只包含9，如99，这样需要开头多添加一位，这里使用dummy指针指向head，初始化为0，如果pre == dummy,
  *      则返回dummy】
  *    b) 若cur != null, 说明这个9..9结构在中间，后面还有数字，那么重复上述过程，继续找剩下部分数字中第一个不为9的数。
  *   3）遍历到链表最后，返回head。
  */
 public ListNode plusOne(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode cur = head;
    ListNode pre = dummy;
    while(cur != null){
        while(cur != null && cur.val != 9){
            pre = cur;
            cur = cur.next;
        }
        if(cur == null){
            pre.val++;
            break;
        }
        else{
            while(cur != null && cur.val == 9){
                cur = cur.next;
            }
            if(cur == null){
                pre.val++;
                cur = pre.next;
                while(cur != null){
                    cur.val = 0;
                    cur = cur.next;
                }
                if(pre == dummy){
                    return dummy;
                }
            }
        }
    }
    return head;
 }
