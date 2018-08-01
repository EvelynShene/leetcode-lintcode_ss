/* 205. Isomorphic Strings(leetcode) / 638. Isomorphic Strings(lintcode)
 *      Given two strings s and t, determine if they are isomorphic.
 *      Two strings are isomorphic if the characters in s can be replaced to get t.
 *      All occurrences of a character must be replaced with another character while preserving the order of 
 *  characters. No two characters may map to the same character but a character may map to itself.
 *
 *      Example: 1) Given s = "egg", t = "add", return true.
 *               2) Given s = "foo", t = "bar", return false.
 *               3) Given s = "paper", t = "title", return true.
 */
 
 /* My Method: HashMap
  *   用两个map是为了考虑s和t的顺序对互换映射的影响，例如 “aa” 和 “ab”
  *   Note: 不能一个一个替换然后最后比较是否相等，应为替换是同时进行的，如“abab”和“baba”.
  */
 public boolean isIsomorphic(String s, String t) {
    if(s == null || t == null){
        return false;
    }
    int n = s.length();
    if(n == 0){
        return true;
    }
    Map<Character,Character> replace = new HashMap<Character,Character>();
    for(int i = 0; i < n; i++){
        if(replace.containsKey(s.charAt(i))){
            char tmp = replace.get(s.charAt(i));
            if(tmp != t.charAt(i)){
                return false;
            }  
        }
        else{
            replace.put(s.charAt(i),t.charAt(i));
        }
    }
    Map<Character,Character> reverse = new HashMap<Character,Character>();
    for(Map.Entry<Character,Character> entry: replace.entrySet()){
        if(reverse.containsKey(entry.getValue()))
            return false;
        else
            reverse.put(entry.getValue(),entry.getKey());
    }
    return true;
 }
 
 //Method 2: 直接用map.containsValue()函数，减少空间开销
 public boolean isIsomorphic(String s, String t) {
    if(s == null || t == null){
        return false;
    }
    int n = s.length();
    if(n == 0){
        return true;
    }
    Map<Character,Character> replace = new HashMap<Character,Character>();
    for(int i = 0; i < n; i++){
        if(replace.containsKey(s.charAt(i))){
            char tmp = replace.get(s.charAt(i));
            if(tmp != t.charAt(i)){
                return false;
            }  
        }
        else{
            if(!replace.containsValue(t.charAt(i))){
                replace.put(s.charAt(i),t.charAt(i));
            }
            else{
                return false;
            }
        }
    }
    return true;
 }
 
 // Method 3:  与使用hashmap的思路一样，只是运用八位ASCII码有256个设置两个固定数组，每个字符的ascii对应元素在数组中的下标。
 public boolean isIsomorphic(String s, String t) {
    if(s == null || t == null){
        return false;
    }
    int n = s.length();
    if(n == 0){
        return true;
    }
    int[] map1 = new int[256];
    int[] map2 = new int[256];
    for(int i = 0; i < n; i++){
        if(map1[s.charAt(i)] != map2[t.charAt(i)]){
            return false;
        }
        map1[s.charAt(i)] = i + 1; //用i+1是为了区别初始值0
        map2[t.charAt(i)] = i + 1;
    }
    return true;
 }
