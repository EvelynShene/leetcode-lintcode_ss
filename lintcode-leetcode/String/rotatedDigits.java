/** 1028. Rotated Digits(lintcode)/ 788. Rotated Digits (leetcode)
 *      X is a good number if after rotating each digit individually by 180 degrees, 
 *  we get a valid number that is different from X. Each digit must be rotated - we cannot choose to leave it alone.
 *      A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 
 *  2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number 
 *  and become invalid.
 *      Now given a positive number N, how many numbers X from 1 to N are good?
 *
 *      Note: N will be in range [1, 10000].
 *
 *      Example: Input: 10 ; Output: 4
 *        Explanation: There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
 *                      Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
 */ 
 
 //Method 1:
 public int rotatedDigits(int N) {
    // write your code here
    int count = 0;
    for(int i = 0; i <= N; i++){
        String s = String.valueOf(i);
        if(!s.contains("3") && !s.contains("4") && !s.contains("7")){
            StringBuilder s2 = new StringBuilder();
            for(int j = 0; j < s.length(); j++){
                switch(s.charAt(j)){
                    case '2': s2.append('5');break;
                    case '5': s2.append('2');break;
                    case '6': s2.append('9');break;
                    case '9': s2.append('6');break;
                    default: s2.append(s.charAt(j));break;
                }
            }
            if(!s.equals(s2.toString())){
                count++;
            }
        }
    }
    return count;
 }

//Method 2: [From leetcode] - O(n)
class Solution {
    public int rotatedDigits(int N) {
        int count = 0;
        for (int i = 1; i <= N; i ++) {
            if (isValid(i)) count ++;
        }
        return count;
    }
    
    public boolean isValid(int N) {
        /*
         Valid if N contains ATLEAST ONE 2, 5, 6, 9
         AND NO 3, 4 or 7s
         */
        boolean validFound = false;
        while (N > 0) {
            if (N % 10 == 2) validFound = true;
            if (N % 10 == 5) validFound = true;
            if (N % 10 == 6) validFound = true;
            if (N % 10 == 9) validFound = true;
            if (N % 10 == 3) return false;
            if (N % 10 == 4) return false;
            if (N % 10 == 7) return false;
            N = N / 10;
        }
        return validFound;
    }
}
