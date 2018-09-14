/** 551. Nested List Weight Sum(lintcode) / 339. Nested List Weight Sum(leetcode - locked)
 *      Given a nested list of integers, return the sum of all integers in the list weighted by their depth. 
 *  Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 *      Example: 1) Given the list [[1,1],2,[1,1]], return 10.
 *                    (four 1's at depth 2, one 2 at depth 1, 4 * 1 * 2 + 1 * 2 * 1 = 10)
 *               2) Given the list [1,[4,[6]]], return 27. 
 *                    (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4 * 2 + 6 * 3 = 27)
 */ 
 
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 
 //Method 1: Recursive - DFS
 public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        return dsum(nestedList, 1);
    }
    
    public int dsum(List<NestedInteger> nestedList, int level){
        if(nestedList == null || nestedList.size() == 0){
            return 0;
        }
        int res = 0;
        for(int i = 0; i < nestedList.size(); i++){
            if(nestedList.get(i).isInteger()){
                res += level * nestedList.get(i).getInteger();
            }
            else{
                List<NestedInteger> l = nestedList.get(i).getList();
                res += dsum(l, level + 1);
            }
        }
        return res;
    }
}

//Method 2: Iterative - BFS
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList == null || nestedList.size() == 0){
            return 0;
        }
        int res = 0;
        Queue<NestedInteger> q = new LinkedList<NestedInteger>();
        int depth = 1;
        
        for(int i = 0; i < nestedList.size(); i++){
            q.offer(nestedList.get(i));
        }
        
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0){
                NestedInteger tmp = q.poll();
                if(tmp.isInteger()){
                    res += depth * tmp.getInteger();
                }
                else{
                    List<NestedInteger> l = tmp.getList();
                    for(int i = 0; i < l.size(); i++){
                        q.offer(l.get(i));
                    }
                }
                size--;
            }
            depth++;
        }
        return res;
    }
}
