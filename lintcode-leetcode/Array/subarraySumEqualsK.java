/** 560. Subarray Sum Equals K(leetcode) / 838. Subarray Sum Equals K(lintcode)
 *      Given an array of integers and an integer k, you need to find the total number of continuous subarrays 
 *  whose sum equals to k.
 *
 *      Example: Input:nums = [1,1,1], k = 2 ; Output: 2
 *
 *      Note: 1) The length of the array is in range [1, 20,000].
 *            2) The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
 
//My Method: Use Cummulative sum - O(n^2) time and O(n) space complexity
public int subarraySum(int[] nums, int k) {
    if(nums == null || nums.length == 0){
        return 0;
    }
    int n = nums.length;
    int[] sum = new int[n + 1];
    for(int i = 1; i <= n; i++){
        sum[i] = sum[i - 1] + nums[i - 1];
    }

    int count = 0;
    for(int i = 0; i < n; i++){
        for(int j = i + 1; j <= n; j++){
            if(sum[j] - sum[i] == k){
                count++;
            }
        }
    }
    return count;
}

//Method 2: Use Cummulative sum - O(n^2) time and O(1) space complexity
public int subarraySum(int[] nums, int k) {
    if(nums == null || nums.length == 0){
        return 0;
    }
    int n = nums.length;
    int count = 0;
    for(int i = 0; i < n; i++){
        int sum = 0;
        for(int j = i; j < n; j++){
            sum += nums[j];
            if(sum == k){
                count++;
            }
        }
    }
    return count;
}

//Method 3: Use HashMap - 类似Two Sum - O(n) time and O(n) space complexity
class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count = 0;
        map.put(0, 1);
        for(int i = 0; i < n; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return count;
    }
}
