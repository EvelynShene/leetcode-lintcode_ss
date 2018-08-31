/** 120. Triangle(leetcode)/ 109. Triangle(lintcode)
 *      Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers
 *  on the row below.
 *
 *      For example, given the following triangle
 *                     [
 *                          [2],
 *                         [3,4],
 *                        [6,5,7],
 *                       [4,1,8,3]
 *                     ]
 *      The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 *      Note: Bonus point if you are able to do this using only O(n) extra space, 
 *            where n is the total number of rows in the triangle.
 */
 
 //Method 1: O(n^2) time and space complexity
 public int minimumTotal(List<List<Integer>> triangle) {
    if(triangle == null || triangle.size() == 0){
        return 0;
    }
    int row = triangle.size();
    int max_col = triangle.get(row - 1).size();
    int[][] dp = new int[row][max_col];
    dp[0][0] = triangle.get(0).get(0);

    for(int i = 1; i < row; i++){
        for(int j = 0; j < triangle.get(i).size(); j++){
            if(j == 0){
                dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
            }
            else if(j == triangle.get(i).size() - 1){
                dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
            }
            else{
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i - 1][j], dp[i - 1][ j - 1]);
            } 
        }
    }

    int min = Integer.MAX_VALUE;
    for(int i = 0; i < max_col; i++){
        if(min > dp[row - 1][i]){
            min = dp[row - 1][i];
        }
    }
    return min;
 }
 
 //Method 2: O(n^2) time and O(n) space complexity
 public int minimumTotal(List<List<Integer>> triangle) {
    if(triangle == null || triangle.size() == 0){
        return 0;
    }
    int row = triangle.size();
    int max_col = triangle.get(row - 1).size();
    int[] dp = new int[max_col];
    dp[0] = triangle.get(0).get(0);

    for(int i = 1; i < row; i++){
        for(int j = triangle.get(i).size() - 1; j >= 0 ; j--){
            if(j == 0){
                dp[j] = dp[j] + triangle.get(i).get(j);
            }
            else if(j == triangle.get(i).size() - 1){
                dp[j] = dp[j - 1] + triangle.get(i).get(j);
            }
            else{
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[ j - 1]);
            } 
        }
    }

    int min = Integer.MAX_VALUE;
    for(int i = 0; i < max_col; i++){
        if(min > dp[i]){
            min = dp[i];
        }
    }
    return min;
 }
