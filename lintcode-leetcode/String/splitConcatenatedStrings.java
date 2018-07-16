/** 876. Split Concatenated Strings(lintcode) / 555. Split Concatenated Strings(leetcode-locked)
 *      Given a list of strings, you could concatenate these strings together into a loop, 
 *   where for each string you could choose to reverse it or not. Among all the possible loops, 
 *   you need to find the lexicographically biggest string after cutting the loop, which will make the looped 
 *   string into a regular one.
 *
 *      Specifically, to find the lexicographically biggest string, you need to experience two phases:
 *        1)Concatenate all the strings into a loop, where you can reverse some strings or not 
 *    and connect them in the same order as given.
 *        2)Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular
 *    one starting from the character at the cutpoint.
 *        And your job is to find the lexicographically biggest one among all the possible regular strings.
 *
 *      Note: - The input strings will only contain lowercase letters.
 *            - The total length of all the strings will not over 1,000.
 *
 *      Example: Given str = ["abc", "xyz"], return "zyxcba"
 *      Explanation:  You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-", 
 *                    where '-' represents the looped status. 
 *                    The answer string came from the fourth looped one, 
 *                    where you could cut from the middle character 'a' and get "zyxcba".
 */
 
 /* Method: 
  *   题意：给一系列字符串，把它们连接合成一个长字符串，连接过程中每个字符串可以反转或者不变，最后得到一个字符串它的字母顺序最大。
  *        求字母顺序最大的字符串，可以直接用String的compareTo()方法：s1.compareTo(s2) > 0 => s1的字母顺序大于s2的字母顺序。
  *
  *   思路：1）先逐个判断所有的字符串本身和它的反转谁的字母顺序大，反转顺序大，则连接它的反转。
  *        2）对于切割点，可能出现在每个字符串的每个字符节点位置，所以需要逐个判断。
  *        eg: 比如字符串数组["abc", "xyz"]，遍历切割点在"abc"时,考虑：
  *              -abc-zyx-, -bc-zyx-a-, -c-zyx-ab-, -zyx-abc-
  * 
  *     **需要注意(Note)：切割点所在的字符串不一定是反转的顺序大就使得整个长字符串顺序大，随意切割点所在的字符串正、反都要测试一次。       
  *        eg: 比如字符串数组["lc", "love", "ydc"]，如果每个字符串反转顺序大就反转的思路得到的字符串应该为"ydclclove"，
  *        但"ylclovecd" > "ydclclove"。cut位置所在的字符串ydc反转成了cdy，虽然cdy小于ydc，但翻转后能使整个字符串顺序变大了。
  *        （其他的字符都是按照字母顺序来确定要不要翻转的）
  *
  */
  
 //code:
 public String splitLoopedString(List<String> strs) {
    // write your code here
    if(strs == null || strs.size() == 0){
        return "";
    }

    String s = "";
    for(int i = 0; i < strs.size(); i++){
        String rev = new StringBuilder(strs.get(i)).reverse().toString();
        // String rev = strs.get(i).reverse();
        if(rev.compareTo(strs.get(i)) > 0){
            s += rev;
        }
        else{
            s += strs.get(i);
        }
    }
    int start = 0;
    String max = "a";
    for(int i = 0; i < strs.size(); i++){
        String rev = new StringBuilder(strs.get(i)).reverse().toString();
        String body = s.substring(start + strs.get(i).length()) + s.substring(0,start);
        for(int j = 0; j <= rev.length(); j++){
            String s1 = strs.get(i).substring(j) + body + strs.get(i).substring(0,j);
            String s2 = rev.substring(j) + body + rev.substring(0,j);
            if(s1.compareTo(max) > 0){
                max = s1;
            }
            if(s2.compareTo(max) > 0){
                max = s2;
            }
        }
        start += strs.get(i).length();
    }
    return max;
 }
