/** 908. Line Reflection(lintcode) / 356. Line Reflection(leetcode - locked)
 *      Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given 
 *  points.
 *  
 *      Example: 1) Given points = [[1,1],[-1,1]], return true.
 *               2) Given points = [[1,1],[-1,-1]], return false.
 *
 *      Challenge: Could you do better than O(n2)?
 *      
 *      理解题意：问存不存在一条平行于y轴的直线，使得所有的点关于该直线对称。
 */
 
 /* Hint: 
  *     1) Find the smallest and largest x-value for all points.
  *     2) If there is a line then it should be at y = (minX + maxX) / 2.
  *     3) For each point, make sure that it has a reflected point in the opposite side.
  */
 
 //Method: O(n) time and space complexity
 public boolean isReflected(int[][] points) {
    if(points == null || points.length == 0){
        return true;
    }
    int n = points.length;
    int leftmost = points[0][0];
    int rightmost = points[0][0];
    Map<Integer, Set<Integer>> p = new HashMap<>();
    for(int i = 0; i < n; i++){
        if(!p.containsKey(points[i][0])){
            p.put(points[i][0], new HashSet<>());
        }
        p.get(points[i][0]).add(points[i][1]);

        if(points[i][0] < leftmost){
            leftmost = points[i][0];
        }
        if(points[i][0] > rightmost){
            rightmost = points[i][0];
        }
    }

    int line = leftmost + rightmost;
    for(int i = 0; i < n; i++){
        int reflex_x = line - points[i][0];
        if(!p.containsKey(reflex_x)){
            return false;
        }
        if(!p.get(reflex_x).contains(points[i][1])){
            return false;
        }
    }
    return true;
}
