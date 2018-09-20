/** 385. Mini Parser(leetcode)
 *      Given a nested list of integers represented as a string, implement a parser to deserialize it.
 *      Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 *      Note: You may assume that the string is well-formed:
 *              1) String is non-empty.
 *              2) String does not contain white spaces.
 *              3) String contains only digits 0-9, [, - ,, ].
 *
 *      Example: 1) Given s = "324",
 *                  You should return a NestedInteger object which contains a single integer 324.
 *               2) Given s = "[123,[456,[789]]]",
 *                  Return a NestedInteger object containing a nested list with 2 elements:
 *                     1. An integer containing value 123.
 *                     2. A nested list containing two elements:
 *                         i.  An integer containing value 456.
 *                         ii. A nested list with one element:
 *                              a. An integer containing value 789.
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 
 //My Method:
 class Solution {
    public NestedInteger deserialize(String s) {
        Stack<NestedInteger> stk = new Stack<NestedInteger>();
        int len = s.length();
        StringBuilder num = new StringBuilder();
        NestedInteger cur = new NestedInteger();
        for(int i = 0; i < len; i++){
            if(s.charAt(i) != ',' && s.charAt(i) != '[' && s.charAt(i) != ']'){
                num.append(s.charAt(i));
            }
            //encounters '[',start a new NestedInteger and push it into stack
            else if(s.charAt(i) == '['){
                cur = new NestedInteger();
                stk.push(cur);
            }
            else{
                if(num.length() != 0){ //append a new number to current NestedInteger
                    int n = Integer.valueOf(num.toString());
                    cur.add(new NestedInteger(n));
                }
                // encounters ']', 说明当前NestedInteger结束，把它作为元素加入上一层NestedInteger，cur变成新的最上层
                if(s.charAt(i) == ']'){ 
                    NestedInteger tmp = stk.pop();
                    if(!stk.isEmpty()){
                        cur = stk.peek();
                        cur.add(tmp);
                    }
                }
                num = new StringBuilder();
            }
        }
        if(num.length() != 0){
            int n = Integer.valueOf(num.toString());
            cur.setInteger(n);
        }
        return cur;
    }
}
