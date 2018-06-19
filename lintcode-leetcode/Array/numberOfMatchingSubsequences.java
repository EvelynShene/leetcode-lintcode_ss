/** 1024. Number of Matching Subsequences(lintcode) /792. Number of Matching Subsequences(leetcode)
 *    Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *    Note: 
 *       All words in words and S will only consists of lowercase letters.
 *       The length of S will be in the range of [1, 50000].
 *       The length of words will be in the range of [1, 5000].
 *       The length of words[i] will be in the range of [1, 50].
 *    Example:
 *        Input: S = "abcde", words = ["a", "bb", "acd", "ace"]
 *        Output: 3
 *     Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 */
 
 //Method 1: [AC in lintcode but Time Limit Exceeded in leetcode]
  public int numMatchingSubseq(String S, String[] words) {
      // Write your code here
      int count = 0;
      for(int i = 0; i < words.length; i++){
          int x = 0, y = 0;
          while(x < S.length() && y < words[i].length()){
              if(S.charAt(x) == words[i].charAt(y)){
                  y++;
              }
              x++;
          }
          if(y == words[i].length()){
              count++;
          }
      }
      return count;
  }
  
  /* Method 2:(Idea From Leetcode Discuss, AC in leetcode)
   *  Idea: 
   *  Classify the words in words[] by its first letter that are not matched.
   *  Go through String S once, once the first letter matched, classify the words again using their new nonmatched first letter.
   */
   
 class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, List<String>> map = new HashMap<Character,List<String>>();
         
        for(int i = 0; i < words.length; i++){
            char firstChar = words[i].charAt(0);
            addWord(map,firstChar,words[i]);
        }
        int count = 0;
        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            if(map.containsKey(c)){
                count += matchAndChangeMap(map,c);
            }
        }
        return count;
    }
    
    public int matchAndChangeMap(Map<Character, List<String>> map, char c){
        int match_num = 0;
        List<String> list = map.get(c);
        map.remove(c);
        for(int x = 0; x < list.size(); x++){
            String str = list.get(x);
            if(str.length() == 1){
                match_num++;
            }
            else{
                str = str.substring(1); //remove matched letter
                addWord(map, str.charAt(0), str);
            } 
        }
        return match_num;
    }
    
    public void addWord(Map<Character, List<String>> map, char c, String w){
        List<String> list = new ArrayList<String>();
        if(map.containsKey(c)){
            list = map.get(c);
        }
        list.add(w);
        map.put(c,list);
    }
}  
