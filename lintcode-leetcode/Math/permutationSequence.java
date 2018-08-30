/** 60.Permutation Sequence(leetcode) / 388. Permutation Sequence(lintcode)
 *      The set [1,2,3,...,n] contains a total of n! unique permutations.
 *      By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 *                 "123"
 *                 "132"
 *                 "213"
 *                 "231"
 *                 "312"
 *                 "321"
 *      Given n and k, return the kth permutation sequence.
 *
 *      Note: 1) Given n will be between 1 and 9 inclusive.
 *            2) Given k will be between 1 and n! inclusive.
 *
 *      Example: 1) Input: n = 3, k = 3
 *                  Output: "213"
 *               2) Input: n = 4, k = 9
 *                  Output: "2314"
 */
 
 //My Method: Use Math - O(n^2) time and O(n) space complexity
 class Solution {
    public String getPermutation(int n, int k) {
        int[] perm = new int[n + 1]; // perm[n] stores n!
        perm[0] = 1;
        List<Integer> nums = new ArrayList<Integer>();
        for(int i = 1; i <= n; i++){
            perm[i] = perm[i-1] * i;
            nums.add(i);
        }     
        StringBuilder s = new StringBuilder();
        
        while(nums.size() != 0){
            int p = k / perm[n - 1];
            int q = k % perm[n - 1];
            if(q == 0){
                s.append("" + nums.get(p - 1));
                nums.remove(p - 1);
                for(int i = nums.size() - 1; i >= 0; i--){
                    s.append("" + nums.get(i));
                }
                return s.toString();
            }
            s.append("" + nums.get(p));
            nums.remove(p);
            n--;
            k = q;
        }
        return s.toString();
    }
}
