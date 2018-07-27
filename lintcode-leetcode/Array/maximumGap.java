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
 
 /* Method 3: 桶排序 - 举例说明：nums[] = {1,8,7,9,5}.
  *   1) 先找到数组nums中的最大值max_val和最小值min_val。由最大最小值求桶bucket的大小，桶的大小是最小的最大差值。
  *    Q:如何求最小的最大差值呢？因为一共由n个数，如果这n个数排序后是等差数列，那么他们的公差值就等于(max - min)/(n-1)；
  * 如果不是等差数列排列，假设排序后(数组是n)，第i个数n[i]不满足等差数列且它离n[i-1]更近，那么它离n[i+1]更远，
  * 这时的最大距离max_gap > 原来等差数列的公差，所以说最小的最大差值是当数组成等差数列时的公差(max - min)/(n-1)。
  * 考虑到公差(max - min)/(n-1)可能是小数，取上整，因为要使得桶的个数是n-1个。
  *   2) 对于每一个桶i，记录桶内的最大值bucket[i].max 和桶内最小值bucket[i].min。这样一来，所求的最大距离max_gap为：
  *       max_gap = 当前有效桶i的bucket[i].min - 紧邻的前一个有效桶j的bucket[j].max。
  *    【有效桶是指数组中有元素分配到该桶中】
  *
  *   3) 对与数nums[i]属于那个桶，用公式(nums[i] - min_val)/bucket_size。其中：
  *     a) min_val会分配到第1号桶；max_val会分配到第n-1号桶。
  *     b) 对于其他的数，每个桶的包含关系是[i,i+bucket_size),[i+bucket_size,i+2*bucket_size) ...
  */
 public int maximumGap(int[] nums) {
     if(nums == null || nums.length < 2){
         return 0;
     }
     int n = nums.length;
     int max_val = nums[0];
     int min_val = nums[0];
     for(int i = 1; i < n; i++){
         max_val = Math.max(max_val, nums[i]);
         min_val = Math.min(min_val, nums[i]);
     }
     if(n-1 == 1){
         return max_val - min_val;
     }

     int bucket_size = (max_val - min_val) / (n - 1) + 1;
     int[] bucket = new int[n-1];
     int[] bucket_min = new int[n-1];
     int[] bucket_max = new int[n-1];
     Arrays.fill(bucket_min, Integer.MAX_VALUE);
     Arrays.fill(bucket_max, Integer.MIN_VALUE);

     for(int i = 0; i < n; i++){
         int belong = (nums[i] - min_val) / bucket_size;
         bucket[belong]++;
         bucket_min[belong] = Math.min(bucket_min[belong],nums[i]);
         bucket_max[belong] = Math.max(bucket_max[belong],nums[i]);
     }

     int max = 0;
     int i = 0;
     int j = 1;
     while(i < n-1 && j < n-1){
         if(bucket[i] != 0 && bucket[j] != 0){
             max = Math.max(max,bucket_min[j] - bucket_max[i]);
             i = j;
             j = i+1;
         }
         else if(bucket[i] == 0){
             i++;
             j++;
         }
         else if(bucket[j] == 0){
             j++;
         }
     }
     return max;
 }

