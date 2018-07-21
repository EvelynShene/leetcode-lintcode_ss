/** 154. Regular Expression Matching(lintcode) / 10. Regular Expression Matching (leetcode)
 *      Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' 
 *   and '*'.
 *          1) '.' Matches any single character.
 *          2) '*' Matches zero or more of the preceding element.
 *      The matching should cover the entire input string (not partial).
 *
 *      Note: 1)s could be empty and contains only lowercase letters a-z.
 *            2) p could be empty and contains only lowercase letters a-z, and characters like . or *.
 *
 *      Example: isMatch("aa","a") → false
 *               isMatch("aa","aa") → true
 *               isMatch("aaa","aa") → false
 *               isMatch("aa", "a*") → true
 *               isMatch("aa", ".*") → true
 *               isMatch("ab", ".*") → true
 *               isMatch("aab", "c*a*b") → true
 */
 
 /* Method 1: 递归
  *   对于每一个字符串s和匹配串p：
  *   1) p和s都是空串，返回true。
  *   2) p和s都是长度为1的字符串时，s[0] == p[0] 或者 p[0] == '.'时，返回true；否则false
  *   3) p长度大于2时，查看p[0]和p[1]：
  *     a) 若是p[1] == '*'，那么s可能与p匹配上必须是：
  *        “s与p(2,n)匹配上” 或着 “s[0] == p[0] 或者 p[0] == '.' 且 s(1,m)与p匹配上” (s(1,m)与p是因为p[0]*可能还需匹配s[1])
  *     b) 若是p[1] != '*', 那么s可能与p匹配上就必须：
  *        “s[0] == p[0] 或者 p[0] == '.' 且 s(1,m)与p(1,n)匹配上”
  */
 public boolean isMatch(String s, String p) {
    if(s == null || p == null){
        return false;
    }
    if(p.length() == 0){
        if(s.length() == 0)
            return true;
        return false;
    }

    if(p.length() == 1){
        if(s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'))
            return true;
        return false;
    }

    if(p.charAt(1) != '*'){
        return (s.length() != 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1),p.substring(1)));
    }
    else{// p.charAt(1) == '*'
        boolean res1 = isMatch(s, p.substring(2));
        boolean res2 = (s.length() != 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1),p));
        return res1 || res2;
    }   
 }

/* Method 2: DP - O(nm) time and space complexity
 *   用dp[i][j]表示s[0,i)和p[0,j)是否匹配(i,j可看作分别表示s和p子串包含的字符数目),则求dp[i][j]：
 *     1) p[i-1] == '*'时，dp[i][j] = (s[i-1] == p[j-1] || p[j-1] == '.') && dp[i-1][j-1];
 *     2) p[i-1] != '*'时，dp[i][j] = ((s[i-1] == p[j-1] || p[j-1] == '.') && dp[i-1][j]) || dp[i][j-2].
 *   dp的初始化：
 *     1) s和p都是空串：dp[0][0] = true; 
 *     2) p是空串，s不是：dp[x][0] = false (x = 1,2,..., m = s.length())
 *     2) s是空串，p不是：(y = 1,2,...,n = p.length())
 *          a) y > 1 && p以“k*”结尾，dp[0][y] = dp[0][y-2] 
 *          b) 否则 dp[0][y] = false.
 */

public boolean isMatch(String s, String p) {
     if(s == null || p == null){
         return false;
     }
     //dp[i][j] = if s[0,i) and p[0,j) is matched
     int m = s.length();
     int n = p.length();
     if(n > 0 && p.charAt(0) == '*'){ //此if语句只对lintcode有效，因为lintcode中有 "*a*ba*" 这样的测试用例
         return false;
     }
     boolean[][] dp = new boolean[m+1][n+1];
     dp[0][0] = true;
     for(int i = 1; i <= m; i++){
         dp[i][0] = false;
     }
     for(int i = 1; i <= n; i++){
         if(i > 1 && p.charAt(i-1) == '*'){
             dp[0][i] = dp[0][i-2];
         }
         else{
             dp[0][i] = false;
         }
     }

     for(int i = 1; i <= m; i++){
         for(int j = 1; j <= n; j++){
             if(p.charAt(j-1) != '*'){
                 dp[i][j] = (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') && dp[i-1][j-1];
             }
             else{
                 boolean res1 = (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') && dp[i-1][j];
                 dp[i][j] = res1 || dp[i][j-2];
             }
         }
     }
     return dp[m][n];
 }
