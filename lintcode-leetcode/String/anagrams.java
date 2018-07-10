/** 171. Anagrams(lintcode)
 *    Given an array of strings, return all groups of strings that are anagrams.
 *    What is Anagram?
 *     Ans: Two strings are anagram if they can be the same after change the order of characters.
 *
 *    Example: 1) Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"].
 *             2) Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba", "cd", "dc"].
 */
 
 //Method: Use HashMap
 public List<String> anagrams(String[] strs) {
      // write your code here
      List<String> list = new ArrayList<String>();
      if(strs == null || strs.length == 0){
          return list;
      }

      Map<String,List<String>> map = new HashMap<String,List<String>>();
      for(int i = 0; i < strs.length; i++){
          char[] arr = strs[i].toCharArray();
          Arrays.sort(arr);
          String s = String.valueOf(arr);
          if(!map.containsKey(s)){
              List<String> l = new ArrayList<String>();
              map.put(s,l);
          }
          map.get(s).add(strs[i]);
      }

      for(Map.Entry<String, List<String>> entry : map.entrySet()) {
        if(entry.getValue().size() >= 2){
            list.addAll(entry.getValue());
        }
      }
      return list;
 }
