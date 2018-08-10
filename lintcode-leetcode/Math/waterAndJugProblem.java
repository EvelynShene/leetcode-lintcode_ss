/** 365. Water and Jug Problem(leetcode) / 1277. Water and Jug Problem(lintcode)
 *      You are given two jugs with capacities x and y litres. There is an infinite amount of water supply 
 *  available. You need to determine whether it is possible to measure exactly z litres using these two jugs.
 *      If z liters of water is measurable, you must have z liters of water contained within one or both buckets 
 *  by the end.
 *
 *      Operations allowed:
 *        1) Fill any of the jugs completely with water.
 *        2) Empty any of the jugs.
 *        3) Pour water from one jug into another till the other jug is completely full or the first jug itself 
 *      is empty.
 *
 *      Example: 1) Input: x = 3, y = 5, z = 4 ; Output: True
 *               2) Input: x = 2, y = 6, z = 5 ; Output: False
 */
 
 /* Method:
  *    这道题的思路在于找是否存在 m,n 使得等式 z = m*a + n*b 成立。<=> a,b的最大公约数 gcd(a,b) 是否能被 z 整除。   
  */
class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if(z == 0){
            return true;
        }
        if(z > x + y){
            return false;
        }
        int tmp = 0;
        if(x < y){
            tmp = gcd(y, x);
        }
        else{
            tmp = gcd(x, y);
        }
        
        if(z % tmp == 0){
            return true;
        }
        return false;
    }
    public int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        while(a % b != 0){
            int res = b;
            b = a % b;
            a = res;
        }
        return b;
    }
}
