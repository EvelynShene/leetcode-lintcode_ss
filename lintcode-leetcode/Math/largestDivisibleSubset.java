/** 368. Largest Divisible Subset(leetcode) / 603. Largest Divisible Subset(lintcode)
 *     Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements
 *  in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 *     If there are multiple solutions, return any subset is fine.
 *
 *     Example: 1) nums: [1,2,3] ; Result: [1,2] (of course, [1,3] will also be ok)
 *              2) nums: [1,2,4,8] ; Result: [1,2,4,8]
 */
 
 /* Method: DP
  *   思路：1) 先排序，这样只要后面的数是否能整除前面的数。
  *        2) 用dp[i]表示能被数nums[i]整除的数的个数(最多有多少个数能被nums[i]整除)；则递推公式为：
  *               dp[i] = Math.max(dp[i],dp[j] + 1) if (dp[i] % dp[j] == 0, j < i) 
  *        3) 因为最后要输出这个长集合，所以需要数组 pre[i] = j 来记录nums[i]的最大集合里能被nums[i]整除的前一个数的下标j，
  *         然后从后往前回溯加入list中。
  */
  public List<Integer> largestDivisibleSubset(int[] nums) {
    List<Integer> res = new ArrayList<Integer>();
    if(nums == null || nums.length == 0){
        return res;
    }
    int n = nums.length;
    Arrays.sort(nums);
    int[] dp = new int[n];
    int[] pre = new int[n];
    Arrays.fill(dp,1);
    int maxlen = 0;
    int maxins = 0;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < i; j++){
            if(nums[i] % nums[j] == 0){
                if(dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1;
                    pre[i] = j;
                }
            }
        }
        if(maxlen < dp[i]){
            maxlen = dp[i];
            maxins = i;
        }
    }
    while(maxlen != 0){
        res.add(nums[maxins]);
        maxins = pre[maxins];
        maxlen--;
    }
    return res;
 }
