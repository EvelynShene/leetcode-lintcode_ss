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
 
 /* Method 1: Sort and compair the sum
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
