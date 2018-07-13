/** 779. Generalized Abbreviation(lintcode) / 320. Generalized Abbreviation(leetcode - locked)
 *    Write a function to generate the generalized abbreviations of a word.
 *
 *    Example: Given word = `"word"`, return the following list (order does not matter):
 *             ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", 
 *                  "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */
 
 //My Method: DFS
 public class Solution {
    /**
     * @param word: the given word
     * @return: the generalized abbreviations of a word
     */
    public List<String> generateAbbreviations(String word) {
        // Write your code here
        List<String> list = new ArrayList<String>();
        if(word == null || word.length() == 0){
            return list;
        }
        
        dfs(list, "", 0, false, word);
        return list;
    }
    
    public void dfs(List<String> list, String s, int index, boolean prev_num, String word){
        if(index == word.length()){
            list.add(s);
            return;
        }

        dfs(list, s + word.charAt(index), index + 1, false, word);
        if(!prev_num){ //数字不能相邻出现
            for(int i = 1; i <= word.length() - index; i++){
                dfs(list, s + ""+i, index + i, true, word);
            }
        }        
    }
}
