/** 382. Linked List Random Node(leetcode)
 *    Given a singly linked list, return a random node's value from the linked list. Each node must have the same 
 *  probability of being chosen.
 *
 *    Follow up:
 *        What if the linked list is extremely large and its length is unknown to you? Could you solve this
 *    efficiently without using extra space?
 *
 *    Example: // Init a singly linked list [1,2,3].
 *             ListNode head = new ListNode(1);
 *             head.next = new ListNode(2);
 *             head.next.next = new ListNode(3);
 *             Solution solution = new Solution(head);
 *
 *             // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 *             solution.getRandom();
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
//Method 1: 
class Solution {
    List<Integer> list;
    Random r;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        list = new ArrayList<Integer>();
        ListNode cur = head;
        while(cur != null){
            list.add(cur.val);
            cur = cur.next;
        }
        r = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int count = 0;
        int res = -1;
        for(int i = 0; i < list.size(); i++){
            count++;
            if(r.nextInt(count) == 0){
                res = list.get(i);
            }
        }
        return res;
    }
}

//Method 2: no extra space used
class Solution {
    ListNode node;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.node = head; 
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        Random r = new Random();
        int count = 0;
        int res = -1;
        ListNode cur = node;
        while(cur != null){
            count++;
            if(r.nextInt(count) == 0){
                res = cur.val;
            }
            cur = cur.next;
        }
        return res;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
 
