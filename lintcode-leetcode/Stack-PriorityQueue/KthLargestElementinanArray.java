/** 215. Kth Largest Element in an Array(leetcode) / 5. Kth Largest Element in an Array(lintcode)
 *       Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted 
 *  order, not the kth distinct element.
 *
 *       Example: 1) Input: [3,2,1,5,6,4] and k = 2 ; Output: 5
 *                2) Input: [3,2,3,1,2,4,5,5,6] and k = 4 ; Output: 4
 *
 *       Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
 
 //Method 1: Use PriorityQueue - O(n) time and space complexity
 public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> max_heap = new PriorityQueue<Integer>(new Comparator<Integer>(){
        public int compare(Integer o1, Integer o2){
            return o2 - o1;
        }
    });

    for(int i = 0; i < nums.length; i++){
        max_heap.offer(nums[i]);
    }

    for(int i = 1; i < k; i++){
        max_heap.poll();
    }
    return max_heap.poll();
}

//Method 2: Array sort - O(nlogn) time and O(1) space complexity
public int findKthLargest(int[] nums, int k) {
    Arrays.sort(nums);
    return nums[nums.length - k];
}
