/** 249. Group Shifted Strings(leetcode - locked)
 *     Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". 
 *  We can keep "shifting" which forms the sequence:
 *          "abc" -> "bcd" -> ... -> "xyz"
 *     Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same 
 *  shifting sequence.
 *
 *     For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
 *                  Return:
 *                  [
 *                    ["abc","bcd","xyz"],
 *                    ["az","ba"],
 *                    ["acef"],
 *                    ["a","z"]
 *                  ]
 *     Note: For the return value, each inner list's elements must follow the lexicographic order.
 */
 
 /* Method:
  *   能成为一组的字符串都满足一个规则，即对于两串字符串s和t，如果它们的相邻字符的差值是一致的，那么s和t就能成为一组
  *   1) 例如abc和efg互为偏移，对于abc来说，b和a的距离是1，c和a的距离是2，对于efg来说，f和e的距离是1，g和e的距离是2。
  *   2) 对于az, ba这样的，字符之间的差值不一样，但是它们是一组。因为虽然az字符之间的差值是25，ba之间字符的差值是-1，
  *   但是字符是每隔26就一循环，所以，对于-1，加上26取余26就是25.
  */
  
public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < strings.length; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < strings[i].length(); j++) {
                sb.append(Integer.toString(((strings[i].charAt(j) - strings[i].charAt(0)) + 26) % 26));
                sb.append(" ");
           }
           String shift = sb.toString();

           if (map.containsKey(shift)) {
               map.get(shift).add(strings[i]);
           } else {
               List<String> list = new ArrayList<String>();
               list.add(strings[i]);
               map.put(shift, list);
           }
       }

       for (String s : map.keySet()) {
           Collections.sort(map.get(s));
           result.add(map.get(s));
       }
       return result;
   }
}
