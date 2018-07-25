/** 243. Shortest Word Distance(leetcode - locked)
 *    Given a list of words and two words word1 and word2, return the shortest distance between these two words 
 *  in the list.
 *
 *    Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 *
 *    For example: Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *                   Given word1 = “coding”, word2 = “practice”, return 3.
 *                   Given word1 = "makes", word2 = "coding", return 1.
 */
 
 //Method 1: O(nm) time complexity (n is the length of word1 and m is the length of word2)
 public static int shortestWordDistance(String[] words, String word1, String word2){
      List<Integer> w1 = new ArrayList<Integer>();
      List<Integer> w2 = new ArrayList<Integer>();
      for(int i = 0; i < words.length; i++){
          if(words[i].equals(word1)){
            w1.add(i);
          }
          else if(words[i].equals(word2)){
            w2.add(i);
          }
      }
      int distance = Integer.MAX_VALUE;
      for(int i = 0; i < w1.size(); i++){
          for(int j = 0; j < w2.size(); j++){
              distance = Math.min(distance,Math.abs(w1.get(i)-w2.get(j)));
              if(distance == 1){
                return 1;
              }
          }
      }
      return distance;
 }
 
 /* Method 2: 用两个指针记录目前找到的word1和word2的最新位置，用它们的距离更新word1和word2的最小distance。
  *     因为是顺序遍历数组，所以最新的两个指针的差值才有可能更新最小distance（它们指向相邻的word1和word2）。
  */
 public static int shortestWordDistance(String[] words, String word1, String word2){
      int distance = Integer.MAX_VALUE;
      int index1 = -1;
      int index2 = -1;
      for(int i = 0; i < words.length; i++){
          if(words[i].equals(word1)){
              index1 = i;
              if(index2 != -1){
                  distance = Math.min(distance, index1 - index2);
              }
          }
          else if(words[i].equals(word2)){
              index2 = i;
              if(index1 != -1){
                  distance = Math.min(distance, index2 - index1);
              }
          }
      }
      return distance;
 }
