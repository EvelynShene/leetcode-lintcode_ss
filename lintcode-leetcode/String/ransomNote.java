/** 1270. Ransom Note(lintcode) / 383. Ransom Note(leetcode)
 *      Given an arbitrary ransom note string and another string containing letters from all the magazines, 
 *   write a function that will return true if the ransom note can be constructed from the magazines; otherwise, it will return false.
 *      Each letter in the magazine string can only be used once in your ransom note.
 *
 *      Note: You may assume that both strings contain only lowercase letters.
 *
 *      Example: 1) canConstruct("a", "b") -> false
 *               2) canConstruct("aa", "ab") -> false
 *               3) canConstruct("aa", "aab") -> true
 */
 
 public boolean canConstruct(String ransomNote, String magazine) {
    if(ransomNote == null || magazine == null){
        return false;
    }
    if(ransomNote.length() == 0){
        return true;
    }
    int[] letters = new int[26];
    for(int i = 0; i < magazine.length(); i++){
        letters[magazine.charAt(i) - 'a']++;
    }
    for(int i = 0; i < ransomNote.length(); i++){
        letters[ransomNote.charAt(i) - 'a']--;
        if(letters[ransomNote.charAt(i) - 'a'] < 0){
            return false;
        }
    }
    return true;
 }
