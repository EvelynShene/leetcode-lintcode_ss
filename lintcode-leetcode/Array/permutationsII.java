/** 47. Permutations II(leetcode)
 *    Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 *    Example: Input: [1,1,2]
 *             Output:
 *                [
 *                  [1,1,2],
 *                  [1,2,1],
 *                  [2,1,1]
 *                ]
 */
 
 //Method: Backtracking/DFS
 class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0){
            return list;
        }
        Arrays.sort(nums);

        boolean[] visited = new boolean[nums.length];
        List<Integer> p = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++){
            visited[i] = true;
            p.add(nums[i]);
            dfs(list, p, nums, visited);
            p.remove(p.size() - 1);
            visited[i] = false;
            
            while(i + 1 < nums.length && nums[i] == nums[i+1]){ //deal with duplicates
                i++;
            }
        }
        return list;
    }
    public void dfs(List<List<Integer>> list, List<Integer> p, int[] nums, boolean[] visited){
        if(p.size() == nums.length){
            list.add(new ArrayList<Integer>(p));
        }
        else{
            for(int i = 0; i < nums.length; i++){
                if(!visited[i]){
                    visited[i] = true;
                    p.add(nums[i]);
                    dfs(list, p, nums, visited);
                    p.remove(p.size() - 1);
                    visited[i] = false;
                    
                    while(i + 1 < nums.length && nums[i] == nums[i+1]){ //deal with duplicates
                        i++;
                    }
                } 
            }
        }
    }
}
