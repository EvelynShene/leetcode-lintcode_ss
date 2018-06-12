/** this question is not about the maximum of absolute difference between i, j, 
 *  but instead it is about whether there are two indexes i, j with nums[i] == nums[j] and abs(i - j) <= k. 
 *  For example, the input is [1, 0, 1, 1], nums[2] = 1, nums[3] = 1, thus i, j = 2, 3 and abs(i- j) <=1. 
 *  There exist two indexes match the requirements, thus the output is true.
 */

//Method 1: Time complexity is O(n^2)
public static boolean containsNearbyDuplicate(int[] nums, int k) {       
  for(int i = 0; i < nums.length; i++){
      for(int j = i+1; j < nums.length; j++){
          if(nums[i] == nums[j] && (j-i)<=k ){
//             System.out.println("i:"+i+",j:"+j);
              return true;
          }
      }
  }
  return false;
}

//Method 2: Hashmap, Time complexity is O(n), space complexity is O(n)
public boolean containsNearbyDuplicate(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < nums.length; i++) {
        if (map.containsKey(nums[i])) {
            if (i - map.get(nums[i]) <= k) return true;
        }
        map.put(nums[i], i);
    }
    return false;
}
/** Hint:用hashmap映射<nums值，index>，如果同一个值重复出现，map.containsKey(nums[i])==true。
 *  每次都更新同一nums值的最新index，因为题目只要找到有nums[i] == nums[j] and abs(i - j) <= k，就可以返回true
 *  所以更新最新index后，下次遇到同样值的新index时，新index与原index的差值最小，最有可能<=k。
 */
