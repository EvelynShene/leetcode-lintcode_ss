/** 344. Reverse String(leetcode)
 *    Write a function that takes a string as input and returns the string reversed.
 *
 *    Example: Given s = "hello", return "olleh".
 */
 
 //Method: O(n) time and space complexity (n = s.length())
 public String reverseString(String s) {
    if(s == null || s.length() == 0){
        return s;
    }
    StringBuilder str = new StringBuilder();
    for(int i = s.length()-1; i >= 0; i--){
        str.append(s.charAt(i));
    }
    return str.toString();
 }
