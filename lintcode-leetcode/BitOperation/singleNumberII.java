/** 83. Single Number II (lintcode) / 137. Single Number II(leetcode) 
 *     Given a non-empty array of integers, every element appears three times except for one,
 *   which appears exactly once. Find that single one.
 *
 *     Note: Your algorithm should have a linear runtime complexity. 
 *           Could you implement it without using extra memory?
 *
 *     Example: 1) Input: [2,2,3,2] ; Output: 3
 *              2) Input: [0,1,0,1,0,1,99] ; Output: 99
 */
 
 //Method 1: Use HashMap - O(n) time and space complexity
 public int singleNumber(int[] nums) {
    Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    for(int i = 0; i < nums.length; i++){
        if(!map.containsKey(nums[i])){
            map.put(nums[i],1);
        }
        else{
            map.put(nums[i],map.get(nums[i])+1);
        }
    }
    int n = 0;
    for(Map.Entry<Integer, Integer> entry: map.entrySet()){
        if(entry.getValue() == 1){
            n = entry.getKey();
            break;
        }
    }
    return n;
 }
 
 //Method 2: Use Math 3*(a+b+c) - (a+a+a+b+b+b+c)  - O(n) time and space complexity
 public int singleNumber(int[] nums) {
    long sum = 0; //运算可能导致整数溢出
    long res = 0;
    Set<Integer> set = new HashSet<Integer>();
    for(int i = 0; i < nums.length; i++){
        if(!set.contains(nums[i])){
            set.add(nums[i]);
            sum += nums[i];
        }
        res += nums[i];
    }
    return (int)((sum*3 - res) / 2);
 }
 //Note: Method 2 在lintcode中没AC；leetcode里AC了
 
 //Method 3: Bit Operator - O(n) time and O(1) space complexity
 public int singleNumber(int[] nums) {
    int[] res = new int[32];
    for(int i =0; i< nums.length; i++){
        for(int j = 0; j < 32; j++){
            res[j] += (nums[i] >> j) & 1;
            res[j] = res[j] % 3;
        }
    }
    int n = 0;
    for(int i = 31; i >=0; i--){
        n = n << 1;
        n = (n + res[i]);
    }
    return n;
 }
 
 /* 规律：一个数组中有一个元素只出现1次，其他所有元素都出现k次，求这个只出现1次的元素。
  *     1）当k为偶数时，将数组所有元素都进行异或得到一个不为0的结果，即是所求元素
  *     2）当k为奇数时，将数组中每个元素的每一位相加mod k，得到结果即位出现1次的元素
  *     ( 这里相加的是二进制的位，按位相加)
  *／
