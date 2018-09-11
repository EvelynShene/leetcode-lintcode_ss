/** 311. Sparse Matrix Multiplication(leetcode) / 654. Sparse Matrix Multiplication(lintcode)
 *      Given two Sparse Matrix A and B, return the result of AB.
 *      You may assume that A's column number is equal to B's row number.
 *
 *      Example: A = [
 *                      [ 1, 0, 0],
 *                      [-1, 0, 3]
 *                   ]
 *               B = [
 *                      [ 7, 0, 0 ],
 *                      [ 0, 0, 0 ],
 *                      [ 0, 0, 1 ]
 *                    ]
 *                    |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 *               AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                                 | 0 0 1 |
 */
 
 //My Method:
 public int[][] multiply(int[][] A, int[][] B) { // A is m x k matrix and B is k x n matrix
    int m = A.length;
    int k = A[0].length;
    int n = B[0].length;

    int[][] res = new int[m][n];
    for(int i = 0; i < m; i++){
        for(int j = 0; j < k; j++){
            if(A[i][j] != 0){
                for(int x = 0; x < n; x++){
                    res[i][x] += A[i][j] * B[j][x];
                }
            }
        }
    }
    return res;
}
