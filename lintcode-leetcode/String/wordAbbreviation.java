/** 639. Word Abbreviation(lintcode) / 527. Word Abbreviation(leetcode- locked)
 *      Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations 
 *  for every word following rules below.
 *      1. Begin with the first character and then the number of characters abbreviated, which followed by 
 *  the last character.
 *      2. If there are any conflict, that is more than one words share the same abbreviation, a longer prefix 
 *  is used instead of only the first character until making the map from word to abbreviation become unique. 
 *  In other words, a final abbreviation cannot map to more than one original words.
 *      3. If the abbreviation doesn't make the word shorter, then keep it as original.
 *
 *      Note: 1. Both n and the length of each word will not exceed 400.
 *            2. The length of each word is greater than 1.
 *            3. The words consist of lowercase English letters only.
 *            4. The return answers should be in the same order as the original array.
 *
 *      Example: 
 *         Given dict = ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 *         return ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 */ 
 
 //Method: 
 public class Solution {
    /**
     * @param dict: an array of n distinct non-empty strings
     * @return: an array of minimal possible abbreviations for every word
     */
    public String[] wordsAbbreviation(String[] dict) {
        // write your code here
        if(dict == null || dict.length == 0){
            return null;
        }
        String[] abbrs = new String[dict.length];
        int[] prefix = new int[dict.length];
        
        for(int i = 0; i < dict.length; i++){
            prefix[i] = 1;
            abbrs[i] = getAbbreviation(dict[i],prefix[i]);
        }
        for(int i = 0; i < abbrs.length; i++){
            while(true){
                Set<Integer> set = new HashSet<>();
                for(int j = i+1; j < abbrs.length; j++){//找到与abbrs[i]有相同缩写形式的所有字符串（的索引j）
                    if(abbrs[j].equals(abbrs[i])){// abbrs[j] and abbrs[i] has the same abbreviations
                        set.add(j);
                    }
                }
                if(set.isEmpty()){
                    break;
                }
                set.add(i);
                for(int index: set){
                    abbrs[index] = getAbbreviation(dict[index],++prefix[index]);
                }
            }
        }
        return abbrs;
    }
    
    public String getAbbreviation(String word, int prefix){
        if(word.length() - prefix > 2){
            return word.substring(0,prefix) + (word.length() - prefix - 1) + word.charAt(word.length()-1);
        }
        return word;
    }
}
