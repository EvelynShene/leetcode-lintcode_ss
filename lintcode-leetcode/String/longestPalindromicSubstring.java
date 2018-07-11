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
  
/* Method 2: DP - O(n^2) time and space complexity
 *    Idea: 一个字符串s(i,j)如果是回文字符串，那么s(i-1,j-1)一定是回文字符串。
 *         1）用数组isP[i][j]指示字符串s(i,j)是否为字符串，初始化所有的单个字符isP[i][i] = true（所有字符本身可以构成回文字符串）
 *         2）当遇到s(i) == s(j)时，isP[i][j]是否为回文字符串，取决于isP[i+1][j-1]是不是回文字符串。
 *         3）由于isP[i][j] 需要 下一行的值isP[i+1][j-1]判断，所有行循环从第n行开始向上遍历。
 */

public String longestPalindrome(String s) {
     if(s == null || s.length() == 0){
         return "";
     }
     int n = s.length();
     boolean[][] isP = new boolean[n][n];
     int longest = 1;
     int index = 0;
     for(int i = 0; i < n; i++){
         isP[i][i] = true;
     }

     for(int i = n - 1; i >= 0; i--){
         for(int j = i + 1; j < n; j++){
             if( s.charAt(i) == s.charAt(j) ){
                 if( j - 1 == i || (i + 1 < n && isP[i + 1][j - 1])){ //j - 1 == i用来处理回文字符长度是偶数的情况
                     isP[i][j] = true;
                     if(longest < j - i + 1){
                         longest = j - i + 1;
                         index = i;
                     }
                 }
             }
         }
     }

     return s.substring(index,index + longest);
 }
