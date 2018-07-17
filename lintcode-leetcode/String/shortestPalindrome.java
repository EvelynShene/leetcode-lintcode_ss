/** 678. Shortest Palindrome(lintcode) / 214. Shortest Palindrome(leetcode)
 *     Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. 
 *  Find and return the shortest palindrome you can find by performing this transformation.
 *
 *     Example: 1) Given "aacecaaa", return "aaacecaaa".
 *              2) Given "abcd", return "dcbabcd".
 */
 
 /* Method 1: [Memory Limit Exceeded]
  *     一开始想到的思路：1）s是回文字符串，返回s;
  *                    2) 从字符串s的最后一位字符开始，逐个往原字符串s前面加，每次加完判断得到的新字符串是不是回文字符串。
  *                     eg: check "abcd" -> false
  *                         check "dabcd" -> false
  *                         check "dcabcd" -> false
  *                         check "dcbabcd" -> true
  *     代码如下，运行中报错，Memory Limit Exceeded
  */
  public class Solution {
    public String convertPalindrome(String str) {
        // Write your code here
        if(str == null || str.length() == 0){
            return str;
        }
        if(isPalindrome(str)){
            return str;
        }
        StringBuilder rev = new StringBuilder(str);
        rev.reverse();
        int len = str.length() - 1;
        for(int i = 1; i <= len; i++){
            if(isPalindrome(rev.substring(0,i) + str)){
                return rev.substring(0,i) + str;
            }
        }
        return rev.substring(0,len) + str;
    }
    
    public boolean isPalindrome(String s){
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

 /* Method 2: [AC]
  *     既然加字符会检测会造成的Memory Limit Exceeded，那减字符也是一样的原理：
  *          从字符串s的最后一位字符开始，逐个去掉最后一个字符串，然后判断得到的新字符串是不是回文字符串。
  *                     eg: check "abcd" -> false
  *                         check "abc" -> false
  *                         check "ab" -> false
  *                         check "a" -> true
  *          然后在加上去掉的字符串的反转，即得到最短回文字符串。
  */
  public class Solution {
    /**
     * @param str: String
     * @return: String
     */
    public String convertPalindrome(String str) {
        // Write your code here
        if(str == null || str.length() == 0){
            return str;
        }
        
        int i = str.length() - 1;
        for(; i >= 0; i--){
            if(isPalindrome(str,i)){
                break;
            }
        }
        StringBuilder s = new StringBuilder(str.substring(i + 1));
        return s.reverse() + str;
    }
    
    public boolean isPalindrome(String s, int end){
        int i = 0;
        int j = end;
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

/* Method 3: [From jiuzhang]
 *   用i,j两个指针相向遍历，找到右边起第一个不使得字符串回文的位置
 *   eg: "aaacecaaa" , j = 7
 */
public String shortestPalindrome(String s) {
     int j = 0;
     for (int i = s.length() - 1; i >= 0; i--) {//找到第一个使他不回文的位置
        if (s.charAt(i) == s.charAt(j)) { 
            j += 1; 
        }
     }

     if (j == s.length()) {  //本身是回文
         return s; 
     }

     String suffix = s.substring(j); // 后缀不能够匹配的字符串      
     String prefix = new StringBuilder(suffix).reverse().toString(); // 前面补充prefix让他和suffix回文匹配        
     String mid = shortestPalindrome(s.substring(0, j)); //递归调用找 [0,j]要最少可以补充多少个字符让他回文       
     String ans = prefix + mid  + suffix;

     return  ans;
}
