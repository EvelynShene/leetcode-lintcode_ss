/** 641. Missing Ranges(lintcode) / 163. Missing Ranges(leetcode)
 *     Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], 
 *   return its missing ranges.
 *
 *     Example:  Given nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99
 *               return ["2", "4->49", "51->74", "76->99"].
 */
 
 //My Method - O(n)
  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
      // write your code here
      List<String> res = new ArrayList<String>();
      String s = ""; 
      if(nums == null || nums.length == 0){
          if(lower == upper){
              s = lower + "";
          }
          else{
              s = lower + "->" + upper;
          }
          res.add(s);
          return res;
      }
      if(nums[0] > lower){
          if(lower == nums[0] - 1)
              s = lower + "";
          else
              s = lower + "->" + (nums[0]-1);
          res.add(s);
      }
      for(int i = 1; i < nums.length; i++){
          while(i < nums.length && nums[i] == nums[i-1]){
              i++;
          }
          if(i < nums.length && nums[i] != nums[i-1] + 1){
              if(nums[i-1] + 1 == nums[i] - 1){
                  s = (nums[i-1]+1) + "";
              }
              else{
                  if(nums[i] > upper){
                      s = (nums[i-1]+1) + "->" + upper;
                  }
                  else{
                      s = (nums[i-1]+1) + "->" + (nums[i]-1);
                  }
              }
              res.add(s);
          }
      }
      if(nums[nums.length - 1] < upper){
          if(nums[nums.length - 1] == upper - 1 ){
              s = upper+"";
          }
          else{
              s = (nums[nums.length - 1]+1) + "->" + upper;
          }
          res.add(s);
      }
      return res;
  }
