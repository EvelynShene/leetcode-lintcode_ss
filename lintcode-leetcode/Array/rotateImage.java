/** 48. Rotate Image(leetcode) / 161. Rotate Image(lintcode)
 *      You are given an n x n 2D matrix representing an image.
 *      Rotate the image by 90 degrees (clockwise).
 *
 *      Note: You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
 *            DO NOT allocate another 2D matrix and do the rotation.
 *
 *      Example: 1) Given input matrix = 
 *                     [
 *                       [1,2,3],
 *                       [4,5,6],
 *                       [7,8,9]
 *                     ],
 *                  rotate the input matrix in-place such that it becomes:
 *                     [
 *                       [7,4,1],
 *                       [8,5,2],
 *                       [9,6,3]
 *                     ]
 *               2) Given input matrix =
 *                     [
 *                       [ 5, 1, 9,11],
 *                       [ 2, 4, 8,10],
 *                       [13, 3, 6, 7],
 *                       [15,14,12,16]
 *                     ], 
 *                  rotate the input matrix in-place such that it becomes:
 *                     [
 *                       [15,13, 2, 5],
 *                       [14, 3, 4, 1],
 *                       [12, 6, 8, 9],
 *                       [16, 7,10,11]
 *                     ]
 */
 
 /* My Method:
  *     Idea: 有两种思路；一种是对称交换矩阵的行，然后再以正对角线交换所有的元素；
  *                     另一种是对称交换矩阵的列，然后以反对角线交换所有的元素
  */
 class Solution {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        int n = matrix.length;
        
        //swap row[i] and row[j], where i < j && i + j = n - 1
        for(int i = 0; i < n / 2; i++){
            for(int col = 0; col < n; col++){
                int tmp = matrix[i][col];
                matrix[i][col] = matrix[n - 1 - i][col];
                matrix[n - 1 - i][col] = tmp;
            }
        }
        
        //swap matrix[i][j] and matrix[j][i] when i != j
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}
