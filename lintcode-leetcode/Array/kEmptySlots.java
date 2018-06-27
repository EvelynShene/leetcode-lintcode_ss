/** 861. K Empty Slots(lintcode) / 683. K Empty Slots(leetcode-locked)
 *     There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. 
 *  In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
 *     Given an array flowers consists of number from 1 to N. Each number in the array represents the place 
 *  where the flower will open in that day.
 *     For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, 
 *  where i and x will be in the range from 1 to N.
 *     Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, 
 *  and also the number of flowers between them is k and these flowers are not blooming.
 *     If there isn't such day, output -1.
 *
 *     Note: The given array will be in the range [1, 20000].
 *
 *     Example: 1) Given flowers = [1,3,2], k = 1, return 2.
 *                 Explanation: In the second day, the first and the third flower have become blooming.
 *              2) Given flowers = [1,2,3], k = 1, return -1
 */
 
 /* My Method - O(N^2) Time complexity
  *   Idea: 1. 题目描述性问题：对于下标以0开始的数组，flowers[i] = x 应该表示一朵花会在第（i+1）天在位置 x 处绽放。
  *         2. 思路是遍历数组时，只有i下标之前的元素指向的位置的花开放了，而后面没有。
  *         而对于位置i处的花朵，与它相邻的可能有k束花未开的位置只有可能是i下标之前的元素中，大于flowers[i]的最小数和小于flowers[i]的最大数。
  *         所以，找到这两个值，与flowers[i]比较刚好等于k时，返回i+1，即是要求的那一天。
  */
 public int kEmptySlots(int[] flowers, int k) {
      // Write your code here
      if(flowers == null || flowers.length < k + 2){
          return -1;
      }
      for(int i = 1; i < flowers.length; i++){
          int min = Integer.MAX_VALUE;
          int max = Integer.MIN_VALUE;
          for(int j = 0; j < i; j++){
              if(flowers[j] > flowers[i]){
                  min = Math.min(min,flowers[j]);
              }
              else if(flowers[j] < flowers[i]){
                  max = Math.max(max,flowers[j]);
              }
          }
          if(min != Integer.MAX_VALUE && Math.abs(flowers[i] - min) - 1 == k){
              return (i + 1);
          }
          if(max != Integer.MIN_VALUE && Math.abs(flowers[i] - max) - 1 == k){
              return (i + 1);
          }
      }
      return -1;
  }
  
  /** Method 2: Use TreeSet - O(n) time and space complexity
   *  Idea: 与上述方法是一致的思路，只是利用TreeSet本身的数组结构（有序的集合），
   *        每次遍历时，遍历的数都排序好了，用函数higher()和lower()调用得到所需的最大最小值。
   */
  public int kEmptySlots(int[] flowers, int k) {
      // Write your code here
      if(flowers == null || flowers.length < k + 2){
          return -1;
      }
      TreeSet<Integer> f = new TreeSet<>();
      for(int i = 0; i < flowers.length; i++){
          f.add(flowers[i]);
          Integer min = f.lower(flowers[i]);
          Integer max = f.higher(flowers[i]);
          if(min != null && Math.abs(flowers[i] - min) - 1 == k){
              return (i + 1);
          }
          if(max != null && Math.abs(flowers[i] - max) - 1 == k){
              return (i + 1);
          }
      }
      return -1;
  } 
