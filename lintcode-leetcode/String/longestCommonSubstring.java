/** 79. Longest Common Substring(lintcode)
 *    Given two strings, find the longest common substring. Return the length of it.
 *
 *    Note: The characters in substring should occur continuously in original string. This is different with subsequence.
 *
 *    Example: Given A = "ABCD", B = "CBCE", return 2.
 */
 
 //Method: O(nm) time 
 public int longestCommonSubstring(String A, String B) {
    // write your code here
    if(A == null || B == null || A.length() == 0 || B.length() == 0){
        return 0;
    }
    if(A.length() < B.length()){
        return longestCommonSubstring(B, A); // always make A.length() > B.length()
    }
    int common = 0;
    for(int i = 0; i < B.length(); i++){
        int j = 1;
        while(i+j <= B.length() && A.contains(B.substring(i,i+j))){
            j++;
        }
        common = Math.max(common,j - 1);
    }
    return common;
 }
