/** 268. Missing Number(leetcode) / 196. Missing Number(lintcode)
 *     Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from 
 *  the array.
 *
 *     Example: 1) Input: [3,0,1] ; Output: 2
 *              2) Input: [9,6,4,2,3,5,7,0,1] ; Output: 8
 *
 *     Note: Your algorithm should run in linear runtime complexity. 
 *           Could you implement it using only constant extra space complexity?
 */
 
 //Method 1: Math
 class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        for(int i = 0; i < n; i++){
            sum -= nums[i];
        }
        return sum;
    }
}

//Method 2: Bit Manipulation
public int findMissing(int[] nums) {
    int n = nums.length;
    for(int i = 0; i < nums.length; i++){
        n ^= i ^ nums[i];
    }
    return n;
}
