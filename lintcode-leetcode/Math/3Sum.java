/** 57. 3Sum (lintcode)/ 15. 3Sum(leetcode)
 *    Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 *    Find all unique triplets in the array which gives the sum of zero.
 *
 *    Note: The solution set must not contain duplicate triplets.
 */

//Method 1: Use DFS, same as Combination Sum II [Accepted in lintcode, Time Limit Exceeded in leetcode]- O(2^n)
public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] numbers) {
        // write your code here
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> s = new ArrayList<Integer>();
        Arrays.sort(numbers);
        if(numbers == null || numbers.length == 0 || numbers[0] > 0){
            return res;
        }
        
        dfs(0,0,0,res,s,numbers);
        
        return res;
    }
    
    public void dfs(int level, int index, int target, List<List<Integer>> res, List<Integer> s, int[] numbers){
        if(level == 3 && target == 0 ){
            res.add(new ArrayList<Integer>(s));
        }
        else if(level < 3){
            for(int i = index; i < numbers.length; i++){
                s.add(numbers[i]);
                dfs(level+1,i+1,target-numbers[i],res,s,numbers);
                s.remove(s.size()-1);
                while(i < numbers.length-1 && numbers[i] == numbers[i+1]){
                    i++;
                }
            }
        }
    }
}

//Method 2: Use two pointers - O(n^2)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<List<Integer>>(); 
        Arrays.sort(nums);
        if(nums == null || nums.length == 0 || nums[0] > 0){
            return res;
        }
        
        int l = 0;
        int r = 0;
        for(int i = 0; i < nums.length; i++){ 
            int target = -nums[i]; //fix one number in triplet
            List<Integer> triplet = new ArrayList<Integer>();
            triplet.add(nums[i]);
            l = i+1;
            r = nums.length-1;
            while(l < r && r < nums.length && l < nums.length){ //find two numbers such that their sum is target/-triplet[0]
                if(nums[l] + nums[r] == target){ 
                    triplet.add(nums[l]);
                    triplet.add(nums[r]);
                    res.add(new ArrayList<Integer>(triplet));
                    triplet.remove(triplet.size()-1);
                    triplet.remove(triplet.size()-1);
                    l++;
                    r--;
                    while(l < r && nums[l] == nums[l-1]){//Avoid duplicates
                        l++;
                    }
                    while(l < r && nums[r] == nums[r+1]){//Avoid duplicates
                        r--;
                    }
                }
                else if(nums[l] + nums[r] < target){ // need a larger number (nums[l+1] > nums[l])
                    l++;
                }
                else{ // need a smaller number (nums[r-1] < nums[r]
                    r--;
                }
            }
            while(i+1 < nums.length && nums[i] == nums[i+1]){ //Remove duplicate numbers in the same level
                i++;
            }
        }
             
        return res;
    }
    
}

