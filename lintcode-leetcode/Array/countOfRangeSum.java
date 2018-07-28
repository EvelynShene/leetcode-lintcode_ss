/** 1293.  Count of Range Sum(lintcode) / 327. Count of Range Sum(leetcode)
 *    Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 *    Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 *   【找有多少个不同的区间使得每个区间的和在给定的上下限之间】
 *
 *    Note: A naive algorithm of O(n2) is trivial. You MUST do better than that.
 *
 *    Example: Given nums = [-2, 5, -1], lower = -2, upper = 2, Return 3.
 *             The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 */
 
 //Method 1: Brute Force - O(n^2) time complexity [AC in leetcode, TLE in lintcode]
 public int countRangeSum(int[] nums, int lower, int upper) {
    if(nums == null || nums.length == 0){
        return 0;
    }
    int n = nums.length;
    int count = 0;
    for(int i = 0; i < n; i++){
        long sum = 0;
        for(int j = i; j < n; j++){
            sum += nums[j];
            if(sum >= lower && sum <= upper){
                count++;
            }
        }
    }
    return count;
 }
 
 //Method 2: Idea from http://qsxuan.com/articles/930.html
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int ans = 0;
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum[i + 1] = sum[i] + nums[i];
        }
        ans = sort(sum, 0, nums.length + 1, lower, upper);
        return ans;
    }

    public int sort(long[] sum, int l, int r, int lower, int upper) {
        if (r - l <= 1)	return 0;
        int ret = 0;
        int mid = (l + r) >> 1;
        long[] temp = new long[r - l];
        ret += sort(sum, l, mid, lower, upper) + sort(sum, mid, r, lower, upper);
        int rl = mid, rr = mid, j = mid;
        for (int i = l, k = 0; i < mid; ++i) {
            if (rl < r && sum[rl] - sum[i] <= upper) {
                while (rl < r && sum[rl] - sum[i] < lower) rl++;
                while (rr < r && sum[rr] - sum[i] <= upper)	rr++;
                ret += rr - rl;
            }
            while (j < r && sum[j] < sum[i]) temp[k++] = sum[j++];
            temp[k++] = sum[i];
        }
        System.arraycopy(temp, 0, sum, l, j - l);
        return ret;
    }
}
