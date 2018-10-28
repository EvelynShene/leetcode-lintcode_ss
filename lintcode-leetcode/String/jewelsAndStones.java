/** 771. Jewels And Stones(leetcode) / 1038. Jewels And Stones(lintcode)
 *      You're given strings J representing the types of stones that are jewels, and S representing the stones 
 *  you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have
 *  are also jewels.
 *      The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case 
 *  sensitive, so "a" is considered a different type of stone from "A".
 *
 *      Example: Input: J = "aA", S = "aAAbbbb" ; Output: 3
 *               Input: J = "z", S = "ZZ" ; Output: 0
 *
 *      Note: 1) S and J will consist of letters and have length at most 50.
 *            2) The characters in J are distinct.
 */
 
 //Method: Use HashSet
 class Solution {
    public int numJewelsInStones(String J, String S) {
        if(J == null || J.length() == 0 || S == null || S.length() == 0) {
            return 0;
        }
        Set<Character> jewels = new HashSet<Character>();
        for(int i = 0; i < J.length(); i++) {
            jewels.add(J.charAt(i));
        }
        int count = 0;
        for(int i = 0; i < S.length(); i++){
            if(jewels.contains(S.charAt(i))){
                count++;
            }
        }
        return count;
    }
}
