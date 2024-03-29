/** 396. Rotate Function(leetcode) / 1260. Rotate Function(lintcode)
 *      Given an array of integers A and let n to be its length.
 *      Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation 
 *  function" F on A as follow:
 *          F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
 *      Calculate the maximum value of F(0), F(1), ..., F(n-1).
 *
 *      Note: n is guaranteed to be less than 105.
 *
 *      Example: A = [4, 3, 2, 6]
 *                 F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 *                 F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 *                 F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 *                 F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 *               So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 */
 
 //Method 1: Brute Force - TLE
 public int maxRotateFunction(int[] A) {
    if(A == null || A.length < 2){
        return 0;
    }
    int n = A.length;
    int[] factors = new int[n];
    for(int i = 0; i < n; i++){
        factors[i] = i;
    }
    int index = 0;
    int max = Integer.MIN_VALUE;
    for(int i = 0; i < n; i++){
        int sum = 0;
        for(int j = 0; j < n; j++){
            sum += A[j] * factors[(j + index) % n];
        }
        max = Math.max(max, sum);
        index++;
    }
    return max;
 }
 
 /* Method 2: 推导公式
  *     F(k)   = 0 * Bk[0] + 1 * Bk[1] + 2 * Bk[2] + ... + (n-2) * Bk[n-2] + (n-1) * Bk[n-1]
  *     F(k-1) = 0 * Bk-1[0] + 1 * Bk-1[1] + ... + (n-1) * Bk-1[n-1] //coz: Bk-1[i] = Bk[i+1]
  *            =             0 * Bk[1] + 1 * Bk[2] + ... + (n-2) * Bk[n-1] + (n-1) * Bk[0]
  *   Then,
  *       F(k) - F(k-1) = Bk[1] + Bk[2] + ... + Bk[n-1] + (1-n)Bk[0]
  *                     = (Bk[0] + ... + Bk[n-1]) - nBk[0]
  *                     = sum - nBk[0]
  *   Thus,
  *       F(k) = F(k-1) + sum - nBk[0]
  *
  *   Also,  B0[0] = A[0]
  *          B1[0] = A[n-1]
  *          B2[0] = A[n-2]
  *          ......
  */
 public int maxRotateFunction(int[] A) {
    if(A == null || A.length < 2){
        return 0;
    }
    int n = A.length;
    int sum = 0;
    int f = 0;
    for(int i = 0; i < n; i++){
        sum += A[i];
        f += i * A[i];
    }
    int max = f;
    for(int i = 1; i < n; i++){
        f = f + sum - n * A[n-i];
        max = Math.max(f,max);
    }
    return max;
 }
