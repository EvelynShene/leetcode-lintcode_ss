/** 190. Next Permutation II(lintcode) / 31. Next Permutation II(leetcode)
 *    Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *    If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *    The replacement must be in-place and use only constant extra memory.
 *   
 *    Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *          1,2,3 → 1,3,2
 *          3,2,1 → 1,2,3
 *          1,1,5 → 1,5,1
 */
 
 /** 分析： [6 5 4 8 7 5 1]
  *   从后往前，找到第一个排列递增的位置，如[4,8]；
  *   从8开始找大于4的最小数，如5；将[4]、[5]位置交换（[6 5 5 8 7 4 1]）
  *   再将8开始之后的数组反转排列，即可得到“下一个”排列
  */
  
public class Solution {
    /**
     * @param nums: An array of integers
     * @return: nothing
     */
    public void nextPermutation(int[] nums) {
        // write your code here
        int i = 0;
        int j = 0;
        for(i = nums.length-1;i > 0; i--){
            if(nums[i] > nums[i-1]){
                for(j = i; j < nums.length; j++){
                    if(nums[j] <= nums[i-1]){
                        break;
                    }
                }
                int temp = nums[i-1];
                nums[i-1] = nums[j-1];
                nums[j-1] = temp;
                break;
            }
        }
        
        j = nums.length-1;
        while(i < j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }        
    }
}

public void nextPermutation(int[] nums) {
    int i = 0;
    int j = 0;
    int temp = 0;
    for(i = nums.length-1; i > 0; i--){
        if(nums[i] > nums[i-1]){
            break;
        }
    }
    if(i != 0){
        for(j = i; j < nums.length; j++){
            if(nums[j] <= nums[i-1]){
                break;
            }
        }
        temp = nums[i-1];
        nums[i-1] = nums[j-1];
        nums[j-1] = temp;
    }

    j = nums.length-1;
    while(i < j){
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        i++;
        j--;
    }
}
