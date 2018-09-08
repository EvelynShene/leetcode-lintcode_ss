/** 354. Russian Doll Envelopes(leetcode) / 602. Russian Doll Envelopes(lintcode)
 *      You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
 *  One envelope can fit into another if and only if both the width and height of one envelope is greater than
 *  the width and height of the other envelope.
 *      What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 *      Note: Rotation is not allowed.
 *
 *      Example: Input: [[5,4],[6,4],[6,7],[2,3]] ; Output: 3 
 *          Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 *
 *      Follow up: 如果信封是可以旋转的呢？Ans: 例如一个信封是<3,4>，那么把<3,4>、<4,3>都加入，再找最长序列。
 */
 
 /* Method 1: Sort + DP - O(n^2) time and O(n) space complexity [AC in leetcode but TLE in lintcode]
  *    思路：1) 首先将二维数组排序，规则：(i,j) < (x,y) if (i < x) or (i == x && j < y)
  *         2) 对排序后的信封进行遍历，对每一个信封都遍历其前面所有的信封，如果当前信封的长和宽都比前面那个信封的大，那么更新dp数组:
  *                  dp[i] = max(dp[i], dp[j] + 1)
  *         3) 在更新过程中，更新最大值max
  */
 class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0){
            return 0;
        }
        int n = envelopes.length;
        List<int[]> enp = sort2DArray(envelopes);
        int[] dp = new int[n];
        int max = 0;
        for(int i = 0; i < n; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(enp.get(j)[0] < enp.get(i)[0] && enp.get(j)[1] < enp.get(i)[1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    
    public List<int[]> sort2DArray(int[][] x) {
        List<int[]> res = new ArrayList<int[]>();
        res.add(x[0]);
        for(int i = 1; i < x.length; i++){
            int left = 0;
            int right = res.size() - 1;
            while(left <= right){
                int mid = left + (right - left) / 2;
                if(res.get(mid)[0] < x[i][0]){
                    left = mid + 1;
                }
                else{
                    right = mid - 1;
                }
            }
            if(left < res.size() && res.get(left)[0] == x[i][0]){
                if(res.get(left)[1] > x[i][1]){
                    res.add(left,x[i]);
                }
                else{
                    res.add(left + 1, x[i]);
                }
            }
            else{
                res.add(left,x[i]);
            }
        }
        return res;
    }
}

/* Method 2: Use Binary Search - O(nlogn) time complexity
 *    思路：依然是先给信封排序，按信封宽度还是从小到大排，但是宽度相等时，让高度大的在前面。那么现在问题就简化成了找高度数字中的LIS，
 *      完全就和300. Longest Increasing Subsequence一样了。
 */
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0){
            return 0;
        }
        int n = envelopes.length;
        int[] height = sort2DArray(envelopes);
        
        int max = findLIS(height);
        
        return max;
    }
    
    public int findLIS(int[] h){
        List<Integer> res = new ArrayList<Integer>();
        res.add(h[0]);
        
        for(int i = 1; i < h.length; i++){
            if(h[i] < res.get(0)){
                res.add(0,h[i]);
                res.remove(1);
            }
            else if(h[i] > res.get(res.size() - 1)){
                res.add(h[i]);
            }
            else{
                if(h[i] > res.get(0) && h[i] < res.get(res.size() - 1)){
                    int left = 0;
                    int right = res.size() - 1;
                    while(left <= right){
                        int mid = left + (right - left) / 2;
                        if(res.get(mid) < h[i]){
                            left = mid + 1;
                        }
                        else{
                            right = mid - 1;
                        }
                    }
                    res.add(left, h[i]);
                    res.remove(left + 1);
                }
            }
        }
        return res.size();
    }
    
    public int[] sort2DArray(int[][] x) {
        List<int[]> res = new ArrayList<int[]>();
        res.add(x[0]);
        for(int i = 1; i < x.length; i++){
            int left = 0;
            int right = res.size() - 1;
            while(left <= right){
                int mid = left + (right - left) / 2;
                if(res.get(mid)[0] < x[i][0]){
                    left = mid + 1;
                }
                else{
                    right = mid - 1;
                }
            }
            while(left < res.size() && res.get(left)[0] == x[i][0] && res.get(left)[1] > x[i][1]){
                left++;
            }
            res.add(left,x[i]);
        }
        int[] h = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            h[i] = res.get(i)[1];
        }
        return h;
    }
}
