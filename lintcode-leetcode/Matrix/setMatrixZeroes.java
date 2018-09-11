/** 73. Set Matrix Zeroes(leetcode) / 162. Set Matrix Zeroes(lintcode)
 *      Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 *
 *      Example 1) Input: 
 *                      [
 *                        [1,1,1],
 *                        [1,0,1],
 *                        [1,1,1]
 *                      ]
 *                 Output: 
 *                      [
 *                        [1,0,1],
 *                        [0,0,0],
 *                        [1,0,1]
 *                      ]
 *              2) Input: 
 *                      [
 *                        [0,1,2,0],
 *                        [3,4,5,2],
 *                        [1,3,1,5]
 *                      ]
 *                 Output: 
 *                      [
 *                        [0,0,0,0],
 *                        [0,4,5,0],
 *                        [0,3,1,0]
 *                      ]
 *
 *      Follow up: 1) A straight forward solution using O(mn) space is probably a bad idea.
 *                 2) A simple improvement uses O(m + n) space, but still not the best solution.
 *                 3) Could you devise a constant space solution? 
 */
 
 //Method 1: O(mn) time and O(m + n) space complexity
 public void setZeroes(int[][] matrix) {
    if(matrix == null || matrix.length == 0){
        return;
    }
    int m = matrix.length;
    int n = matrix[0].length;
    boolean[] row = new boolean[m];
    boolean[] col = new boolean[n];

    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(matrix[i][j] == 0){
                row[i] = true;
                col[j] = true;
            }
        }
    }

    for(int i = 0; i < m; i++){
        if(row[i]){
            Arrays.fill(matrix[i], 0);
        }
    }

    for(int j = 0; j < n; j++){
        if(col[j]){
            for(int i = 0; i < m; i++){
                matrix[i][j] = 0;
            }
        }
    }
}

/* Method 2: Use the row[0] and col[0] to store whether the row and col need to be set 0
 *                ------ O(mn) time and O(1) space complexity 
 */
public void setZeroes(int[][] matrix) {
    if(matrix == null || matrix.length == 0){
        return;
    }
    int m = matrix.length;
    int n = matrix[0].length;

    boolean col0 = false;
    boolean row0 = false;

    for(int i = 0; i < m; i++){
        if(matrix[i][0] == 0){
            col0 = true;
            break;
        } 
    }

    for(int j = 0; j < n; j++){
        if(matrix[0][j] == 0){
            row0 = true;
            break;
        }
    }

    for(int i = 1; i < m; i++){
        for(int j = 1; j < n; j++){
            if(matrix[i][j] == 0){
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }

    for(int i = 1; i < m; i++){
        for(int j = 1; j < n; j++){
            if(matrix[i][0] == 0 || matrix[0][j] == 0){
                matrix[i][j] = 0; 
            }
        }
    }

    if(row0){
        Arrays.fill(matrix[0], 0);
    }

    if(col0){
        for(int i = 0; i < m; i++){
            matrix[i][0] = 0;
        }
    }
}
