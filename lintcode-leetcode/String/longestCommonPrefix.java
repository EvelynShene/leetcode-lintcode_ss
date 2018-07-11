/** 78. Longest Common Prefix(lintcode) / 14. Longest Common Prefix —— 面试常见题（简单多方法）
 *    Given k strings, find the longest common prefix (LCP).
 *
 *    Example: 1) For strings "ABCD", "ABEF" and "ACEF", the LCP is "A"
 *             2) For strings "ABCDEFG", "ABCEFG" and "ABCEFA", the LCP is "ABC"
 */
 
 /* My Method: Time complexity : O(S) time (S = min_len*k)
  *   最长前缀不可能比数组strs中的最短字符串长，所以先求出最短字符串的长度min_len，然后在循环遍历求前缀
  */
 public String longestCommonPrefix(String[] strs) {
    // write your code here
    if(strs == null || strs.length == 0){
        return "";
    }
    if(strs.length == 1){
        return strs[0];
    }
    int k = strs.length;
    int min_len = strs[0].length();
    for(int i = 1; i < k; i++){
        min_len = Math.min(min_len,strs[i].length());
    }
    int i = 0;
    for(; i < min_len; i++){
        char c = strs[0].charAt(i);
        int j = 1;
        for(; j < k; j++){
            if(strs[j].charAt(i) != c){
                break;
            }
        }
        if(j < k){
            break;
        }
    }
    return strs[0].substring(0,i);
 }
 
/* Method 2: O(S) time (S = m * k, m = strs[0].length)
 * 两个两个的求 - LCP(strs[1],...,strs[n]) = LCP(LCP(strs[n-1],LCP(...LCP(strs[1],strs[2])),strs[n])
 */
public String longestCommonPrefix(String[] strs) {
    if(strs == null || strs.length == 0){
        return "";
    }
    String prefix = strs[0];
    int k = strs.length;
    for(int i = 0; i < k; i++){
        while(strs[i].indexOf(prefix) != 0){
            if(prefix.length() - 1 == 0){
                return "";
            }
            prefix = strs[0].substring(0,prefix.length()-1);
        }
    }
    return prefix;
 }
 
/* Method 3: [code from leetcode] - O(S) time (S = m * k) and O(m⋅log(n)) space complexity
 * Divide and conquer - LCP(strs[1],...,strs[n]) = LCP(LCP(strs[1],...,strs[k]),LCP(strs[k+1],...,strs[n]))
 */
 
class Solution{
   public String longestCommonPrefix(String[] strs) {
      if (strs == null || strs.length == 0) return "";    
          return longestCommonPrefix(strs, 0 , strs.length - 1);
   }

   private String longestCommonPrefix(String[] strs, int l, int r) {
      if (l == r) {
          return strs[l];
      }
      else {
          int mid = (l + r)/2;
          String lcpLeft =   longestCommonPrefix(strs, l , mid);
          String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
          return commonPrefix(lcpLeft, lcpRight);
     }
   }

   String commonPrefix(String left,String right) {
      int min = Math.min(left.length(), right.length());       
      for (int i = 0; i < min; i++) {
          if ( left.charAt(i) != right.charAt(i) )
              return left.substring(0, i);
      }
      return left.substring(0, min);
   }
}
