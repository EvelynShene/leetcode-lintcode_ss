/** 422. Length of Last Word(lintcode)/ 58. Length of Last Word(leetcode)
 *      Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
 *   return the length of last word in the string.
 *      If the last word does not exist, return 0.
 *
 *      Note: A word is defined as a character sequence consists of non-space characters only.
 *
 *      Example: Given s = "Hello World", return 5.
 */
 
 public int lengthOfLastWord(String s) {
    // write your code here
    if(s == null || s == "" || s.length() == 0){
        return 0;
    }
    int i = s.length() - 1;
    while(i >= 0 && s.charAt(i) == ' '){
        i--;
    }
    int end = i + 1;
    while(i >= 0 && s.charAt(i) != ' '){
        i--;
    }
    int len = 0;
    if(end >= s.length()){
        len = s.length() - i - 1;
    }
    else{
        len = end - i - 1;
    }
    return len;
 }
