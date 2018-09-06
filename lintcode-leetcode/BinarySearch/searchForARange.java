/** 34. Find First and Last Position of Element in Sorted Array(leetcode) / 61. Search for a Range(lintcode)
 *      Given an array of integers nums sorted in ascending order, find the starting and ending position of a 
 *   given target value.
 *      Your algorithm's runtime complexity must be in the order of O(log n).
 *      If the target is not found in the array, return [-1, -1].
 *
 *      Example: 1) Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 *               2) Given nums = [5,7,7,8,8,10], target = 6, return [-1,-1].
 */
 
 //My Method: 
 class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = res[1] = -1;
        if(nums == null || nums.length == 0){
            return res;
        }
        int left = 0;
        int right = nums.length - 1;
        
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                res[0] = findIndex(nums, left, mid, target, true);
                res[1] = findIndex(nums, mid, right, target, false);
                break;
            }
            else if(nums[mid] < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return res;
    }
    
    public int findIndex(int[] nums, int left, int right, int target, boolean findFirst){
        if(left == right){
            return left;
        }
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            }
            else if(nums[mid] > target){
                right = mid - 1;
            }
            else{ //nums[mid] == target
                if(findFirst){
                    right = mid - 1;
                }
                else{
                    left = mid + 1;
                }
            }
        }
        if(findFirst){
            return left;
        }
        return right;
    }
}
