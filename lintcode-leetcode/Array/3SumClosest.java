/** 59. 3Sum Closest(lintcode) /16. 3Sum Closest (leetcode)
 *    Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
 *    Return the sum of the three integers.
 *    Note: You may assume that each input would have exactly one solution.
 */
 
 //Method: 排序数组，然后左右指针求最接近和
  public int threeSumClosest(int[] numbers, int target) {
      // write your code here
      Arrays.sort(numbers);
      int closest = numbers[0] + numbers[1] + numbers[2];

      for(int i = 0; i < numbers.length; i++){
          int twoTarget = target - numbers[i];

          int l = i+1;
          int r = numbers.length-1;

          while(l < r){
              if(numbers[l] + numbers[r] == twoTarget){
                  return target;
              }
              if(numbers[l] + numbers[r] < twoTarget){
                  if(Math.abs(closest-target) >  (twoTarget - numbers[l] - numbers[r])){
                      closest = numbers[i] + numbers[l] + numbers[r];
                  }
                  l++;
                  while(l < r && numbers[l] == numbers[l-1]){
                      l++;
                  }

              }
              else{
                  if(Math.abs(closest-target) >  (numbers[l] + numbers[r] - twoTarget )){
                      closest = numbers[i] + numbers[l] + numbers[r];
                  }
                  r--;
                  while(l < r && numbers[r] == numbers[r+1]){
                      r--;
                  }
              }
          }
          while(i+1 < numbers.length && numbers[i] == numbers[i+1]){
              i++;

          }
      }
      return closest;
  }
  
  // Method from leetcode, more concise code
  public int threeSumClosest(int[] num, int target) {
      int result = num[0] + num[1] + num[num.length - 1];
      Arrays.sort(num);
      for (int i = 0; i < num.length - 2; i++) {
          int start = i + 1, end = num.length - 1;
          while (start < end) {
              int sum = num[i] + num[start] + num[end];
              if (sum > target) {
                  end--;
              } else {
                  start++;
              }
              if (Math.abs(sum - target) < Math.abs(result - target)) {
                  result = sum;
              }
          }
      }
      return result;
  }
