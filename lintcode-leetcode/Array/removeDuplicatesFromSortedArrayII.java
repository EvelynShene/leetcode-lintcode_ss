/** 101. Remove Duplicates from Sorted Array II(lintcode)/80. Remove Duplicates from Sorted Array II
 *  Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice 
 *        and return the new length.
 */
 
 //Method 1: Use extra array[Accepted in lintcode] 
 public int removeDuplicates(int[] nums) {
    // write your code here
    if(nums == null || nums.length <= 2){
        return nums.length;
    }

    int[] temp = nums.clone();
    int index = 1;
    int dup = 1;
    for(int i = 1; i < temp.length; i++){
        if(temp[i] != temp[i-1]){
            dup = 1;
            nums[index] = temp[i];
            index++;
        }
        else{
            dup++;
            if(dup <= 2){
                nums[index] = temp[i];
                index++;
            }
        }
    }
    return index;
}


/** In leetcode:
 *  Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 */
//Method 2: 
public int removeDuplicates(int[] nums) {
    if(nums == null || nums.length == 0){
        return nums.length;
    }

    int index = 0;
    int dup = 1;
    for(int curt = 1; curt < nums.length; curt++){
        if(nums[curt] != nums[curt-1]){
            dup = 1;
            nums[++index] = nums[curt];
        }
        else{
            dup++;
            if(dup < 3){
                nums[++index] = nums[curt];
            }
        }
    }
    return index+1;
}

