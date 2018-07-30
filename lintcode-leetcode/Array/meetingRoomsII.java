/** 919. Meeting Rooms II(lintcode) / 253. Meeting Rooms II(leetcode - locked)
 *      Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 *   find the minimum number of conference rooms required.
 *
 *      Example: Given intervals = [(0,30),(5,10),(15,20)], return 2.
 */
 
 //My Method: O(n^2) time and O(n) space complexity
 public int minMeetingRooms(List<Interval> intervals) {
    // Write your code here
    if(intervals == null || intervals.size() == 0){
        return 0;
    }
    Collections.sort(intervals, new Comparator<Interval>(){
        public int compare(Interval t1, Interval t2){
            return t1.start - t2.start;
        }
    });
    List<Integer> list = new ArrayList<Integer>();
    int n = intervals.size();
    list.add(intervals.get(0).end);
    for(int i = 1; i < n; i++){
        int j = 0;
        for(; j < list.size(); j++){
            if(intervals.get(i).start >= list.get(j)){
                break;
            }
        }
        if(j < list.size()){
            list.remove(j);
        }
        list.add(intervals.get(i).end);
    }
    return list.size();
 }
