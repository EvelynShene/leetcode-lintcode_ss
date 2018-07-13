/** 107. Word Break(lintcode) / 139. Word Break(leetcode) - Dynamical Programming
 *      Given a string s and a dictionary of words dict, determine if s can be break into 
 *    a space-separated sequence of one or more dictionary words.
 *
 *      Example: Given s = "lintcode", dict = ["lint", "code"].
 *               Return true because "lintcode" can be break as "lint code".
 */
 
 /* Method 1: AC in leetcode
  *     In leetcode, the problem declares that :
  *         s is a non-empty string and wordDict is a dictionary containing a list of non-empty words
  */
 public boolean wordBreak(String s, List<String> wordDict) {
      Set<String> dict = new HashSet<>();
      dict.addAll(wordDict);

      boolean[] dp = new boolean[s.length() + 1];
      dp[0] = true;
      for(int i = 1; i <= s.length(); i++){
          for(int j = 0; j < i; j++){
              dp[i] = dp[j] && dict.contains(s.substring(j,i));
              if(dp[i]){
                  break;
              }
          }
      }
      return dp[s.length()];
 }
 
 /* Method 2: Above Method will cause memory limited error in lintcode
  *       用dict里面最长的可能匹配串长度来约束／减少动态规划遍历的分割字符串的情况。
  */
 public class Solution {
    /*
     * @param s: A string
     * @param dict: A dictionary of words dict
     * @return: A boolean
     */
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int max = getMaxLength(dict);
        boolean[] dp = new boolean[s.length() + 1];

        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= max && j <= i; j++) {
                if(!dp[i - j]){
                    continue;
                }
                
                if (dict.contains(s.substring(i - j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
    
    private int getMaxLength(Set<String> dict){
        int max = 0;
        for(String s : dict){
            max = Math.max(max,s.length());
        }
        return max;
    }
}
