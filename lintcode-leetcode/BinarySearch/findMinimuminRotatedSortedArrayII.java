/** 154. Find Minimum in Rotated Sorted Array II(leetcode) / 160. Find Minimum in Rotated Sorted Array II(lintcode)
 *      Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *        (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *      Find the minimum element.
 *      The array may contain duplicates.
 *
 *      Example: Given [4,4,5,6,7,0,1,2] return 0.
 *
 *      Note: 1) This is a follow up problem to Find Minimum in Rotated Sorted Array.
 *            2) Would allow duplicates affect the run-time complexity? How and why?
 */
 
 //Method: - Average O(logn) time complexity
 class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while(left < right){
            int mid = left + (right - left) / 2;
    
            if(nums[mid] < nums[right]){ // [mid, right] is ascending order
                right = mid ;
            }
            else if(nums[mid] > nums[right]){
                left = mid + 1;
            }
            else{
                right--;
            }
        }
        return nums[left];
    }
}
