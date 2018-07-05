/** 158. Valid Anagram(lintcode)/ 242. Valid Anagram (leetcode)
 *     Write a method anagram(s,t) to decide if two strings are anagrams or not.
 *     Clarification: What is Anagram?
 *                Two strings are anagram if they can be the same after change the order of characters.
 *
 *     Example:
 *             Given s = "abcd", t = "dcab", return true.
 *             Given s = "ab", t = "ab", return true.
 *             Given s = "ab", t = "ac", return false.
 */
 
 //Method 1: Use HashMap - O(n) time space
 public boolean anagram(String s, String t) {
    // write your code here
    if(s == null || t == null || s.length() != t.length()){
        return false;
    }
    if(s.equals(t)){
        return true;
    }
    Map<Character, Integer> map = new HashMap<Character,Integer>();
    for(int i = 0; i < s.length(); i++){
        if(map.containsKey(s.charAt(i))){
            map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        }
        else{
            map.put(s.charAt(i),1);
        }
    }

    for(int i = 0; i < t.length(); i++){
        if(map.containsKey(t.charAt(i))){
            if(map.get(t.charAt(i)) == 1){
                map.remove(t.charAt(i));
            }
            else{
                map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
            }
        }
        else{
            return false;
        }
    }
    return true;
 }
 
 /* In leetcode: You may assume the string contains only lowercase alphabets.
  *  Follow up: What if the inputs contain unicode characters? How would you adapt your solution to such case?
  *        Ans: Use a hash table instead of a fixed size counter (like Method 1).
  */
 
 // Method 2: Sort Array - O(nlogn) time and O(1) space
 public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    char[] str1 = s.toCharArray();
    char[] str2 = t.toCharArray();
    Arrays.sort(str1);
    Arrays.sort(str2);
    return Arrays.equals(str1, str2);
 }
 
 // Method 3: O(n) time and space
 public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    int[] counter = new int[26];
    for (int i = 0; i < s.length(); i++) {
        counter[s.charAt(i) - 'a']++;
        counter[t.charAt(i) - 'a']--;
    }
    for (int count : counter) {
        if (count != 0) {
            return false;
        }
    }
    return true;
 }
