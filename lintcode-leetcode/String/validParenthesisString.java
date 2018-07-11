/** 1089. Valid Parenthesis String(lintcode) / 678. Valid Parenthesis String(leetcode)
 *    Given a string containing only three types of characters: '(', ')' and '*', 
 *  write a function to check whether this string is valid. We define the validity of a string by these rules:
 *      1) Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 *      2) Any right parenthesis ')' must have a corresponding left parenthesis '('.
 *      3) Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 *      4) '*' could be treated as a single right parenthesis ')' 
 *                              or a single left parenthesis '(' or an empty string.
 *      5) An empty string is also valid.
 *  
 *    Note: The string size will be in the range [1, 100].
 *    
 *    Example: 1) Input: "()"   ; Output: True
 *             2) Input: "(*)"  ; Output: True
 *             3) Input: "(*))" ; Output: True
 */
  
 /* Method:  O(n) time complextiy
  *     '*'具有灵活性，既可以当作左括号'('也可以当作右括号')'，所以匹配过程中，应该尽量减少剩余未匹配的左括号和右括号。
  *      如果最后左括号'('和右括号')'都匹配完，则字符串是有效的，返回true；否则返回false。
  *      以下代码思路：1）left_p 存储未匹配的左括号的个数，left_start存储未匹配的左括号和星号的总个数。
  *                  2）遍历过程中遇到 '*' 和 ')' 都先去和未匹配的左括号'('匹配；若是遇到右括号，左括号以匹配完，则用星号匹配
  *                     没有星号和左括号匹配右括号时，直接返回false。
  */
 
 public boolean checkValidString(String s) {
    // Write your code here
    if(s == null){
        return false;
    }
    if(s.length() == 0){
        return true;
    }
    int left_p = 0;
    int left_start = 0;
    for(int i = 0; i < s.length(); i++){
        if(s.charAt(i) == '('){
            left_p++;
            left_start++;
        }
        else if(s.charAt(i) == '*'){
            if(left_p > 0){//有左括号，* 匹配左括号
                left_p--;
            }
            left_start++;
        }
        else{ // s.charAt(i) == ')'
            if(left_p > 0){//有左括号，( 匹配左括号
                left_p--;
            }
            left_start--;
            if(left_start < 0){ // 没有星号和左括号匹配右括号,匹配失败
                return false;
            }
        }
    }
    if(left_p > 0) //还有未匹配的左括号，匹配失败
        return false;
    return true;
 }
