/** 980. Basic Calculator II(lintcode) / 227. Basic Calculator II(leetcode)
 *    Implement a basic calculator to evaluate a simple expression string.
 *    The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
 *  The integer division should truncate toward zero.
 *    You may assume that the given expression is always valid.
 *
 *    Note: Do not use the eval built-in library function.
 *
 *    Example: 1) "3+2*2" = 7
 *             2) " 3/2 " = 1
 *             3) " 3+5 / 2 " = 5
 */
 
 //My Method:  Use stack - 时间复杂度O(N), 空间复杂度O(N)
 public int calculate(String s) {
    if(s == null || s.length() == 0){
        return 0;
    }
    Stack<String> stk = new Stack<String>();
    for(int i = 0; i < s.length(); i++){
        if(s.charAt(i) == ' ')
            continue;
        else if(s.charAt(i) == '+' || s.charAt(i) == '-'){
            if(stk.size() > 2){ //Avoid memory limited error
                int b = Integer.valueOf(stk.pop());
                String op = stk.pop();
                int a = Integer.valueOf(stk.pop());
                if(op.equals("+")){
                    a += b;
                }
                else{
                    a -= b;
                }
                stk.push("" + a);
            }
            stk.push(""+s.charAt(i));
        }
        else if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
            int start = i;
            while(i+1 < s.length() && s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9'){
                i++;
            }
            stk.push(s.substring(start, i+1));
        }
        else if(s.charAt(i) == '*' || s.charAt(i) == '/'){
            boolean product = false;
          if(s.charAt(i) == '*'){
            product = true;
          }
          while(i+1 < s.length() && s.charAt(i+1) == ' ' ){
            i++;
          }
            int start = i+1;
            while(i+1 < s.length() && s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9'){
                i++;
            }
            int b = Integer.valueOf(s.substring(start, i + 1));
            int a = Integer.valueOf(stk.pop());
            if(product){
                a *= b;
            }
            else{
                a /= b;
            }
            stk.push(""+ a);
        }
    }
    int res = 0;
    while(!stk.isEmpty()){
      int n = Integer.valueOf(stk.pop());
      if(!stk.isEmpty()){
        if(stk.peek().equals("-")){
            n = -n;
          }
          stk.pop();
        }
      res += n;
    }
    return res;
 }
 
 /* Method 2: [Idea from leetcode discuss]
  *     如果遇到‘+’，那么把+num入栈。如果是‘-’,那么把‘-num'入栈。
  *     如果是’/'或‘*’,那么把栈顶元素弹出除以或者乘以当前的num然后再次入栈。
  */
 public int calculate(String s) {
    if(s == null || s.length() == 0){
        return 0;
    }
    Stack<Integer> stk = new Stack<Integer>();
    char pre_op = '+';
    int num = 0;
    for(int i = 0; i < s.length(); i++){
        if(Character.isDigit(s.charAt(i))){
            num = num*10 + (s.charAt(i) - '0');
        }
        if((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == s.length() - 1){
            switch(pre_op){
                case '+':
                    stk.push(num); 
                    break;
                case '-':
                    stk.push(-num); 
                    break;
                case '*':
                    stk.push(num * stk.pop()); 
                    break;
                case '/':
                    stk.push(stk.pop() / num);
                    break;
            }
            pre_op = s.charAt(i);
            num = 0;
        }

    }  
    int res = 0;
    while(!stk.isEmpty()){
      res += stk.pop();
    }
    return res;
}
