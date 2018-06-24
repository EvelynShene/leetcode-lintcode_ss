/** 1064. My Calendar II (lintcode) / 731. My Calendar II (leetcode)
 *      Implement a MyCalendarTwo class to store your events. 
 *   A new event can be added if adding the event will not cause a triple booking.
 *      Your class will have one method, book(int start, int end). 
 *   Formally, this represents a booking on the half open interval [start, end), 
 *   the range of real numbers x such that start <= x < end.
 *      A triple booking happens when three events have some non-empty intersection 
 *   (ie., there is some time that is common to all 3 events.)
 *      For each call to the method MyCalendar.book, 
 *   return true if the event can be added to the calendar successfully without causing a triple booking. 
 *   Otherwise, return false and do not add the event to the calendar.
 *      Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 *   Note: 
 *      The number of calls to MyCalendar.book per test case will be at most 1000.
 *      In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 *
 *   Example: MyCalendar();
 *            MyCalendar.book(10, 20); // returns true
 *            MyCalendar.book(50, 60); // returns true
 *            MyCalendar.book(10, 40); // returns true
 *            MyCalendar.book(5, 15); // returns false
 *            MyCalendar.book(5, 10); // returns true
 *            MyCalendar.book(25, 55); // returns true
 */
 
 class MyCalendarTwo {
    List<int[]> book;
    List<int[]> overlap;
    
    public MyCalendarTwo() {
        book = new ArrayList<int[]>();
        overlap = new ArrayList<int[]>();
    }
    
    public boolean book(int start, int end) {
        for(int i = 0; i < overlap.size(); i++){
            if(!(overlap.get(i)[0] >= end || overlap.get(i)[1] <= start)){
                return false;
            }
        }
        for(int i = 0; i < book.size(); i++){
            if(!(book.get(i)[0] >= end || book.get(i)[1] <= start)){
                overlap.add(new int[]{Math.max(book.get(i)[0],start), Math.min(book.get(i)[1],end)});
            }
        }
        // for(int[] iv: overlap){
        //     if(iv[0] < end && iv[1] > start){
        //         return false;
        //     }
        // }
        // for(int[] iv: book){
        //     if((iv[0] < end) && (iv[1] > start)){
        //         overlap.add(new int[]{Math.max(iv[0],start), Math.min(iv[1],end)});
        //     }
        // }
        book.add(new int[]{start,end});
        return true;    
    }
}
/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
