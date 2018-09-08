/** 300. Longest Increasing Subsequence(leetcode) / 76. Longest Increasing Subsequence(lintcode)
 *      Given an unsorted array of integers, find the length of longest increasing subsequence.
 *     
 *      Note: 1) There may be more than one LIS combination, it is only necessary for you to return the length.
 *            2) Your algorithm should run in O(n^2) complexity.
 *
 *      Example: Input: [10,9,2,5,3,7,101,18] ; Output: 4 
 *                Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
 *
 *      Follow up: Could you improve it to O(n log n) time complexity?
 */
 
 /* Method 1: O(n^2) time and O(n) space complexity
  *     思路：数组dp, dp[i]表示以nums[i]为结尾的最长递增子串的长度，对于每一个nums[i], 从第一个数再搜索到i，
  *       如果发现某个数小于nums[i]，更新dp[i]: dp[i] = max(dp[i], dp[j] + 1)，
  *      【即比较当前dp[i]的值和那个小于num[i]的数的dp值加1的大小】
  */
 public int lengthOfLIS(int[] nums) {
    if(nums == null || nums.length == 0){
        return 0;
    }
    int n = nums.length;
    int[] dp = new int[n];
    int max = 0;
    for(int i = 0; i < n; i++){
        dp[i] = 1;
        for(int j = 0; j < i; j++){
            if(nums[i] > nums[j]){
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        max = Math.max(dp[i], max);
    }
    return max;
 }
 
 /** Method 2: Binary Search - O(nlogn) time complexity
  *     思路：建立一个list，先把首元素放进去，然后顺序遍历并比较之后的元素：
  *             1）如果遍历到的新元素比list中的首元素小的话，替换首元素为此新元素;
  *             2）如果遍历到的新元素比list中的末尾元素大的话，将此新元素添加到ends数组末尾(注意不是覆盖原末尾元素);
  *             3) 如果遍历到的新元素比ends数组首元素大，比尾元素小时，用二分查找法找到第一个不小于此新元素的位置【注意是等于也算】
  *               替换掉这个找到位置上原来的数字。
  *         以此类推直至遍历完整个nums数组，最后list的长度就是要求的LIS的长度。
  *         Note(特别注意): list的值并不一定是一个真实的LIS，比如若输入数组nums为{4, 2， 4， 5， 3， 7}，
  *               算完后的list为{2， 3， 5， 7}，可以发现它不是一个原数组的LIS，只是长度相等而已。
  */
  
 class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        List<Integer> res = new ArrayList<Integer>();
        res.add(nums[0]);
        for(int i = 1; i < n; i++){
            if(nums[i] < res.get(0)){
                res.add(0,nums[i]);
                res.remove(1);
            }
            else if(nums[i] > res.get(res.size() - 1)){
                res.add(nums[i]);
            }
            else if(nums[i] > res.get(0) && nums[i] < res.get(res.size() - 1)){
                replaceFirstLargerNum(res, nums[i]);
            }
        }
        return res.size();
    }
    
    public void replaceFirstLargerNum(List<Integer> res, int num){
        int left = 0;
        int right = res.size() - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(res.get(mid) < num){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        res.add(left, num);
        res.remove(left + 1);
    }
}
