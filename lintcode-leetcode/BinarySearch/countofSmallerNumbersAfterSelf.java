/** 315. Count of Smaller Numbers After Self(leetcode) / 1297. Count of Smaller Numbers After Self(lintcode)
 *      You are given an integer array nums and you have to return a new counts array. The counts array has the 
 *    property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 *      Example: 1) Input: [5,2,6,1]
 *                  Output: [2,1,1,0] 
 *                      Explanation:
 *                            To the right of 5 there are 2 smaller elements (2 and 1).
 *                            To the right of 2 there is only 1 smaller element (1).
 *                            To the right of 6 there is 1 smaller element (1).
 *                            To the right of 1 there is 0 smaller element.
 */ 
 
 /* Method: O(nlogn) time and O(n) space complexity
  *         Use binary search to insert element nums[i] (from right to left <=> n - 1 -> 0) into new array, 
  *     and return the insert position which indicates the number of smaller elements to the right of it.
  */
 class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if(nums == null || nums.length == 0){
            return res;
        }
        int n = nums.length;
        List<Integer> arr = new ArrayList<Integer>();
        for(int i = n - 1; i >= 0; i--){
            int c = insertNum(arr, nums[i]);
            res.add(c);
        }
        Collections.reverse(res);
        return res;
    }
    
    public int insertNum(List<Integer> arr, int target){ // return the insert position
        if(arr.size() == 0){
            arr.add(target);
            return 0;
        }
        
        int left = 0; 
        int right = arr.size() - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(arr.get(mid) < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        arr.add(left, target);
        return left;
    }
}
