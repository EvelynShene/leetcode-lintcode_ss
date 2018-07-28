/** 1290. Patching Array(lintcode) / 330. Patching Array(leetcode)
 *     Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that 
 *  any number in range [1, n] inclusive can be formed by the sum of some elements in the array. 
 *  Return the minimum number of patches required.
 *
 *     Example: 1) Input: nums = [1,3], n = 6 ; Output: 1 
 *        Explanation: Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 *                     Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 *                     Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 *                     So we only need 1 patch. 
 *              2) Input: nums = [1,5,10], n = 20 ; Output: 2
 *        Explanation: The two patches can be [2, 4].
 */
 
 /* My Method: Time Limit Exceeded
  *    发现了每次都添加当前在范围[1,n]中最小的缺失的数字，加入后，更新可以计算的出来的和sum，然后再添加下一个范围内最小缺失数字
  *  下述代码时间复杂度是O(n^2), 对于n很大的时(n = Integer.MAX_VALUE)，超时。
  */  
  public static int minPatches(int[] nums, int n) {
    if(nums == null){
        return 0;
    }

    Set<Integer> set = new TreeSet<Integer>();
    for(int i = 0; i < nums.length; i++){
        Set<Integer> need_to_add = new HashSet<Integer>();
        Iterator itr = set.iterator();
        while(itr.hasNext()){
            int sum = (Integer) itr.next() + nums[i];
            if(sum <= n && !set.contains(sum)){
                need_to_add.add(sum);
            }
            else if(sum > n){
                break;
            }
        }
        for(Integer x: need_to_add){
          set.add(x);
        }
        if(!set.contains(nums[i])){
            set.add(nums[i]);
        }
    }
    int min_lose = 1;
    int count = 0;
    while(true){
        while(set.contains(min_lose)){
            min_lose++;
        }
        if(min_lose <= n){
            count++;
        }
        else{
            break;
        }
        Set<Integer> need_to_add = new HashSet<Integer>();
        for(Integer x: set){
          int sum = x + min_lose;
          if(sum <= n && !set.contains(sum)){
            need_to_add.add(sum);
          }
          else if(sum > n)
            break;
        }
        for(Integer x: need_to_add){
          set.add(x);
        }
        if(!set.contains(min_lose)){
            set.add(min_lose);
        }
    }
    return count;
 }

/* Method 2: Idea from leetcode discuss
 *    同样是找[1,n]内最小缺失数字，但是时间复杂度是O(n): 
 *    1) 初始化最小缺失数字 miss = 1；
 *    2) 遍历数组(已知数组升序)，我们已知[1,miss)范围内的数是可求得的；
 *      a) 如果nums[i] <= miss
 *         i) 那么要么 miss = nums[i],正好补上miss这个空缺，miss可以更新；
 *         ii)要么miss - nums[i] > 0, 这个数(miss - nums[i])一定在[1,miss)范围内存在了，那么加上nums[i]也能补上miss这个空缺，miss也要更新。 
 *      => miss此时应该更新为miss + nums[i]
 *        【因为范围[miss - nums[i],miss)内的数都存在了，所以每个数加上nums[i]可以把范围[miss,miss+nums[i])的空缺都补上了】
 *      b) 如果nums[i] > miss,后面的数肯定更大，miss这个数缺失，需要人为补上，patch_num++。
 *         补上miss后，范围[1,2*miss)的数都补充上了，所以miss更新为2*miss。
 *    3) 遍历直到miss > n时结束。
 *
 *    Note: miss要用长整型表示，因为可能n = Integer.MAX_VALUE，这样一来，如果miss用32位int，而miss+nums[i] > n,就会溢出为负数
 *  进入死循环，TLE。
 */
 public static int minPatches(int[] nums, int n) {
    if(nums == null){
        return 0;
    }
    int patch_num = 0;
    long miss = 1;
    int index = 0;
    while(miss <= n){
        if(index < nums.length && nums[index] <= miss){
            miss = miss + nums[index];
            index++;
        }
        else{
            miss += miss;
            patch_num++;
        }
    }
    return patch_num;
 } 
