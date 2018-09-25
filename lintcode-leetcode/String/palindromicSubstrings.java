/** 647. Palindromic Substrings(leetcode) / 837. Palindromic Substrings(lintcode)
 *      Given a string, your task is to count how many palindromic substrings in this string.
 *      The substrings with different start indexes or end indexes are counted as different substrings even they 
 *  consist of same characters.
 *      Example: 1) Input: "abc" ; Output: 3
 *                  Explanation: Three palindromic strings: "a", "b", "c".
 *               2) Input: "aaa" ; Output: 6
 *                  Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
 
 //My Method:
 class Solution {
    public int countSubstrings(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int res = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            res++;
            //palindromic substrings - even
            int x = 0;
            while(i - x >= 0 && i + x + 1 < n && s.charAt(i - x) == s.charAt(i + x + 1)){
                res++;
                x++;
            }
            x = 1;
            while(i - x >= 0 && i + x < n && s.charAt(i - x) == s.charAt(i + x)){
                res++;
                x++;
            }
        }
        return res;
    }
}
