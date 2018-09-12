/** 38. Search a 2D Matrix II(lintcode)
 *      Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.
 *      This matrix has the following properties:
 *          1) Integers in each row are sorted from left to right.
 *          2) Integers in each column are sorted from up to bottom.
 *          3) No duplicate integers in each row or column.
 *
 *      Example: Consider the following matrix:
 *                    [
 *                      [1, 3, 5, 7],
 *                      [2, 4, 7, 8],
 *                      [3, 5, 9, 10]
 *                    ]
 *               Given target = 3, return 2.
 *
 *      Challenge: O(m+n) time and O(1) extra space
 */
 
 //Method: O(m+n) time and O(1) extra space
 public int searchMatrix(int[][] matrix, int target) {
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
        return 0;
    }
    int m = matrix.length;
    int n = matrix[0].length;
    if(target > matrix[m - 1][n - 1] || target < matrix[0][0]){
        return 0;
    }
    int row = 0;
    int col = n - 1;
    int count = 0;
    while(row < m && col >= 0){
        if(matrix[row][col] == target){
            count++;
        }
        if(matrix[row][col] <= target){
            row++;
        }
        else{ // matrix[row][n - 1] > target
            col--;
        }
    }
    return count;
 }
