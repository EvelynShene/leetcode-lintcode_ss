/** 218. The Skyline Problem(leetcode)
 *        A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when 
 *   viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a 
 *   cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively 
 *   (Figure B). [https://leetcode.com/problems/the-skyline-problem/description/]
 *
 *        The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where 
 *   Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its 
 *   height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all 
 *   buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 *        For instance, the dimensions of all buildings in Figure A are recorded as: 
 *              [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *        The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], 
 *   [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line 
 *   segment.[key point指每条水平线最左边的点] 
 *        Note that the last key point, where the rightmost building ends, is merely used to mark the 
 *   termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings
 *   should be considered part of the skyline contour.
 *
 *        For instance, the skyline in Figure B should be represented as:
 *              [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *     
 *        Note: 1) The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 *              2) The input list is already sorted in ascending order by the left x position Li.
 *              3) The output list must be sorted by the x position.
 *              4) There must be no consecutive horizontal lines of equal height in the output skyline. 
 *          For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height
 *                     5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */

/* Method: Sweep line - 扫描线方法[https://www.youtube.com/watch?v=7AE-VCGEhtI]
 *    思路：扫描线从左往右，遇到区间起始点，取最高点；遇到区间终止点，取第二高的点。
 *    小技巧：存(x，height)时，如果是起始点，取其负数，这样既可以区分起始点和终止点，而且排序后x相同的情况起始点的值一定在终止点前面
 */
 
 class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<int[]>();
        if(buildings == null || buildings.length == 0){
            return res;
        }
        List<int[]> heights = new ArrayList<int[]>();
        for(int[] r: buildings){
            heights.add(new int[]{r[0], -r[2]}); // (start point, -height)
            heights.add(new int[]{r[1], r[2]}); // (end point, height)
        }
        Collections.sort(heights, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });
        
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer n1, Integer n2){
                return n2 - n1;
            }
        });
        q.offer(0);
        int prev = 0;
        for(int[] point: heights){
            if(point[1] < 0){//start point
                q.offer(-point[1]);
            }
            else{//end point
                q.remove(point[1]);
            }
            int cur_top = q.peek();
            if(cur_top != prev){
                res.add(new int[]{point[0], cur_top});
                prev = cur_top;
            }
        }
        return res;
    }
}
