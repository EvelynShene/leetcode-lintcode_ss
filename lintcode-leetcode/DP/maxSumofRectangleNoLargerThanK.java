/** 363. Max Sum of Rectangle No Larger Than K(leetcode)/ 1278. Max Sum of Rectangle No Larger Than K(lintcode)
 *      Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such 
 *  that its sum is no larger than k.
 *
 *      Example: Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 *               Output: 2 
 *                  Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
 *                             and 2 is the max number no larger than k (k = 2).
 *
 *      Note: 1) The rectangle inside the matrix must have an area > 0.
 *            2) What if the number of rows is much larger than the number of columns?
 */
 
 /* Method 1: O((nm)^2) time and O(mn) space complexity
  *     用sum[i][j] 表示从坐标[0,0] 到 [i,j]的矩形和；遍历所有的子矩形，找到最大的矩形和
  */
 public int maxSumSubmatrix(int[][] matrix, int k) {
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] sum = new int[m][n];
    int max = Integer.MIN_VALUE;
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(i == 0 && j ==0){
                sum[i][j] = matrix[i][j];
            }
            else if(i == 0){
                sum[i][j] = sum[i][j - 1] + matrix[i][j];
            }
            else if(j == 0){
                sum[i][j] = sum[i - 1][j] + matrix[i][j];
            }
            else{
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + matrix[i][j];
            }

            for(int r = 0; r <= i; r++){
                for(int c = 0; c <= j; c++){
                    int area = sum[i][j];
                    if(r == 0 && c == 0) ;
                    else if(r == 0){
                        area -= sum[i][c - 1];
                    }
                    else if(c == 0){
                        area -= sum[r - 1][j];
                    }
                    else{
                        area = area - sum[r - 1][j] - sum[i][c - 1] + sum[r - 1][c - 1];
                    }
                    if(area <= k){
                        max = Math.max(max, area);
                    }
                }
            }
        }
    }
    return max;
}

//Method 2: [idea from leetcode - https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/discuss/83599/Accepted-C++-codes-with-explanation-and-references] 
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int max_sum = Integer.MIN_VALUE;
        
        for(int l = 0; l < col; l++){
            int[] sum = new int[row];
            for(int r = l; r < col; r++){
                for(int i = 0; i < row; i++){
                    sum[i] += matrix[i][r];
                }
                // store maximum subarray sum int sum[] which <= k
                int max_1D_sum = maxSumSubArrayLessK(sum, k);
                
                if(max_1D_sum > max_sum){
                    max_sum = max_1D_sum;
                }
            }
        }
        return max_sum;
    }
    public int maxSumSubArrayLessK(int[] a , int k){
        int max = Integer.MIN_VALUE;
        int sumj = 0;
        TreeSet<Integer> ts = new TreeSet();
        ts.add(0);
    
        for(int i=0;i<a.length;i++){
            sumj += a[i];
            Integer gap = ts.ceiling(sumj - k);
            if(gap != null){ 
                max = Math.max(max, sumj - gap);
            }
            ts.add(sumj);
        }
        return max;
    }
}
