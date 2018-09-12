/** 74. Search a 2D Matrix(leetcode) / 28. Search a 2D Matrix(lintcode)
 *     Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following 
 *  properties:
 *        1) Integers in each row are sorted from left to right.
 *        2) The first integer of each row is greater than the last integer of the previous row.
 *
 *     Example: Input:
 *                  matrix = [
 *                    [1,   3,  5,  7],
 *                    [10, 11, 16, 20],
 *                    [23, 30, 34, 50]
 *                  ]
 *                  target = 3
 *              Output: true
 */
 
 //My Method: O(m + logn) time complexity
 class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0 ){
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        if(matrix[m - 1][n - 1] < target){
            return false;
        }
        int row = 0;
        for(; row < m; row++){
            if(matrix[row][n - 1] >= target){
                break;
            }
        }
        if(row < m  && matrix[row][0] > target){
            return false;
        }
        
        int left = 0;
        int right = n - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(matrix[row][mid] == target){
                return true;
            }
            else if(matrix[row][mid] < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return false;
    }
}

//Method 2: Use binary Search
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0 ){
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        if(matrix[m - 1][n - 1] < target){
            return false;
        }
        
        int left = 0;
        int right = m * n - 1;
        
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(matrix[mid / n][mid % n] == target){
                return true;
            }
            else if(matrix[mid / n][mid % n] < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return false;
    }
}
