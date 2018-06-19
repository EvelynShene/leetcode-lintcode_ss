/** 692.Sliding Window Unique Elements Sum(lintcode)
 *    Given an array and a window size that is sliding along the array, 
 *    find the sum of the count of unique elements in each window.
 * 
 *    Example :Given a array nums = [1, 2, 1, 3, 3] and k = 3  -> Return 5
 *      First window [1, 2, 1], only 2 is unique, count is 1.
 *      Second window [2, 1, 3], all elements unique, count is 3.
 *      Third window [1, 3, 3], only 1 is unique, count is 1.
 *      sum of count = 1 + 3 + 1 = 5
 */
 
 //My Method: T = O(nk); S = O(k)
 public class Solution {
    /**
     * @param nums: the given array
     * @param k: the window size
     * @return: the sum of the count of unique elements in each window
     */
    public int slidingWindowUniqueElementsSum(int[] nums, int k) {
        // write your code here
        int n = 0;
        if(k >= nums.length){
            n = findUnique(nums,0,nums.length);
        }
        else{
            for(int i = 0; i <= nums.length - k; i++){
                n += findUnique(nums,i,k);
            }
        }
        return n;
    }
    public int findUnique(int[] nums, int start, int k){
        // int k = end-start+1;
        int[] t = new int[k];
        for(int i = start, j = 0; i < k+start; i++, j++){
            t[j] = nums[i];
        }
        Arrays.sort(t);
        int unique = 0;
        for(int i = 0; i < k; i++){
            if(i == 0 && t[i] != t[i+1]){
                unique++;
            }
            else if(i == k-1 && t[i-1] != t[i]){
                unique++;
            }
            else if(i > 0 && i < k-1 && t[i] != t[i+1] && t[i] != t[i-1]){
                unique++;
            }
        }
        if(unique == k-1){
            unique = k;
        }
        return unique;
        
    }
}

//Method 2: O(N) time, O(k) space
public int slidingWindowUniqueElementsSum(int[] nums, int k) {
    // write your code here
    Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    int sum = 0;
    int count = 0;
    for(int i = 0; i < nums.length; i++){
        if(!map.containsKey(nums[i])){
            map.put(nums[i],1);
            count++;
        }
        else{
            map.put(nums[i],map.get(nums[i])+1);
            // A unique elem is not unique any longer: when the third or more duplicate numbers add, no need to do "count--"
            if (map.get(nums[i]) == 2) { 
                count--;
            }
        }
        if(i >= k-1){
            sum += count;
            map.put(nums[i-k+1],map.get(nums[i-k+1])-1);
            if(map.get(nums[i-k+1]) == 0){ //Remove a unique number
                map.remove(nums[i-k+1]);
                count--;
            }
            else if(map.get(nums[i-k+1]) == 1){// A duplicate number becomes unique in a new window slide
                count++;
            }
        }
    }

    if(k > nums.length){
        return count;
    }
    return sum;
}
