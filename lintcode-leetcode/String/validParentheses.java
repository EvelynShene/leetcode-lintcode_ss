/** 423. Valid Parentheses(lintcode) / 20. Valid Parentheses(leetcode)
 *    Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
 *  determine if the input string is valid.
 *
 *    An input string is valid if:
 *        1) Open brackets must be closed by the same type of brackets.
 *        2) Open brackets must be closed in the correct order.
 *     Note that an empty string is also considered valid.
 *    
 *    Example: The brackets must close in the correct order, 
 *                  "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 *   
 */
 
 //Method: Use Stack
 public boolean isValid(String s) {
    if(s.length() == 0){
        return true;
    }
    Stack<Character> stk = new Stack<Character>();

    for(int i = 0; i < s.length(); i++){
        switch(s.charAt(i)){
            case '(': 
            case '[':
            case '{': 
                stk.push(s.charAt(i));
                break;
            case ')': 
                if(stk.isEmpty() || stk.pop() != '('){
                    return false;
                }
                break;
            case ']': 
                if(stk.isEmpty() || stk.pop() != '['){
                    return false;
                }
                break;
            case '}': 
                if(stk.isEmpty() || stk.pop() != '{'){
                    return false;
                }
                break;  
        }
    }
    if(stk.isEmpty()){
        return true;
    }
    return false;
 }
