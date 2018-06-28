/** 1040. Max Chunks To Make Sorted II(lintcode) / 768. Max Chunks To Make Sorted II(leetcode)
 *    Given an array arr of integers (not necessarily distinct), we split the array into some number of "chunks" (partitions), 
 *  and individually sort each chunk. After concatenating them, the result equals the sorted array.
 *    What is the most number of chunks we could have made?
 *
 *    Note: arr will have length in range [1, 2000].
 *          arr[i] will be an integer in range [0, 10^8].
 *
 *    Example: 1)Input: arr = [5,4,3,2,1];  Output: 1
 *             2)Input: arr = [2,1,3,4,4];  Output: 4
 *             3)Input: arr = [1,1,0,0,1];  Output: 2
 */
 
 /* Method 1: Sort and compair the sum - O(nlogn) time and O(n) space complexity
  *     Idea: 可以分割的块在排序前和排序后的块内元素之和是相等的。
  */  
  public int maxChunksToSorted(int[] arr) {
      if(arr == null || arr.length == 0){
          return 0;
      }
      int chunks = 0;
      int[] temp = arr.clone();
      Arrays.sort(temp);
      int[] sum = new int[arr.length];
      int[] sort_sum = new int[arr.length];
      sum[0] = arr[0];
      sort_sum[0] = temp[0];

      if(sum[0] == sort_sum[0]){
          chunks++;
      }
      for(int i = 1; i < arr.length; i++){
          sum[i] = sum[i-1] + arr[i];
          sort_sum[i] = sort_sum[i-1] + temp[i];
          if(sum[i] == sort_sum[i]){
              chunks++; 
          }  
      }
      return chunks;
  }

 /* Method 2: Left Max and Right Min - O(n) time and space complexity
  *     Idea: 遍历数组，对于i位置的元素，找到它之前元素的最大值和它之后元素的最小值。
  *           如果可以以i处分割分块，那么arr[i]前面的元素一定要比它之后的最小元素大或等于。
  */ 
  public int maxChunksToSorted(int[] arr) {
       if(arr == null || arr.length <= 1){
           return arr.length;
       }
       int chunks = 0;
       int[] left_max = new int[arr.length]; // the maximum element in subarray [0,i]
       int[] right_min = new int[arr.length]; // the minimum element in subarray (i,length-1]
       left_max[0] = arr[0];
       for(int i = 1; i < arr.length; i++){
           left_max[i] = Math.max(left_max[i-1],arr[i]);
       }
       right_min[arr.length-2] = arr[arr.length-1];
       for(int i = arr.length - 3; i >= 0; i--){
           right_min[i] = Math.min(right_min[i+1],arr[i+1]);
       }
       for(int i = 0; i < arr.length-1; i++){
           if(left_max[i] <= right_min[i]){
               chunks++;
           }
       }
       chunks++;
       return chunks;
   }
