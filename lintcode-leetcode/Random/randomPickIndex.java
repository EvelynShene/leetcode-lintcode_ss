/** 398. Random Pick Index(leetcode)
 *      Given an array of integers with possible duplicates, randomly output the index of a given target number. 
 *  You can assume that the given target number must exist in the array.
 *
 *      Note: The array size can be very large. Solution that uses too much extra space will not pass the judge.
 *
 *      Example: int[] nums = new int[] {1,2,3,3,3};
 *               Solution solution = new Solution(nums);
 *               // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 *               solution.pick(3);
 *               // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 *               solution.pick(1);
 */
 
 //My Method: Use HashMap
 class Solution {
    Map<Integer, List<Integer>> map;
    public Solution(int[] nums) {
        map = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], new ArrayList<Integer>());
            }
            map.get(nums[i]).add(i);
        }
    }
    
    public int pick(int target) {
        List<Integer> l = map.get(target);
        if(l.size() == 1){
            return l.get(0);
        }
        Random r = new Random();
        int i = r.nextInt(l.size());
        return l.get(i);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */

//Method 2: Use reservoir sampling - less space[https://www.cnblogs.com/strugglion/p/6424874.html]
class Solution {
    int[] nums;
    Random r;
    public Solution(int[] nums) {
        this.nums = nums;
        r = new Random();
    }
    
    public int pick(int target) {
        int count = 0;
        int res = -1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != target){
                continue;
            }
            count++;
            if(r.nextInt(count) == 0){
                res = i;
            }
        }
        return res;
    }
}
