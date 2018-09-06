/** 81. Search in Rotated Sorted Array II(leetcode) / 63. Search in Rotated Sorted Array II(lintcode)
 *      Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *          (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 *      You are given a target value to search. If found in the array return true, otherwise return false.
 *
 *      Example: 1) Given [1, 1, 0, 1, 1, 1] and target = 0, return true.
 *               2) Given [1, 1, 1, 1, 1, 1] and target = 0, return false.
 *
 *      Follow up: 
 *          1) This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 *          2) Would this affect the run-time complexity? How and why?
 */
 
 //My Method:
 class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return false;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(target == nums[mid]){
                return true;
            }
            else if(target < nums[mid]){
                while(left <= mid && nums[left] == nums[mid]){
                    left++;
                }
                if(left < mid && nums[left] < nums[mid]){ // [left, mid] is sorted in ascending order
                    if(target < nums[left]){
                        left = mid + 1; 
                        while(left < right && nums[left] == nums[mid]){
                            left++;
                        }
                    }
                    else{
                        right = mid - 1;
                        while(left <= right && nums[right] == nums[mid]){
                            right--;
                        }
                    }
                }
                // nums[left] > nums[mid] => [mid,right] is sorted in ascending order
                else if(left < mid && nums[left] > nums[mid]){
                    right = mid - 1;
                    while(left <= right && nums[right] == nums[mid]){
                        right--;
                    }
                }
            }
            else{ // target > nums[mid]
                while(left <= mid && nums[left] == nums[mid]){
                    left++;
                }
                if(left < mid && nums[left] < nums[mid]){ // [left, mid] is sorted in ascending order
                    left = mid + 1;
                    while(left < right && nums[left] == nums[mid]){
                        left++;
                    }
                }
                // nums[left] > nums[mid] => [mid,right] is sorted in ascending order
                else if(left < mid && nums[left] > nums[mid]){ 
                    if(target > nums[right]){
                        right = mid - 1;
                        while(left <= right && nums[right] == nums[mid]){
                            right--;
                        }
                    }
                    else{
                        left = mid + 1;
                        while(left < right && nums[left] == nums[mid]){
                            left++;
                        }
                    }
                }
            }
        }
        return false;
    }
}
