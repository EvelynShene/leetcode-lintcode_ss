/** 97. Interleaving String(leetcode) / 29. Interleaving String(lintcode) 
 *      Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 *      Example: For s1 = "aabcc", s2 = "dbbca"
 *               1) When s3 = "aadbbcbcac", return true.
 *               2) When s3 = "aadbbbaccc", return false.
 *
 *      Challenge: O(n^2) time or better
 */
 
 /* Method: DP - O(n^2) time and space complexity
  *    思路：1) 先判断：字符串s1和s2的长度和必须等于s3的长度，如果不等于，肯定返回false。
  *         2) 设置dp数组，dp[i][j]指示s1[0,i-1]和s2[0,j-1]是否能交叉构成字符串s3[0,i+j-1]。
  *            a) dp的第0行和第0列分别表示s2、s1为空串；
  *            b) 当s1和s2是空串的时候，s3必然是空串，返回true。所以直接给dp[0][0]赋值true；
  *            c) 若s1和s2其中的一个为空串的话，那么另一个肯定和s3的长度相等，则按位比较，若相同且上一个位置为True，赋True，
  *          其余情况都赋False。
  *         3）推导递推公式：在非边缘位置dp[i][j]时，它的左边或上边有可能为True或是False，只要有一条路通着，那么这个点就可以为True。
  *            a) 如果左边的为True，说明s1[0,i-1]和s2[0,j-2]可以交叉构成s3[0,i+j-2]，那么只需保证s2[j-1] == s3[i+j-1]; 
  *            b) 如果上边的为True，说明s1[0,i-2]和s2[0,j-1]可以交叉构成s3[0,i+j-2]，那么只需保证s1[i-1] == s3[i+j-1]; 
  *       => dp[i][j] = (dp[i - 1][j] && s1[i - 1] == s3[i - 1 + j])  
  *                                                   || (dp[i][j - 1] && s2[j - 1] == s3[j - 1 + i]);
  *             【dp[i][j] 表示的是 s2 的前 i 个字符和 s1 的前 j 个字符是否匹配 s3 的前 i+j 个字符】
  */
public boolean isInterleave(String s1, String s2, String s3) {
    int len1 = s1.length();
    int len2 = s2.length();
    int len3 = s3.length();
    if(len3 != len1 + len2){
        return false;
    }
    boolean[][] dp = new boolean[len1 + 1][len2 + 2];
    dp[0][0] = true;

    for(int i = 1; i <= len1; i++){
        if(dp[i - 1][0] && s3.charAt(i - 1) == s1.charAt(i - 1)){
            dp[i][0] = true;
        }
    }
    for(int j = 1; j <= len2; j++){
        if(dp[0][j - 1] && s3.charAt(j - 1) == s2.charAt(j - 1)){
            dp[0][j] = true;
        }
    }

    for(int i = 1; i <= len1; i++){
        for(int j = 1; j <= len2; j++){
            dp[i][j] = (dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
            dp[i][j] = dp[i][j] || (dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1));
        }
    }
    return dp[len1][len2];
}
