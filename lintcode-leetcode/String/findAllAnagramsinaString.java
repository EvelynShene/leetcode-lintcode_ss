/** 438. Find All Anagrams in a String(leetcode) / 647. Find All Anagrams in a String(lintcode)
 *      Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *      Strings consists of lowercase English letters only and the length of both strings s and p will not be 
 *  larger than 20,100.
 *      The order of output does not matter.
 *
 *      Example: 1) Input: s: "cbaebabacd" p: "abc" ; Output: [0, 6]
 *               2) Input: s: "abab" p: "ab" ; Output: [0, 1, 2]
 */
 
 //My Method: - 滑动窗口法
 class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        if(s == null || s.length() == 0 || p.length() > s.length()){
            return res;
        }
        int[] letters = new int[26];
        for(int i = 0; i < p.length(); i++){
            letters[p.charAt(i) - 'a']++;
        }
        int[] tmp = new int[26];
        System.arraycopy(letters, 0, tmp, 0, 26);
        int left = 0;
        for(int i = 0; i < s.length() && left <= s.length() - p.length(); i++){
            tmp[s.charAt(i) - 'a']--;
            while(tmp[s.charAt(i) - 'a'] < 0){
                if(letters[s.charAt(i) - 'a'] == 0){
                    left = i + 1;
                    System.arraycopy(letters, 0, tmp, 0, 26);
                }
                else{
                    tmp[s.charAt(left) - 'a']++;
                    left++;
                }
            }
            if(tmp[s.charAt(i) - 'a'] == 0){
                if(check(tmp)){
                    res.add(left);
                    tmp[s.charAt(left) - 'a']++;
                    left++;
                }
            }
        }
        return res;
    }
    
    public boolean check(int[] letter){
        for(int i = 0; i < 26; i++){
            if(letter[i] != 0){
                return false;
            }
        }
        return true;
    }
}

//Method from jiuzhang:
public List<Integer> findAnagrams(String s, String p) {
    // Write your code here
    List<Integer> ans = new ArrayList <Integer>();
    int[] sum = new int[30];

    int plength = p.length(), slength = s.length();
    for(char c : p.toCharArray()){
        sum[c - 'a'] ++;
    }

    int start = 0, end = 0, matched = 0;
    while(end < slength){
        if(sum[s.charAt(end) - 'a'] >= 1){
            matched ++;
        }
        sum[s.charAt(end) - 'a'] --;
        end ++;
        if(matched == plength) {
            ans.add(start);
        }
        if(end - start == plength){
            if(sum[s.charAt(start) - 'a'] >= 0){
                matched --;
            }
            sum[s.charAt(start) - 'a'] ++;
            start ++;
        }
    }
    return ans;
}
