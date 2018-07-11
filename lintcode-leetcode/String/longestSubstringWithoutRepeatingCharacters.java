/** 384. Longest Substring Without Repeating Characters(lintcode) /3. Longest Substring Without Repeating Characters(leetcode)
 *    Given a string, find the length of the longest substring without repeating characters.
 *
 *    Example: 1) Given "abcabcbb", the answer is "abc", which the length is 3.
 *             2) Given "bbbbb", the answer is "b", with the length of 1.
 *             3) Given "pwwkew", the answer is "wke", with the length of 3. 
 *        Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
 
 //Method 1: O(n^2)
 public int lengthOfLongestSubstring(String s) {
    // write your code here
    if(s == null || s.length() == 0){
        return 0;
    }
    Set<Character> set = new HashSet<Character>();
    int max = 0;
    int start = 0;
    for(int i = 0; i < s.length(); i++){
        if(!set.contains(s.charAt(i))){
            max = Math.max(max, i - start + 1);
        }
        else{
            while(start < s.length() && s.charAt(start) != s.charAt(i)){
                set.remove(s.charAt(start));
                start++;
            }
            start++;
        }
        set.add(s.charAt(i));
    }
    return max;
 }
 
 //Method 2:  Sliding Window (对方法一的简化) - O(n) time and O(k) space (k is the size of the Set)
 public int lengthOfLongestSubstring(String s) {
    if(s == null || s.length() == 0){
        return 0;
    }
    Set<Character> set = new HashSet<Character>();
    int max = 0;
    int start = 0;
    int i = 0;
    while(i < s.length()){
        if(!set.contains(s.charAt(i))){
            set.add(s.charAt(i));
            i++;
            max = Math.max(max, i - start);
        }
        else{
            set.remove(s.charAt(start));
            start++;
        }   
    }
    return max;
 }
 
 /* Note:
  *   上述两种方法的空间复杂度也可以表示为 O(min(n,m))，即集合的大小：
  *   The size of the Set is upper bounded by the size of the string nn and the size of the charset/alphabet mm. 
  *／
