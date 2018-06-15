/** 100. Remove Duplicates from Sorted Array(lintcode)/ 26. Remove Duplicates from Sorted Array(leetcode)
 *  Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 *  Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 */
 
public class Solution {
    /*
     * @param nums: An ineger array
     * @return: An integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0){
            return 0;
        }
       
        int index = 0;
        for(int curt = 1; curt < nums.length; curt++){
            if(nums[curt] != nums[curt-1]){
                nums[index+1] = nums[curt];
                index++;
            }
        }
        return index+1;
    }
}

