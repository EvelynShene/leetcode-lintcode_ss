/** 1305. Integer to English Words(lintcode-easy) / 273. Integer to English Words(leetcode)
 *    Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
 *
 *    Example: 123 -> "One Hundred Twenty Three"
 *             12345 -> "Twelve Thousand Three Hundred Forty Five"
 *             1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *             1234567891 -> "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
 
 //Method: 每三位一转换（即个、十、百位），然后加上对应的单位。要注意：1000,0，1000,010这样的特殊例子
 class Solution {
    public String numberToWords(int num) {
        if(num == 0){
            return "Zero";
        }
        String[] units = {"Thousand","Million","Billion"};
        String s = String.valueOf(num);
        int len = s.length();
        String words = dealHundred(num % 1000);
        num = num / 1000;
        int u = 0;
        while(num > 0){
            if(num % 1000 != 0){
                if(words.equals("")){
                    words = dealHundred(num % 1000) +" "+ units[u];
                }
                else{
                    words = dealHundred(num % 1000) +" "+ units[u] + " " + words;
                }   
            }
            u = (u + 1) % 3;
            num = num / 1000;
        }
        
        return words;
    }
    
    public String dealHundred(int num){
        String[] numbers = {"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten", "Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
        String[] tens = {"","Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
        StringBuilder s = new StringBuilder();
        int ones = num % 10;
        int twos = num % 100;
        int hundreds = num / 100;
        if(hundreds > 0){
            s.append(numbers[hundreds]+" Hundred");
            if(twos != 0){
                s.append(" ");
            }
        }
        
        if(twos < 20){
            s.append(numbers[twos]);
        }
        else if(ones != 0){
            s.append(tens[twos/10] + " " + numbers[ones]);
        }
        else{
            s.append(tens[twos/10]);
        }
        return s.toString();
    }
}
