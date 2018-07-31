/** 362. Sliding Window Maximum(lintcode) / 239. Sliding Window Maximum(leetcode)
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
 
 /* My Method: Use PriorityQueue(最大堆) - O(nk) time and O(max(k,n-k+1)) space complexity 
  * [AC in leetcode but TLE in lintcode]
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

/* Method 2: 双向队列 - O(n) time and O(k) space complexity
 *    1) 建立一个双向队列，队列内数的个数不超过k，而且永远保持降序：
 *      a) 遍历数组添加新的数nums[i]时，将新的数nums[i]和双向队列的末尾数比较，如果末尾比新数小，则把末尾扔掉，循环此过程
 *    直到该队列的末尾大于等于新数或者队列为空的时候才住手。然后把新数加入队列尾端。这样，就可以保证队列里的元素是从头到尾降序的。
 *   【由于队列里大小不超过k，所以每次都只包含一个窗口内的数，而它们其实就是窗口内第一大，第二大，第三大...的数】
 *      b) 因为每次在加新数的时候，可能把很多没用的数给扔了，这样队列头部的数并不一定是窗口最左边的数。为了判断每次滑动窗口时，
 *    是否要把队列头部元素去掉，这里的技巧是在队列中存的是每个数在原数组中的下标，这样既可以知道这个数的值，也可以知道该数是不是
 *    窗口最左边的数。从而判断滑动窗口是要不要删除队头元素。
 *      Question：为什么时间复杂度是O(N)呢？
 *      Ans: 因为每个数最多只可能被操作两次：添加队列 + 移出队列
 *           i) 一次是加入队列的时候，
 *           ii) 一次是因为有别的更大数在后面，所以被扔掉，或者因为出了窗口而被扔掉。
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
    List<Integer> max = new ArrayList<Integer>();
    max.add(0);
    for(int i = 1; i < k; i++){
        while(max.size() != 0 && nums[i] > nums[max.get(max.size()-1)]){
            max.remove(max.size()-1);
        }
        max.add(i);
    }
    list[0] = nums[max.get(0)];
    int index = 1;
    for(int i = k; i < n; i++){
        if(max.get(0) == i-k){
            max.remove(0);
        }
        while(max.size() != 0 && nums[i] > nums[max.get(max.size()-1)]){
            max.remove(max.size()-1);
        }
        max.add(i);
        list[index] = nums[max.get(0)];
        index++;
    }

    return list;
}
