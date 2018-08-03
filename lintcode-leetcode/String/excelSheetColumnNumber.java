/** 171. Excel Sheet Column Number(leetcode) / 1348. Excel Sheet Column Number(lintcode)
 *    Given a column title as appear in an Excel sheet, return its corresponding column number.
 *       For example:
 *           A -> 1
 *           B -> 2
 *           C -> 3
 *           ...
 *           Z -> 26
 *           AA -> 27
 *           AB -> 28 
 *           ...
 */
 
 //My Method: 
 public int titleToNumber(String s) {
    if(s == null || s.length() == 0)
        return 0;
    int n = s.length();
    int num = 0;
    for(int i = 0; i < s.length(); i++){
        int digit = s.charAt(i) - 'A' + 1;
        num = num * 26 + digit;
    }
    return num;
 }
