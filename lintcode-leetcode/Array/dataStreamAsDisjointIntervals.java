/** 352. Data Stream as Disjoint Intervals(leetcode)
 *      Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far
 *  as a list of disjoint intervals.
 *
 *      For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 *          [1, 1]
 *          [1, 1], [3, 3]
 *          [1, 1], [3, 3], [7, 7]
 *          [1, 3], [7, 7]
 *          [1, 3], [6, 7]
 *
 *      Follow up: What if there are lots of merges and the number of disjoint intervals are small 
 *                 compared to the data stream's size?
 */
 
 //My Method: TreeSet - Set的子类，TreeSet集合用来对对象元素进行排序,同样保证元素的唯一
 /**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class SummaryRanges {

    /** Initialize your data structure here. */
    Set<Integer> numbers;
    public SummaryRanges() {
        numbers = new TreeSet<Integer>();
    }
    
    public void addNum(int val) {
        numbers.add(val); //TreeSet内部操作：O(logn)
    }
    
    public List<Interval> getIntervals() {
        int n = numbers.size();
        List<Interval> list = new ArrayList<Interval>();
        if(n == 0){
            return list; 
        }
        Iterator<Integer> itr = numbers.iterator();
        int[] newnum = new int[n];
        int i = 0;
        while(itr.hasNext()){
            newnum[i] = itr.next();
            i++;
        }
        for(i = 0; i < n; i++){
            int start = newnum[i];
            while(i + 1 < n && newnum[i+1] == newnum[i] + 1){
                i++;
            }
            list.add(new Interval(start,newnum[i]));
        }

        return list;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
