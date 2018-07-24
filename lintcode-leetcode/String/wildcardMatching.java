/** 192. Wildcard Matching(lintcode) / 44. Wildcard Matching(leetcode)
 *    Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 *      1) '?' Matches any single character.
 *      2) '*' Matches any sequence of characters (including the empty sequence).
 *    The matching should cover the entire input string (not partial).
 *
 *    Note: 1) s could be empty and contains only lowercase letters a-z.
 *          2) p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 *
 *    Example: isMatch("aa","a") → false
 *             isMatch("aa","aa") → true
 *             isMatch("aaa","aa") → false
 *             isMatch("aa", "*") → true
 *             isMatch("aa", "a*") → true
 *             isMatch("ab", "?*") → true
 *             isMatch("aab", "c*a*b") → false
 */
 
 //My Method: DP - (与 Regular Expression Matching 题思路一致)
 public boolean isMatch(String s, String p) {
    if(s == null || p == null){
        return false;
    }
    int m = s.length();
    int n = p.length();
    boolean[][] dp = new boolean[m+1][n+1];
    dp[0][0] = true;
    for(int i = 1; i <= m; i++){//p is an empty string but s[0,i) is not empty.
        dp[i][0] = false;
    }
    for(int j = 1; j <= n; j++){//p[0,j) is not empty but s is empty
        if(p.charAt(j-1) == '*'){
            dp[0][j] = dp[0][j-1];
        }
        else{
            dp[0][j] = false;
        }
    }

    for(int i = 1; i <= m; i++){
        for(int j = 1; j <= n; j++){
            if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){
                dp[i][j] = dp[i-1][j-1];
            }
            else if(p.charAt(j-1) == '*'){
                dp[i][j] = dp[i-1][j] || dp[i][j-1];
            }
        }
    }      
    return dp[m][n];
 } 
