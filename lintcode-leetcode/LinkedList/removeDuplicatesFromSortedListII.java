/** 113. Remove Duplicates from Sorted List II(lintcode) / 82. Remove Duplicates from Sorted List II(leetcode)
 *      Given a sorted linked list, delete all nodes that have duplicate numbers, 
 *   leaving only distinct numbers from the original list.
 *
 *      Example: 1) Input: 1->2->3->3->4->4->5 ; Output: 1->2->5
 *               2) Input: 1->1->1->2->3 ; Output: 2->3
 */
 
 /* Method:
  *     用三个指针head、prev、cur来遍历链表，找到重复的元素并删除
  *     dummy是虚拟的链表头，初始化val为一个与原head节点的值肯定不一样的值；这样可以保证第一个元素一定是唯一不重复的。
  *   11234 和 12334 就可以在while循环中统一处理了
  */
   public ListNode deleteDuplicates(ListNode head) {
      // write your code here
      if(head == null || head.next == null){
          return head;
      }
      ListNode dummy = new ListNode(0 == head.val? 1: 0);
      dummy.next = head;
      ListNode prev = head;
      head = dummy;
      ListNode cur = prev.next;
      while(cur != null){
          while(cur != null && cur.val == prev.val){
              cur = cur.next;
          }
          if(prev.next != cur){
              head.next = cur;
          }
          else{
              head = prev;
          }
          if(cur == null){
              break;
          }
          prev = cur;
          cur = cur.next;
      }
      if(prev.next != cur){
          head.next = cur;
      }
      return dummy.next;
  }
