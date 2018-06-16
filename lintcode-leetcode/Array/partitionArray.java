/** 31. Partition Array(lintcode)
 *    Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:
 *       - All elements < k are moved to the left
 *       - All elements >= k are moved to the right
 *    Return the partitioning index, i.e the first index i nums[i] >= k.
 *    If all elements in nums are smaller than k, then return nums.length
 */

//Method 1: Use Array Sort ; O(nlogn)

  public int partitionArray(int[] nums, int k) {
      // write your code here
      Arrays.sort(nums);
      for(int i = 0; i < nums.length; i++){
          if(nums[i] >= k){
              return i;
          }
      }
      return nums.length;
  }

//Method 2: partition the array in-place and in O(n)
  public int partitionArray(int[] nums, int k) {
      // write your code here
      int index = 0;
      int end = nums.length-1;
      while(index <= end){
          if(nums[index] < k){
              index++;
          }
          else{
              if(index != end){
                  int temp = nums[index];
                  nums[index] = nums[end];
                  nums[end] = temp;
              }
              end--;
          }
      }
      return index;
  }
