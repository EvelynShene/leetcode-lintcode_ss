/**  1334. Rotate Array(lintcode) /189. Rotate Array(leetcode)
 *   Given an array, rotate the array to the right by k steps, where k is non-negative.
 *   Example:  Input: [1,2,3,4,5,6,7] and k = 3
 *             Output: [5,6,7,1,2,3,4]
 *   Note: 
 *      Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 *      Could you do it in-place with O(1) extra space?
 */


//Method 1: Using Extra Array
public int[] rotate(int[] nums, int k) {
    // Write your code here    
    int[] temp = new int[nums.length];
    int index = 0;
    k = k % nums.length;

    for(int i = nums.length-k; i < nums.length; i++){
        temp[index] = nums[i];
        index++;
    }

    for(int i = 0; i < nums.length-k;i++){
        temp[index] = nums[i];
        index++;
    }
    return temp;

}

//Method 2: Using Reverse
/* idea: 
 *    Original List                   : [1 2 3 4 5 6 7]
 *    After reversing all numbers     : [7 6 5 4 3 2 1]
 *    After reversing first k numbers : [5 6 7] 4 3 2 1
 *    After revering last n-k numbers :  5 6 7 [1 2 3 4] --> Result
 */

public int[] rotate(int[] nums, int k) {
    k = k % nums.length;

    reverse(nums,0,nums.length-1);
    reverse(nums,0,k-1);
    reverse(nums,k,nums.length-1);

    return nums;
}

public void reverse(int nums[],int l,int r){
    if(l < 0 || r < 0){
        return;
    }
    for(int i = l; i <= (r+l)/2; i++){
        int temp = nums[i];
        nums[i] = nums[r+l-i];
        nums[r+l-i] = temp;
    }
}
