/** 406. Minimum Size Subarray Sum(lintcode) / 209. Minimum Size Subarray Sum (leetcode)
 *    Given an array of n positive integers and a positive integer s, 
 *    find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return -1 instead.
 *
 *    Example: Given the array [2,3,1,2,4,3] and s = 7, Return 2.
 *          the subarray [4,3] has the minimal length under the problem constraint.
 */
 
 /* Method 1: Use 2 pointers, sliding the window - O(n) Time and O(1) Space
  *   滑动窗口 [left, right] 初始大小为0，滑动的规则如下：
  *     - 若元素之和 < s，窗口右边沿向右延伸，直到 元素之和≥s 为止。
  *     - 若元素之和 ≥ s，更新最小长度。然后窗口左边沿右移一位（即移除窗口中第一个元素），重复第1步。
  *   Example: 
  *     [2,3,1,2,4,3]
  *     [2,3,1,2]       ----- minimum window size = 4
  *       [3,1,2,4]     ----- minimum window size = 4
  *         [1,2,4]     ----- minimum window size = 3
  *           [2,4,3]   ----- minimum window size = 3
  *             [4,3]   ----- minimum window size = 2
  */
  
  public int minimumSize(int[] nums, int s) {
      // write your code here
      if(nums == null || nums.length == 0){
          return -1;
      }

      int min_array = Integer.MAX_VALUE;
      int sum = 0;
      int l = 0, r = 0;
      while(l < nums.length && r <= nums.length){
          while(sum < s && r < nums.length){
              sum += nums[r];
              r++;
          }

          if(sum >= s){
              min_array = Math.min(min_array,r-l);
              sum -= nums[l];
          }
          l++;
      }
      if(min_array == Integer.MAX_VALUE){
          return -1;
      }
      return min_array;
  }
  
/* Method 2-(1): Use binary Search
 * Idea: 数组元素都是正整数，所以数组的子数组和一定大于0。用二分法尝试各个长度的子数组是否满足条件。
 *       例如：[2,3,1,2,4,3]长度为6，如果长度为 6/2 = 3 的Subarray数组里没有和大于等于7的，
 *       说明和大于等于7的最小长度的子数组的长度肯定大于3，所以只需在长度在[4，6]的子数组中找即可。
 */
public class Solution {  
    public int minSubArrayLen(int s, int[] nums) {  
        if (nums == null || nums.length == 0) return 0;  
        int i=1, j=nums.length;  
        boolean found = false;  
        while (i<=j) {  
            int m = (i+j)/2;  
            if (window(s, nums, m)) {   
                j = m-1;   
                found = true;   
            } else {  
                i=m+1;  
            }  
        }  
        return found ? i : 0;  
    }  
    private boolean window(int s, int[] nums, int w) {  
        int sum = 0;  
        for(int i=0; i<nums.length; i++) {  
            if (i>=w) sum -= nums[i-w];  
            sum += nums[i];  
            if (sum>=s) return true;  
        }  
        return false;  
    }  
}  

/* Method 2-(2): Same idea to 2-(1)
 * Note: 代码中用到了一个新数组sum[i]，用来记录前i个数组元素的和。初始化：sum[0] = 0;sum[1] = nums[0].
 *       长度的变化变化可以体现在s这个数上，例如：
 *          [2,3,1,2,4,3]对s=7是考察len=6的子数组的一个例子；而[2,3,1,2,4,3]对s=7+2=9是考察从3开始len=5的子数组[3,1,2,4,3]的一个例子
 *       这一过程类似于Method 1 中的窗口左侧滑动，然后用二分法在sum[]数组中找最小的大于s的窗口右侧(即minimum window size)
 *       循环这一过程记录minimum window size，最后的到最小的大于s的窗口值
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int[] sum = new int[nums.length+1];
        for(int i = 1; i < sum.length; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
        int min_size = nums.length+1;
        for(int i = 0; i < sum.length; i++){
            int r = findRightSide(i+1,nums,sum,sum[i]+s);
            if(r == sum.length) break; //no such subarray to satisfied that their sum >= s
            min_size = Math.min(min_size, r-i);
        }
        if(min_size == nums.length+1){
            return 0;
        }
        return min_size;
    }
    
    public int findRightSide(int start, int[] nums, int[] sum, int s){
        int end = sum.length-1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(sum[mid] >= s){
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        return start;
    }
}
