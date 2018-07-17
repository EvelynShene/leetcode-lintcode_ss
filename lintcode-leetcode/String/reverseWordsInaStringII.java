/** 927. Reverse Words in a String II(lintcode) / 186. Reverse Words in a String II(leetcode - locked)
 *      Given an input character array, reverse the array word by word. 
 *  A word is defined as a sequence of non-space characters.
 *      The input character array does not contain leading or trailing spaces and the words are always separated 
 *  by a single space.
 *
 *      Example: Given s = "the sky is blue", after reversing : "blue is sky the"
 *
 *      Challenge: Could you do it in-place without allocating extra space?
 */
 
 //Method 1: 
 public char[] reverseWords(char[] str) {
    // write your code here
    if(str == null || str.length == 0){
        return str;
    }
    String s = String.valueOf(str);
    StringBuilder newstr = new StringBuilder();
    int start = 0;
    for(int i = 0; i < str.length; i++){
        if(str[i] == ' '){
            newstr.insert(0," "+s.substring(start, i));
            start = i + 1;
        }
    }
    if(start < str.length){
        newstr.insert(0,s.substring(start));
    }
    return newstr.toString().toCharArray();
 }
 
 //Method 2: reverse the string, do it in-place
 public class Solution {
    public char[] reverseWords(char[] str) {
        if(str == null || str.length == 0){
            return str;
        }
        reverse(str, 0, str.length - 1);
        int start = 0;
        for(int i = 0; i < str.length; i++){
            if(str[i] == ' '){
                reverse(str,start,i-1);
                start = i + 1;
            }
        }
        if(start < str.length){
            reverse(str,start,str.length-1);
        }
        return str;
    }
    
    public void reverse(char[] str, int start, int end){
        while(start < end){
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
    }
}
