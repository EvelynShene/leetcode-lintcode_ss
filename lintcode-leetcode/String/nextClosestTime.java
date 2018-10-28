/** 862. Next Closest Time(lintcode) / 681. Next Closest Time(leetcode - locked)
 *    Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. 
 *  There is no limit on how many times a digit can be reused.
 *    You may assume the given input string is always valid. 
 *  For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 *
 *    Example: 1) Given time = "19:34", return "19:39".
 *            Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  
 *                       It is not 19:33, because this occurs 23 hours and 59 minutes later.
 *             2) Given time = "23:59", return "22:22".
 *            Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that
 *                       the returned time is next day's time since it is smaller than the input time numerically.
 */
 
 /* My Method(Idea) - 找规律 
  *     1） 对于时间的四个数字num[i],在四个数中找出每个数字的比它大得最小数字，存入leastmax[i]数组中。(leastmax[i] = 10是最大值)
  *     2） 时间的每一位都有一个最大取值{2,4,5,9}，存在max[]数组中。
  *       Note:如果最高位是1，那么第二位可以取1-9任意数！eg: Input: 13:55 ; Output: 15:11
  *     3） 从后往前遍历，找到第一个leastmax[i]在当前时间位置的可取范围内(最大值不计),把当前位置值换成它的最小大于值。
  *     4） 置换位之后的位置的值，全部有最小值min替代。
  */
 
public String nextClosestTime(String time) {
    int[] nums = new int[4];
    int[] max = {2, 3, 5, 9};
    int min = 10;
    nums[0] = time.charAt(0) - '0';
    nums[1] = time.charAt(1) - '0';
    nums[2] = time.charAt(3) - '0';
    nums[3] = time.charAt(4) - '0';

    int[] leastMax = {10, 10, 10, 10};
    for(int i = 0; i < 4; i++){
        min = Math.min(min, nums[i]);
        for(int j = 0; j < 4; j++){
            if(nums[j] > nums[i]){
                leastMax[i] = Math.min(nums[j], leastMax[i]);
            }
        }
    }

    int index = 3;
    while(index >= 0){
     if(index == 1){
      if(nums[0] != 2){
       if(leastMax[index] != 10){
        nums[index] = leastMax[index];
        index++;
        break;
       }
      }
     }
     if(leastMax[index] != 10 && leastMax[index] <= max[index]){
            nums[index] = leastMax[index];
            index++;
            break;
        }
        index--;
    }
    // System.out.println(index);
    if(index < 0){
        index++;
    }

    while(index < 4){
        nums[index] = min;
        index++;
    }
    String res = "" + nums[0] + nums[1] + ":" + nums[2] + nums[3];
    return res;
}
