/** 1318. Contains Duplicate III(lintcode) / 220. Contains Duplicate III(leetcode)
 *      Given an array of integers, find out whether there are two distinct indices i and j in the array such that
 *  the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j 
 *  is at most k.
 *
 *      Example: 1) Input: nums = [1,2,3,1], k = 3, t = 0 ; Output: true
 *               2) Input: nums = [1,0,1,1], k = 1, t = 2 ; Output: true
 *               3) Input: nums = [1,5,9,1,5,9], k = 2, t = 3 ; Output: false
 */
 
 //Method 1: Brute Force - O(n^2) time complexity
 public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if(nums == null || nums.length == 0){
        return false;
    }
    int n = nums.length;
    for(int i = 0; i < n; i++){
        for(int j = 1; j <= k ; j++){
            if(i + j >= n) break;
            if(Math.abs((long)nums[i] - nums[i+j]) <= t){
                return true;
            }
        }
    }
    return false;
 }
