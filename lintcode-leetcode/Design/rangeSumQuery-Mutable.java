/** 307. Range Sum Query - Mutable(leetcode) / 840. Range Sum Query - Mutable(lintcode)
 *      Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *      The update(i, val) function modifies nums by updating the element at index i to val.
 *
 *      Example: Given nums = [1, 3, 5]
 *               sumRange(0, 2) -> 9
 *               update(1, 2)
 *               sumRange(0, 2) -> 8
 *
 *      Note: 1) The array is only modifiable by the update function.
 *            2) You may assume the number of calls to update and sumRange function is distributed evenly.
 */

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
 
 //My Method: O(n) per update(), O(1) per sumRange and O(n) time pre-computation; O(n) space complexity
 class NumArray {
    int[] sum;
    int[] numbers;
    public NumArray(int[] nums) {
        int n = nums.length;
        numbers = new int[n];
        sum = new int[n + 1];
        for(int i = 1; i <= n; i++){
            numbers[i - 1] = nums[i - 1];
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }
    
    public void update(int i, int val) {
        int old = numbers[i];
        numbers[i] = val;
        for(int index = i + 1; index <= numbers.length; index++){
            sum[index] = sum[index] + val - old;
        }
    }
    
    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
