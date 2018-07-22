/** 1061. Parse Lisp Expression(lintcode) / 736. Parse Lisp Expression(leetcode)
 *     You are given a string expression representing a Lisp-like expression to return the integer value of.
 *     The syntax for these expressions is given as follows.
 *     1) An expression is either an integer, a let-expression, an add-expression, a mult-expression, or 
 *  an assigned variable. Expressions always evaluate to a single integer. 
 *     2) (An integer could be positive or negative.)
 *     3) A let-expression takes the form (let v1 e1 v2 e2 ... vn en expr), where let is always the string "let",
 *  then there are 1 or more pairs of alternating variables and expressions, meaning that the first variable v1 
 *  is assigned the value of the expression e1, the second variable v2 is assigned the value of the expression e2,
 *  and so on sequentially; and then the value of this let-expression is the value of the expression expr.
 *     4) An add-expression takes the form (add e1 e2) where add is always the string "add", there are always 
 *  two expressions e1, e2, and this expression evaluates to the addition of the evaluation of e1 and 
 *  the evaluation of e2.
 *     5) A mult-expression takes the form (mult e1 e2) where mult is always the string "mult", there are always 
 *  two expressions e1, e2, and this expression evaluates to the multiplication of the evaluation of e1 and
 *  the evaluation of e2.
 *     6) For the purposes of this question, we will use a smaller subset of variable names. 
 *  A variable starts with a lowercase letter, then zero or more lowercase letters or digits. 
 *  Additionally for your convenience, the names "add", "let", or "mult" are protected and will never be used 
 *  as variable names.
 *     7) Finally, there is the concept of scope. When an expression of a variable name is evaluated, 
 *  within the context of that evaluation, the innermost scope (in terms of parentheses) is checked first 
 *  for the value of that variable, and then outer scopes are checked sequentially. 
 *  It is guaranteed that every expression is legal. Please see the examples for more details on scope.
 *
 *     Note: 1) The given string expression is well formatted: There are no leading or trailing spaces, 
 *            there is only a single space separating different components of the string, and no space between 
 *            adjacent parentheses. The expression is guaranteed to be legal and evaluate to an integer.
 *           2) The length of expression is at most 2000. 
 *            (It is also non-empty, as that would not be a legal expression.)
 *           3) The answer and all intermediate calculations of that answer are guaranteed to fit in a 32-bit integer.
 *
 *     Example: https://leetcode.com/problems/parse-lisp-expression/description/
 */
 
 //Method: 递归 - Recursive Parsing 
 class Solution {
    ArrayList<Map<String, Integer>> var_scope;
    public Solution(){
        var_scope = new ArrayList<Map<String, Integer>>();
    }
    
    public int evaluate(String expression) {
        var_scope.add(new HashMap());
        int res = evaluateExp(expression);
        var_scope.remove(var_scope.size()-1);
        return res;
    }
    public int evaluateExp(String e){
        if(e.charAt(0) != '('){
            if(Character.isDigit(e.charAt(0)) || e.charAt(0) == '-'){//e is a number
                return Integer.valueOf(e);
            }
            else{ // e is variable, need to get its value from scope
                for(int i = var_scope.size() - 1; i >= 0; i--){
                    if(var_scope.get(i).containsKey(e)){
                        return var_scope.get(i).get(e);
                    }
                } 
            }
        }
        
        List<String> sub_expression = null;
        if(e.charAt(1) == 'm'){
            sub_expression = parseExp(e.substring(6, e.length()-1));
            return evaluate(sub_expression.get(0)) * evaluate(sub_expression.get(1));
        }
        else{
            sub_expression = parseExp(e.substring(5,e.length()-1));
            if(e.charAt(1) == 'a'){
                return evaluate(sub_expression.get(0)) + evaluate(sub_expression.get(1));
            }
            for(int i = 0; i < sub_expression.size(); i = i + 2){
                int n = var_scope.size();
                if(i + 1 < sub_expression.size()){
                    int v = evaluate(sub_expression.get(i+1));
                    var_scope.get(n-1).put(sub_expression.get(i),v);
                }
            }
            return evaluate(sub_expression.get(sub_expression.size()-1));
        }
    }
    
    public List<String> parseExp(String e){
        int left_p = 0;
        List<String> list = new ArrayList<String>();
        StringBuilder seg = new StringBuilder();
        for(int i = 0; i < e.length(); i++){
            if(e.charAt(i) == '('){
                left_p++;
            }
            if(e.charAt(i) == ')'){
                left_p--;
            }
            if(e.charAt(i) == ' ' && left_p == 0){
                list.add(seg.toString());
                seg = new StringBuilder();
                continue;
            }
            seg.append(""+e.charAt(i));
        }
        if(!seg.toString().equals(""))
            list.add(seg.toString());
        return list;
    }
}
