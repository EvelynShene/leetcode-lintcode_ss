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

/* Method 2: O(nlogn) time and O(n) space complexity - 思路跟Method 1一样，但是使用PrioritQueue降低时间复杂度
 *     思路：【PriorityQueue 默认是实现最小堆】
 *       1) 将intervals按照start升序排序；将intervals[0].end值加入最小堆队列中。
 *       2) 从intervals[1]开始遍历intervals，根据PriorityQueue性质，每次使用peek()函数取出堆中最小值，也就是当前end的最小值
 *         a) 如果遍历的intervals[i].start >= end，说明这个intervals[i]可以和最小值end所属于的intervals[j]公用一个Rooms。
 *       所以end更新(即删除最小的end值，加入新的intervals[i].end，因为它们是公用一个房间，房间现在又加入了新时段)。
 *         b) 如果遍历的intervals[i].start < end，它肯定也比其他时段的end小(小于最小值，一定小于其他值)，所以这个新时段与当前
 *       堆中所有时段冲突，必须重新开一个Room，则直接在堆中加入新的intervals[i].end。
 *       3) 最后堆的大小就是不能合并的时间段的个数，也就是最少要开的Room的数目。
 *
 *    【PriorityQueue 默认是实现最小堆，要实现最大堆，则： 
 *        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(INITIAL_CAPACITY,new Comparator<Integer>(){
 *            public int compare(Integer o1, Integer o2) {                
 *                return o2-o1;
 *            }
 *        });
 *     】
 */
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
    PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
    int n = intervals.size();
    heap.offer(intervals.get(0).end);
    for(int i = 1; i < n; i++){
        if(intervals.get(i).start >= heap.peek()){
            heap.poll();
        }
        heap.offer(intervals.get(i).end);
    }
    return heap.size();
}

/* Method 3: Greedy Algorithm - O(nlogn) time and O(n) space complexity
 *    1) 用两个数组分别存储所有时间段的start和end，并且对两个数组从小到达排序;
 *    2) 从end[0]开始，让所有的start与end比较，
 *     a) 如果start[i] > end[j]，说明当前以end[j]结尾的时间段使用的房间可以结束，让start[i]开始的时间段可以接着这个房间用
 *     b) 否则，必须新开一个房间供使用。
 *    example：[1,3][2,4]
 *        start[] = {1,2}
 *        end[]  =  {3,4}
 *       让1，2分别与end[0] = 3比较，都小，说明以这两个时间开始的时间段必须有不同的房间，不然会冲突。
 *      【3虽然不是以1开始的时间段的结束时间，但是它是最小的结束时间，在3之前开始的时间段，都是不相容的。】
 */
public int minMeetingRooms(List<Interval> intervals) {
    if(intervals == null || intervals.size() == 0){
        return 0;
    }
    int n = intervals.size();
    int[] start = new int[n];
    int[] end = new int[n];
    for(int i = 0; i < n; i++){
        start[i] = intervals.get(i).start;
        end[i] = intervals.get(i).end;
    }
    Arrays.sort(start);
    Arrays.sort(end);
    int room = 0;
    int index = 0;
    for(int i = 0; i < n; i++){
        if(start[i] < end[index]){
            room++;
        }
        else{
            index++;
        }
    }
    return room;
}
