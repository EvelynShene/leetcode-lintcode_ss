/** 244. Shortest Word Distance II(leetcode - locked)
 *      This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words 
 *  and your method will be called repeatedly many times with different parameters. How would you optimize it?
 *      Design a class which receives a list of words in the constructor, and implements a method that takes two 
 *  words word1 and word2 and return the shortest distance between these two words in the list.
 *
 *      For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *          Given word1 = “coding”, word2 = “practice”, return 3.
 *          Given word1 = "makes", word2 = "coding", return 1.
 *
 *      Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
 
 /* Method: Use HashMap
  *    因为会多次调用这个函数，所以用HashMap存储每个单词和它出现的位置，然后调用最短距离函数时，分别测试所有的位置差
  */
  
 public class WordDistance {
    Map<String, List<Integer>> wds;
    
    public WordDistance(String[] words){
         wds = new HashMap<String, List<Integer>>();
         if(words == null) return;
         for(int i = 0; i < words.length; i++){ 
             if(!wds.containsKey(words[i])){	 
                 wds.put(words[i], new ArrayList<Integer>());
             }
             List<Integer> l = wds.get(words[i]);
             l.add(i);
             wds.put(words[i], l);
         }
    }
    
    //O(mn) time complexity - m == word1.length; n == word2.length
    public int shortest(String word1, String word2) {
        List<Integer> l1 = wds.get(word1);
        List<Integer> l2 = wds.get(word2);

        int distance = Integer.MAX_VALUE;
        for(int i = 0; i < l1.size(); i++){
            for(int j = 0; j < l2.size(); j++){
                distance = Math.min(distance, Math.abs(l1.get(i) - l2.get(j)));
            }
        }
        return distance;
    }
}

/* shortest函数改进：O(m+n) time complexity
 *    l1和l2都是升序排列，所以每次更新distance后，移动指向下标小的那一个。
 */
public int shortest(String word1, String word2) {
      List<Integer> l1 = wds.get(word1);
      List<Integer> l2 = wds.get(word2);
      int i = 0;
      int j = 0;
      int distance = Integer.MAX_VALUE;
      while(i < l1.size() && j < l2.size()){
          distance = Math.min(distance, Math.abs(l1.get(i) - l2.get(j)));
          if(l1.get(i) < l2.get(j)){
              i++;
          }
          else{
              j++;
          }
      }
      return distance;
 }
