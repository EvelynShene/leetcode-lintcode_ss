/** 461. Hamming Distance(leetcode) / 835. Hamming Distance(lintcode)
 *      The Hamming distance between two integers is the number of positions at which the corresponding bits are 
 *  different.
 *      Given two integers x and y, calculate the Hamming distance.
 *
 *      Note: 0 ≤ x, y < 231.
 *
 *      Example: Input: x = 1, y = 4 ; Output: 2
 */
 
 //My Method:
 class Solution {
    public int hammingDistance(int x, int y) {
        int tmp = x ^ y;
        int res = 0;
        while(tmp != 0){
            res += tmp & 1;
            tmp = tmp >>> 1;
        }
        return res;
    }
}
