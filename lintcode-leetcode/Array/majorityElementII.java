/** 47. Majority Element II(lintcode) / 229. Majority Element II(leetcode)
 *    Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 *    Note: The algorithm should run in linear time and in O(1) space.
 *
 *    Example 1: Input: [3,2,3] ; Output: [3]
 *    Example 2: Input: [1,1,1,3,3,2,2,2] ; Output: [1,2]
 */
 
 /* Method: Boyer-Moore Algorithm
  *     数组中至多可能会有2个出现次数超过 ⌊ n/3 ⌋ 的众数, 记res1, res2为候选众数； c1, c2为它们对应的出现次数
  *   遍历数组，记当前数字为num[i]: （注意if-else的顺序）
  *     1) 若num[i]与res1或res2相同，则将其对应的出现次数加1 (先判断相等，可以避免res1 == res2)
  *     2) 否则，若c1或c2为0，则将其置为1，对应的候选众数置为num[i]
  *     3) 否则，将c1与c2分别减1
  *   最后，还需要再检查候选众数在数组中出现的次数是否满足数目大于⌊ n/3 ⌋，若满足要求，加入list中。
  *
  *   理解：
  *     2)-3)步找候选众数的原理是“三三抵消”，众数res1, res2和非众数构成整个数组，三个一组[res1,res2,非res1&&res2]，去掉所有的
  *   三元组后，剩下的非三元组中一定包含众数。
  *     但是也可能包含非众数，所以还需要遍历检查一遍。例如：[1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 4, 4] 三三抵消后剩下 [1, 4，4]。 
  *   4数量占优，但结果应该是1，所以三三抵消后，再loop一遍找1和4谁数量超过了len(nums)/3
  */
 public List<Integer> majorityElement(int[] nums) {
    List<Integer> list = new ArrayList<Integer>();
    int n = nums.length;
    if(n == 0)
        return list;

    int c1 = 0;
    int c2 = 0;
    int res1 = 0;
    int res2 = 0;
    for(int i = 0; i < n; i++){
        if(res1 == nums[i]){
            c1++;
        }
        else if(res2 == nums[i]){
            c2++;
        }
        else if(c1 == 0){
            res1 = nums[i];
            c1 = 1;
        }
        else if(c2 == 0){
            res2 = nums[i];
            c2 = 1;
        }
        else{
            c1--;
            c2--;
        }
    }
    c1 = 0;
    c2 = 0;
    for(int i = 0; i < n; i++){
        if(nums[i] == res1) c1++;
        else if(nums[i] == res2) c2++;
    }
    if(c1 > n / 3)
        list.add(res1);
    if(c2 > n / 3)
        list.add(res2);
    return list;
 }
