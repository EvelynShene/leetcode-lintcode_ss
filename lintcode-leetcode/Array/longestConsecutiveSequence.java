/** 124. Longest Consecutive Sequence(lintcode) / 128. Longest Consecutive Sequence (leetcode)
 *     Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *  Note: Your algorithm should run in O(n) complexity.
 *
 *  Example: Given [100, 4, 200, 1, 3, 2],
 *           The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 */
 
 /* Method 1: Sort Array - O(nlogn)
  *  This method can be answered in the technique interview.
  */
 public int longestConsecutive(int[] num) {
      // write your code here
      if(num == null || num.length == 0){
          return 0;
      }
      Arrays.sort(num);
      int longest = 1;
      int temp = 1;
      for(int i = 0; i < num.length; i++){
          while(i + 1 < num.length && num[i] == num[i+1]){i++;} //Remove duplicate numbers
          if(i + 1 < num.length && num[i] == num[i+1]-1){
              temp++;
          }
          else if(i + 1 < num.length){
              longest = Math.max(longest,temp);
              temp = 1;
          }
      }
      longest = Math.max(longest,temp);
      return longest;
  }
  
  
 // Method 2: Use HashSet
  public int longestConsecutive(int[] num) {
      // write your code here
      Set<Integer> set = new HashSet<Integer>();
      for(int i = 0; i < num.length; i++){
          set.add(num[i]);
      }
      int longest = 0;

      for(int i = 0; i < num.length; i++){
          int left_num = num[i] - 1;
          int right_num = num[i] + 1;
          while(set.contains(left_num)){
              set.remove(left_num);
              left_num--;
          }
          while(set.contains(right_num)){
              set.remove(right_num);
              right_num++;
          }
          longest = Math.max(longest,right_num - left_num - 1);
      }
      return longest;
  }
