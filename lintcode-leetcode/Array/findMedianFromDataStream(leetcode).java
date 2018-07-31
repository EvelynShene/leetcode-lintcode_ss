/** 295. Find Median from Data Stream(leetcode)
 *      Median is the middle value in an ordered integer list. If the size of the list is even, 
 *  there is no middle value. So the median is the mean of the two middle value.
 *
 *      For example: [2,3,4], the median is 3
 *                   [2,3], the median is (2 + 3) / 2 = 2.5
 *
 *      Design a data structure that supports the following two operations:
 *        1) void addNum(int num) - Add a integer number from the data stream to the data structure.
 *        2) double findMedian() - Return the median of all elements so far.
 */
 
 //My Method: Insertion Sort - O(n) time and O(n) space complexity
 class MedianFinder {
    List<Integer> nums;
    /** initialize your data structure here. */
    public MedianFinder() {
        nums = new ArrayList<Integer>();
    } 
    
    public void addNum(int num) {
        int index = 0;
        for(; index < nums.size(); index++){
            if(nums.get(index) >= num)
                break;
        }
        nums.add(index, num);
    }
    
    public double findMedian() {
        int n = nums.size();
        int mid = n / 2;
        int median = 0;
        if(n % 2 == 0){
            return ((float)nums.get(mid) + nums.get(mid-1)) / 2;
        }
        else{
            return nums.get(mid);
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

/* Method 2: Use Two Heap - O(logn) time complexity
 *   1）用一个最大堆记录前一半数组，则root处就是前一半数的最大值
 *   2）用一个最小堆记录后一半数组，则root处就是后一半数的最小值
 *   3）Median的值就由这两个堆的root决定
 *  【每次加入新数字的时候，先加入最大堆中，然后在最大堆中取出root值加入最小堆(最大堆中最大值加入最小堆)；
 *    再平衡两个堆的大小，保证最大堆的数 -- 要么比最小堆多一个，要么相等。】
 *／
class MedianFinder {
    PriorityQueue<Integer> max_half; 
    PriorityQueue<Integer> min_half;
    /** initialize your data structure here. */
    public MedianFinder() {
        min_half = new PriorityQueue<Integer>();
        max_half = new PriorityQueue<Integer>(1000, new Comparator<Integer>(){
            public int compare(Integer t1, Integer t2){
                return t2 - t1;
            }
        });
    } 
    
    public void addNum(int num) {
        max_half.offer(num);
        min_half.offer(max_half.poll());
        if(min_half.size() > max_half.size()){
            max_half.offer(min_half.poll());
        }
    }
    
    public double findMedian() {
        if(max_half.size() == min_half.size())
            return ((double)max_half.peek()+min_half.peek()) / 2;
        else
            return max_half.peek();
    }
}
