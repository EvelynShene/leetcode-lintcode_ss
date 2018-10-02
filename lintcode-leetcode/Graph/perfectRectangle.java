/** 391. Perfect Rectangle(leetcode) / 1264. Perfect Rectangle(lintcode)
 *     Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a 
 *  rectangular region.
 *      Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is 
 *  represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2))
 *
 *      Example: 1) rectangles = [
 *                                  [1,1,3,3],
 *                                  [3,1,4,2],
 *                                  [3,2,4,4],
 *                                  [1,3,2,4],
 *                                  [2,3,3,4]
 *                               ]
 *                  Return true. All 5 rectangles together form an exact cover of a rectangular region.
 */
 
 //Method: idea from [https://www.cnblogs.com/grandyang/p/5825619.html]
 class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        if(rectangles == null || rectangles.length == 0){
            return true;
        }
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;
        int area = 0;
        Set<String> set = new HashSet<>();
        for(int[] r: rectangles){
            area += (r[2] - r[0])*(r[3] - r[1]);
            x1 = Math.min(x1, r[0]);
            y1 = Math.min(y1, r[1]);
            x2 = Math.max(x2, r[2]);
            y2 = Math.max(y2, r[3]);
            
            String a = r[0] + "," + r[1];
            String b = r[0] + "," + r[3];
            String c = r[2] + "," + r[1];
            String d = r[2] + "," + r[3];
            
            if(!set.add(a)){
                set.remove(a);
            }
            if(!set.add(b)){
                set.remove(b);
            }
            if(!set.add(c)){
                set.remove(c);
            }
            if(!set.add(d)){
                set.remove(d);
            }
        }
        int recArea = (x2 - x1) * (y2 - y1);
        if(area != recArea){
            return false;
        }
        if(set.size() != 4){
            return false;
        }
        if(!set.contains(x1 + "," + y1) || !set.contains(x1 + "," + y2) || !set.contains(x2 + "," + y1) || !set.contains(x2 + "," + y2)){
            return false;
        }
        return true;
    }
}
