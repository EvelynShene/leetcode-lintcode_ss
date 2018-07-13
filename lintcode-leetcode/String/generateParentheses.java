/** 427. Generate Parentheses(lintcode) / 22. Generate Parentheses(leetcode)
 *    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 *    Example: Given n = 3, a solution set is: "((()))", "(()())", "(())()", "()(())", "()()()"
 */
 
 //Method: DFS
 class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        if(n == 0){
            return list;
        }
        dfs(list, "" , 0 , 0, n);
        
        return list;
    }
    
    public void dfs(List<String> list, String s, int leftp, int rightp, int n){
        if(leftp < n){
            dfs(list, s + "(", leftp + 1, rightp, n);
        }
        if(rightp < n && leftp > rightp){
            dfs(list, s + ")", leftp, rightp + 1, n);
        }
        if(leftp == n && rightp == n){
            list.add(s);
            return;
        }
    }
}
