/** 829. Word Pattern II(lintcode) / 291. Word Pattern II(leetcode - locked)
 *      Given a pattern and a string str, find if str follows the same pattern.
 *      Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty
 *   substring in str.(i.e if a corresponds to s, then b cannot correspond to s. For example, given pattern = "ab",
 *   str = "ss", return false.)
 *
 *      Note: You may assume both pattern and str contains only lowercase letters.
 *
 *      Example: 1) Given pattern = "abab", str = "redblueredblue", return true.
 *               2) Given pattern = "aaaa", str = "asdasdasdasd", return true.
 *               3) Given pattern = "aabb", str = "xyzabcxzyabc", return false.
 */
 
 //My Method:
 public class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<Character, String>();
        return wordPattern(pattern, str, map);
    }
    
    public boolean wordPattern(String pattern, String str, Map<Character, String> map){
        if(pattern.length() == 0 && str.length() == 0){
            return true;
        }
        if(pattern.length() == 0 || str.length() == 0){
            return false;
        }
        if(map.containsKey(pattern.charAt(0))){
            if(str.indexOf(map.get(pattern.charAt(0))) != 0){
                return false;
            }
            else{
                int len = map.get(pattern.charAt(0)).length();
                return wordPattern(pattern.substring(1), str.substring(len), map);
            }
        }
        else{
            boolean match = false;
            for(int i = 1; i <= str.length(); i++){
                String tmp = str.substring(0,i);
                if(!map.containsValue(tmp)){
                    map.put(pattern.charAt(0), tmp);
                    match = wordPattern(pattern.substring(1), str.substring(i), map);
                    if(match){
                        return true;
                    }
                    map.remove(pattern.charAt(0));
                }
            }
            return match;
        }
    }
}
