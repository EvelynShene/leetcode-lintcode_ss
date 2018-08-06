/** 76. Minimum Window Substring(leetcode) / 32. Minimum Window Substring(lintcode)
 *      Given a string S and a string T, find the minimum window in S which will contain all the characters in T 
 *  in complexity O(n).
 *
 *      Example: Input: S = "ADOBECODEBANC", T = "ABC" ; Output: "BANC"
 *
 *      Note:1) If there is no such window in S that covers all characters in T, return the empty string "".
 *           2) If there is such window, you are guaranteed that there will always be only one unique minimum 
 *          window in S.
 */
 
 /* Method: Use HashMap/Array + Two Pointer
  *    思路：1) 用两个指针作为滑动窗口的两端，用2个hashMap或数组，一个记录目标字符串t内包含的所有字符和它们出现的个数，另一个记录当前
  *      遍历的字符串s得到的窗口[start,end]中的所有字符和它们出现的个数。
  *         2) count记录窗口[start,end]中t里面字符出现的个数
  *         3) 先移动窗口右端end指针，找到s中满足条件(count == tlen)的字符串。
  *         4) 再移动窗口左端start指针，让窗口逼近最小的子串，即start不能往右移为止，多右移一位子串就不满足包含t内所有的字符了
  *         5) 比较当前得到的子串和上一次找到的子串，取长度小的子串
  *         6) 把start右移一位，让子串不符合要求。
  *           [因为第4步已经让窗口趋于最小，所以右移动一位，一定不包含t中的某个字符／或少于需要的字符个数]
  *         7) 循环回到第3步，只到s遍历完成。
  *
  *     时间复杂度 = O(n):
  *         之所以时间复杂度是O(n)是因为s里每个字符最多遍历两次，一次是end右移时，一次是start右移时。
  */
 //Use HashMap
 public String minWindow(String s, String t) {
    if(s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length())
        return "";
    int slen = s.length();
    int tlen = t.length();
    Map<Character,Integer> t_set = new HashMap<Character,Integer>();
    for(int i = 0; i < tlen; i++){
        if(!t_set.containsKey(t.charAt(i))){
            t_set.put(t.charAt(i),1);
        }
        else{
            t_set.put(t.charAt(i),t_set.get(t.charAt(i)) + 1);
        }
    }
    int start = 0;
    int count = 0;
    String res = "";
    Map<Character,Integer> found = new HashMap<Character,Integer>();
    for(int end = 0; end < slen; end++){
        if(!found.containsKey(s.charAt(end))){
            found.put(s.charAt(end), 1);
        }
        else{
            found.put(s.charAt(end),found.get(s.charAt(end)) + 1);
        }

        if(t_set.containsKey(s.charAt(end)) && found.get(s.charAt(end)) <= t_set.get(s.charAt(end))){
            count++;
        }
        if(count == tlen){//found all the characters in T
            while(!t_set.containsKey(s.charAt(start)) || (t_set.get(s.charAt(start)) < found.get(s.charAt(start))) ){         
                if(found.get(s.charAt(start)) == 1){
                    found.remove(s.charAt(start)); 
                }                                                                                                                                 else{
                    found.put(s.charAt(start), found.get(s.charAt(start)) - 1);
                }
                start++;
            }   
            String tmp = s.substring(start,end + 1);
            if(res.equals("") || res.length() > tmp.length()){
                res = tmp;
            }
            if(found.get(s.charAt(start)) == 1){
                found.remove(s.charAt(start)); 
            }                                                                                                                                 else{
                found.put(s.charAt(start), found.get(s.charAt(start)) - 1);
            }
            count--;
            start++;   
        }
    }
    return res;
 }
 
 //Use Array
 public String minWindow(String s, String t) {
    if(s == null || t == null)
        return "";
    int slen = s.length();
    int tlen = t.length();
    if(slen == 0 || tlen == 0 || slen < tlen)
        return "";
    int[] t_set = new int[256]; //ASCII has 256 characters
    int[] found = new int[256];
    for(int i = 0; i < tlen; i++){
        t_set[t.charAt(i)]++;
    }
    int start = 0;
    int count = 0;
    String res = "";
    for(int end = 0; end < slen; end++){
        char ch = s.charAt(end);
        found[ch]++;
        if(t_set[ch] != 0 && found[ch] <= t_set[ch]){
            count++;
        }
        if(count == tlen){
            ch = s.charAt(start);
            while(t_set[ch] == 0 || t_set[ch] < found[ch]){
                found[ch]--;
                start++;
                ch = s.charAt(start);
            }
            String tmp = s.substring(start, end + 1);
            if(res.equals("") || res.length() > tmp.length()){
                res = tmp;
            }
            found[s.charAt(start)]--;
            start++;
            count--;
        }
    }
    return res;
 }
