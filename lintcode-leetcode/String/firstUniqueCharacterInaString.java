/** 209. First Unique Character in a String(lintcode)
 *   Find the first unique character in a given string. You can assume that there is at least one unique character in the string.
 *
 *   Example: For "abaccdeff", return 'b'.
 */
 
 //Method: O(n^2), n is the length of string
 public char firstUniqChar(String str) {
    // Write your code here
    char c = 0; //initial the char variable
    if(str == null || str == "" || str.length() == 0){
        return c;
    }

    for(int i = 0; i < str.length(); i++){
        int j = 0;
        for(j = 0; j < str.length(); j++){
            if(i != j && str.charAt(i) == str.charAt(j)){
                break;
            }
        }
        if(j >= str.length()){
            c = str.charAt(i);
            break;
        }
    }
    return c;
 }
 
 /**  387. First Unique Character in a String(leetcode)
  *   Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
  *
  *   Note: You may assume the string contain only lowercase letters.
  *
  *   Example: s = "leetcode" ;     return 0.
  *            s = "loveleetcode" ; return 2.
  */
  
  //Method 1: O(n^2), n is the length of string
  public int firstUniqChar(String s) {
      if(s == null || s == "" || s.length() == 0){
          return -1;
      }
      int index = -1;
      for(int i = 0; i < s.length(); i++){
          int j = 0;
          for(j = 0; j < s.length(); j++){
              if(i != j && s.charAt(i) == s.charAt(j)){
                  break;
              }
          }
          if(j >= s.length()){
              index = i;
              break;
          }
      }
      return index;
  }
  
  //Method 2: O(n) time and O(1) space (n is the length of string)
  public int firstUniqChar(String s) {
      if(s == null || s == "" || s.length() == 0){
          return -1;
      }
      int[][] count = new int[26][2];
      for(int i = 0; i < s.length(); i++){
          if(count[s.charAt(i) - 'a'][0] == 0){
              count[s.charAt(i) - 'a'][1] = i; // store the first position
          }
          count[s.charAt(i) - 'a'][0]++;
      }

      int index = -1;
      for(int i = 0; i < 26; i++){
          if(count[i][0] == 1){
              if(index == -1){
                  index = count[i][1];
              }
              else{
                  index = Math.min(index, count[i][1]);
              }
          }
      }
      return index;
  }
