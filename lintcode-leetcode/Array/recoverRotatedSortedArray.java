/** 39. Recover Rotated Sorted Array
 *   Given a rotated sorted array, recover it to sorted array in-place.
 *   What is rotated array?
 *     For example, the orginal array is [1,2,3,4], The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
 *
 *   Example:  [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
 */
 
 //My Method: Use reverse
 public void recoverRotatedSortedArray(List<Integer> nums) {
      // write your code here
      int k = 1;
      for(int i = 1; i < nums.size(); i++){
          if(nums.get(i) >= nums.get(i-1)){
              k++;
          }
          else{
              break;
          }
      }
      if(k < nums.size()){
          reverse(nums,0,k-1);
          reverse(nums,k,nums.size()-1);
          reverse(nums,0,nums.size()-1);
      }
  }

  public void reverse(List<Integer> nums, int start, int end){

      while(start < end){
          int temp = nums.get(start);
          nums.set(start,nums.get(end));
          nums.set(end,temp);
          start++;
          end--;
      }
  }
 
 //Method from jiuzhang(More concise)
 private void reverse(ArrayList<Integer> nums, int start, int end) {
    for (int i = start, j = end; i < j; i++, j--) {
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }
 }

  public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
      for (int index = 0; index < nums.size() - 1; index++) {
          if (nums.get(index) > nums.get(index + 1)) {
              reverse(nums, 0, index);
              reverse(nums, index + 1, nums.size() - 1);
              reverse(nums, 0, nums.size() - 1);
              return;
          }
      }
  }
