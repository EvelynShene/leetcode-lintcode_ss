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

//Method 2: TODO
