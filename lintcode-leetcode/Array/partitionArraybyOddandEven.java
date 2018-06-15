/** 373. Partition Array by Odd and Even(lintcode)
 *   Partition an integers array into odd number first and even number second. Do it in-place.
 */
 
 public void partitionArray(int[] nums) {
    // write your code here
    int l = 0;
    int r = nums.length-1;

    while(l < r){
        if(nums[l] % 2 == 0){//even number
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            r--;
        }
        else{
            l++;
        }
    }
 }
