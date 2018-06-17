/**  153. Combination Sum II (lintcode)/40. Combination Sum II(leetcode)
 *    Given a collection of candidate numbers (C) and a target number (T), 
 *        find all unique combinations in C where the candidate numbers sums to T.
 *    Each number in C may only be used once in the combination.
 *
 *    Note:
 *      - All numbers (including target) will be positive integers.
 *      - The solution set must not contain duplicate combinations.
 */
 
 // Idea: Use DFS to find unique paths, but remove duplicate numbers in the same level
 public class Solution {
    /**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> solution = new ArrayList<Integer>();
        Arrays.sort(num);
        
        dfs(0,res,solution,target,num);
        
        return res;
    }
    
    public void dfs(int index, List<List<Integer>> res, List<Integer> solution, int target, int[] num){
        if(target == 0){
            res.add(new ArrayList<Integer>(solution));
        }
        else if(target > 0){
            for(int i = index; i < num.length; i++){
                solution.add(num[i]);
                dfs(i+1,res,solution, target-num[i],num);
                solution.remove(solution.size()-1);
                while(i < num.length-1 && num[i] == num[i+1]){//Remove duplicate numbers in the same level
                    i++;
                }
            }
        }
    }
}
 
