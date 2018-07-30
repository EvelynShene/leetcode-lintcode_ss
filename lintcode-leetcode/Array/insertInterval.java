/** 30. Insert Interval(lintcode) / 57. Insert Interval(leetcode)
 *     Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *     You may assume that the intervals were initially sorted according to their start times.
 *
 *     Example: 1) Insert (2, 5) into [(1,2), (5,9)], we get [(1,9)].
 *              2) Insert (3, 4) into [(1,2), (5,9)], we get [(1,2), (3,4), (5,9)].
 */
 
 //My Method:
 /**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> list = new ArrayList<Interval>();
        if(intervals == null || intervals.size() == 0){
            list.add(newInterval);
            return list;
        }
        int n = intervals.size();
        if(newInterval.start > intervals.get(n-1).end){
            for(int i = 0; i < n; i++){
                list.add(intervals.get(i));
            }
            list.add(newInterval);
            return list;
        }
        else if(newInterval.end < intervals.get(0).start){
            list.add(newInterval);
            for(int i = 0; i < n; i++){
                list.add(intervals.get(i));
            }
            return list;
        }
        
        int l = 0;
        int r = 0;
        for( ;l < n; l++){
            if(intervals.get(l).start > newInterval.start)
                break;
        }
        if(l - 1 >= 0 && intervals.get(l-1).end >= newInterval.start){
            l--;  
        }
        r = l;
        for(; r < n; r++){
            if(intervals.get(r).start > newInterval.end)
                break;
        }
        r--;
        if(r < l){
            for(int i = 0; i < n; i++){
                if(i == l){
                    list.add(newInterval);
                }
                list.add(intervals.get(i));
            }
            return list;
        }
        //merge l->r
        Interval temp = new Interval();
        temp.start = Math.min(newInterval.start,intervals.get(l).start);
        temp.end = Math.max(newInterval.end, intervals.get(r).end);
        
        for(int i = 0; i < n; i++){
            if(i == l){
                list.add(temp);
            }
            else if(i > l && i <= r){
                continue;
            }
            else{
                list.add(intervals.get(i));
            }
        }
        return list;
    }
}

//Method 2: More concise code
public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> list = new ArrayList<Interval>();
    if(intervals == null || intervals.size() == 0){
        list.add(newInterval);
        return list;
    }
    int n = intervals.size();
    int insert_pos = 0;
    for(int i = 0; i < n; i++){
        if(intervals.get(i).end < newInterval.start){
            list.add(intervals.get(i));
            insert_pos++;
        }
        else if(intervals.get(i).start > newInterval.end){
            list.add(intervals.get(i));
        }
        else{
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
        }
    }
    list.add(insert_pos,newInterval);
    return list;
}
