/** 90. Subsets II(leetcode) / 18. Subsets II(lintcode)
 *      Given a collection of integers that might contain duplicates, nums, return all possible subsets 
 *  (the power set).
 *
 *      Note: The solution set must not contain duplicate subsets.
 *
 *      Example: Input: [1,2,2]
 *               Output:
 *                 [
 *                   [2],
 *                   [1],
 *                   [1,2,2],
 *                   [2,2],
 *                   [1,2],
 *                   []
 *                 ]
 */
 
 //Method:
 class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        res.add(new ArrayList<Integer>());
        
        findSubset(nums, 0, new ArrayList<Integer>(), res);
        
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
            while(i + 1 < nums.length && nums[i] == nums[i + 1]){
                i++;
            }
            list.remove(list.size() - 1);
        }
    }
}
