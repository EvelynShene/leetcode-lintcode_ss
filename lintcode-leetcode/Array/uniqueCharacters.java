/** 157. Unique Characters (lintcode)
 *  Implement an algorithm to determine if a string has all unique characters. (String/Array)
 */
 
 public class Solution {
    /*
     * @param str: A string
     * @return: a boolean
     */
    public boolean isUnique(String str) {
        // write your code here
        Map<Character, Integer> m = new HashMap<Character,Integer>();
        
        for(int i = 0; i < str.length(); i++){
            if(m.containsKey(str.charAt(i))){
                return false;
            }
            m.put(str.charAt(i),i);
        }
        return true;
    }
}
