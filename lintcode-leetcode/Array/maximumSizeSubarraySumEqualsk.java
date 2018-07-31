/** 911. Maximum Size Subarray Sum Equals k(lintcode) / 325. Maximum Size Subarray Sum Equals k(leetcode - locked)
 *      Given an array nums and a target value k, find the maximum length of a subarray that sums to k. 
 *  If there isn't one, return 0 instead.
 *      The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 *
 *      Example: 1) Given nums = [1, -1, 5, -2, 3], k = 3, return 4.
 *                  Explanation: Because the subarray [1, -1, 5, -2] sums to 3 and is the longest.
 *               2) Given nums = [-2, -1, 2, 1], k = 1, return 2.
 *                  Explanation:Because the subarray [-1, 2] sums to 1 and is the longest.
 *
 *      Challenge: Can you do it in O(n) time?
 */
 
 //My Method: Brute Force - O(n^2) time and O(n) space complexity
 public int maxSubArrayLen(int[] nums, int k) {
    if(nums == null || nums.length == 0){
        return 0;
    }
    int n = nums.length;
    int[] sum = new int[n];
    sum[0] = nums[0];
    int max = 0;
    if(sum[0] == k){
        max = 1;
    }
    for(int i = 1; i < n; i++){
        sum[i] = sum[i-1] + nums[i];
        if(sum[i] == k){
            max = i+1;
        }
    }
    if(max == n) 
        return max;
    for(int i = 0; i < n; i++){
        for(int j = i+1; j < n; j++){
            if(sum[j] - sum[i] == k){
                max = Math.max(j-i,max);
            }
        }
    }
    return max;
 }

/* Method 2: Use HashMap —— O(n) time and space complexity
 *   1）原理：sum[j] - sum[i] == k => 区间大小 = j-i
 *   2）用hashMap记录sum[i] + k, 如果map里已经存在sum[j] == sum[i] + k, 则说明有大小为j-i的区间和为sum。
 *   3）同样的sum[i]+k的值只存一次，因为是顺序遍历的，如果sum[i1] + k == sum[i2]+k(i1<i2),
 *  那么后面如果遇到sum[j] == sum[i1] + k == sum[i2] + k，肯定取 (j-i1) 得到的区间长度更长，所以后一个下标i2就不需要保存了
 */
 public int maxSubArrayLen(int[] nums, int k) {
    if(nums == null || nums.length == 0){
        return 0;
    }
    int n = nums.length;
    int max = 0;
    Map<Integer,Integer> map = new HashMap<Integer, Integer>();//map<sum,i>
    int sum = 0;
    map.put(k,-1);
    for(int j = 0; j < n; j++){
        sum += nums[j];
        if(map.containsKey(sum)){//存在sum[i] + k = sum[j] (j > i)
            max = Math.max(max,j - map.get(sum));
        }
        if(!map.containsKey(sum + k)){
            map.put(sum+k,j);
        }
    }
    return max;
 }
