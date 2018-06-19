/** 58 4Sum (lintcode) / 18. 4Sum (leetcode)
 *    Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 *    Find all unique quadruplets in the array which gives the sum of target.
 *    Note:
 *      Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 *      The solution set must not contain duplicate quadruplets.
 *
 *    Example: Given array S = {1 0 -1 0 -2 2}, and target = 0. A solution set is:
 *              (-1, 0, 0, 1), (-2, -1, 1, 2), (-2, 0, 0, 2)
 */
 
 //Method 1: DFS - [AC in lintcode but Time Limit Exceeded in leetcode]
 public class Solution {
    /**
     * @param numbers: Give an array
     * @param target: An integer
     * @return: Find all unique quadruplets in the array which gives the sum of zero
     */
    public List<List<Integer>> fourSum(int[] numbers, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(numbers == null || numbers.length < 4){
            return res;
        }
        
        Arrays.sort(numbers);
        List<Integer> quadr = new ArrayList<Integer>();
        for(int i = 0; i < numbers.length; i++){
            quadr.add(numbers[i]);
            dfs(1, numbers, res, quadr, i+1, target-numbers[i]);
            quadr.remove(quadr.size()-1);
            while(i+1 < numbers.length && numbers[i] == numbers[i+1]){
                i++;
            }
        }
        
        return res;
    }
    
    public void dfs(int level, int[] numbers, List<List<Integer>> res, List<Integer> q, int index, int target){
        if(level > 3){
            return;
        }
        for(int i = index; i < numbers.length; i++){
            q.add(numbers[i]);
            if(level == 3 && target == numbers[i]){
                res.add(new ArrayList<Integer>(q));
            }
            else{
                dfs(level+1,numbers,res,q,i+1,target - numbers[i]);
            }
            q.remove(q.size()-1);
            while(i+1 < numbers.length && numbers[i] == numbers[i+1]){
                i++;
            }
        }
    }
}
//Method 2: 
 
