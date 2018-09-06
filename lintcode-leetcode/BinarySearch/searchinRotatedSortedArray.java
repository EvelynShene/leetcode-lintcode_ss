/** 33. Search in Rotated Sorted Array(leetcode) / 62. Search in Rotated Sorted Array(lintcode)
 *      Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *          (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *      You are given a target value to search. If found in the array return its index, otherwise return -1.
 *      You may assume no duplicate exists in the array.
 *      Your algorithm's runtime complexity must be in the order of O(log n).
 *
 *      Example: 1) For [4, 5, 1, 2, 3] and target=1, return 2.
 *               2) For [4, 5, 1, 2, 3] and target=0, return -1.
 */
 
 //My Method:
 public int search(int[] nums, int target) {
    if(nums == null || nums.length == 0){
        return -1;
    }
    int left = 0;
    int right = nums.length - 1;

    while(left <= right){
        int mid = left + (right - left) / 2; 
        if(nums[mid] == target){
            return mid;
        }
        else if(target < nums[mid]){
            if(nums[left] <= nums[mid]){ // [left, mid] 有序增
                if(target < nums[left]){ //比左边最小的还小，只可能在右边
                    left = mid + 1;
                }
                else{
                    right = mid - 1;
                }
            }
            else{ // [mid, right] 有序增，target比mid小，肯定不可能在右边
                right = mid - 1;
            }
        }
        else{ // nums[mid] < target 
            if(nums[left] <= nums[mid]){ // [left, mid] 是有序的, target比mid大，肯定比左边的都大
                left = mid + 1;
            }
            else{ //nums[left] > nums[mid] - [mid, right] 是有序增
                if(nums[right] < target){
                    right = mid - 1;
                }
                else{
                    left = mid + 1;
                }
            }
        }
    }
    return -1;
}
