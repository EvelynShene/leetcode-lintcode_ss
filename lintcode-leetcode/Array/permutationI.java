/** 46. Permutations(leetcode)
 *    Given a collection of distinct integers, return all possible permutations.
 *
 *    Example: Input: [1,2,3]
 *             Output:
 *                [
 *                  [1,2,3],
 *                  [1,3,2],
 *                  [2,1,3],
 *                  [2,3,1],
 *                  [3,1,2],
 *                  [3,2,1]
 *                ]
 */
 
 //Method 1: 方法同NextPermutationII
 /** 分析： [6 5 4 8 7 5 1]
  *   从后往前，找到第一个排列递增的位置，如[4,8]；
  *   从8开始找大于4的最小数，如5；将[4]、[5]位置交换（[6 5 5 8 7 4 1]）
  *   再将8开始之后的数组反转排列，即可得到“下一个”排列
  */
 class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0){
            return list;
        }
        Arrays.sort(nums);
        List<Integer> p = new ArrayList<Integer>();
        for(int i: nums){
            p.add(i);
        }
        list.add(new ArrayList<Integer>(p));
        p.clear();
        
        int index = nums.length - 1;
        int pos = 0;
        
        while(index > 0){
            while(index - 1 >= 0 && nums[index] < nums[index - 1]){
                index--;
            }
            if(index - 1 >= 0){
                pos = index;
                for(int i = nums.length - 1; i >= index; i--){
                    if(nums[i] > nums[index - 1]){
                        pos = i;
                        break;
                    }
                }
                int temp = nums[pos];
                nums[pos] = nums[index-1];
                nums[index - 1] = temp;
                reverse(nums, index);
                for(int x: nums){
                    p.add(x);
                }
                list.add(new ArrayList<Integer>(p));
                p.clear();
                index = nums.length - 1;
            }
        }
        return list;
    }
    
    public void reverse(int[] nums, int start){
        int end = nums.length - 1;
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

//Method 2: Backtracking = DFS with one variable assigned at each level [code from leetcode]
public List<List<Integer>> permute(int[] nums) {
   List<List<Integer>> list = new ArrayList<>();
   // Arrays.sort(nums); // not necessary
   backtrack(list, new ArrayList<>(), nums);
   return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
   if(tempList.size() == nums.length){
      list.add(new ArrayList<>(tempList));
   } else{
      for(int i = 0; i < nums.length; i++){ 
         if(tempList.contains(nums[i])) continue; // element already exists, skip, no duplicate number
         tempList.add(nums[i]);
         backtrack(list, tempList, nums);
         tempList.remove(tempList.size() - 1);
      }
   }
} 
