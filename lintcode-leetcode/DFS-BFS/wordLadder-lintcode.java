/** 120. Word Ladder(lintcode)
 *     Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from 
 *  start to end, such that:
 *        1) Only one letter can be changed at a time
 *        2) Each intermediate word must exist in the dictionary
 *
 *     Note: 1) Return 0 if there is no such transformation sequence.
 *           2) All words have the same length.
 *           3) All words contain only lowercase alphabetic characters.
 *
 *     Example: Given:
 *                  start = "hit"
 *                  end = "cog"
 *                  dict = ["hot","dot","dog","lot","log"]
 *             As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 *    
 *    【与leetcode-127题不同的地方在于此题允许start == end，而且是start通过dict内字符串逐步转化为end，所以dict里可以没有end】
 */
 
 //Method: BFS
 public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if(dict == null || dict.size() == 0){
            return 0;
        }
        if(start.equals(end)){
            return 1;
        }
        
        Queue<String> q = new LinkedList<String>();
        Set<String> visited = new HashSet<String>();
        
        dict.add(start);
        dict.add(end);
        
        q.offer(start);
        visited.add(start);
        int len = 1;
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0){
                String str = q.poll();
                if(str.equals(end)){
                    return len;
                }
                for(String s: dict){
                    if(!visited.contains(s) && oneDiff(s,str)){
                        q.offer(s);
                        visited.add(s);
                    }
                }
                size--;
            }
            len++;
        }
        return 0;
    }
    
    public boolean oneDiff(String s1, String s2){
        int diff = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                diff++;
            }
            if(diff > 1){
                return false;
            }
        }
        return diff == 1? true: false;
    }
}
