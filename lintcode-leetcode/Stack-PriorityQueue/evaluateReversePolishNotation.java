/** 150. Evaluate Reverse Polish Notation(leetcode) / 424. Evaluate Reverse Polish Notation(lintcode)
 *    Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *    Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 *    Note: 1) Division between two integers should truncate toward zero.
 *          2) The given RPN expression is always valid. That means the expression would always evaluate to a 
 *          result and there won't be any divide by zero operation.
 *
 *    Example: 1) Input: ["2", "1", "+", "3", "*"] ; Output: 9
 *                Explanation: ((2 + 1) * 3) = 9
 *             2) Input: ["4", "13", "5", "/", "+"] ; Output: 6
 *                Explanation: (4 + (13 / 5)) = 6
 */
 
 //My Method: Use Stack
 class Solution {
    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length == 0){
            return 0;
        }
        
        Stack<Integer> stk = new Stack<Integer>(); 
        for(int i = 0; i < tokens.length; i++){
            String s = tokens[i];
            int op = isOperator(s); 
            if(op == 0){
                stk.push(Integer.valueOf(s));
            }
            else{
                int n1 = stk.pop();
                switch(op){
                    case 1: n1 += stk.pop(); break;
                    case 2: n1 = stk.pop() - n1; break;
                    case 3: n1 *= stk.pop(); break;
                    case 4: n1 = stk.pop() / n1; break;
                    default: break;
                }
                stk.push(n1);
            }
        }
        if(!stk.isEmpty()){
            return stk.pop();
        }
        return 0;
    }
    
    public int isOperator(String s){
        if(s.equals("+")){
            return 1;
        }
        else if(s.equals("-")){
            return 2;
        }
        else if(s.equals("*")){
            return 3;
        }
        else if(s.equals("/")){
            return 4;
        }
        else{
            return 0;
        }
    }
}
