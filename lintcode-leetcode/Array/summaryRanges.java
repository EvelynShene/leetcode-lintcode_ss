/** 1315. Summary Ranges (lintcode)/ 228. Summary Ranges(leetcode)
 *    Given a sorted integer array without duplicates, return the summary of its ranges.
 *
 *    Example: Input:  [0,1,2,4,5,7] ; Output: ["0->2","4->5","7"]
 *      Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 */
 
  public List<String> summaryRanges(int[] nums) {
      // Write your code here
      List<String> str = new ArrayList<String>();
      if(nums == null || nums.length == 0){
          return str;
      }
      String s = "";
      int start = 0;
      int i = 0;
      while(i < nums.length){
          if(start == i){
              s = nums[i] + "";
          }
          while(i + 1 < nums.length && (nums[i] == nums[i+1] - 1)){
              i++;
          }
          if(start != i){
              s = s + "->" + nums[i];
          }
          str.add(s);
          start = i + 1;
          i++;
      }
      return str;
  }
