/** 155. Min Stack(leetcode) / 12. Min Stack(lintcode) 
 *    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *        1)push(x) -- Push element x onto stack.
 *        2)pop() -- Removes the element on top of the stack.
 *        3)top() -- Get the top element.
 *        4)getMin() -- Retrieve the minimum element in the stack.
 *
 *    Example: MinStack minStack = new MinStack();
 *             minStack.push(-2);
 *             minStack.push(0);
 *             minStack.push(-3);
 *             minStack.getMin();   --> Returns -3.
 *             minStack.pop();
 *             minStack.top();      --> Returns 0.
 *             minStack.getMin();   --> Returns -2.
 */
 
 //Method:
 class MinStack {
    List<Integer> left_min; // left_min.get(i) stores the minimum number in list[0, i]
    List<Integer> list;
    /** initialize your data structure here. */
    public MinStack() {
        left_min = new ArrayList<Integer>();
        list = new ArrayList<Integer>();
    }
    
    public void push(int x) {
        list.add(x);
        if(left_min.size() == 0){
            left_min.add(x);
        }
        else{
            int lmin = left_min.get(left_min.size() - 1);
            if(lmin >= x){
                left_min.add(x);
            }
            else{
                left_min.add(lmin);
            }
        }
    }
    
    public void pop() {
        list.remove(list.size() - 1);
        left_min.remove(left_min.size() - 1);
    }
    
    public int top() {
        return list.get(list.size() - 1);
    }
    
    public int getMin() {
        return left_min.get(left_min.size() - 1);
    }
}
