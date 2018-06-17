/** 394. Coins in a Line(lintcode)
 *  There are n coins in a line. Two players take turns to take one or two coins from right side until there are no more coins left. The player who take the last coin wins.
 *  Could you please decide the first play will win or lose?
 */
 
 /** Idea: 
  *   如果N个球，A和B分别拿球，每次最多拿K个，最少拿一个，A先拿，要确保A拿到最后一个球，那么，甲第一次就要拿（N%（K+1））个，
  *   后来每次确保与另一方拿的球的个数和为（K+1）个。 
  *   而在本题中，A第一次要拿N%3 = 0, 1, 2个，只有当N%3 == 0时，无论A拿1个或2个，结果都是A输B赢。
  *   而当N%3==1,2时，无论A第一次拿1个或2个都是A赢B输。
  */
 
  public boolean firstWillWin(int n) {
      // write your code here
      if(n % 3 == 0){
          return false;
      }
      return true;
  }
  
