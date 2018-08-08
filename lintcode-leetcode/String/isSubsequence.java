/** 392. Is Subsequence(leetcode) / 1263. Is Subsequence(lintcode)
 *      Given a string s and a string t, check if s is subsequence of t.
 *      You may assume that there is only lower case English letters in both s and t. t is potentially a very long 
 *  (length ~= 500,000) string, and s is a short string (<=100).
 *      A subsequence of a string is a new string which is formed from the original string by deleting some 
 *  (can be none) of the characters without disturbing the relative positions of the remaining characters. 
 *  (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 *
 *      Example: 1) s = "abc", t = "ahbgdc" ; Return true.
 *               2) s = "axc", t = "ahbgdc" ; Return false.
 *
 *      Follow up:
 *         If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one 
 *      to see if T has its subsequence. In this scenario, how would you change your code?
 */
 
 //Method 1:
 public boolean isSubsequence(String s, String t) {
    if(t == null || s == null){
        return false;
    }
    int m = s.length();
    int n = t.length();
    if(m == 0){
        return true;
    }
    if(n < m){
        return false;
    }
    int i = 0;
    int j = 0;
    while(i < m && j < n){
        if(s.charAt(i) == t.charAt(j)){
            i++;
        }
        j++;
    }
    if(i < m){
        return false;
    }
    return true;
 }
 
 /* Follow up:  Use Map [code from leetcode]
  *      The best solution is to create a map for String t, key is char, value is the index of appearance 
  *  in ascending order.
  */
  /**
 * Follow-up
 * If we check each sk in this way, then it would be O(kn) time where k is the number of s and t is the length of t. 
 * This is inefficient. 
 * Since there is a lot of s, it would be reasonable to preprocess t to generate something that is easy to search for if a character of s is in t. 
 * Sounds like a HashMap, which is super suitable for search for existing stuff. 
 */
public boolean isSubsequence(String s, String t) {
    if (s == null || t == null) return false;
    
    Map<Character, List<Integer>> map = new HashMap<>(); //<character, index>
    
    //preprocess t
    for (int i = 0; i < t.length(); i++) {
        char curr = t.charAt(i);
        if (!map.containsKey(curr)) {
            map.put(curr, new ArrayList<Integer>());
        }
        map.get(curr).add(i);
    }
    
    int prev = -1;  //index of previous character
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        
        if (map.get(c) == null)  {
            return false;
        } else {
            List<Integer> list = map.get(c);
            prev = binarySearch(prev, list, 0, list.size() - 1);
            if (prev == -1) {
                return false;
            }
            prev++;
        }
    }
    
    return true;
}
//binarySearch - 找到比prev下标大的最小下标
private int binarySearch(int index, List<Integer> list, int start, int end) {
    while (start <= end) {
        int mid = start + (end - start) / 2;
        if (list.get(mid) < index) {
            start = mid + 1;
        } else {
            end = mid - 1;
        }
    }
    
    return start == list.size() ? -1 : list.get(start);
}
  
