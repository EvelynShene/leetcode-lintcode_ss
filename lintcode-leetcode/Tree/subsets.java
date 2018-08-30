/** 78. Subsets(leetcode) / 17. Subsets(lintcode)
 *    Given a set of distinct integers, nums, return all possible subsets (the power set).
 *    
 *    Note: The solution set must not contain duplicate subsets.
 *
 *    Example: Input: nums = [1,2,3]
 *             Output:
 *             [
 *               [3],
 *               [1],
 *               [2],
 *               [1,2,3],
 *               [1,3],
 *               [2,3],
 *               [1,2],
 *               []
 *             ]
 */
 
 //Method: Backtracking
 class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        res.add(new ArrayList<>());
        
        findSubset(nums, 0, new ArrayList<>(), res);

        return res;
    }
    
    public void findSubset(int[] nums, int start, List<Integer> list, List<List<Integer>> res){
        if(start == nums.length){
            return;
        }
        for(int i = start; i < nums.length; i++){
            list.add(nums[i]);
            res.add(new ArrayList<>(list));
            findSubset(nums, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}
