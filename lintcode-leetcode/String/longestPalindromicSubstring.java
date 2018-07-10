/** 200. Longest Palindromic Substring(lintcode) /5. Longest Palindromic Substring(leetcode)
 *     Given a string S, find the longest palindromic substring in S. 
 *  You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 *
 *     Example : Given the string = "abcdzdcab", return "cdzdc".
 *
 *     Challenge: O(n2) time is acceptable. Can you do it in O(n) time.
 */
 
 /* Method 1: 中心点枚举法 - O(n^2)
  *     中心点枚举法 - 从中心点向往外扩（遍历一下字符串，让每个字符都尝试当中间的值）
  *     注意： 最长回文子序列的长度可能是奇数也可能是偶数，所以中心点的位置有两种情况：从s(i)向外扩
  *         1）遍历所有的 s(i - x) == s(i + x) (x = 1, 2, ...)
  *         2) 遍历所有的 s(i - x) == s(i + 1 + x) (x = 0, 1, 2, ...)
  */
  public String longestPalindrome(String s) {
    // write your code here
    if(s == null || s.length() == 0){
        return "";
    }
    int[] index = {0,1};
    int longest = 1;
    for(int i = 0; i < s.length(); i++){
        int x = 1;
        while(i - x >= 0 && i + x < s.length() && s.charAt(i-x) == s.charAt(i+x)){
            x++;
        }
        if(longest < (x - 1)*2 + 1){
            index[0] = i - x + 1; // substring start position
            index[1] = i + x; // substring end position, not include end
            longest = (x - 1)*2 + 1;
        }
    }
    for(int i = 0; i < s.length(); i++){
        if(i + 1 < s.length() && s.charAt(i) == s.charAt(i+1)){
            int x = 1;
            while(i - x >= 0 && i + 1 + x < s.length() && s.charAt(i-x) == s.charAt(i+1+x)){
                x++;
            }
            if(longest < x*2){
                index[0] = i - x + 1; // substring start position
                index[1] = i + 1 + x; // substring end position, not include end
                longest = x*2;
            }
        }
    }
    if(index[1] > s.length()){
        return s.substring(index[0]);
    }
    return s.substring(index[0],index[1]);
 }
 
 /* 为简化代码，用函数findLongestPalindrome合并相同的代码: 
  *     findLongestPalindromeFrom(s, i, i) 和findLongestPalindromeFrom（s, i, i + 1）
  *／
