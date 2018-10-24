/** 301. Remove Invalid Parentheses(leetcode) / 780. Remove Invalid Parentheses(lintcode)
 *      Remove the minimum number of invalid parentheses in order to make the input string valid. Return all 
 *  possible results.
 *
 *      Note: The input string may contain letters other than the parentheses ( and ).
 *
 *      Example: 1) Input: "()())()" ; Output: ["()()()", "(())()"]
 *               2) Input: "(a)())()" ; Output: ["(a)()()", "(a())()"]
 *               3) Input: ")(" ; Output: [""]
 *
 *      Hint:
 *        1. Since we don't know which of the brackets can possibly be removed, we try out all the options! 
 *        2. We can use recursion to try out all possibilities for the given expression. 
 *      For each of the brackets, we have 2 options:
 *          1) We keep the bracket and add it to the expression that we are building on the fly during recursion.
 *          2) OR, we can discard the bracket and move on.
 *        3. For every left parenthesis, we should have a corresponding right parenthesis. We can make use of 
 *      two counters which keep track of misplaced left and right parenthesis and in one iteration we can find 
 *      out these two values.
 *                    0 1 2 3 4 5 6 7
 *                    ( ) ) ) ( ( ( )  
 *                    i = 0, left = 1, right = 0
 *                    i = 1, left = 0, right = 0
 *                    i = 2, left = 0, right = 1
 *                    i = 3, left = 0, right = 2
 *                    i = 4, left = 1, right = 2
 *                    i = 5, left = 2, right = 2
 *                    i = 6, left = 3, right = 2
 *                    i = 7, left = 2, right = 2
 *       We have 2 misplaced left and 2 misplaced right parentheses.
 *         4. We found out that the exact number of left and right parenthesis that has to be removed to get a 
 *       valid expression. So, e.g. in a 1000 parentheses string, if there are 2 misplaced left and 2 misplaced 
 *       right parentheses, after we are done discarding 2 left and 2 right parentheses, we will have only one 
 *       option per remaining character in the expression i.e. to consider them. We can't discard them.
 */
 
 // My Method:
 class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<String>();
        if(checkValid(s)){
            res.add(s);
            return res;
        }
        
        int left = 0;
        int right = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != '(' && s.charAt(i) != ')'){
                continue;
            }
            if(s.charAt(i) == '('){
                left++;
            }
            else if(s.charAt(i) == ')'){
                if(left != 0){
                    left--;
                }
                else{
                    right++;
                }
            }
        }
        Set<String> set = new HashSet<String>();
        Set<String> checked = new HashSet<String>();
        removeInvalid(s, left, right, set, checked);
        Iterator<String> itr = set.iterator();
        while(itr.hasNext()){
            res.add(itr.next());
        }
        return res;
    }
    
    public boolean checkValid(String s){
        int left = 0; 
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != '(' && s.charAt(i) != ')'){
                continue;
            }
            if(s.charAt(i) == '('){
                left++;
            }
            else if(s.charAt(i) == ')'){
                if(left == 0){
                    return false;
                }
                left--;
            }
        }
        return left == 0;
    }
    
    public void removeInvalid(String s, int left, int right, Set<String> res, Set<String> checked){
        if(left == 0 && right == 0){
            if(checkValid(s)){
                res.add(s);
            }
            return;
        }
        
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != '(' && s.charAt(i) != ')'){
                continue;
            }
            if(s.charAt(i) == '(' && left > 0){
                String substr = s.substring(0, i) + s.substring(i + 1);
                if(checked.contains(substr)){
                    continue;
                }
                checked.add(substr);
                removeInvalid(substr, left - 1, right, res, checked);
            }
            else if(s.charAt(i) == ')' && right > 0){
                String substr = s.substring(0, i) + s.substring(i + 1);
                if(checked.contains(substr)){
                    continue;
                }
                checked.add(substr);
                removeInvalid(substr, left, right - 1, res, checked);
            }
        }
    }
}
