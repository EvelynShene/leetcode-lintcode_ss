/** 149. Max Points on a Line(leetcode) / 186. Max Points on a Line(lintcode)
 *      Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 *      Example:1) Given 4 points: (1,2), (3,6), (0,0), (1,3). The maximum number is 3.
 *
 *              2) Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]] ; Output: 4
 *                        Explanation:
 *                              ^
 *                              |
 *                              |  o
 *                              |     o        o
 *                              |        o
 *                              |  o        o
 *                              +------------------->
 *                              0  1  2  3  4  5  6
 */
 
 /* Method: 根据斜率来判断其他点是否在同一条直线上 => k = (y2 - y1) / (x2 - x1)
  *   idea: 对于每一个点，求出它与其他点组成的斜率，用一个hashmap记录相同斜率的点的个数 - map<k, num>。这里还需要考虑3个问题：
  *         1）斜率是一个小数，可以用double表示，但是对double的比较是否相等的操作是比较复杂的，所以考虑使用分数表示斜率，则map变成：
  *                map<map<dy, dx>, num>
  *        注意：存分数也不能直接存dx,dy,因为可能可以约分，比如 8/4 = 4/2；所以需要计算dx和dy的最大公约数，存入的分子分母要是互质的
  *         2）点重复的问题，如果两个点重合了，那么不存在斜率一说，直接记录重复点的个数；
  *         3）组成的直线垂直与x轴，此时斜率无穷大，不能做斜率除法操作，这时直接记为map<INT_MAX, 0>即可
  */
  
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
 class Solution {
    public int maxPoints(Point[] points) {
        if(points == null || points.length == 0){
            return 0;
        }
        int res = 0;
        for(int i = 0; i < points.length; i++){
            Map<Map<Integer, Integer>, Integer> map = new HashMap<>();
            int duplicate = 1;
            for(int j = i + 1; j < points.length; j++){
                if(points[i].x == points[j].x && points[i].y == points[j].y){
                    duplicate++;
                }
                else{
                    int dx = points[i].x - points[j].x;
                    int dy = points[i].y - points[j].y;
                    Map<Integer, Integer> m = new HashMap<>();
                    if(dx != 0){
                        int d = gcd(dy, dx);
                        m.put(dy / d, dx / d);
                    }
                    else{
                        m.put(Integer.MAX_VALUE, 0);
                    }
                    map.put(m , map.getOrDefault(m, 0) + 1);
                }
            }
            res = Math.max(res, duplicate);
            for(Map.Entry<Map<Integer, Integer>, Integer> entry: map.entrySet()){
                res = Math.max(res, entry.getValue() + duplicate);
            }
        }
        return res;
    }
    
    public int gcd(int a, int b){
        if(a == b || a == 0){
            return b;
        }
        
        int r = b;
        while(a % b != 0){
            int tmp = a % b;
            a = b;
            b = tmp;
            r = tmp;
        }
        return r;
    }
}
