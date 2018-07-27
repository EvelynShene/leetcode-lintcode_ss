/** 400. Maximum Gap(lintcode) /164. Maximum Gap(leetcode)
 *     Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 *   Return 0 if the array contains less than 2 elements.
 *
 *     Note: 
 *      1) You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 *      2) Try to solve it in linear time/space.
 *  
 *     Example: Given [1, 9, 2, 5], the sorted form of it is [1, 2, 5, 9], the maximum gap is between 5 and 9 = 4.
 */
 
 //Method 1: sort Array - O(nlogn) time complexity
 public int maximumGap(int[] nums) {
    if(nums == null || nums.length < 2){
        return 0;
    }
    Arrays.sort(nums);
    int max = 0;
    for(int i = 1; i < nums.length; i++){
        max = Math.max(max, nums[i] - nums[i-1]);
    }
    return max;
 }
 //普通的排序算法的时间复杂度是O(nlogn), 而基数排序，桶排序的时间复杂度则是O(n),所以利用这些排序算法求此题，可以实现O(n)的时间复杂度。
 
 /* Method 2: 基数排序(radix sort) - https://blog.csdn.net/qq_35820702/article/details/52975231
  *   每一次循环，都是对当前base位的一次排序：
  *     1) 先用count[(nums[i] / base) % 10]++;记录当前位上相同数字的个数。
  *     2) 再循环更新count[i] = count[i] + count[i-1]; 用于记录这次排序后，base位数字小于等于i的数的总数。
  *     3) 从后往前遍历，更新新数组sort_num，取得的这个数字nums[i]所在的位置是小于等于i的数的总数-1.
  *     eg: nums[] = [278,110,63,890,675]
  *         1) count[] = [2,0,0,1,0,1,0,0,1,0];
  *         2) count[] = [2,2,2,3,3,4,4,4,5,5];
  *         3) 从后往前遍历nums, nums[4] = 675，base = 1位上数字是5，查找count[5] = 4，说明个位上数字小于等于5的数一共有4个。
  *     因为是从后往前遍历，675一定是4个数中排最后的那一个，所以新数组sort_num[4-1] = 675。然后将count[5]--(已经处理完一个数了)。
  */
 public int maximumGap(int[] nums) {
    if(nums == null || nums.length < 2){
        return 0;
    }
    int n = nums.length;
    int max_num = -1;
    for(int i = 0; i < n; i++){
        max_num = Math.max(max_num,nums[i]);
    }

    int base = 1;
    int[] sort_nums = new int[n];

    while(max_num / base > 0){
        int[] count = new int[10];

        for(int i = 0; i < n; i++){
            count[(nums[i] / base) % 10]++;
        }
        for(int i = 1; i < 10; i++){
            count[i] += count[i-1];
        }
        for(int i = n-1; i >= 0; i--){
            sort_nums[--count[(nums[i] / base) % 10]] = nums[i];
        }

        for(int i = 0; i < n; i++){
            nums[i] = sort_nums[i];
        }
        base *= 10;
    }

    int max = 0;
    for(int i = 1; i < n; i++){
        max = Math.max(max, nums[i] - nums[i-1]);
    }
    return max;
 }
 
 //Method 3: 桶排序
 
 
