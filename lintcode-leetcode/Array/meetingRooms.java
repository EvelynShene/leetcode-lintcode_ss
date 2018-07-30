/** 920. Meeting Rooms(lintcode)/ 252. Meeting Rooms(leetcode - locked)
 *     Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
 *  determine if a person could attend all meetings.
 *
 *     Example: Given intervals = [[0,30],[5,10],[15,20]], return false.
 */
 
 //My Method:
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
    public boolean canAttendMeetings(List<Interval> intervals) {
        if(intervals == null || intervals.size() == 0){
            return true;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval t1, Interval t2){
                return t1.start - t2.start;
            }
        });
        int n = intervals.size();
        for(int i = 0; i < n-1; i++){
            if(intervals.get(i).end > intervals.get(i+1).start){
                return false;
            }
        }
        return true;
    }
 }
