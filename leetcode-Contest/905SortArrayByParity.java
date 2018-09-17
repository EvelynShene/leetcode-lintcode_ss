/** 905. Sort Array By Parity(leetcode)
 *      Given an array A of non-negative integers, return an array consisting of all the even elements of A,
 *  followed by all the odd elements of A.
 *      You may return any answer array that satisfies this condition.
 *
 *      Example: Input: [3,1,2,4]
 *               Output: [2,4,3,1]
 *                    The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 *
 *      Note: 1) 1 <= A.length <= 5000
 *            2) 0 <= A[i] <= 5000
 */
 
 public int[] sortArrayByParity(int[] A) {
    if(A == null || A.length < 2){
        return A;
    }
    int[] res = new int[A.length];
    int even = 0;
    int odd = A.length - 1;
    for(int i = 0; i < A.length; i++){
        if((A[i] & 1) == 1){
            res[odd] = A[i];
            odd--;
        }
        else{
            res[even] = A[i];
            even++;
        }
    }
    return res;
}
