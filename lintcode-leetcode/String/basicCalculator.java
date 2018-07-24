/** 978. Basic Calculator(lintcode)/ 224. Basic Calculator(leetcode)
 *      Implement a basic calculator to evaluate a simple expression string.
 *    The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, 
 *    non-negative integers and empty spaces .
 *
 *      Note: 1) You may assume that the given expression is always valid.
 *            2) Do not use the eval built-in library function.
 *
 *      Example: "1 + 1" = 2
 *               " 2-1 + 2 " = 3
 *               "(1+(4+5+2)-3)+(6+8)" = 23
 */
 
 //My Method: 遇到 ( 时，找到整个括号内容(计为token)，然后递归计算括号内整体的值
 class Solution {
    public int calculate(String s) {
        s = s.trim();
        if(s.length() == 0){
            return 0;
        }
        
        int pre_op = 1;// 1 - "+"; -1 - "-"
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            if(Character.isDigit(s.charAt(i))){
                int num = s.charAt(i) - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i+1))){
                    i++;
                    num = num*10 + s.charAt(i) - '0';
                }
                res = res + pre_op*num;
            }
            else if(s.charAt(i) == '-'){
                pre_op = -1;
            }
            else if(s.charAt(i) == '+'){
                pre_op = 1;
            }
            else if(s.charAt(i) == '('){
                int left_p = 1;
                int j = i + 1;
                for(; j < s.length(); j++){
                    if(s.charAt(j) == '('){
                        left_p++;
                    }
                    else if(s.charAt(j) == ')'){
                        left_p--;
                    }
                    if(left_p == 0)
                        break;
                }
                res = res + pre_op*(calculate(s.substring(i+1,j)));
                i = j;
            }
        }
        return res;
    }
}
