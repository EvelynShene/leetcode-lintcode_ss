/** 523. Continuous Subarray Sum (leetcode)
 *    Given a list of non-negative numbers and a target integer k, 
 *    write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, 
 *    that is, sums up to n*k where n is also an integer.
 *    Example: 1)Input: [23, 2, 4, 6, 7],  k=6
 *               Output: True
 *         Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 *             2)Input: [23, 2, 6, 4, 7],  k=6
 *               Output: True
 *         Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 */
 
 //My Method: O(n^2)
  public boolean checkSubarraySum(int[] nums, int k) {
      if(nums == null || nums.length < 2){
          return false;
      }
      int sum = 0;
      for(int i = 0; i < nums.length; i++){
          sum = nums[i];
          for(int j = i+1; j < nums.length; j++){
              sum += nums[j];
              if(k != 0 && sum % k == 0){
                  return true;
              }
              else if(k == 0 && sum == 0){
                  return true;
              }
          }
      }
      return false;
  }
  
  /* Method 2:(from leetcode)
   * Idea: Keep tracking of the running sum mod k of the elements in the process
   *       If we can find two index i and j such that (n1+n2+...+ni) % k = m and (n1+n2+...+nj) % k = m. (Assume that i < j)
   *       Then, we can obtain that (n[i+1]+...n[j-1]+n[j]) % k = 0, that is, subarray (i,j] contains a desired sum.
   */
   
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums == null || nums.length < 2){
            return false;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = 0;
        map.put(0,-1);  //deal with [0,0] and k=0     
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(k != 0) sum = sum % k;
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
            else if(map.containsKey(sum) && (i - map.get(sum) > 1)){
                return true;
            }
        }
        return false;  
    }
  
