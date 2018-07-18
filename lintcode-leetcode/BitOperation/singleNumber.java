/** 82. Single Number(lintcode) / 136. Single Number(leetcode)
 *    Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 *
 *    Note: Your algorithm should have a linear runtime complexity. 
 *          Could you implement it without using extra memory?
 *
 *    Example: Input: [2,2,1] ; Output: 1
 */
 
 //Method: num ^ num = 0 - O(n) time and O(1) space complexity
 public int singleNumber(int[] nums) {
    int res = 0;
    for(int i = 0; i < nums.length; i++){
        res = res ^ nums[i];
    }
    return res;
 }
 
 //Method 2: Use Math: 2*(a+b+c) - (a+a+b+b+c) = c - O(n) time and O(n) space complexity
 public int singleNumber(int[] nums) {
    int res = 0;
    int sum = 0;
    Set<Integer> set = new HashSet<Integer>();
    for(int i = 0; i < nums.length; i++){
        if(!set.contains(nums[i])){
            set.add(nums[i]);
            sum += nums[i];
        }
        res += nums[i];
    }
    return (sum*2 - res);
 }
 
 //Method 3: HashTable/HashMap -  O(n) time and O(n) space complexity
