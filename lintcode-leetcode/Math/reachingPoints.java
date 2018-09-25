/** 780. Reaching Points(leetcode) / 1036. Reaching Points(lintcode)
 *      A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).
 *      Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves 
 *  exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.
 *
 *     Examples: 1) Input: sx = 1, sy = 1, tx = 3, ty = 5 ; Output: True
 *               Explanation: One series of moves that transforms the starting point to the target is:
 *                       (1, 1) -> (1, 2)
 *                       (1, 2) -> (3, 2)
 *                       (3, 2) -> (3, 5)
 *               2) Input: sx = 1, sy = 1, tx = 2, ty = 2 ; Output: False
 *               3) Input: sx = 1, sy = 1, tx = 1, ty = 1 ; Output: True
 */
 
 /* Method: Math
  *    思路：这是一道math题，通过几步推演可以知道，我们需要判断的是：是否存在整数a1,b1,a2,b2，使得：
  *             tx = a1*sx + b1*sy && ty = a2*sx + b2*sy => tx - ty = a3*sx + b3*sy
  *        所以我们可以对大数取余，即如果tx > ty, 就用tx % ty代替tx; 反之如果ty > tx, 就用ty % tx代替ty。
  *        直到ty == sy(或者 tx == sx)时，再判断(tx - sx) % sy == 0(或者(ty - sy) % sx == 0) 是否成立。
  */
public boolean reachingPoints(int sx, int sy, int tx, int ty) {
    while(tx >= sx && ty >= sy){
        if(tx > ty){
            if(ty > sy){
                tx  = tx % ty;
            }
            else{//ty == sy
                return (tx - sx) % sy == 0;
            }
        }
        else{ // tx < ty
            if(tx > sx){
                ty = ty % tx;
            }
            else{// tx == sx
                return (ty - sy) % sx == 0;
             }
        }
    }
    return false;
}
