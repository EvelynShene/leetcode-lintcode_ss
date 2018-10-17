/** 281. Zigzag Iterator(leetcode) / 540. Zigzag Iterator(lintcode)
 *      Given two 1d vectors, implement an iterator to return their elements alternately.
 *
 *      Example: Input: v1 = [1,2]
 *                      v2 = [3,4,5,6] 
 *               Output: [1,3,2,4,5,6]
 *               Explanation: By calling next repeatedly until hasNext returns false, 
 *                       the order of elements returned by next should be: [1,3,2,4,5,6].
 *
 *      Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
 */
 
/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
 
 //Method + Follwo up : Use Iterator
 public class ZigzagIterator {
    List<Iterator> lists;
    int turns;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        lists = new ArrayList<>();
        lists.add(v1.iterator());
        lists.add(v2.iterator());
        turns = 0;
    }

    public int next() {
        while(!lists.get(turns).hasNext()){
            turns = (turns + 1) % 2;
        }
        int res = (int)lists.get(turns).next();
        turns = (turns + 1) % 2;
        return res;
    }

    public boolean hasNext() {
        return lists.get(0).hasNext() || lists.get(1).hasNext();
    }
}
 
 //My Method: Construct tree for each list, then use Queue
 class Node{
    int val;
    Node next;

    public Node(int val){
        this.val = val;
        this.next = null;
    }
}

public class ZigzagIterator {
    Queue<Node> q;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        q = new LinkedList<>();
        Node h1 = null;
        Node cur = null;
        Node pre = null;
        for(int i = 0; i < v1.size(); i++){
            cur = new Node(v1.get(i));
            if(h1 == null){
                h1 = cur;
            }
            if(pre != null){
                pre.next = cur;
            }
            pre = cur;
        }
        Node h2 = null;
        cur = null;
        pre = null;
        for(int i = 0; i < v2.size(); i++){
            cur = new Node(v2.get(i));
            if(h2 == null){
                h2 = cur;
            }
            if(pre != null){
                pre.next = cur;
            }
            pre = cur;
        }
        
        if(h1 != null){
            q.offer(h1);
        }
        if(h2 != null){
            q.offer(h2);
        }
    }

    public int next() {
        Node node = q.poll();
        if(node.next != null){
            q.offer(node.next);
        }
        return node.val;
    }

    public boolean hasNext() {
        return q.size() != 0;
    }
}
 
