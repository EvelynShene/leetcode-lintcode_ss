/** 1362. Substring with Concatenation of All Words(lintcode) / 30. Substring with Concatenation of All Words(leetcode)
 *      You are given a string, s, and a list of words, words, that are all of the same length. 
 *    Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once
 *    and without any intervening characters.
 *
 *    Example 1: Input: s = "barfoothefoobarman", words = ["foo","bar"] 
 *             Output: [0,9]
 *        Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
 *                     The output order does not matter, returning [9,0] is fine too.
 *
 *    Example 2: Input: s = "wordgoodstudentgoodword", words = ["word","stud"]
 *               Output: []        
 */
 
 //Method: Use two HashMap
 public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> list = new ArrayList<Integer>();
    if(s == null || words == null || s.length() == 0 || words.length == 0){
        return list;
    }

    Map<String, Integer> w = new HashMap<String, Integer>();
    for(int i = 0; i < words.length; i++){
        if(!w.containsKey(words[i])){
            w.put(words[i],1);
        }
        else{
            w.put(words[i], w.get(words[i]) + 1);
        }
    }
    int wordlen = words[0].length();
    Map<String, Integer> found = new HashMap<String, Integer>();
    for(int i = 0; i <= s.length() - words.length*wordlen; i++){
        found.clear();
        int j = 0;
        for(; j < words.length; j++){
            int k = i + j * wordlen;
            String subword = s.substring(k, k + wordlen);
            if(!w.containsKey(subword)){
                break;
            }
            else{
                if(!found.containsKey(subword)){
                    found.put(subword,1);
                }
                else{
                    found.put(subword, found.get(subword)+1);
                }
            }
            if(found.get(subword) > w.get(subword)){
                break;
            }
        }
        if(j == words.length){
            list.add(i);
        }
    }       
    return list;
 }
