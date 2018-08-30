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
        if(nums == null || nums.length == 0){
            res.add(new ArrayList<>());
            return res;
        }
        //Remove duplicates
        Set<Integer> set = new HashSet<Integer>();
        List<Integer> unique_num = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(!set.contains(nums[i])){
                set.add(nums[i]);
                unique_num.add(nums[i]);
            }
        }
        Collections.sort(unique_num);
        
        List<Integer> list = new ArrayList<Integer>();
        res.add(new ArrayList<>(list));
        findSubset(unique_num, 0, list, res);

        return res;
    }
    
    public void findSubset(List<Integer> nums, int start, List<Integer> list, List<List<Integer>> res){
        if(start == nums.size()){
            return;
        }
        for(int i = start; i < nums.size(); i++){
            list.add(nums.get(i));
            res.add(new ArrayList<>(list));
            findSubset(nums, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}
