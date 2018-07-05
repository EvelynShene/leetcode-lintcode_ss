/** 567. Permutation in String(leetcode)
 *    Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, 
 *  one of the first string's permutations is the substring of the second string.
 *  
 *    Note: The input strings only contain lower case letters.
 *          The length of both given strings is in range [1, 10,000].
 *
 *    Example: 1) Input:s1 = "ab" s2 = "eidbaooo" ; Output:True
 *             Explanation: s2 contains one permutation of s1 ("ba").
 *             2) Input:s1= "ab" s2 = "eidboaoo" ;  Output: False
 */
 
 //My method:
 class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s2.length() < s1.length()){
            return false;
        }
        boolean include = false;
        
        for(int i = 0; i <= s2.length() - s1.length(); i++){
            include = isPermutation(s1, s2, i);
            if(include){
                break;
            }
        }
        return include;
    }
    public boolean isPermutation(String s1, String s2, int start){
        int[] letter = new int[26];
        for(int i = 0; i < s1.length(); i++){
            letter[s1.charAt(i) - 'a']++;
        }
        for(int i = start; i < start + s1.length(); i++){
            letter[s2.charAt(i) - 'a']--;
            if(letter[s2.charAt(i) - 'a'] < 0){
                return false;
            }
        }
        return true;
    }
}
