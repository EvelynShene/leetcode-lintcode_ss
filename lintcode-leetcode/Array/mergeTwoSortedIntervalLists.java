/** 839. Merge Two Sorted Interval Lists(lintcode)
 *  Merge two sorted (ascending) lists of interval and return it as a new sorted list. 
 *  The new sorted list should be made by splicing together the intervals of the two lists and sorted in ascending order.
 *    Note: The intervals in the given list do not overlap. The intervals in different lists may overlap.
 *    Example: Given list1 = [(1,2),(3,4)] and list2 = [(2,3),(5,6)], return [(1,4),(5,6)]
 */
 
 
 /**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */
 /** 
  * Hint: 每个单独list内是没有重叠区间的。用current记录（当前没被合并的）最早开始区间，
  *       last记录没被加入res的当前正在合并的区间，用于和新加入的current区间比较，看看能不能合并到一起
  */
public class Solution {
    /**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        // write your code here
        List<Interval> res = new ArrayList<>();
        
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        
        int i = 0, j = 0;
        Interval current = null;
        Interval last = null;
        while(i < list1.size() && j < list2.size()){
            if(list1.get(i).start <= list2.get(j).start){ //current一直选（当前没被合并的）最早开始区间
                current = list1.get(i);
                i++;
            }
            else{
                current = list2.get(j);
                j++;
            }
            
            last = merge(res,current,last); //last是没被加入res的当前正在合并的区间，和新加入的current区间比较，看看能不能合并到一起
        }
        
        while(i < list1.size()){
            last = merge(res,list1.get(i),last);
            i++;
        }
        
        while(j < list2.size()){
            last = merge(res,list2.get(j),last);
            j++;
        }
        
        if(last != null){
            res.add(last);
        }
        return res;
    }
    
    public Interval merge(List<Interval> result, Interval current, Interval last){
        if(last == null){
            return current;
        }
        
        if(current.start > last.end){ //last和新加入的current区间不能合并,把last放入result中，current成为新的要合并的区间last
            result.add(last);
            return current;
        }
        
        last.end = Math.max(last.end,current.end);
        return last;
        
    }
}

//时间复杂度 O(n+m)
