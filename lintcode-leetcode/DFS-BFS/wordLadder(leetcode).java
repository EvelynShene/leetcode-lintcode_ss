/** 127. Word Ladder(leetcode)
 *     Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest 
 *  transformation sequence from beginWord to endWord, such that:
 *     Only one letter can be changed at a time.
 *     Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *    
 *     Note: 1) Return 0 if there is no such transformation sequence.
 *           2) All words have the same length.
 *           3) All words contain only lowercase alphabetic characters.
 *           4) You may assume no duplicates in the word list.
 *           5) You may assume beginWord and endWord are non-empty and are not the same.
 *
 *     Example: 1) Input:
 *                   beginWord = "hit",
 *                   endWord = "cog",
 *                   wordList = ["hot","dot","dog","lot","log","cog"]
 *                 Output: 5
 *                      Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *                   return its length 5.
 *
 *              2) Input:
 *                   beginWord = "hit"
 *                   endWord = "cog"
 *                   wordList = ["hot","dot","dog","lot","log"]
 *                 Output: 0
 *                    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
 
 //Method: BFS
 class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.size() == 0){
            return 0;
        }
        boolean exist = false;
        for(int i = 0; i < wordList.size(); i++){
            if(endWord.equals(wordList.get(i))){
                exist = true;
                break;
            }
        }
        if(!exist){
            return 0;
        }
        int len = 1;
        Queue<String> q = new LinkedList<String>();
        q.offer(beginWord);
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0){
                String str = q.poll();
                if(str.equals(endWord)){
                    return len;
                }
                for(int i = wordList.size() - 1; i >= 0; i--){
                    if(oneDiff(str,wordList.get(i))){
                        q.offer(wordList.get(i));
                        wordList.remove(i);
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
