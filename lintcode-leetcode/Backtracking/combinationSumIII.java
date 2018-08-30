/** 216. Combination Sum III(leetcode)
 *     Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 
 *  9 can be used and each combination should be a unique set of numbers.
 *
 *     Note: 1) All numbers will be positive integers.
 *           2) The solution set must not contain duplicate combinations.
 *
 *     Example: 1) Input: k = 3, n = 7
 *                 Output: [[1,2,4]]
 *              2) Input: k = 3, n = 9
 *                 Output: [[1,2,6], [1,3,5], [2,3,4]]   
 */
 
 //Method: Backtracking
 class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(k == 0){
            return res;
        }
        
        findCombine(new ArrayList<Integer>(), 1, k, n, res);
        return res;
    }
    
    public void findCombine(List<Integer> list, int start, int k, int sum, List<List<Integer>> res){
        if(list.size() == k){
            if(sum == 0){
                res.add(new ArrayList<>(list));
            }
            return;
        }
        for(int i = start; i < 10; i++){
            if(sum < i){
                break;
            }
            list.add(i);
            findCombine(list, i + 1, k, sum - i, res);
            list.remove(list.size() - 1);
        }
    }
}
