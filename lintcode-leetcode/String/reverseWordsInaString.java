/** 53. Reverse Words in a String(lintcode) / 151. Reverse Words in a String(leetcode)
 *    Given an input string, reverse the string word by word.
 *
 *    For example, Given s = "the sky is blue", return "blue is sky the".
 *    
 *    Note: - A word is defined as a sequence of non-space characters.
 *          - Input string may contain leading or trailing spaces. 
 *                However, your reversed string should not contain leading or trailing spaces.
 *          - You need to reduce multiple spaces between two words to a single space in the reversed string.
 */
 
 //Method:
 public String reverseWords(String s) {
    // write your code here
    if(s == null || s == "" || s.length() == 0){
        return s;
    }
    int i = s.length()-1;
    while(i >= 0 && s.charAt(i) == ' '){ // remove trailing spaces
        i--;
    }
    StringBuilder str = new StringBuilder();
    while(i >= 0){
        int end = i+1;
        while(i >= 0 && s.charAt(i) != ' '){
            i--;
        }
        if(end >= s.length())
            str.append(s.substring(i+1));
        else
            str.append(s.substring(i+1,end));
        while(i >= 0 && s.charAt(i) == ' '){
            i--;
        }
        if(i >= 0){
            str.append(" ");
        }
    }
    return str.toString();
 }
