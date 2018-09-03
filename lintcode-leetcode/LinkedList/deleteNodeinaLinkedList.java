/** 237. Delete Node in a Linked List(leetcode) / 372. Delete Node in a Linked List(lintcode)
 *      Write a function to delete a node (except the tail) in a singly linked list, given only access to that 
 *  node.
 *
 *      Example: Linked list is 1->2->3->4, and given node 3, delete the node in place 1->2->4
 *
 *      Note: 1) The linked list will have at least two elements.
 *            2) All of the nodes' values will be unique.
 *            3) The given node will not be the tail and it will always be a valid node of the linked list.
 *            4) Do not return anything from your function.
 */
 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 /* Method: 
  *    这题只给了要删除的节点node，并没有给list的头指针，所以可以把node节点变成node的下一个节点，将下一个节点删除
  */
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
