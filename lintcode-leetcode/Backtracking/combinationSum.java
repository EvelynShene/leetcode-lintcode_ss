/** 135. Combination Sum(lintcode) / 39. Combination Sum(leetcode)
 *    Given a set of candidate numbers (C) and a target number (T), 
 *    find all unique combinations in C where the candidate numbers sums to T.
 *    The same repeated number may be chosen from C unlimited number of times.
 *
 *    Note: - All numbers (including target) will be positive integers.
 *          - The solution set must not contain duplicate combinations.
 *    
 *    Example: Given candidate set [2,3,6,7] and target 7, a solution set is:
 *                        [7]
 *                        [2, 2, 3]
 */
 
 //Method: Backtracking - First sort and then use DFS
 public class Solution {
    /**
     * @param candidates: A list of integers
     * @param target: An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length == 0){
            return res;
        }
        Arrays.sort(candidates);
        
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < candidates.length; i++){
            if(candidates[i] > target){
                break;
            }
            list.add(candidates[i]);
            if(candidates[i] == target){
                res.add(new ArrayList<Integer>(list));
            }
            else{
                dfs(i,res,list,candidates,target - candidates[i]);
            }
            list.remove(list.size()-1);
        }
        return res;
    }
    
    public void dfs(int index, List<List<Integer>> res, List<Integer> list, int[] candidates, int target){
        if(target < 0){
            return;
        }
        for(int i = index; i < candidates.length; i++){
            if(target == candidates[i]){
                list.add(candidates[i]);
                res.add(new ArrayList<Integer>(list));
                list.remove(list.size()-1);
            }
            else{
                list.add(candidates[i]);
                dfs(i,res,list,candidates,target - candidates[i]);
                list.remove(list.size()-1);
            }
        }
    }
}

//Code from Leetcode Discuss, more concise
public class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{ 
            for(int i = start; i < nums.length; i++){
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }
} 
