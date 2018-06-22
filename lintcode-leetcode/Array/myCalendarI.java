/** 1065. My Calendar I (lintcode) / 729. My Calendar I(leetcode)
 *      Implement a MyCalendar class to store your events. 
 *  A new event can be added if adding the event will not cause a double booking.
 *      Your class will have the method, book(int start, int end). 
 *  Formally, this represents a booking on the half open interval [start, end), 
 *  the range of real numbers x such that start <= x < end.
 *      A double booking happens when two events have some non-empty intersection 
 *  (ie., there is some time that is common to both events.)
 *      For each call to the method MyCalendar.book, 
 *  return true if the event can be added to the calendar successfully without causing a double booking. 
 *  Otherwise, return false and do not add the event to the calendar.
 *      Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 *  Note: The number of calls to MyCalendar.book per test case will be at most 1000.
 *        In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 * 
 *  Example: MyCalendar();
 *           MyCalendar.book(10, 20); // returns true
 *           MyCalendar.book(15, 25); // returns false
 *           MyCalendar.book(20, 30); // returns true
 *  Explanation: 
 *        The first event can be booked.  The second can't because time 15 is already booked by another event.
 *        The third event can be booked, as the first event takes every time less than 20, but not including 20.
 */
 
 //My Method: - O(n^2) Time and O(n) Space
 class MyCalendar {
    List<int[]> list;
    public MyCalendar() {
        list = new ArrayList<int[]>();
    }
    
    public boolean book(int start, int end) {
        int[] time = new int[2];
        if(list == null || list.size() == 0){
            time[0] = start;
            time[1] = end;
            list.add(time);
        }
        else{
            for(int i = 0; i < list.size(); i++){
                if(end <= list.get(i)[0] || start >= list.get(i)[1]){
                    continue;
                }
                else{
                    return false;
                }
            }
            time[0] = start;
            time[1] = end;
            list.add(time);
        }
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
 
 /* Method 2: Balanced Tree - O(nlogn) Time and O(n) Space (Code from leetcode solution)
  *     使用Java的TreeMap数据结构。TreeMap 是一个有序的key-value集合，它通过红黑树实现，但继承AbstractMap，所以本质是一个Map，即一个key-value集合。
  *  TreeMap可以查询小于等于某个值的最大的key，也可查询大于等于某个值的最小的key。
  *     在该题中，对于每个以start开始、end结束的新时间段，若用start做key，end做value，
  *  只需查询TreeMap中start值相邻两侧的key，保证 “左侧end <= start <= end <= 右侧start” 即可。
  */
 
 class MyCalendar {
    TreeMap<Integer, Integer> calendar;

    MyCalendar() {
        calendar = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start),
                next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}
