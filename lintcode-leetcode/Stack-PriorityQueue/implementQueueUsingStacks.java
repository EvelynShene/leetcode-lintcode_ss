/** 232. Implement Queue using Stacks(leetcode) / 40. Implement Queue using Stacks(lintcode)
 *    Implement the following operations of a queue using stacks.
 *        1) push(x) -- Push element x to the back of queue.
 *        2) pop() -- Removes the element from in front of queue.
 *        3) peek() -- Get the front element.
 *        4) empty() -- Return whether the queue is empty.
 *
 *    Example: MyQueue queue = new MyQueue();
 *             queue.push(1);
 *             queue.push(2);  
 *             queue.peek();  // returns 1
 *             queue.pop();   // returns 1
 *             queue.empty(); // returns false
 *
 *    Note: 1) You must use only standard operations of a stack -- which means only push to top, peek/pop from top, 
 *            size, and is empty operations are valid.
 *          2) Depending on your language, stack may not be supported natively. You may simulate a stack by using 
 *            a list or deque (double-ended queue), as long as you use only standard operations of a stack.
 *          3) You may assume that all operations are valid (for example, no pop or peek operations will be called
 *            on an empty queue).
 */
 
 //Method: Use Two Stack - Push - O(1) per operation, Pop - O(n) per operation
 class MyQueue {
    Stack<Integer> stk;
    Stack<Integer> stk2;
    /** Initialize your data structure here. */
    public MyQueue() {
        stk = new Stack<Integer>();
        stk2 = new Stack<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stk.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        while(!stk.isEmpty()){
            stk2.push(stk.pop());
        }
        int top = stk2.pop();
        while(!stk2.isEmpty()){
            stk.push(stk2.pop());
        }
        return top;
    }
    
    /** Get the front element. */
    public int peek() {
        while(!stk.isEmpty()){
            stk2.push(stk.pop());
        }
        int top = stk2.peek();
        while(!stk2.isEmpty()){
            stk.push(stk2.pop());
        }
        return top;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stk.isEmpty();
    }
}
