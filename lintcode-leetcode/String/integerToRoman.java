/** 418. Integer to Roman(lintcode) / 12. Integer to Roman(leetcode)
 *    Given an integer, convert it to a roman numeral. 
 *    The number is guaranteed to be within the range from 1 to 3999.
 *
 *    What is Roman Numeral? Ans: https://en.wikipedia.org/wiki/Roman_numerals
 *  
 *    Example: 
 *             4 -> IV
 *             12 -> XII
 *             21 -> XXI
 *             99 -> XCIX
 */
 
 //Method 1: 
 public String intToRoman(int n) {
    // write your code here
    String[][] romans = { {"","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}, 
                          {"","X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"},
                          {"","C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM", "M"} };

    int i = 0;
    StringBuilder s = new StringBuilder();
    while(n > 0 && i < 3){
        int mod = n % 10;
        s.insert(0,romans[i][mod]);
        i = i + 1;
        n = n / 10;
    }
    if(i == 3 && n > 0){
        for(int x = 0; x < n; x++){
            s.insert(0,'M');
        }
    }
    return s.toString();
 }
 // More concise code from leetcode discuss
 public static String intToRoman(int num) {
    String M[] = {"", "M", "MM", "MMM"};
    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
 }
  
 //Method 2: 
 public String intToRoman(int num) {
        char[] romans = {'I','V','X','L','C','D','M'};
        int base = 0;
        StringBuilder s = new StringBuilder();
        while(num > 0 && base < 7){
            int mod = num % 10;
            switch(mod){
                case 1:
                case 2:
                case 3:
                    for(int i = 0; i < mod; i++){
                        s.insert(0,romans[base]);
                    }
                    break;
                case 4: 
                    s.insert(0,""+ romans[base] + romans[base+1]);
                    break;
                case 5: 
                    s.insert(0,""+ romans[base+1]);
                    break;
                case 6:
                case 7:
                case 8:
                    for(int i = 0; i < mod - 5; i++){
                        s.insert(0,""+ romans[base]);
                    }
                    s.insert(0,""+ romans[base+1]);
                    break;
                case 9: 
                    s.insert(0,""+ romans[base] + romans[base+2]);
                default: break;      
            }
            base = base + 2;
            num = num / 10;
        }     
        return s.toString();
    }
