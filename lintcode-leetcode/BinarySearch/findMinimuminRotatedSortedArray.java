/** 153. Find Minimum in Rotated Sorted Array(leetcode) / 159. Find Minimum in Rotated Sorted Array(lintcode)
 *      Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *          (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *      Find the minimum element.
 *      You may assume no duplicate exists in the array.
 *
 *      Example: Given [4, 5, 6, 7, 0, 1, 2] return 0
 */ 
 
 //Method: 
 public int findMin(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    while(left < right){
        int mid = left + (right - left) / 2;
        if(nums[left] <= nums[mid] && nums[mid] > nums[right]){ // [left,mid] is sorted in ascending order
            left = mid + 1;
        }
        else{ // nums[left] >= nums[mid] => [mid,right] is sorted in ascending order
            right = mid;
        }
    }
    return nums[left];
 }
