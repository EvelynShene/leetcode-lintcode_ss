/** 419. Roman to Integer(lintcode) / 13. Roman to Integer(leetcode)
 *    Given a roman numeral, convert it to an integer. 
 *    The answer is guaranteed to be within the range from 1 to 3999.
 *
 *    What is Roman Numeral? Ans: https://en.wikipedia.org/wiki/Roman_numerals
 *
 *    Example: IV -> 4
 *             XII -> 12
 *             XXI -> 21
 *             XCIX -> 99
 */
 
 //Method 1: 
 public int romanToInt(String s) {
    if(s == null || s.length() == 0){
        return 0;
    }

    int x = 0;
    int num = 0;
    for(int i = 0; i < s.length(); i++){
        switch(s.charAt(i)){
            case 'I': 
                if(i + 1 < s.length() && (s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'X')){
                    num -= 1;
                }
                else{
                    num += 1;
                }
                break;
            case 'V': num += 5; break;
            case 'X': 
                if(i + 1 < s.length() && (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C')){
                    num -= 10;
                }
                else{
                    num += 10;
                }
                break;
            case 'L': num += 50; break;
            case 'C':
                if(i + 1 < s.length() && (s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M')){
                    num -= 100;
                }
                else{
                    num += 100;
                }
                break;
            case 'D': num += 500; break;
            case 'M': num += 1000; break;
            default: break;
        }
    }
    return num;
 }
 
 //Method 2:
 class Solution {
    public int romanToInt(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int num = 0;
        for(int i = 0; i < s.length(); i++){
            int x = toInt(s.charAt(i));
            if(i + 1 < s.length() && x < toInt(s.charAt(i+1))){
                num -= x;
            }
            else{
                num += x;
            }
        }
        
        return num;
    }
    public int toInt(char c){
        switch(c){
            case 'I': return 1; 
            case 'V': return 5; 
            case 'X': return 10; 
            case 'L': return 50; 
            case 'C': return 100; 
            case 'D': return 500;
            case 'M': return 1000; 
            default: break;
        }
        return 0;
    }
}
