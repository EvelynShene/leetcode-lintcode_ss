/** 608. Two Sum II - Input array is sorted (lintcode) /167. Two Sum II - Input array is sorted (leetcode)
 *      Given an array of integers that is already sorted in ascending order, 
 *   find two numbers such that they add up to a specific target number.
 *      The function twoSum should return indices of the two numbers such that they add up to the target, 
 *   where index1 must be less than index2.
 *
 *   Note: Your returned answers (both index1 and index2) are not zero-based.
 *         You may assume that each input would have exactly one solution and you may not use the same element twice.
 *
 *   Example: Input: numbers = [2,7,11,15], target = 9 ; Output: [1,2]
 *      Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 */
 
 //My Method: Use HashMap (AC in lintcode and leetcode, but sometimes Time Limit Exceeded in leetcode)
  public int[] twoSum(int[] nums, int target) {
      // write your code here
      Map<Integer, Integer> map = new HashMap<Integer,Integer>();
      int[] res = new int[2];
      for(int i = 0; i < nums.length; i++){
          if(map.containsKey(nums[i])){
              res[0] = map.get(nums[i]) + 1;
              res[1] = i + 1;
              break;
          }
          else{
              map.put(target-nums[i],i);
          }
      }
      return res;
  }
  
 /* Method 2: Two Pointer: Because the array is sorted, 
  *          so if nums[first] + nums[last] > target, last--; nums[first] + nums[last] < target, fisrt++
  */
  
  public int[] twoSum(int[] nums, int target) {
      int l = 0, r = nums.length - 1;
      while (l < r) {
        int sum = nums[l] + nums[r];
        if (sum == target) {
          return new int[] { l + 1, r + 1 };
        } else if (sum < target) {
          l++;
        } else {
          r--;
        }
      }
      return null; 
  }
