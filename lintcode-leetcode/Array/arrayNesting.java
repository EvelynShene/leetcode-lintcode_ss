/** 989. Array Nesting(lintcode) /565. Array Nesting(leetcode)
 *      A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of set S, 
 *   where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.
 *      Suppose the first element in S starts with the selection of element A[i] of index = i, 
 *   the next element in S should be A[A[i]], and then A[A[A[i]]]… 
 *   By that analogy, we stop adding right before a duplicate element occurs in S.
 *
 *   Note:
 *       1. N is an integer within the range [1, 20,000].
 *       2. The elements of A are all distinct.
 *       3. Each element of A is an integer within the range [0, N-1].
 *
 *   Example: Input: A = [5,4,0,3,1,6,2]; Output: 4
 *      Explanation: A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 *               One of the longest S[K]:
 *                   S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 */
 
 //Method 1: Use HashSet - O(n) time and space complexity 
  public int arrayNesting(int[] nums) {
      // Write your code here
      if(nums == null || nums.length == 0){
          return 0;
      }
      Set<Integer> set = new HashSet<Integer>();
      int longest = 0;
      int count = 0;
      for(int i = 0; i < nums.length; i++){
          int next = i;
          while(!set.contains(nums[next])){
              set.add(nums[next]);
              count++;
              next = nums[next];
          }
          if(count > longest){
              longest = count;
          }
          if(longest > nums.length / 2){
              break;
          }
          count = 0;
      }
      return longest;
  }
  
  //Method 2: Use extra array - O(n) time and space complexity 
    public int arrayNesting(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        boolean[] visit = new boolean[nums.length];
        int longest = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(!visit[i]){
                int next = i;
                do{
                    count++;
                    visit[next] = true;
                    next = nums[next];
                }
                while(!visit[next]);
            }
            if(count > longest){
                longest = count;
            }
            if(longest > nums.length / 2){
                break;
            }
            count = 0;
        }
        return longest;
    }
    
 //Method 3: Without Using Extra Space, But Change the element which is visited to Integer.MAX_VALUE (code from leetcode)
  public int arrayNesting(int[] nums) {
      int res = 0;
      for (int i = 0; i < nums.length; i++) {
          if (nums[i] != Integer.MAX_VALUE) {
              int start = nums[i], count = 0;
              while (nums[start] != Integer.MAX_VALUE) {
                  int temp = start;
                  start = nums[start];
                  count++;
                  nums[temp] = Integer.MAX_VALUE;
              }
              res = Math.max(res, count);
          }
      }
      return res;
  }
 
