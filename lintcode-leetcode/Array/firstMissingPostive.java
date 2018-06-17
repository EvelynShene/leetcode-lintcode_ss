/** 189. First Missing Positive(lintcode) /41. First Missing Positive
 *    Given an unsorted integer array, find the first missing positive integer.
 *    Example:
 *        Given [1,2,0] return 3,
 *        Given [3,4,-1,1] return 2.
 */
 
 //Method 1: Sort Array - O(nlogn) [AC in lintcode]
 public int firstMissingPositive(int[] A) {
      // write your code here
      Arrays.sort(A);
      int firstpostive = -1;
      for(int i = 0; i < A.length; i++){
          if(firstpostive == -1 && A[i] > 0 && A[i] != 1){
              return A[i]-1;
          }
          if(A[i] > 0){
              firstpostive++;
              if(i+1 < A.length && A[i+1] - A[i] > 1){
                  return A[i]+1;
              }
          }
      }
      if(firstpostive == -1)
          return 1;
      else
          return A[A.length-1]+1;
  }
  
  /* Method 2: Your algorithm should run in O(n) time and uses constant extra space.
   * Idea: 数组最多有n个正整数，将每个正整数i放在i-1的位置上，则第一个不满足nums[i] = i+1的位置就指示了第一个缺少的最小正整数
   */
  public int firstMissingPositive(int[] nums) {
      int i = 0;
      while(i < nums.length){
          if(nums[i] == i+1 || nums[i] <= 0 || nums[i] > nums.length){
              i++;
          }
          //use "nums[nums[i]-1] != nums[i]" not "nums[i] != i+1" is to avoid same numbers to lead to endless loop. Eg:[1,1]
          else if(nums[nums[i]-1] != nums[i]){ 
              int temp = nums[nums[i]-1];
              nums[nums[i]-1] = nums[i];
              nums[i] = temp;
          }
          else{
              i++;
          }
      }

      for(i = 0; i < nums.length; i++){
          if(nums[i] != i+1){
              break;
          }
      }
      return i+1;
  }
  
