/** 390. Elimination Game(leetcode) / 1265. Elimination Game(lintcode)
 *      There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and 
 *  every other number afterward until you reach the end of the list.
 *      Repeat the previous step again, but this time from right to left, remove the right most number and every
 *  other number from the remaining numbers.
 *      We keep repeating the steps again, alternating left to right and right to left, until a single number 
 *  remains.
 *      Find the last number that remains starting with a list of length n.
 *
 *      Example: Input: n = 9,
 *                   1 2 3 4 5 6 7 8 9
 *                   2 4 6 8
 *                   2 6
 *                   6
 *               Output: 6
 */

/* Method:
 *    1）首先根据题意可以发现，f(2k + 1) = f(2k)【因为奇数项第一次删除时都会删除掉】。
 *    2）从第二步开始，剩下 2, 4, 6, 8, ... , 2k，我们同样可以把数列变换成 2*[1,2,3,4,...,k]的形式，继续删除，但要注意删除方向
 *      a) 如果是从左往右删除，依然是删除奇数项，然后递归调用 2 * remaining(n/2);
 *      b) 如果是从右往左删除，这时要考虑当前n的奇偶性：【下面例子使用 n/2 = 6 or 5】
 *        i) n 是偶数，从右删除后，剩下是如 1, 3, 5，依然可以变化成 1, 2, 3,[用(i + 1) / 2], 调用使用2*remaining(n／2) - 1;
 *        ii)n 是奇数，从右删除后，剩下是如 2，4, 可直接递归调用 2 * remaining(n/2)
 */
class Solution {
    public int lastRemaining(int n) {
        if(n == 1){
            return 1;
        }
        if(n % 2 != 0){// f(2k + 1) = f(2k)
            n = n - 1;
        }
        return 2 * remaining(n / 2, true);
    }
    
    public int remaining(int n, boolean right_to_left){
        if(n == 1){
            return n;
        }
        if(right_to_left){
            if(n % 2 != 0){
                return 2 * remaining(n / 2, false);
            }
            return 2 * remaining(n / 2, false) - 1;
        }
        else{
            return 2 * remaining(n / 2, true);
        }
    }
}
