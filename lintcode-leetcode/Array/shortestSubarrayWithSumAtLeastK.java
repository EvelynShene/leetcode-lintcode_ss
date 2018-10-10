/** 1616. Shortest subarray II(lintcode-ladder) / 862. Shortest Subarray with Sum at Least K(leetcode)
 *    Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 *    If there is no non-empty subarray with sum at least K, return -1.
 *
 *    Example: Input: A = [2,-1,2], K = 3 ; Output: 3
 *
 *    Note: 1) 1 <= A.length <= 50000
 *          2) -10 ^ 5 <= A[i] <= 10 ^ 5
 *          3) 1 <= K <= 10 ^ 9
 */
 
 //Method:
 class Solution {
    public int shortestSubarray(int[] A, int K) {
        int res = -1;
        int n = A.length;
        int[] sum = new int[n + 1]; // sum[i] = A[0] + A[1] + .. + A[i - 1]
        for(int i = 1; i <= n; i++){
            sum[i] = A[i - 1] + sum[i - 1];
        }
        /*  traverse array, for each position y, find the largest position x (x < y) for y to makes sum[y] - sum[x] >= k
         *    1) if x1 < x2 && sum[x1] >= sum[x2] && sum[y] - sum[x1] >= k, must have sum[y] - sum[x2] >= k, 
         *          so x2 should be a candidate as largest position for y
         *    2) when find the largest position x for y, no need to check x and elements before x:
         *        eg: we know check element A[y2] (y2 > y), if its candidate x2 <= x, we must have |y - x| < |y2 - x2|,
         *            it will not be the smallest subarray with sum at least K
         */
        Deque<Integer> deq = new LinkedList<>();
        int smallest = -1;
        for(int x = 0; x <= n; x++){
            // x1 = deq.getLast() , x2 = x , if sum[x2] <= sum[x1], the candidate must be x2 or position larger than x2
            while(!deq.isEmpty() && sum[x] <= sum[deq.getLast()]){ 
                deq.removeLast();
            }
            
            // y' = x, x' = deq.getFirst(); if sum[y'] - sum[x'], update smallest 
            while(!deq.isEmpty() && sum[x] - sum[deq.getFirst()] >= K){
                //after update smallest, we can remove x'/deq.getFirst(), above explaination 2)
                if(smallest == -1){
                    smallest = x - deq.removeFirst();
                }
                else{
                    smallest = Math.min(smallest, x - deq.removeFirst());
                }
            }
            deq.addLast(x);
        }
        return smallest;
    }
}
