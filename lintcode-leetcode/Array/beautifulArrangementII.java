/** 992. Beautiful Arrangement II (lintcode)/ 526. Beautiful Arrangement II (leetcode)
 *    Given two integers n and k, you need to construct a list which contains n different positive integers 
 *  ranging from 1 to n and obeys the following requirement: Suppose this list is [a1, a2, a3, ... , an],     
 *  then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.
 *  If there are multiple answers, print any of them.
 *
 *    Note: The n and k are in the range 1 <= k < n <= 10^4.
 */
 
 /* My Method: Construction
  * Idea: A[0] = 1; 
  *       A[1] = A[0] + k;  A[2] = A[1] - (k-1); ...; A[k+1] = A[k] +/- 1;
  *       A[x] = x + 1 (x > k+1).
  */
  public int[] constructArray(int n, int k) {
      // Write your code here
      int[] res = new int[n];
      res[0] = 1;
      for(int i = 1; i < n; i++){
          if(k != 0){
              if(i % 2 == 0){
                  res[i] = res[i - 1] - k;
              }
              else{
                  res[i] = res[i - 1] + k;
              }
              k--;
          }
          else{
              res[i] = i + 1;
          }
      }
      return res;
  }
