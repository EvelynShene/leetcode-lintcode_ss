/** 384. Shuffle an Array(leetcode)
 *    Shuffle a set of numbers without duplicates.
 *
 *    Example: // Init an array with set 1, 2, and 3.
 *               int[] nums = {1,2,3};
 *               Solution solution = new Solution(nums);
 *
 *               // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 *               solution.shuffle();
 *
 *               // Resets the array back to its original configuration [1,2,3].
 *               solution.reset();
 *
 *               // Returns the random shuffling of array [1,2,3].
 *               solution.shuffle();
 */
 
 class Solution {
    
    List<Integer> list;
    public Solution(int[] nums) {
        list = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++){
            list.add(nums[i]);
        }
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        List<Integer> l = new ArrayList<>();
        l.addAll(list);
        int[] res = new int[list.size()];
        Random r = new Random();
        for(int i = 0; i < list.size(); i++){
            int random = r.nextInt(l.size());
            res[i] = l.get(random);
            l.remove(random);
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
 
 //Other Method - swap - Knuth shuffle
