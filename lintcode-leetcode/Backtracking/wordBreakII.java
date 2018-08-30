/** 140. Word Break II(leetcode) / 582. Word Break II(lintcode)
 *      Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s
 *  to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 *
 *      Note: 1)The same word in the dictionary may be reused multiple times in the segmentation.
 *            2)You may assume the dictionary does not contain duplicate words.
 *      
 *      Example: 1) Input: s = "catsanddog" ; wordDict = ["cat", "cats", "and", "sand", "dog"]
 *                  Output:
 *                         [
 *                           "cats and dog",
 *                           "cat sand dog"
 *                         ]
 *               2) Input: s = "pineapplepenapple" ; wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 *                  Output:
 *                          [
 *                            "pine apple pen apple",
 *                            "pineapple pen apple",
 *                            "pine applepen apple"
 *                          ]
 *                  Explanation: Note that you are allowed to reuse a dictionary word.
 */
 
 //Method: Backtracking + memorization (only Backtracking will lead to TLE)
 class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wd = new HashSet<String>();
        for(int i = 0; i < wordDict.size(); i++){
            wd.add(wordDict.get(i));
        }
        List<String> res = new ArrayList<String>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        wordsbreak(s, wd, "", res, map);
        return res;
    }
    
    public void wordsbreak(String s, Set<String> wordDict, String str, List<String> res, Map<String, List<String>> map){
        if(s.length() == 0){
            res.add(str);
            return;
        }
        List<String> l = new ArrayList<>();
        if(!map.containsKey(s)){
            for(int i = 1; i <= s.length(); i++){
                String word = s.substring(0, i);
                if(wordDict.contains(word)){
                    if(s.indexOf(word) == 0){
                        wordsbreak(s.substring(word.length()), wordDict, word, l, map);
                    }
                }
            }
            map.put(s,l);
        }
        else{
            l = map.get(s);
        }
        for(int i = 0; i < l.size(); i++){
            if(str.length() == 0){
                res.add(l.get(i));
            }
            else{
                res.add(str + " " + l.get(i));
            }
        }
    }
}
