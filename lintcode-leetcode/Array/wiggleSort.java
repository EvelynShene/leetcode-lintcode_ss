/** 508. Wiggle Sort(lintcode)/280. Wiggle Sort(leetcode-locked)
 *    Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *    Note: Please complete the problem in-place.
 *
 *    Example: Given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */
 
 /* Method 1: Sort Array - O(nlogn)
  *  Idea: First sort the array, then exchange the second and the third number so that n[1]<= n[2]<= n[3] becomes n[1]<= n[3]<= n[2];
  *         then exchange the fourth and the fifth number, ..., finally we get the wiggle sorted array.
  */
  public void wiggleSort(int[] nums) {
      // write your code here
      if(nums == null || nums.length < 2){
          return; 
      }
      Arrays.sort(nums);
      int i = 1;
      while(i+1 < nums.length){
          int temp = nums[i];
          nums[i] = nums[i+1];
          nums[i+1] = temp;
          i += 2;
      }    
  }

/* Method 2: Swap numbers using definition - O(n)
 *   Idea: From definition of Wiggle sorted array, we have:
 *         1) If i is odd, n[i] >= n[i-1]
 *         2) If i is even, n[i] <= n[i-1]
 *    So check the array once to exchange all unsatisfied pairs (n[i-1],n[i]).
 */
 
  public void wiggleSort(int[] nums) {
      // write your code here
      if(nums == null || nums.length < 2){
          return; 
      }

      for(int i = 1; i < nums.length; i++){
          if((i % 2 == 0 && nums[i] > nums[i-1]) || (i % 2 ==1 && nums[i] < nums[i-1])){
              int temp = nums[i-1];
              nums[i-1] = nums[i];
              nums[i] = temp;
          }
      }
  }
