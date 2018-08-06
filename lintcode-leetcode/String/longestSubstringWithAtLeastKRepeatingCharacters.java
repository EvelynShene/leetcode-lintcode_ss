/** 395. Longest Substring with At Least K Repeating Characters(leetcode)
 *                1261. Longest Substring with At Least K Repeating Characters(lintcode)
 *      Find the length of the longest substring T of a given string (consists of lowercase letters only) such that
 *  every character in T appears no less than k times.
 *
 *      Example: 1) Input: s = "aaabb", k = 3 ; Output: 3
 *                  The longest substring is "aaa", as 'a' is repeated 3 times.
 *               2) Input: s = "ababbc", k = 2 ; Output: 5
 *                  The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
 
 /* My Method: 递归 -- worst O(n^2) and average O(nlogn) time complexit
  *    思想：1) 先遍历整个string，并记录不同的字符character的出现次数。
  *           a)如果所有character出现次数都不小于k，那么说明整个string就是满足条件的longest substring，返回原string的长度即可；
  *           b)如果有character的出现次数小于k，假设这个字符是ch，因为满足条件的substring永远不会包含ch，所以满足条件的substring
  *         一定是在以ch为分割参考下的某个substring中。所以把ch当做是split的参考，在得到的String[]中每个字符串再次递归调用函数，
  *         直到找到最大的返回值。
  */
 public int longestSubstring(String s, int k) {
    if(s == null || s.length() == 0 || s.length() < k){
        return 0;
    }
    int[] unsatisfied = new int[26];
    int len = s.length();
    for(int i = 0; i < len; i++){
        unsatisfied[s.charAt(i) - 'a']++;
    }
    boolean all = true;
    int res = 0;
    for(int i = 0; i < 26; i++){
        if(unsatisfied[i] >= k){
            unsatisfied[i] = 0;
        }
        else if(unsatisfied[i] != 0 && unsatisfied[i] < k){
            all = false;
        }
    }
    if(all){
        return len;
    }
    List<String> list = new ArrayList<String>();
    list.add(s);
    for(int i = 0; i < 26; i++){
        if(unsatisfied[i] != 0){
            String ch = ""+(char)('a' + i);
            List<String> tmp = new ArrayList<String>();
            for(int j = 0; j < list.size(); j++){
                String[] str = list.get(j).split(ch);
                for(String x: str){
                    tmp.add(x);
                }
            }
            list = tmp;
        }
    }

    for(int i = 0; i < list.size(); i++){
        String l = list.get(i);
        res = Math.max(res,longestSubstring(l,k));
    }
    return res;
 }
