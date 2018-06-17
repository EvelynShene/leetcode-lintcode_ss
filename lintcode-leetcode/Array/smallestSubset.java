/** 761. Smallest Subset(lintcode)
 *    Given an array of non-negative integers. Our task is to find minimum number of elements 
 *    such that their sum should be greater than the sum of rest of the elements of the array.
 *
 *    Example:
 *      Given nums = [3, 1, 7, 1], return 1
 *      Given nums = [2, 1, 2], return 2
 */
 
 public int minElements(int[] arr) {
      // write your code here
      if(arr == null || arr.length == 0){
          return 0;
      }
      Arrays.sort(arr);
      int[] sum = new int[arr.length];
      sum[0] = arr[0];
      for(int i = 1; i < arr.length; i++){
          sum[i] = sum[i-1] + arr[i];
      }
      for(int i = arr.length-2; i >= 0; i--){
          if(sum[arr.length-1] - sum[i] > sum[i]){
              return arr.length-1-i;
          }
      }
      return arr.length-1;
  }
