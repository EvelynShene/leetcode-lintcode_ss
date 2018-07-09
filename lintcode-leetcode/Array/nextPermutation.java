/** 52. Next Permutation(lintcode) / 31. Next Permutation(leetcode)
 *  (lintcode:)
 *    Given a list of integers, which denote a permutation. Find the next permutation in ascending order.
 *
 *    Example: 1) For [1,3,2,3], the next permutation is [1,3,3,2]
 *             2) For [4,3,2,1], the next permutation is [1,2,3,4]
 *
 *  (leetcode:)
 *    Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *    If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *    The replacement must be in-place and use only constant extra memory.
 *    Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *
 *            Example: 1,2,3 → 1,3,2
 *                     3,2,1 → 1,2,3
 *                     1,1,5 → 1,5,1
 */
 
 /* Method: 
  *   从后往前，找到第一个nums[i-1] < nums[i]的位置i-1，将它与后面比它大的最小数交换，然后再把位置[i,length-1]的数逆序（即升序排列）即可。
  */
 public int[] nextPermutation(int[] nums) {
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
    return nums;
 }
