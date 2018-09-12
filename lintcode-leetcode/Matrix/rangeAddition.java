/** 903. Range Addition(lintcode) / 370. Range Addition(leetcode - locked)
 *      Assume you have an array of length n initialized with all 0's and are given k update operations.
 *      Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of 
 *  subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
 *      Return the modified array after all k operations were executed.
 *
 *      Example: Given: length = 5,
 *                      updates = 
 *                          [
 *                            [1,  3,  2],
 *                            [2,  4,  3],
 *                            [0,  2, -2]
 *                          ]
 *                      return [-2, 0, 3, 5, 3]
 *               Explanation:
 *                      Initial state:
 *                      [ 0, 0, 0, 0, 0 ]
 *                      After applying operation [1, 3, 2]:
 *                      [ 0, 2, 2, 2, 0 ]
 *                      After applying operation [2, 4, 3]:
 *                      [ 0, 2, 5, 5, 3 ]
 *                      After applying operation [0, 2, -2]:
 *                      [-2, 0, 3, 5, 3 ]
 */
 
 //Method 1: Brute Force - O(nk) time complexity (n = updates.length; k = length)
 public int[] getModifiedArray(int length, int[][] updates) {
    int[] res = new int[length];
    if(updates == null || updates.length == 0){
        return res;
    }
    int n = updates.length;

    for(int i = 0; i < n; i++){
        for(int j = updates[i][0]; j <= updates[i][1]; j++){
            res[j] += updates[i][2];
        }
    }
    return res;
 }
 
 /* Method 2: O(n + k) time complexity
  *     思路：每次遇到一个 update，把 arr[start] 加上 value，arr[end + 1] 减去 value；最后遍历一遍，每个元素是自身与前一个元素的和。
  *       比如 [1, 3, 2]: 我们就把原数组变成 [0, 2, 0, 0, -2]。这样最后构建数组的时候，即为：
  *               newArr[i]= newArr[i - 1] + arr[i] 
  *       又因为都是线性叠加的，所以多个 updates 可以叠加。
  */
 public int[] getModifiedArray(int length, int[][] updates) {
    int[] res = new int[length];
    if(updates == null || updates.length == 0){
        return res;
    }
    int n = updates.length;

    for(int i = 0; i < n; i++){
        res[updates[i][0]] += updates[i][2];
        if(updates[i][1] + 1 < length){
            res[updates[i][1] + 1] -= updates[i][2];
        }
    }

    for(int i = 1; i < length; i++){
        res[i] = res[i - 1] + res[i];
    }
    return res;
 }
