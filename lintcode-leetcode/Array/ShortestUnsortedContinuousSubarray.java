/** 1157. Shortest Unsorted Continuous Subarray(lintcode) / 581. Shortest Unsorted Continuous Subarray(leetcode)
 *  Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, 
 *  then the whole array will be sorted in ascending order, too.
 *  You need to find the shortest such subarray and output its length.
 */

  public int findUnsortedSubarray(int[] nums) {
    // Write your code here
    int[] a = nums.clone();
    Arrays.sort(a);
    int i = 0;
    for(; i < nums.length; i++){
        if(nums[i] != a[i]) break;
    }
    
    if(i == nums.length)
        return 0;

    int j = nums.length-1;
    for(; j >= 0; j--){
        if(nums[j] != a[j]) break;
    }

    return (j-i+1);
  }
