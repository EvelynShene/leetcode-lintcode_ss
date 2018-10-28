/** 448. Find All Numbers Disappeared in an Array(leetcode) / 1236. Find All Numbers Disappeared in an Array(lintcode)
 *      Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others 
 *  appear once.
 *      Find all the elements of [1, n] inclusive that do not appear in this array.
 *      Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count 
 *  as extra space.
 *
 *      Example: Input: [4,3,2,7,8,2,3,1] ; Output: [5,6]
 */
 
 //Method 1: O(n) time and space complexity
 class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if(nums == null || nums.length == 0){
            return res;
        }
        int n = nums.length;
        int[] miss = new int[n];
        for(int i = 0; i < n; i++){
            miss[nums[i] - 1] = 1;
        }
        for(int i = 0; i < n; i++){
            if(miss[i] == 0){
                res.add(i + 1);
            }
        }
        return res;
    }
}

//Method 2: O(n^2) worst time and O(1) space complexity
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if(nums == null || nums.length == 0){
            return res;
        }
        int n = nums.length;
        for(int i = 0; i < n; i++){
            while(nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]){
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for(int i = 0; i < n; i++){
            if(nums[i] != i + 1){
                res.add(i + 1);
            }
        }
        return res;
    }
}

/* Method 3: O(n) worst time and O(1) space complexity
 *     Idea: 如果[1,n]中的某个数i存在，就把nums[i]的值变为负数，这样一来，[1,n]中不存在的数的值就一定是正数
 */
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if(nums == null || nums.length == 0){
            return res;
        }
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int val = Math.abs(nums[i]) - 1;
            if(nums[val] > 0){
                nums[val] = -nums[val];
            }
        }
        for(int i = 0; i < n; i++){
            if(nums[i] > 0){
                res.add(i + 1);
            }
        }
        return res;
    }
}
