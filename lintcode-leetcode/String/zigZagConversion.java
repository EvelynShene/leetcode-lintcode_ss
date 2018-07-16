/** 1363. ZigZag Conversion(lintcode)/ 6. ZigZag Conversion(leetcode)
 *      The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 *   (you may want to display this pattern in a fixed font for better legibility)
 *              P   A   H   N
 *              A P L S I I G
 *              Y   I   R
 *      And then read line by line: "PAHNAPLSIIGYIR"
 *      Write the code that will take a string and make this conversion given a number of rows:
 *              string convert(string s, int numRows);
 *
 *      Example: 1) Given s = "PAYPALISHIRING", numRows = 3 ; Output: "PAHNAPLSIIGYIR".
 *               2) Input: s = "PAYPALISHIRING", numRows = 4 ; Output: "PINALSIGYAHRPI"
 */
 
 //My Method: O(n) time and space complexity
 public String convert(String s, int numRows) {
    if(s == null || s.length() == 0 || numRows < 2){
        return s;
    }
    String[] zz = new String[numRows]; // initally zz[i] == null, use zz[i] += "abs" becomes "nullabc"
    for(int i = 0; i < zz.length; i++){
        zz[i] = "";
    }
    int index = 0;
    boolean des = false;
    for(int i = 0; i < s.length(); i++){
        zz[index] += s.charAt(i);
        if(!des){
           index++; 
        }
        else{
            index--;
        }
        if(index == numRows - 1){
            des = true;
        }
        else if(index == 0){
            des = false;
        }
    }
    StringBuilder res = new StringBuilder();
    for(int i = 0; i < zz.length; i++){
        res.append(zz[i]);
    }
    return res.toString();
 }
