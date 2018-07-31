/** 239. Sliding Window Maximum(leetcode)
 *      Given an array nums, there is a sliding window of size k which is moving from the very left of the array 
 *  to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by 
 *  one position. Return the max sliding window.
 *
 *      Note: You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 *
 *      Example: Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3 ; Output: [3,3,5,5,6,7] 
 *         Explanation: 
 *              Window position                Max
 *              ---------------               -----
 *              [1  3  -1] -3  5  3  6  7       3
 *               1 [3  -1  -3] 5  3  6  7       3
 *               1  3 [-1  -3  5] 3  6  7       5
 *               1  3  -1 [-3  5  3] 6  7       5
 *               1  3  -1  -3 [5  3  6] 7       6
 *               1  3  -1  -3  5 [3  6  7]      7
 *
 *      Follow up: Could you solve it in linear time?
 */
 
 /* My Method: Use PriorityQueue(最大堆) - O(nlogn) time and O(max(k,n-k+1)) space complexity
  *   1) remove()方法和add()方法时间复杂度为O(logn)，
  *   2) remove(Object obj)和contains()方法需要O(n)时间复杂度，
  *   3) 取队头则需要O(1)时间
  */
 public int[] maxSlidingWindow(int[] nums, int k) {      
    if(nums == null){
        return null;
    }
    int n = nums.length;
    if(k >= n){
        k = n;
    }
    if(n == 0){
        return new int[0];
    }
    int[] list = new int[n-k+1];
    PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new Comparator<Integer>(){
        public int compare(Integer t1, Integer t2){
            return t2 - t1;
        }
    });
    for(int i = 0; i < k; i++){
        heap.offer(nums[i]);
    }
    list[0] = heap.peek();
    int index = 1;
    for(int i = k; i < n; i++){
        heap.remove(nums[i-k]);
        heap.offer(nums[i]);
        list[index] = heap.peek();
        index++;
    }
    return list;
 }
