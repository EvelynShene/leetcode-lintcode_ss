/** 225. Implement Stack using Queues(leetcode) / 495. Implement Stack(lintcode)
 *    Implement the following operations of a stack using queues.
 *       push(x) -- Push element x onto stack.
 *       pop() -- Removes the element on top of the stack.
 *       top() -- Get the top element.
 *       empty() -- Return whether the stack is empty.
 *
 *    Example: MyStack stack = new MyStack();
 *             stack.push(1);
 *             stack.push(2);  
 *             stack.top();   // returns 2
 *             stack.pop();   // returns 2
 *             stack.empty(); // returns false
 *    Notes: 1) You must use only standard operations of a queue -- which means only push to back, peek/pop from 
 *              front, size, and is empty operations are valid.
 *           2) Depending on your language, queue may not be supported natively. You may simulate a queue by using
 *              a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 *           3) You may assume that all operations are valid (for example, no pop or top operations will be called
 *              on an empty stack).
 */
 
 //My Method: -  push O(1), pop O(n)
 class MyStack {
    Queue<Integer> q;
    Queue<Integer> q2;
    int front;
    /** Initialize your data structure here. */
    public MyStack() {
        q = new LinkedList<Integer>();
        q2 = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        front = x;
        if(q2.isEmpty()){
            q.offer(x);
        }
        else{
            q2.offer(x);
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(!q.isEmpty()){
            while(q.size() > 1){
                front = q.peek();
                q2.offer(q.poll());
            }
            return q.poll();
        }
        else{
            while(q2.size() > 1){
                front = q2.peek();
                q.offer(q2.poll());
            }
            return q2.poll();
        }
    }
    
    /** Get the top element. */
    public int top() {
        return front;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty() && q2.isEmpty();
    }
}
