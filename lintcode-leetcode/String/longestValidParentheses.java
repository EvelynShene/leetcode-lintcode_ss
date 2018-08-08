/** 32. Longest Valid Parentheses(leetcode)
 *     Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) 
 *  parentheses substring.
 *      
 *     Example: 1) Input: "(()" ; Output: 2
 *                  Explanation: The longest valid parentheses substring is "()"
 *              2) Input: ")()())" ; Output: 4
 *                  Explanation: The longest valid parentheses substring is "()()"
 */
 
 /* Method 1: Brute Force - O(n^3) time and O(n) space complexity [Time Limited Error]
  *     把所有的偶数长度的子字符串都检查一遍是不是有效括号格式；效率低，超时
  */
 class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.empty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }
    public int longestValidParentheses(String s) {
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j+=2) {
                if (isValid(s.substring(i, j))) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;
    }
}
 
/* Method 2: Use Stack - 栈保存index -  O(n) time and space complexity
 *    思路：栈顶永远记录当前有效括号的最开始位置，一开始stk栈顶为-1，处理有以')'开头的字符串
 *      eg1: ")()())":
 *        1) ')'使得-1弹出栈，栈顶此时为空了，更新i = 0 压入栈中，0为后续有效括号的起始位置的前一个位置。
 *        2) i = 2时，'('使得1弹栈，此时栈顶为0，说明(0,i]此段为有效空号对，更新maxlen；
 *        3) 同理，i = 4时，'('使得3弹栈，此时栈顶仍为0，说明(0,i]此段为有效空号对，更新maxlen；
 *      eg2: "()(()"
 *        1) i = 1时，'('使得0弹栈，此时栈顶为-1，说明(-1,i]此段为有效空号对，更新maxlen = 1 - (-1) = 2；
 *        2) i = 2时，压栈；i = 3时，压栈，此时栈内为[-1,2,3];
 *        3) i = 4时，'('使得3弹栈，此时栈顶为2，说明(2,i]此段为有效空号对，更新maxlen依然是2；
 *      eg3: "())()"
 *        1) i = 1时，'('使得0弹栈，此时栈顶为-1，说明(-1,i]此段为有效空号对，更新maxlen = 1 - (-1) = 2；
 *        2) i = 2时，')'使得-1弹出栈，栈顶此时为空了，更新i = 2 压入栈中，2为后续有效括号的起始位置的前一个位置。
 *        3) i = 4时，'('使得3弹栈，此时栈顶为2，说明(2,i]此段为有效空号对，更新maxlen依然是2；
 *
 */
public int longestValidParentheses(String s) {
    int n = s.length();
    Stack<Integer> stk = new Stack<Integer>();
    int maxlen = 0;
    stk.push(-1);
    for(int i = 0; i < n; i++){
        if(s.charAt(i) == '('){
            stk.push(i);
        }
        else{
            stk.pop();
            if(stk.isEmpty()){
                stk.push(i);
            }
            else{
                maxlen = Math.max(maxlen, i - stk.peek());
            }
        }
    }
    return maxlen;
}

/* Method 3: DP - O(n) time and space complexity
 *    思路：用dp[i]记录以s.charAt(i)结尾的最长有效括号串。因为有效括号串必须以"("结尾，所以只要更新s.charAt(i) == '('时的dp[i]。
 *        1）s.charAt(i-1) = '('，这样子串[i-1,i]可以合成一对有效括号串，如果前面dp[i-2]也是有效的，加上有效的长度
 *        2）s.charAt(i-1) = ')'，这时候找dp[i-1]，dp[i-1]是以s.charAt(i-1)结尾的最长有效括号串，假设dp[i-1] = k，那么：
 *      在s[0,i-1]中s.charAt(i-1)结尾的不符合有效子串的起始位置i - k - 1，同1）检查这个位置是否是'('，如果是，又能构成新的更长
 *      的有效括号串，同时考察dp[i-k-2]，是否因为位置i - k - 1从无效到有效而把两个有效括号串连接起来了。
 *
 *    Example："()(())"：
 *        dp[1] = 2; dp[4] = 2; dp[5] = 6
 *        dp[5] = 6 因为位置5可以与位置5-2-1 = 2 匹配，而位置2的匹配成功将dp[1]与后面的有效括号串也连接上了。
 */
public int longestValidParentheses(String s) {
    int n = s.length();
    int maxlen = 0;
    int[] dp = new int[n];
    for(int i = 0; i < n; i++){
        if(s.charAt(i) == ')'){
            if(i - 1 >= 0 && s.charAt(i - 1) == '('){
                dp[i] = 2;
                if(i - 2 >= 0){
                  dp[i] += dp[i - 2];
                }
                maxlen = Math.max(maxlen, dp[i]);
            }
            else if(i - 1 >= 0){
                int index = i - dp[i - 1] - 1;
                if(index >= 0 && s.charAt(index) == '('){
                    dp[i] = 2 + dp[i - 1];
                    if(index - 1 >= 0){
                        dp[i] += dp[index - 1];
                    }
                    maxlen = Math.max(maxlen, dp[i]);
                }
            }
        }
    }
    return maxlen;
}
