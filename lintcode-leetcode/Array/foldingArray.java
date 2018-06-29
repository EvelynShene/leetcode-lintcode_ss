/** 703. Folding Array(lintcode)
 *      Given an array nums of length n and an array req of length k , you need to fold the array as required, 
 *   and output the result of the fold.
 *
 *      Note: n is power of two.
 *            k is index.(e.g. n = 2^3 = 8，k = 3)
 *
 *      1.If req[i] = 0 means you should fold from left to right. for example: 
 *             1 2 3 4 5 6 7 8  ==>   4 3 2 1
 *                                    5 6 7 8
 *      2.If req[i] = 1 means you should fold from right to left. for example:
 *             1 2 3 4 5 6 7 8  ==>   8 7 6 5
 *                                    1 2 3 4
 *
 *      More examples:
 *                  fold from left to right
 *                  4 3 2 1  ==>  6 5
 *                  5 6 7 8       3 4
 *                                2 1
 *                                7 8
 *                  fold from right to left
 *                  6 5  ==>   8
 *                  3 4        1
 *                  2 1        4
 *                  7 8        5
 *                             6
 *                             3
 *                             2
 *                             7 
 *
 *    Example: Given array nums = [1, 2, 3, 4, 5, 6, 7, 8] and array req = [0, 0, 1]
 *             change array in place to be [8, 1, 4, 5, 6, 3, 2, 7]
 */
 
 /* Method: 
  *    找变换规律：先以确定当前折叠后会产生多少块（即分割区域）。（如第一次折叠会产生2块，再折叠2块变成4块区域）
  *             1）当req = 0时，从左往右折叠，可以看作是奇数块区域组合成左半块，偶数块区域组合成右半块。例如：例子第二步时，有4块
  *                       4 3 | 2 1 | 5 6 | 7 8 
  *                 奇数块形成左半部分：4 3 5 6； 偶数块形成右半部分：2 1 7 8；再最左半块reverse，即可得到折叠后的数组：     
  *                         6 5 3 4 2 1 7 8
  *             2）当req = 1时，从右往左折叠，可以看作是偶数块区域组合成左半块，奇数块区域组合成右半块（类似上面的步骤）。
  *                再最左半块reverse，即可得到折叠后的数组。
  *            
  */
 public class Solution {
    /**
     * @param nums: the original array
     * @param req: the direction each time
     * @return: the final folded array 
     */
    public int[] folding(int[] nums, int[] req) {
        // write your code here
        if(nums == null || nums.length == 0 || req == null || req.length == 0){
            return nums;
        }
        int block = 1;
        for(int i = 0; i < req.length; i++){
            block *= 2;
            if(req[i] == 0){
                LtoR(nums,block);
            }
            else{
                RtoL(nums,block);
            }
        }
        return nums;
    }
    
    public void LtoR(int[] nums, int block){
        int block_len = nums.length / block;
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
        
        for(int i = 0; i < block; i++){
            for(int j = 0; j < block_len; j++){
                if(i % 2 == 0){//left
                    left.add(nums[(i*block_len) + j]);
                }
                else{
                    right.add(nums[(i*block_len) + j]);
                }
            }
        }
        Collections.reverse(left);
        int index = 0;
        for(int i = 0; i <left.size(); i++){
            nums[index] = left.get(i);
            index++;
        }
        for(int i = 0; i <right.size(); i++){
            nums[index] = right.get(i);
            index++;
        }
    }
    
    public void RtoL(int[] nums, int block){
        int block_len = nums.length / block;
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
        
        for(int i = 0; i < block; i++){
            for(int j = 0; j < block_len; j++){
                if(i % 2 != 0){//left
                    left.add(nums[(i*block_len) + j]);
                }
                else{
                    right.add(nums[(i*block_len) + j]);
                }
            }
        }
        Collections.reverse(left);
        int index = 0;
        for(int i = 0; i <left.size(); i++){
            nums[index] = left.get(i);
            index++;
        }
        for(int i = 0; i <right.size(); i++){
            nums[index] = right.get(i);
            index++;
        }
    }
    
}
