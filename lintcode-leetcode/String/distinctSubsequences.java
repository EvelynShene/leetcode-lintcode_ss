/** 118. Distinct Subsequences(lintcode) / 115. Distinct Subsequences(leetcode) - Dynamic Programming
 *      Given a string S and a string T, count the number of distinct subsequences of T in S.
 *      A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of 
 *   the characters without disturbing the relative positions of the remaining characters. 
 *   (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 *    Example: Given S = "rabbbit", T = "rabbit", return 3.
 *           Explanation: As shown below, there are 3 ways you can generate "rabbit" from S.
 *            (The caret symbol ^ means the chosen letters)
 *                         rabbbit
 *                         ^^^^ ^^
 *                         rabbbit
 *                         ^^ ^^^^
 *                         rabbbit
 *                         ^^^ ^^^
 */
 
 /* Method: DP - O(n^2) time and O(n^2) space
  *   先理解题意：求字符串S中有多少种通过删除不同的字符（同字符位置不同也算不同）的方式可以得到字符串T。
  *   Idea: 用数组dp[i][j]表示S(0,i)中能有多少种不同的产生T(0,j)的方式，则对于每一个dp[i][j]：
  *         1) S(i) == T(j)，那么产生T(0,j)的方式有两种：
  *           a) S(0,i-1)通过删除某些字符产生T(0,j);
  *           b) S(0,i-1)通过删除某些字符产生T(0,j-1).【然后S(i) == T(j)，S(0,i)就得到了T(0,j)】
  *         => dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
  * 
  *         2) S(i) != T(j), 那么只能是S(0,i-1)产生T(0,j).
  *         => dp[i][j] = dp[i-1][j];
  */
  
  public int numDistinct(String S, String T) {
    // write your code here
    if(S == null || T == null || (S.length() == 0 && T.length() != 0)){
        return 0;
    }
    if(T.length() == 0){
        return 1;
    }

    int[][] dp = new int[S.length()+1][T.length()+1];
    for(int i = 0; i <= S.length(); i++){
        dp[i][0] = 1;
    }
    for(int i = 1; i <= T.length(); i++){
        dp[0][i] = 0;
    }
    for(int i = 1; i <= S.length(); i++){
        for(int j = 1; j <= T.length(); j++){
            if(S.charAt(i-1) == T.charAt(j-1)){
                dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
            }
            else{
                dp[i][j] = dp[i-1][j];
            }
        }
    }
    return dp[S.length()][T.length()];
 }
