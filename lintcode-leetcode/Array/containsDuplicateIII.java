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
            if(Math.abs((long)nums[i] - nums[i+j]) <= t){ //long - 防止溢出
                return true;
            }
        }
    }
    return false;
 }

//Method 2: Use TreeSet - O(nlogk) time and O(k) space complexity 
public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
     if(nums == null || nums.length == 0 || k <= 0){
         return false;
     }
     TreeSet<Integer> set = new TreeSet<Integer>();
     int n = nums.length;
     for(int i = 0; i < n; i++){
         Integer floor = set.floor(nums[i]);//找到set中比nums[i]小的最大数，没有则返回null - O(logk)
         Integer ceil = set.ceiling(nums[i]);//找到set中比nums[i]大的最小数，没有则返回null - O(logk)
         if((floor != null && (long)nums[i] - floor <= t) || (ceil != null && (long)ceil - nums[i] <= t)){//long - 防止溢出
             return true;
         }
         set.add(nums[i]);
         if(i >= k){
             set.remove(nums[i - k]);
         }
     }
     return false;
 }
