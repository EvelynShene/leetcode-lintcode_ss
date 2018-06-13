/** 539. Move Zeroes(lintcode)/283. Move Zeroes(leetcode)
 *  Given an array nums, write a function to move all 0's to the end of it 
 *  while maintaining the relative order of the non-zero elements.
 *    Note: You must do this in-place without making a copy of the array. Minimize the total number of operations.
 */
 
 public void moveZeroes(int[] nums) {
    // write your code here
    int current = 0, last = 0;
    int temp = 0;
    while(current < nums.length){
        if(nums[current] != 0){
            if(nums[last] != 0){
                last++;
            }
            else{
                if(current != last){
                    nums[last] = nums[current];
                    nums[current] = 0;
                    last++;
                }
            }
        }
        current++;
    }
}
