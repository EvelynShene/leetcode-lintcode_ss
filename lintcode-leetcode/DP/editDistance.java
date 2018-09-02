/** 119. Edit Distance(lintcode) / 72. Edit Distance(leetcode) - Dynamic Programming Method
 *    Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *    You have the following 3 operations permitted on a word:
 *         1. Insert a character
 *         2. Delete a character
 *         3. Replace a character
 *
 *    Example: Given word1 = "mart" and word2 = "karma", return 3.
 */
 
 /* Method: Use DP Method
  *   用二维数组misDis[i][j]表示由字符串word1(0,i)转化为word2(0,j)需要的最少操作数。则对于每一个misDis[i][j]，有以下两种情况：
  *     1) word(i) == word(j):
  *           minDis[i][j] = minDis[i-1][j-1];
  *     2) word(i) != word(j): 此时有三种可能的操作
  *       a) 先把字符串word1(0,i-1)转化为word2(0,j-1)，然后把字符word1(i)替换成word2(j) -> minDis[i-1][j-1]+1
  *           eg: mar -> karm : 先 ma -> kar；再 r 替换成 m
  *       b) 先把字符串word1(0,i-1)转化为word2(0,j)，当多了字符word1(i)时，word1(0,i)删除这个字符即可得word2(0,j) -> minDis[i-1][j]+1
  *           eg: mar -> karm : 先 ma -> karm； 多来一个r删除即可（因为ma已经操作了minDis[i-1][j]步变成了karm）
  *       c) 先把字符串word1(0,i)转化为word2(0,j-1)，当多了字符word2(j)时，word1(0,i)多插入此字符即可得word2(0,j) -> minDis[i][j-1]+1
  *           eg: mar -> karm : 先 mar -> kar； 现在要变成karm，word1插入一个m就行（因为mar已经操作了minDis[i][j-1]步变成了kar）
  *     以上三种可能的操作，取最小操作数的那一个。
  */
  
  public int minDistance(String word1, String word2) {
      if(word1 == null || word2 == null){
          return 0;
      }
      if(word1.length() == 0 || word2.length() == 0){
          return Math.max(word1.length(), word2.length());
      }

      int[][] minDis = new int[word1.length()+1][word2.length()+1];
      for(int i = 0; i <= word1.length(); i++){
          minDis[i][0] = i;
      }
      for(int i = 0; i <= word2.length(); i++){
          minDis[0][i] = i;
      }
      for(int i = 1; i <= word1.length(); i++){
          for(int j = 1; j <= word2.length(); j++){
              if(word1.charAt(i-1) == word2.charAt(j-1)){
                  minDis[i][j] = minDis[i-1][j-1];
              }
              else{// word1.charAt(i-1) != word2.charAt(j-1)
                  minDis[i][j] = Math.min(Math.min(minDis[i-1][j-1],minDis[i-1][j]),minDis[i][j-1]) + 1;
              }
          }
      }
      return minDis[word1.length()][word2.length()];
  }
