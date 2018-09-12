/** 240. Search a 2D Matrix II(leetcode)
 *      Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following 
 *  properties:
 *      1) Integers in each row are sorted in ascending from left to right.
 *      2) Integers in each column are sorted in ascending from top to bottom.
 *
 *      Example: Consider the following matrix:
 *                      [
 *                        [1,   4,  7, 11, 15],
 *                        [2,   5,  8, 12, 19],
 *                        [3,   6,  9, 16, 22],
 *                        [10, 13, 14, 17, 24],
 *                        [18, 21, 23, 26, 30]
 *                      ]
 *               1) Given target = 5, return true.
 *               2) Given target = 20, return false.
 */
 
 //Method: O(m + n) time complexity
 public boolean searchMatrix(int[][] matrix, int target) {
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
        return false;
    }
    int m = matrix.length;
    int n = matrix[0].length;
    if(target > matrix[m - 1][n - 1] || target < matrix[0][0]){
        return false;
    }
    int row = 0;
    int col = n - 1;
    while(row < m && col >= 0){
        if(matrix[row][col] == target){
            return true;
        }
        if(matrix[row][col] < target){
            row++;
        }
        else{ // matrix[row][n - 1] > target
            col--;
        }
    }
    return false;
 }
