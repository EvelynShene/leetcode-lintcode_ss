/** 915. Partition Array into Disjoint Intervals(leetcode)
 *    Given an array A, partition it into two (contiguous) subarrays left and right so that:
 *        1) Every element in left is less than or equal to every element in right.
 *        2) left and right are non-empty.
 *        3) left has the smallest possible size.
 *    Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 *
 *    Example: 1) Input: [5,0,3,8,6] ; Output: 3
 *                Explanation: left = [5,0,3], right = [8,6]
 *             2) Input: [1,1,1,0,6,12] ; Output: 4
 *                Explanation: left = [1,1,1,0], right = [6,12]
 *
 *    Note:1) 2 <= A.length <= 30000
 *         2) 0 <= A[i] <= 10^6
 *         3) It is guaranteed there is at least one way to partition A as described.
 */
 
 class Solution {
    public int partitionDisjoint(int[] A) {
        int n = A.length;
        int[] left_max = new int[n];
        int[] right_min = new int[n];
        left_max[0] = A[0];
        for(int i = 1; i < n; i++){
            if(A[i] > left_max[i - 1]){
                left_max[i] = A[i];
            }
            else{
                left_max[i] = left_max[i - 1];
            }
        }
        right_min[n - 1] = A[n - 1];
        for(int i = n - 2; i >= 0; i--){
            if(A[i] < right_min[i + 1]){
                right_min[i] = A[i];
            }
            else{
                right_min[i] = right_min[i + 1];
            }
        }
        int res = 0;
        for(int i = 0; i < n - 1; i++){
            if(left_max[i] <= right_min[i + 1]){
                res = i + 1;
                break;
            }
        }
        return res;
    }
}
