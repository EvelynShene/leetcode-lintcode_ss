/** this question is not about the maximum of absolute difference between i, j, 
 *  but instead it is about whether there are two indexes i, j with nums[i] == nums[j] and abs(i - j) <= k. 
 *  For example, the input is [1, 0, 1, 1], nums[2] = 1, nums[3] = 1, thus i, j = 2, 3 and abs(i- j) <=1. 
 *  There exist two indexes match the requirements, thus the output is true.
 */

public static boolean containsNearbyDuplicate(int[] nums, int k) {       
  for(int i = 0; i < nums.length; i++){
      for(int j = i+1; j < nums.length; j++){
          if(nums[i] == nums[j] && (j-i)<=k ){
            System.out.println("i:"+i+",j:"+j);
              return true;
          }
      }
  }
  return false;
}
