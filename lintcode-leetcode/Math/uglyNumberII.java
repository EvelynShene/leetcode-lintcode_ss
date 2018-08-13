/** 264. Ugly Number II(leetcode) / 4. Ugly Number II(lintcode)
 *    Write a program to find the n-th ugly number.
 *    Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
 *
 *    Note: 1 is typically treated as an ugly number.
 *          n does not exceed 1690.
 *
 *    Example: Input: n = 10 ; Output: 12
 *            Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 *
 *    Hint: 
 *      1) The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are 
 *      not ugly. Try to focus your effort on generating only the ugly ones.
 *      2) An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
 *      3) The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from 
 *      three sorted lists: L1, L2, and L3.
 *      4) Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
 */
 
 /* Method: 
  *   丑数组合可以分成如下三种组合(其中，第一个乘数为之前计算得出的丑数，第一个丑数为1，第二个乘数为2、3、5中的一个数) ：
  *     a) (以2为乘数)1×2, 2×2, 3×2, 4×2, 5×2, 6×2, 8×2, …
  *     b) (以3为乘数)1×3, 2×3, 3×3, 4×3, 5×3, 6×3, 8×3, …
  *     c) (以5为乘数)1×5, 2×5, 3×5, 4×5, 5×5, 6×5, 8×5, …
  *   而下一个丑数是从三种乘法组合中各自没有被选的最小数中的最小的丑数。（每次都从三个列表中取出当前最小的那个加入序列）
  */
 public int nthUglyNumber(int n) {
    if(n == 1)
        return 1;
    List<Integer> list = new ArrayList<Integer>();
    list.add(1);
    int index1 = 0;
    int index2 = 0;
    int index3 = 0;
    while(list.size() < n){
        int l1 = list.get(index1) * 2;
        int l2 = list.get(index2) * 3;
        int l3 = list.get(index3) * 5;
        int min = Math.min(l1,Math.min(l2,l3));
        list.add(min);
        if(min == l1) index1++;
        if(min == l2) index2++;
        if(min == l3) index3++;
    }
    return list.get(n-1);
 }
