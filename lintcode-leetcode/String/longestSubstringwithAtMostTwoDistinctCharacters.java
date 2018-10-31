/** 159. Longest Substring with At Most Two Distinct Characters(leetcode)
 *     Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 *     Example: 1) Input: "eceba" ; Output: 3
 *                  Explanation: t is "ece" which its length is 3.
 *              2) Input: "ccaabbb" ; Output: 5
 *                  Explanation: t is "aabbb" which its length is 5.
 */
 
 //My Method: Two Pointers - O(n) time and O(n) space complexity
 class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || s.length() < 2){
            return s.length();
        }
        int n = s.length();
        int left = 0; 
        Map<Character, Integer> ch_set = new HashMap<Character, Integer>();
        int longest = 0;
        
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(ch_set.size() < 2){
                ch_set.put(c, ch_set.getOrDefault(c, 0) + 1);
            }
            else{ // set.size() == 2
                if(ch_set.containsKey(c)){
                    ch_set.put(c, ch_set.get(c) + 1);
                }
                else{// c not in ch_set 
                    longest = Math.max(longest, i - left);
                    while(left < i && ch_set.size() == 2){
                        char rm_ch = s.charAt(left);
                        if(ch_set.get(rm_ch) == 1){
                            ch_set.remove(rm_ch);
                        }
                        else{
                            ch_set.put(rm_ch, ch_set.get(rm_ch) - 1);
                        }
                        left++;
                    }
                    ch_set.put(c, 1);
                }
            }
        }
        if(left < n){
            longest = Math.max(longest, n - left);
        }
        return longest;
    }
}
