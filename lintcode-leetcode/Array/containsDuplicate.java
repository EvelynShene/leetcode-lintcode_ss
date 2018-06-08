/** 1320. Contains Duplicate(lintcode)/217. Contains Duplicate(leetcode)
  * Given an array of integers, find if the array contains any duplicates. 
  * Your function should return true if any value appears at least twice in the array, 
  * and it should return false if every element is distinct.
  *
  */
  
/** Method 1: AC in lintcode but "Time Limit Exceeded" in leetcode
  * Time complexity is O(N^2)
  */
public boolean containsDuplicate(int[] nums) {
    // Write your code here
    for(int i = 0; i < nums.length; i++){
        for(int j = 0; j < i; j++){
            if(nums[i] == nums[j]){
                return true;
            }
        }
    }
    return false;
}

/** Method 2: AC in leetcode
  * Time complexity is O(NlgN)
  */
public boolean containsDuplicate(int[] nums) {
    Arrays.sort(nums);
    for(int i = 1; i < nums.length; i++){
        if(nums[i] == nums[i-1]){
            return true;
        }
    }
    return false;
}
/* Note: 若循环从0开始做 nums[i] = nums[i+1] 判断，则可能数组越界 */


/** Method 3: Use HashSet (from Leetcode Discussion)
  * Time complexity is O(N), but Memory = O(N)
  */
public boolean containsDuplicate(int[] nums) {
    HashSet<Integer> set= new HashSet<Integer>();
    for(int i=0;i<nums.length;i++){
        if(set.contains(nums[i])){
            return true;
        }else{
            set.add(nums[i]);
        }
    }
    return false;
}
