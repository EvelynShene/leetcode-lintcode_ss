/** 290. Word Pattern(leetcode) / 828. Word Pattern(lintcode) —— 做法和isomorphic strings题一样
 *      Given a pattern and a string str, find if str follows the same pattern.
 *  Here follow means a full match, such that there is a bijection between a letter in pattern and 
 *  a non-empty word in str.
 *
 *      Example: 1) Given pattern = "abba", str = "dog cat cat dog", return true.
 *               2) Given pattern = "abba", str = "dog cat cat fish", return false.
 *               3) Given pattern = "aaaa", str = "dog cat cat dog", return false.
 *               4) Given pattern = "abba", str = "dog dog dog dog", return false.
 */
 
 //Method: Use HashMap
 public boolean wordPattern(String pattern, String str) {
    if(pattern == null || str == null){
        return false;
    }
    if(str.length() == 0 && pattern.length() == 0){
        return true;
    }
    if(str.length() == 0 || pattern.length() == 0){
        return false;
    }

    String[] word = str.split(" ");
    int wlen = word.length;
    if(wlen != pattern.length()){
        return false;
    } 
    Map<Character,String> map = new HashMap<Character, String>();
    for(int i = 0; i < wlen; i++){
        if(map.containsKey(pattern.charAt(i))){
            if(!word[i].equals(map.get(pattern.charAt(i)))){
                return false;
            }
        }
        else{
            if(map.containsValue(word[i])){
                return false;
            }
            map.put(pattern.charAt(i),word[i]);
        }
    }
    return true;
 }
