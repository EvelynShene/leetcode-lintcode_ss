/** 152. Combinations(lintcode) / 77. Combinations (leetcode)
 *    Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 *    Note: You don't need to care the order of combinations, but you should make sure the numbers in a combination are sorted.
 *
 *    Example: Input: n = 4, k = 2
 *             Output:
 *             [
 *               [2,4],
 *               [3,4],
 *               [2,3],
 *               [1,2],
 *               [1,3],
 *               [1,4],
 *             ]
 */
 
 //Method: DFS
 class Solution {
    public List<List<Integer>> combine(int n, int k) {
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(n < k){
            return res;
        }
        List<Integer> list = new ArrayList<Integer>();
        if(n == k){
            for(int i = 1; i <= n; i++){
                list.add(i);
            }
            res.add(list);
            return res;
        }
        
        for(int i = 1; i <= n - k + 1; i++){
            list.add(i);
            dfs(1, i+1, res, list, n, k);
            list.remove(list.size()-1);
        }
        return res;
    }
    public void dfs(int level, int start, List<List<Integer>> res, List<Integer> list, int n, int k){
        if(level == k){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = start; i <= n && i <= n-k+1+level; i++){
            list.add(i);
            dfs(level+1, i+1, res, list, n, k);
            list.remove(list.size()-1);
        }     
    }
}
