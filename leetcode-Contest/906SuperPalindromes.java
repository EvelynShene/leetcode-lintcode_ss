/** 906. Super Palindromes(leetcode)
 *      Let's say a positive integer is a superpalindrome if it is a palindrome, and it is also the square of a 
 *  palindrome.
 *      Now, given two positive integers L and R (represented as strings), return the number of superpalindromes 
 *  in the inclusive range [L, R].
 *
 *      Example: Input: L = "4", R = "1000"
 *               Output: 4
 *                  Explanation: 4, 9, 121, and 484 are superpalindromes.
 *                    Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
 *
 *      Note: 1) 1 <= len(L) <= 18
 *            2) 1 <= len(R) <= 18
 *            3) L and R are strings representing integers in the range [1, 10^18).
 *            4) int(L) <= int(R)
 */
 
 /** Method: Idea from leetcode [https://leetcode.com/articles/super-palindromes/]
  *      1) Say P = R^2, where P=R^2 is a superpalindrome.
  *      2) Because R is a palindrome, the first half of the digits in R determine R up to two possibilities.
  *   We can iterate through these digits: 
  *      3) let k be the first half of the digits in R. For example, if k = 1234, then R = 1234321 or R = 12344321
  *   Each possibility has either an odd or an even number of digits in R.
  *      4) Notice because 1 < P < 10^18, => R < (10^18)^(1/2) = 10^9 ; and k < (10^9)^(1/2)= k < 10^(4.5) 
  *     which let it to be a MAGIC, that is, our magic constant.
  *     【之所以 k < 10^(4.5)是因为 当 R < 10^9 时，R的一半数字就是 R/(10^(R的len的一半)); 例如 R = 1234321, k = R/1000】
  *      
  *   => For each 1 ≤ k < MAGIC, let's create the associated palindrome R,and check whether R^2 is a palindrome.
  */
  
  class Solution {
    public int superpalindromesInRange(String L, String R) {
        long l = Long.valueOf(L);
        long r = Long.valueOf(R);
        int magic = (int)Math.pow(10, 18 * 0.25);
        int c = 0;
        
        //Use k to construct odd length number
        for(int k = 1; k < magic; k++){
            StringBuilder sub = new StringBuilder(String.valueOf(k));
            for(int i = sub.length() - 2; i >= 0; i--){
                sub.append(sub.charAt(i));
            }

            long v = Long.valueOf(sub.toString());
            v *= v;
            if(v > r){
                break;
            }
            if(v >= l && isPalindrome(v)){
                c++;
            }
        }
        //Use k to construct even length number
        for(int k = 1; k < magic; k++){
            StringBuilder sub = new StringBuilder(String.valueOf(k));
            String num = sub.toString() + sub.reverse().toString();
            long v = Long.valueOf(num);
            v *= v;
            if(v > r){
                break;
            }
            if(v >= l && isPalindrome(v)){
                c++;
            }
        }
        return c;
    }
    
    public boolean isPalindrome(Long num){
        String s = String.valueOf(num);
        
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
