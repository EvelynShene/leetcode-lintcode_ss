/** 1354. Pascal's Triangle II(lintcode)/119. Pascal's Triangle II(leetcode)
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 * Note that the row index starts from 0. In Pascal's triangle, each number is the sum of the two numbers directly above it.
 */


/** My Method: The kth index row of the Pascal's triangle is the coefficients of (x+y)^k, so use the C(k,i) to calculate
 * @param rowIndex: a non-negative index
 * @return: the kth index row of the Pascal's triangle
 */
public List<Integer> getRow(int rowIndex) {
    // write your code here
    List<Integer> res = new ArrayList<Integer>();
    int num = 0;

    for(int i = 0; i <= rowIndex; i++){
        if(i <= rowIndex/2){
            if(i == 0) num = 1;
            else{
                double r = 1;
                for(int j = i-1; j >= 1; j--){
                    r = r * (double)(rowIndex-j)/j;
                }
                r = r * ((double)rowIndex/i);
                num = Integer.parseInt(new java.text.DecimalFormat("0").format(r));
            }
            res.add(num);
        }
        else{
            res.add(res.get(rowIndex-i));
        }
    }
    return res;
}
//Note: 要考虑大乘法数的溢出问题

/** Method 2 (from leetcode disscuss): 
 * @param rowIndex: a non-negative index
 * @return: the kth index row of the Pascal's triangle
 */
public List<Integer> getRow(int rowIndex) {
    int[] nums = new int[rowIndex+1];
    for(int i = 0; i <= rowIndex; i++) {
        for(int j = i; j >= 1; j--)
            nums[j] += nums[j-1];
        nums[i] = 1;
    }

    List<Integer> res = new ArrayList<>(rowIndex);
    for(int num : nums) res.add(num);
    return res;
}
