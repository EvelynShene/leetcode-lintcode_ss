/** 168. Excel Sheet Column Title(leetcode) / 1350. Excel Sheet Column Title(lintcode)
 *      Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 *
 *      For example:
 *           1 -> A
 *           2 -> B
 *           3 -> C
 *           ...
 *           26 -> Z
 *           27 -> AA
 *           28 -> AB 
 *           ...
 *
 *      Example: Input: 701 ; Output: "ZY"
 */ 
 
 //My Method: 
 public String convertToTitle(int n) {
    StringBuilder str = new StringBuilder();
    if(n % 26 == 0){
        while(n > 26){
            char ch = (char)('A' + (n / 26) - 2);
            str.append("" + ch);
            n = n % 26;
        }
        str.append("Z");
    }
    else{
        while(n > 26){
            char ch = (char)('A' + (n % 26) - 1);
            str.insert(0,"" + ch);
            n = n / 26;
        }
        str.insert(0,"" + (char)('A' + n - 1));
    }
    return str.toString();
 }
 
 //Method 2: 
 public String convertToTitle(int n) {
    StringBuilder str = new StringBuilder();
    while(n>0){
        n--;
        str.insert(0, (char)('A' + n % 26));
        n /= 26;
    }
    return str.toString();
 }
