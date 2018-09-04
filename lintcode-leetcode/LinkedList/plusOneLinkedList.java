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
 
 //Method:
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
