/** 341. Flatten Nested List Iterator(leetcode)/ 528. Flatten Nested List Iterator(lintcode)
 *    Given a nested list of integers, implement an iterator to flatten it.
 *    Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 *    Example: Input: [[1,1],2,[1,1]] ; Output: [1,1,2,1,1]
 *               Explanation: By calling next repeatedly until hasNext returns false, 
 *                        the order of elements returned by next should be: [1,1,2,1,1].
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 
 //Method 1: Use List
 public class NestedIterator implements Iterator<Integer> {
    List<Integer> list;
    int index;
    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList<Integer>();
        flatList(nestedList);
    }
    
    public void flatList(List<NestedInteger> nestedList){
        for(int i = 0; i < nestedList.size(); i++){
            NestedInteger n = nestedList.get(i);
            if(n.isInteger()){
                list.add(n.getInteger());
            }
            else{
                flatList(n.getList());
            }
        }
    }

    @Override
    public Integer next() {
        Integer res = list.get(index);
        index++;
        return res;
    }

    @Override
    public boolean hasNext() {
        if(list.size() == index){
            return false;
        }
        return true;
    }
}

//Method 2: Use Stack
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stk;
    public NestedIterator(List<NestedInteger> nestedList) {
        stk = new Stack<NestedInteger>();
        for(int i = nestedList.size() - 1; i >= 0; i--){
            stk.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stk.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stk.isEmpty()){
            NestedInteger top = stk.peek();
            if(top.isInteger()){
                return true;
            }
            stk.pop();
            List<NestedInteger> l = top.getList();
            for(int i = l.size() - 1; i >= 0; i--){
                stk.push(l.get(i));
            }
        }
        return false;
    }
}
