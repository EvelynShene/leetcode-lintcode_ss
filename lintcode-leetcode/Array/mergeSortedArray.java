/** 64. Merge Sorted Array(lintcode)/88. Merge Sorted Array
 *  Given two sorted integer arrays A and B, merge B into A as one sorted array.
 *  Note: You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. 
 *        The number of elements initialized in A and B are m and n respectively.
 */
 
 //Method 1: Space complexity is O(1)
 public void mergeSortedArray(int[] A, int m, int[] B, int n) {
      // write your code here
      int i = m-1, j = n-1;
      int index = m+n-1;

      while(i >= 0 && j >= 0){
          if(A[i] > B[j]){
              A[index] = A[i];
              i--;
          }
          else{
              A[index] = B[j];
              j--;
          }
          index--;
      }
      while(j >= 0){
          A[index] = B[j];
          j--;
          index--;
      }
  }
 
 //Method 2: Space complexity is O(m)
 public void mergeSortedArray(int[] A, int m, int[] B, int n) {
      // write your code here
      int[] temp = A.clone();
      int index = 0;
      int i = 0,j = 0;
      
      while(i < m && j < n){
          if(temp[i] <= B[j]){
              A[index] = temp[i];
              i++;
          }
          else{
              A[index] = B[j];
              j++;
          }
          index++;
      }
      while(i < m){
          A[index] = temp[i];
          i++;
          index++;
      }
      while(j < n){
          A[index] = B[j];
          j++;
          index++;
      }
  }
  
