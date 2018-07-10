/** 772. Group Anagrams(lintcode)/ 49. Group Anagrams(leetcode) (Same as Problem "Anagrams")
 *    Given an array of strings, group anagrams together.
 *
 *    Note: - All inputs will be in lowercase.
 *          - The order of your output does not matter.
 *
 *    Example: Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 *             Output:
 *                [
 *                  ["ate","eat","tea"],
 *                  ["nat","tan"],
 *                  ["bat"]
 *                ]
 */
 
 class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new ArrayList<List<String>>();
        if(strs == null || strs.length == 0){
            return list;
        }
        
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(int i = 0; i < strs.length; i++){
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String s = String.valueOf(arr);
            if(!map.containsKey(s)){
                List<String> l = new ArrayList<String>();
                map.put(s, l);
            }
            map.get(s).add(strs[i]);
        }
        
        for(Map.Entry<String,List<String>> entry: map.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }
}
