/** 269. Alien Dictionary(leetcode - locked) / 892. Alien Dictionary(lintcode)
 *      There is a new alien language which uses the latin alphabet. However, the order among letters are unknown 
 *  to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by
 *  the rules of this new language. Derive the order of letters in this language.
 *
 *      Note: 1) You may assume all letters are in lowercase.
 *            2) You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 *            3) If the order is invalid, return an empty string.
 *            4) There may be multiple valid order of letters, return the smallest in lexicographical order
 *
 *      Example:1) Given the following words in dictionary,
 *                         [
 *                           "wrt",
 *                           "wrf",
 *                           "er",
 *                           "ett",
 *                           "rftt"
 *                         ]
 *                The correct order is: "wertf"
 *              2) Given the following words in dictionary,
 *                         [
 *                           "z",
 *                           "x"
 *                         ]
 *                The correct order is: "zx".
 *
 *      理解题意：words字符串数组里的字符是按照某种新字母表顺序排序的，比如"wrt"排在"wrf"前面，它们有共同前缀"wr"，说明字母t在f
 *              前面；同理"wrf"在"er"前面，那么字母w就应该在e前面；最后得到下列4种新字母表规则：
 *                t->f
 *                w->e
 *                r->t
 *                e->r
 *              然后根据拓扑排序求新字母表的排列。
 *             【中间还用到PriorityQueue实现的最小堆来得到smallest one in lexicographical order】                       
 */
 
 //My Method:
 public class Solution {
    public String alienOrder(String[] words) {
        if(words == null || words.length == 0){
            return "";
        }
        int[] letter = new int[26];
        int len = words.length;
        List[] graph = new ArrayList[26];
        int c = 0;
        for(String word: words){
            for(int i = 0; i < word.length(); i++){
                if(letter[word.charAt(i) - 'a'] == 0){
                    letter[word.charAt(i) - 'a'] = 1;
                    c++;
                }
                if(c == 26){
                    break;
                }
            }
            if(c == 26){
                break;
            }
        }
        
        for(int i = 0; i < 26; i++){
            graph[i] = new ArrayList<Integer>();
        }
        int[] indegree = new int[26];
        for(int i = 0; i < len - 1; i++){
            int pos = findDiffPos(words[i], words[i + 1]);
            if(pos == -1){
                continue;
            }
            graph[words[i].charAt(pos) - 'a'].add(words[i + 1].charAt(pos) - 'a');
            indegree[words[i + 1].charAt(pos) - 'a']++;
        }
        
        PriorityQueue<Integer> min_heap = new PriorityQueue<Integer>();
        for(int i = 0; i < 26; i++){
            if(letter[i] == 1 && indegree[i] == 0){
                min_heap.offer(i);
            }
        }
        int count = 0;
        StringBuilder res = new StringBuilder();
        while(!min_heap.isEmpty()){
            int course = min_heap.poll();
            res.append(""+(char)('a' + course));
            count++;
            List<Integer> l = graph[course];
            for(int i = 0; i < l.size(); i++){
                indegree[l.get(i)]--;
                if(indegree[l.get(i)] == 0){
                    min_heap.offer(l.get(i));
                }
            }
        }
        if(count == c){
            return res.toString();
        }
        return "";
    }
    
    public int findDiffPos(String s1, String s2){
        if(s1.contains(s2) || s2.contains(s1)){
            return -1;
        }
        int len = Math.min(s1.length(), s2.length());
        for(int i = 0; i < len; i++){
            if(s1.charAt(i) != s2.charAt(i)){
                return i;
            }
        }
        return -1;
    }
}
