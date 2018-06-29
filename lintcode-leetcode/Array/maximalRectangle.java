/** 510. Maximal Rectangle(lintcode) / 85. Maximal Rectangle(leetcode)
 *    Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *    
 *    Example: Given a matrix:
 *             [
 *               [1, 1, 0, 0, 1],
 *               [0, 1, 0, 0, 1],
 *               [0, 0, 1, 1, 1],
 *               [0, 0, 1, 1, 1],
 *               [0, 0, 0, 0, 1]
 *             ]
 *             return 6.
 */

/* Method 1: Convert to Largest Rectangle in Histogram
 *     Idea:  每多查看一行，想象俄罗斯方块下沉一样形成新的直方柱图，然后对当前的直方柱图求最大方形面积（Largest Rectangle in Histogram问题）
 *            如例子中：第一行得到直方图：[1, 1, 0, 0, 1],最大面积是2
 *                         添加第二行：[0, 1, 0, 0, 1] => [0, 2, 0, 0, 2] 
 *                    (第2行第1列是0，所以第1行第1列的1不会掉下来形成直方图，但是它对它本行以及它之前的行产生的影响在前一行已经计算过了)
 *                    。。。（依此类推，最后得到最大方形面积也就是1能形成的最大方形）
 *     复杂度：空间复杂度是 O(n^2); 
 *            平均时间复杂度是 O(n^2), 最坏时间复杂度是 O(n^3) （决定于maximalRectangleinHistogram的复杂度）
 */
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        int[][] m = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix[0].length; i++){
            if(matrix[0][i] == '1'){
                m[0][i] = 1;
            }
        }
        for(int i = 1; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == '1'){
                    m[i][j] = m[i-1][j] + 1;
                }
            }
        }
        int area = 0;
        for(int i = 0; i < matrix.length; i++){
            area = Math.max(area, maximalRectangleinHistogram(m[i]));
        }
        return area;
    }
    
    public int maximalRectangleinHistogram(int[] m){
        int[] left = new int[m.length];
        int[] right = new int[m.length];
        left[0] = -1;
        right[m.length-1] = m.length;
        int p = 0;
        for(int i = 1; i < m.length; i++){
            p = i - 1;
            while(p >= 0 && m[p] >= m[i]){
                p = left[p];
            }
            left[i] = p;
        }
        for(int i = m.length - 2; i >= 0; i--){
            p = i + 1;
            while(p < m.length && m[p] >= m[i]){
                p = right[p];
            }
            right[i] = p;
        }
        int max = 0;
        for(int i = 0; i < m.length; i++){
            max = Math.max(max,m[i]*(right[i] - left[i] - 1));
        }
        return max;
    }   
}
