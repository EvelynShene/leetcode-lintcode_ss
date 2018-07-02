/** 213. String Compression (lintcode)/443. String Compression (leetcode)
 *      Implement a method to perform basic string compression using the counts of repeated characters. 
 *   For example, the string aabcccccaaa would become a2b1c5a3.
 *      If the "compressed" string would not become smaller than the original string, 
 *   your method should return the original string.
 *      You can assume the string has only upper and lower case letters (a-z).
 *
 *    Example: 1)str=aabcccccaaa return a2b1c5a3
 *             2)str=aabbcc return aabbcc
 *             3)str=aaaa return a4
 */
 
 public String compress(String str) {
    // write your code here
    if(str == "" || str.length() == 0){
        return str;
    }
    int len = str.length();
    char c = str.charAt(0);
    String s = c + "";
    int count = 1;
    int i = 1;
    while(i < len){
        if(c == str.charAt(i)){
            count++;
        }
        else{
            s = s + (count) + str.charAt(i);
            c = str.charAt(i);
            count = 1;
        }
        i++;
    }
    s += (count);
    if(s.length() >= str.length()){
        return str;
    }
    return s;
 }
