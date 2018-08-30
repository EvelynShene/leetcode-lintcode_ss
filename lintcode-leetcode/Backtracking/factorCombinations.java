/** 1308. Factor Combinations(lintcode) / 377. Factor Combinations(leetcode - locked)
 *      Numbers can be regarded as product of its factors. For example,
 *             8 = 2 x 2 x 2;
 *               = 2 x 4.
 *      Write a function that takes an integer n and return all possible combinations of its factors.
 *
 *      Note: 1) You may assume that n is always positive.
 *            2) Factors should be greater than 1 and less than n.
 *
 *      Example:1)input: 1
 *                output:
 *                  []
 *
 *              2)input: 37
 *                output:
 *                  []
 *
 *              3)input: 12
 *                output:
 *                  [
 *                    [2, 6],
 *                    [2, 2, 3],
 *                    [3, 4]
 *                  ]
 *
 *              4)input: 32
 *                output:
 *                  [
 *                    [2, 16],
 *                    [2, 2, 8],
 *                    [2, 2, 2, 4],
 *                    [2, 2, 2, 2, 2],
 *                    [2, 4, 4],
 *                    [4, 8]
 *                  ]
 */
 
 //My Method:
 public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if(n <= 1){
            return res;
        }
        factors(n, res, new ArrayList<>(), 1, 2);
        
        return res;
    }
    
    //每次从[start n/2]开始查找因子，这样可以防止重复集合，如[2,2,4,2] = [2,2,2,4]
    public void factors(int n, List<List<Integer>> res, List<Integer> list, int product, int start){
        if(product == n){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i <= n / 2; i++){
            if(i > n / product){
                break;
            }
            if(n % i == 0){
                list.add(i);
                factors(n, res, list, product * i, i);
                list.remove(list.size() - 1);
            }
        }
    }
}
