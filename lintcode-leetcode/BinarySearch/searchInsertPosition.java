/** 60. Search Insert Position(lintcode)/35. Search Insert Position(leetcode)
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 */
 
 // Use binary search
 public int searchInsert(int[] A, int target) {
      // write your code here
      int l = 0, r = A.length-1;

      while(l <= r){
          int mid = (l+r)/2;
          if(A[mid] == target){
              return mid;
          }
          if(A[mid] < target){
              l = mid + 1;
          }
          else{
              r = mid - 1;
          }
      }

      return l;
  }
