/** 1039. Max Chunks To Make Sorted(lintcode)/769. Max Chunks To Make Sorted(leetcode)
 *    Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], 
 *    we split the array into some number of "chunks" (partitions), and individually sort each chunk.  
 *    After concatenating them, the result equals the sorted array.
 *    What is the most number of chunks we could have made?
 *
 *    Example: Input: arr = [4,3,2,1,0] ; Output: 1
 *    Explanation: Splitting into two or more chunks will not return the required result.
 *                 For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
 */
 
 public int maxChunksToSorted(int[] arr) {
      // write your code here
      if(arr == null || arr.length == 0){
          return 0;
      }
      int chunks = 0;
      int max = arr[0];
      for(int i = 0; i < arr.length; i++){
          max = Math.max(arr[i],max);
          if(max == i){
              chunks++;
          }
      }
      return chunks;
  }
  
  //More concise code:
  public int maxChunksToSorted(int[] arr) {
      int chunks = 0;
      int max = 0;
      for(int i = 0; i < arr.length; i++){
          max = Math.max(arr[i],max);
          if(max == i){
              chunks++;
          }
      }
      return chunks;
  }
