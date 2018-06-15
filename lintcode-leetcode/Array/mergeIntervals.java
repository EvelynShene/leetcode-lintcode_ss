/** 156. Merge Intervals(lintcode) / 56. Merge Intervals(leetcode)
 *    Given a collection of intervals, merge all overlapping intervals.
 *    Example: Input: [[1,3],[2,6],[8,10],[15,18]]; Output: [[1,6],[8,10],[15,18]]
 *    Note: intervals are not sorted.
 */
 
 //My Method: O(n log n) time and O(1) extra space.
 
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

public class Solution {
    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        if(intervals == null || intervals.size() == 0){
            return intervals;
        }
        
        Collections.sort(intervals,new Comparator<Interval>(){
           public int compare(Interval a, Interval b){
               return a.start - b.start;
           } 
        });
        
        Interval last = intervals.get(0);
        int i = 1;
        int index = 0;
        
        while(i < intervals.size()){
            Interval current = intervals.get(i);
            if(last.start <= current.start && last.end >= current.start){
                last.end = Math.max(last.end, current.end);
            }
            else{
                intervals.set(index,last);
                index++;
                last = current;
            }
            i++;
        }
        
        intervals.set(index,last);
        index++;
        for(int j = intervals.size()-1; j >= index; j--){
            intervals.remove(j);
        }
        
        return intervals;
    }
}

// Method 2: O(n log n) time and O(n) extra space.
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        
        Collections.sort(intervals, new IntervalComparator());       
  
        List<Interval> result = new ArrayList<Interval>();
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (curt.start <= last.end ){
                last.end = Math.max(last.end, curt.end);
            }else{
                result.add(last);
                last = curt;
            }
        }
        
        result.add(last);
        return result;
    }
    
    
    private class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }

}
