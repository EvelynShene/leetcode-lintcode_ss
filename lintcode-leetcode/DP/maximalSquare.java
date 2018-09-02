/** 221. Maximal Square(leetcode) / 436. Maximal Square(lintcode)
 *      Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return 
 *  its area.
 *
 *      Example: Input: 
 *                     1 0 1 0 0
 *                     1 0 1 1 1
 *                     1 1 1 1 1
 *                     1 0 0 1 0
 *               Output: 4
 */
 
 //Method 1: O(n^3) time and O(n^2) complexity [AC in leetcode but TLE in lintcode]
 class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] histogram = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0){
                    histogram[i][j] = matrix[i][j] - '0';
                }
                else{
                    histogram[i][j] = matrix[i][j] == '0'? 0: (matrix[i][j] - '0') + histogram[i - 1][j];
                }
            }
        }
        int max_square = 0;
        for(int i = 0; i < m; i++){
            max_square = Math.max(max_square, findMaxSquare(histogram[i]));
        }
        return max_square;
    }
    
    public int findMaxSquare(int[] height){
        int max = 0;
        for(int i = 0; i < height.length; i++){
            int high = height[i];
            if(high != 0){
                int min = high;
                for(int j = i; j < height.length && j - i + 1 <= high; j++){
                    if(min > height[j]){
                        min = height[j];
                    }
                    int square = Math.min(min, j - i + 1);
                    square *= square;
                    max = Math.max(max, square);
                }
            }
        }
        return max;
    }
}

//Method 2: 优化findMaxSquare函数，见Largest Rectangle in Histogram [AC in both]
public int findMaxSquare(int[] height){
    int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
    int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
    lessFromRight[height.length - 1] = height.length;
    lessFromLeft[0] = -1;

    /* Trick: 找左右两侧最远的大于bar i本身的bar的index位置，如果直接遍历是需要O(n^2);这里用到一个小技巧。
     *     当遍历到height[p] >= height[i]时，因为p处的bar高比i处的bar高，所以bar p左边的最远一个大于bar p高度的bar也一定大于bar i。
     *     所以可以在循环中直接使用 p = lessFromRight[p] 来跳着找bar i左边的最远一个大于bar i高度的bar位置。
     *     平均时间复杂 = O(n) ；最坏时间复杂度 = O(n^2)
     */
    for (int i = 1; i < height.length; i++) {
        int p = i - 1;

        while (p >= 0 && height[p] >= height[i]) {
            p = lessFromLeft[p];
        }
        lessFromLeft[i] = p;
    }

    for (int i = height.length - 2; i >= 0; i--) {
        int p = i + 1;

        while (p < height.length && height[p] >= height[i]) {
            p = lessFromRight[p];
        }
        lessFromRight[i] = p;
    }

    int maxArea = 0;
    for (int i = 0; i < height.length; i++) {
        int square = Math.min(height[i] , (lessFromRight[i] - lessFromLeft[i] - 1));
        square *= square;
        maxArea = Math.max(maxArea, square);
    }
    return maxArea;
}

//Method 3: DP
