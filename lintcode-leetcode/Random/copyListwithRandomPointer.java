/** 138. Copy List with Random Pointer(leetcode) / 105. Copy List with Random Pointer(lintcode)
 *      A linked list is given such that each node contains an additional random pointer which could point to any
 *  node in the list or null.
 *      Return a deep copy of the list.
 *
 *      Challenge: Could you solve it with O(1) space?
 *
 *      理解题意: 复制的时候不仅要复制next指针，还要复制random指针；而random指针是不确定的，可能在逐个next指针复制的过程中，
 *            random指针指向的节点还没有遍历到，所以需要先将所有的指针复制完，再考虑复制random指针的指向。
 */

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
 
 /* Method 1: Use HashMap - O(n) time and space compleixty
  *    Idea: 复制的时候把复制的结点做一个HashMap，以旧结点为key，新节点为value。这么做的目的是为了第二遍扫描的时候,
  *         按照这个哈希表把结点的随机指针接上
  */
 public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null){
            return null;
        }
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        while(cur != null){
            RandomListNode next = new RandomListNode(cur.label);
            map.put(cur, next);
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            if(cur.next != null){
                map.get(cur).next = map.get(cur.next);
            }
            if(cur.random != null){
                map.get(cur).random = map.get(cur.random);
            }
            cur = cur.next;
        }
        return map.get(head);
    }
}

/* Method 2: O(n) time and O(1) space complexity
 *    Idea: 对链表进行三次扫描:
 *          1)第一次扫描对每个结点进行复制，然后把复制出来的新节点接在原结点的next，也就是让链表变成一个重复链表，就是新旧更替；
 *          2) 第二次扫描中把旧结点的随机指针赋给新节点的随机指针，因为新结点都跟在旧结点的下一个，所以赋值比较简单，就是:
 *              node.next.random = node.random.next
 *            其中node.next就是新结点，因为第一次扫描我们就是把新结点接在旧结点后面。
 *          3)最后一次扫描把链表拆成两个，第一个还原原链表，而第二个就是要求的复制链表。
 *            因为现在链表是旧新更替，只要把每隔两个结点分别相连，对链表进行分割即可。
 *      这个方法总共进行三次线性扫描，所以时间复杂度是O(n)。而这里并不需要额外空间，所以空间复杂度是O(1)。
 */
 
public RandomListNode copyRandomList(RandomListNode head) {
    if(head == null){
        return null;
    }

    RandomListNode cur = head;
    while(cur != null){
        RandomListNode newNode = new RandomListNode(cur.label);
        RandomListNode next = cur.next;
        cur.next = newNode;
        newNode.next = next;
        cur = next;
    }
    //the list becomes oldNode -> newNode -> oldNode -> newNode ->....
    cur = head;
    while(cur != null){
        RandomListNode newNode = cur.next;
        if(cur.random != null){
            newNode.random = cur.random.next;
        }
        cur = newNode.next;
    }

    //remove oldNode;
    RandomListNode res = head.next;
    cur = head;
    while(cur != null){
        RandomListNode newNode = cur.next;
        cur.next = newNode.next;
        if(newNode.next != null){
            newNode.next = newNode.next.next;
        }
        cur = cur.next;
    }
    return res;
}
