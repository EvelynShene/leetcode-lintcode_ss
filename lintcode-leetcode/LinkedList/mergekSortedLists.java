/** 23. Merge k Sorted Lists(leetcode) / 104. Merge k Sorted Lists(lintcode)
 *      Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *      
 *      Example: Input:
 *                   [
 *                     1->4->5,
 *                     1->3->4,
 *                     2->6
 *                   ]
 *               Output: 1->1->2->3->4->4->5->6
 */
 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 /* Method 1: Merge Sort
  *     complexity: 设最长list有m个节点，一共有n个list:
  *           1) merge2Lists 需要 O(m) time complexity
  *           2) 外层for循环 (n-1) 次，所以一共需要 O(mn) time complexity
  */
 class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;   
        }
        if(lists.length == 1){
            return lists[0];
        }
        
        for(int i = 1; i < lists.length; i++){
            lists[0] = merge2Lists(lists[0],lists[i]);
        }
        return lists[0];
    }
    
    public ListNode merge2Lists(ListNode l1, ListNode l2){
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode l3 = new ListNode(0);
        ListNode cur = l3;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }
            else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if(l1 != null){
            cur.next = l1;
        }
        else{
            cur.next = l2;
        }
        return l3.next;
    }
}

/* Method 2: Use Heap(PriorityQueue) 
 *    complexity: 设一共有node个节点，一共有n个list:
 *        每个节点只会被访问两次，进堆和出堆，所以是 O(node) time complexity; 需要O(n) space complexity (heap)
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;   
        }
        if(lists.length == 1){
            return lists[0];
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
            public int compare(ListNode l1, ListNode l2){
                return l1.val - l2.val;
            }
        });
        ListNode res = new ListNode(0);
        for(int i = 0; i < lists.length; i++){
            if(lists[i] != null){
                heap.offer(lists[i]);
            }
        }
        ListNode cur = res;
        while(!heap.isEmpty()){
            ListNode min = heap.poll();
            cur.next = min;
            if(min.next != null){
                heap.offer(min.next);
            }
            cur = cur.next;
        }
        return res.next;
    }
}
