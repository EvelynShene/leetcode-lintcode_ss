/** 1287. Increasing Triplet Subsequence(lintcode) / 334. Increasing Triplet Subsequence(leetcode)
 *     Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 *     Formally the function should: 
 *          Return true if there exists i, j, k 
 *                                 such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 *      
 *     Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
 *
 *     Example: 1) Input: [1,2,3,4,5] ; Output: true
 *              2) Input: [5,4,3,2,1] ; Output: false
 */
 
 /* Method: 
  *   1）维护两个变量small和big，small记录当前遍历到的子数组的最小值；big记录当前遍历到的子数组的第二最小值。
  *   2）big和small初始化为32位整型最大值，只要big有赋值，说明当前子数组中有二元升序数组[arr[i],arr[j]](i < j).
  *   后面遍历过程中，只要存在大于big的数，那么符合题目要求的三元子序列就存在。
  *   3）遍历过程中实时更新small和big。
  */
 public boolean increasingTriplet(int[] nums) {
    if(nums == null || nums.length < 3){
        return false;
    }
    int small = Integer.MAX_VALUE;
    int big = Integer.MAX_VALUE;
    for(int i = 0; i < nums.length; i++){
        if(nums[i] <= small){
            small = nums[i];
        }
        else if(nums[i] <= big){
            big = nums[i];
        }
        else{
            return true;
        }
    }
    return false;
 }
