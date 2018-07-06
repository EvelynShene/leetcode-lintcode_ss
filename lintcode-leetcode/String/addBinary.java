/** 408. Add Binary(lintcode) / 67. Add Binary (leetcode)
 *    Given two binary strings, return their sum (also a binary string).
 *    (leetcode: The input strings are both non-empty and contains only characters 1 or 0.)
 *    
 *    Example: Input: a = "1010", b = "1011" ; Output: "10101"
 */
 
 public String addBinary(String a, String b) {
    // write your code here
    if(a == null || b == null){
        return null;
    }
    if(a == "" || a.length() == 0){
        return b;
    }
    if(b == "" || a.length() == 0){
        return a;
    }
    int i = a.length() - 1;
    int j = b.length() - 1;
    // int len = Math.max(i,j);
    StringBuilder str = new StringBuilder();
    int c = 0;
    while(i >= 0 && j >= 0){
        if(a.charAt(i) == '1' && b.charAt(j) == '1'){
            str.append(c);
            c = 1;
        }
        else{
            if(c == 1){
                if(a.charAt(i) == '0' && b.charAt(j) == '0'){
                    c = 0;
                    str.append('1');
                }
                else{
                    str.append('0');
                }
            }
            else{
                if(a.charAt(i) == '1' || b.charAt(j) == '1'){
                    str.append('1');
                }
                else{
                    str.append('0');
                }
            }
        }
        j--;
        i--;
    }
    while(i >= 0){
        if(c == 0){
            str.append(a.charAt(i));
        }
        else{ // c == 1
            if(a.charAt(i) == '1'){
                str.append('0');
            }
            else{
                str.append('1');
                c = 0;
            }
        }
        i--;
    }
    while(j >=0){
        if(c == 0){
            str.append(b.charAt(j));
        }
        else{ // c == 1
            if(b.charAt(j) == '1'){
                str.append('0');
            }
            else{
                str.append('1');
                c = 0;
            }
        }
        j--;
    }
    if(c == 1){
        str.append('1');
    }
    return str.reverse().toString();
 }
