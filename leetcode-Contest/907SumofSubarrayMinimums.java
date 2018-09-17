/** 907. Sum of Subarray Minimums(leetcode)
 *     Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
 *     Since the answer may be large, return the answer modulo 10^9 + 7.
 *    
 *     Example: Input: [3,1,2,4]
 *              Output: 17
 *                Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
 *                            Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
 *
 *     Note: 1) 1 <= A.length <= 30000
 *           2) 1 <= A[i] <= 30000
 */
 
 /* Method 1: O(nlogn) time complexity - [TLE]
  *   Idea: 1) Let x be the smallest element in the A[start, end] and i be its index in the array(starting from 0) 
  *            It's not hard to see that the amount of subarrays that contain x is equal to 
  *                                (i - start + 1)*(end + 1 - i)
  *            and the minimum of all these subarrays is x, so we can add
  *                               (i - start + 1)*(end + 1 - i) * x 
  *            to the answer. 
  *         2) Now, we can split our array in two arrays [start,i - 1] and [i + 1,end] and solve the problem 
  *           recursively, summing up the partial results in the end. 
  */
 class Solution {
    public int sumSubarrayMins(int[] A) {
        if(A == null || A.length == 0){
            return 0;
        }
        return sumSubMins(A, 0, A.length - 1);
    }
    
    public int sumSubMins(int[] A, int start, int end){
        if(start > end){
            return 0;
        }
        if(start == end){
            return A[start];
        }
        int min_i = start;
        for(int i = start + 1; i <= end; i++){
            if(A[i] < A[min_i]){
                min_i = i;
            }
        }
        int mod_num = 1000000007;
        int res = ((end + 1 - min_i) * (min_i - start + 1) * A[min_i]) % mod_num;
        res = (res + sumSubMins(A, start, min_i - 1) % mod_num ) % mod_num;
        res = (res + sumSubMins(A, min_i + 1, end) % mod_num) % mod_num;
        
        return res;
    }
}

/* Method 2: O(n) time and space complexity
 *    Idea: 1) For each position i, find the nearest element (its index) to its right whose value that is less
 *           than that at i (if does not exist, consider this parameter to be n, where n is size of array)
 *             [This whole process can be done in O(n) using stack.]
 *          2) Now start processing the array from right to left. 
 *           Let DP[i] denote the sum of minimum of subarrays [i, j] such that i <= j <= n. 
 *           Assume you are currently processing position i and the value of the above parameter is p. 
 *             a) For all subarrays [i, j] such that i <= j < p, the minimum will be arr[i]. 
 *             b) And for all subarrays [i, j] such that p <= j <= n, the sum of minimum of these subarrays has 
 *                already been computed and stored in DP[p].
 *           So, your formula becomes: DP[i] = (p-i)* arr[i] + DP[p]
 *          3) The result is the sum of all DP[i]
 *
 *    Example: [3, 1, 2, 4] => nearest element less than each element: [1, 4, 4, 4], So: [Let DP[4] = 0]
 *               DP[3] = (4 - 3) * 4 + DP[4] = 4;
 *               DP[2] = (4 - 2) * 2 + DP[4] = 4;
 *               DP[1] = (4 - 1) * 1 + DP[4] = 3;
 *               DP[0] = (1 - 0) * 3 + DP[1] = 6;
 *             res = DP[0] + DP[1] + DP[2] + DP[3] = 17
 */
 class Solution {
    public int sumSubarrayMins(int[] A) {
        if(A == null || A.length == 0){
            return 0;
        }
        int n = A.length;    
        int[] min_index = new int[n];
        int[] DP = new int[n+1];
    
        Stack<Integer> s = new Stack<Integer>();
        s.push(0);
        for(int i = 1; i < n; i++){
            if(A[i] >= A[s.peek()]){
                s.push(i);
            }
            else{//A[i] < A[s.peek()];
                while(!s.isEmpty() && A[i] < A[s.peek()]){
                    min_index[s.pop()] = i;
                }
                s.push(i);
            }
        }
        while(!s.isEmpty()){
            min_index[s.pop()] = n;
        }
        
        int res = 0;
        int mod_num = 1000000007;
        for(int i = n - 1; i >= 0; i--){
            DP[i] = (min_index[i] - i) * A[i] + DP[min_index[i]];
            res += DP[i];
            if(res > mod_num){
                res = res % mod_num;
            }
        }
        return res;
    }  
}
