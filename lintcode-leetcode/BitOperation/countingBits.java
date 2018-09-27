/** 338. Counting Bits(leetcode) / 664. Counting Bits(lintcode)
 *      Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number
 *  of 1's in their binary representation and return them as an array.
 *
 *      Example: 1) Input: 2 ; Output: [0,1,1]
 *               2) Input: 5 ; Output: [0,1,1,2,1,2]
 *
 *      Follow up: 1) It is very easy to come up with a solution with run time O(n*sizeof(integer)). 
 *                    But can you do it in linear time O(n) /possibly in a single pass?
 *                 2) Space complexity should be O(n).
 *                 3) Can you do it like a boss? Do it without using any builtin function like __builtin_popcount 
 *                    in c++ or in any other language.
 */
 
 //Method 1: O(n*sizeof(integer)) time complexity
 public int[] countBits(int num) {
    if(num < 0){
        return null;
    }
    int[] res = new int[num + 1];

    for(int i = 0; i <= num; i++){
        int count = 0;
        int n = i;
        while(n > 0){
            if((n & 1) == 1){
                count++;
            }
            n = n >> 1;
        }
        res[i] = count;
    }
    return res;
 }
 
 /* Method 2: O(n) time complexity
  *    idea: if n is even, the number of 1 in n is equal to that of in n / 2;
  *          if n is odd, the number of 1 in n is equal to that of in (n / 2) add 1.
  */
 public int[] countBits(int num) {
    if(num < 0){
        return null;
    }
    int[] res = new int[num + 1];
    for(int i = 1; i <= num; i++){
        if(i % 2 == 0){//even
            res[i] = res[i / 2];
        }
        else{//odd
            res[i] = res[i / 2] + 1;
        }
    }
    return res;
 }
 
 /* Method 3:
  *     idea: 利用了i&(i - 1) [判断一个数是否是2的指数的快捷方法-比如8，二进制位1000, 那么8&(8-1)为0，只要为0就是2的指数]
  *       i&(i - 1)会消除i中最左边的那个1，同时i&(i - 1)的值也变小了，这个值对应的1的个数已经算出来了，那么：
  *                 res[i] = res[i&(i - 1)] + 1
  */
 public int[] countBits(int num) {
    if(num < 0){
        return null;
    }
    int[] res = new int[num + 1];
    for(int i = 1; i <= num; i++){
        res[i] = res[i & (i - 1)] + 1;
    }
    return res;
}
