/** 916. Word Subsets(leetcode)
 *      We are given two arrays A and B of words.  Each word is a string of lowercase letters.
 *      Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  
 *  For example, "wrr" is a subset of "warrior", but is not a subset of "world".
 *      Now say a word a from A is universal if for every b in B, b is a subset of a. 
 *      Return a list of all universal words in A.  You can return the words in any order.
 *
 *      Example: 1) Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
 *                  Output: ["facebook","leetcode"]
 *               2) Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
 *                  Output: ["facebook","google","leetcode"]
 *
 *      Note: 1) 1 <= A.length, B.length <= 10000
 *            2) 1 <= A[i].length, B[i].length <= 10
 *            3) A[i] and B[i] consist only of lowercase letters.
 *            4) All words in A[i] are unique: there isn't i != j with A[i] == A[j].
 */
 
 class Solution {
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] letterB = new int[26];
        
        for(String b: B){
            int[] tmp = new int[26];
            for(int i = 0; i < b.length(); i++){
                tmp[b.charAt(i) - 'a']++;
            }
            for(int i = 0; i < 26; i++){
                if(tmp[i] != 0){
                    letterB[i] = Math.max(letterB[i], tmp[i]);
                }
            }
        }
        
        int[] letterA = new int[26];
        List<String> res = new ArrayList<>();
        for(String a: A){
            letterA = new int[26];
            for(int i = 0; i < a.length(); i++){
                letterA[a.charAt(i) - 'a']++;
            }
            int index = 0;
            for(; index < 26; index++){
                if(letterB[index] != 0 && letterB[index] > letterA[index]){
                    break;
                }
            }
            if(index == 26){
                res.add(a);
            }
        }
        return res;
    }
}
