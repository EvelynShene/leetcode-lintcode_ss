/** 96. Unique Binary Search Trees(leetcode) / 163. Unique Binary Search Trees(lintcode)
 *    Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *    
 *    Example:
 *            Input: 3
 *            Output: 5
 *            Explanation:
 *            Given n = 3, there are a total of 5 unique BST's:
 *                1         3     3      2      1
 *                 \       /     /      / \      \
 *                  3     2     1      1   3      2
 *                 /     /       \                 \
 *                2     1         2                 3
 */
 
 //My Method: Recursive + Memorization
 class Solution {
    public int numTrees(int n) {
        if(n == 1){
            return 1;
        }
        int[] dp = new int[n+1];
        
        dp[1] = 1;
        dp[2] = 2;
        return numTree(1,n,dp);
    }
    public int numTree(int start, int end, int[] dp){
        if(end < start){
            return 0;
        }
        if(dp[end - start + 1] != 0){
            return dp[end - start + 1];
        }
        int left = 0;
        int right = 0;
        int res = 0;
        for(int i = start; i <= end; i++){
            left = numTree(start,i - 1, dp);
            right = numTree(i+1, end, dp);
            if(left == 0){
                res += right;
            }
            else if(right == 0){
                res += left;
            }
            else{
                res += left * right;
            } 
        }
        dp[end - start + 1] = res;
        return res;
    } 
}
 
//Method 2: [From Jiuzhang]
public class Solution {
  /*
  The case for 3 elements example
  Count[3] = Count[0]*Count[2]  (1 as root)
                + Count[1]*Count[1]  (2 as root)
                + Count[2]*Count[0]  (3 as root)

  Therefore, we can get the equation:
  Count[i] = ∑ Count[0...k] * [ k+1....i]     0<=k<i-1  
  */
  
    public int numTrees(int n) {
        int[] count = new int[n + 2];
        count[0] = 1;
        count[1] = 1;
        
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < i; j++){
                count[i] += count[j] * count[i - j - 1];
            }
        }
        return count[n];
    }
}
