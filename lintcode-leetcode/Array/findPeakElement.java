/** 75. Find Peak Element(lintcode)
 *     There is an integer array which has the following features:
 *        - The numbers in adjacent positions are different.
 *        - A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
 *     We define a position P is a peak if: A[P] > A[P-1] && A[P] > A[P+1]
 *     Find a peak element in this array. Return the index of the peak.
 *
 *     Note: It's guaranteed the array has at least one peak.
 *           The array may contain multiple peeks, find any of them.
 *           The array has at least 3 numbers in it.
 *
 *     Example: Given [1, 2, 1, 3, 4, 5, 7, 6]; Return index 1 (which is number 2) or 6 (which is number 7)
 */
 
 //My Method: Time complexity O(logN)
  public int findPeak(int[] A) {
      // write your code here
      int l = 0, r = A.length - 1;
      while(l < r){
          int mid = (l + r) / 2;
          if(A[mid] > A[mid+1] && A[mid] > A[mid-1]){
              return mid;
          }
          else if(A[mid] < A[mid+1]){
              l = mid;
          }
          else{
              r = mid + 1;
          }
      }
      if(A[l] > A[r]){
          return l;
      }
      return r;
  }
  
  /** 162. Find Peak Element(leetcode)
   *    A peak element is an element that is greater than its neighbors.
   *    Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
   *    The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
   *    You may imagine that nums[-1] = nums[n] = -∞.
   */
   
   public int findPeakElement(int[] nums) {
      if(nums.length == 1){
          return 0;
      }
      int l = 0, r = nums.length - 1;
      while(l < r){
          int mid = (l + r) / 2;
          if(mid == 0){
              if(nums[mid] > nums[mid+1]){
                  return mid;
              }
              l++;
          }
          else if(mid == nums.length - 1){
              if(nums[mid] > nums[mid-1]){
                  return mid;
              }
              r--;
          }
          else{
              if(nums[mid] > nums[mid+1] && nums[mid] > nums[mid-1]){
                  return mid;
              }
              else if(nums[mid] < nums[mid+1]){
                  l = mid + 1;
              }
              else{
                  r = mid - 1;
              }
          }
      }      
      return l;
  }
