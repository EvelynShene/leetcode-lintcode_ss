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
 
 //Method 1: 递归
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
