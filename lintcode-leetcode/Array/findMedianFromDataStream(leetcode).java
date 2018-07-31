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
