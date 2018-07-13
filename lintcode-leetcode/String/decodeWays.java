/** 512. Decode Ways(lintcode) / 91. Decode Ways(leetcode)
 *    A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *             'A' -> 1
 *             'B' -> 2
 *             ...
 *             'Z' -> 26
 *    Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 *    Example : Given encoded message 12, it could be decoded as AB (1 2) or L (12).
 *              The number of ways decoding 12 is 2.
 */
 
 //My Method: Dynamic Programming
 public int numDecodings(String s) {
    // write your code here
    if(s == null || s.length() == 0 || s.charAt(0) == '0'){
        return 0;
    }

    for(int i = 0; i < s.length(); i++){
        if(i + 1 < s.length() && s.charAt(i) == '0' && s.charAt(i+1) =='0') //“00”的情况
            return 0;
        if(i - 1 >= 0 && s.charAt(i) == '0' && s.charAt(i-1) > '2')//有'0'，但是'0'与之前的数组成的值大于26
            return 0;
    }
    //dp[i] 表示s的前i个字符组成的字符串可以解码的不同消息数
    int[] dp = new int[s.length() + 1]; 
    dp[0] = 1;
    dp[1] = 1;
    for(int i = 2; i <= s.length(); i++){
        if(s.charAt(i-1) == '0'){
            dp[i] = dp[i - 2];
        }
        else{
            int n = Integer.valueOf(s.substring(i - 2, i));
            if(n >= 10 && n <= 26){
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            else{
                dp[i] = dp[i - 1];
            }
        }
    }
    return dp[s.length()];
 }
 
 //调整顺序，简化代码
 public int numDecodings(String s) {
    if(s == null || s.length() == 0 || s.charAt(0) == '0'){
        return 0;
    }

    int[] dp = new int[s.length() + 1];
    dp[0] = 1;
    dp[1] = 1;
    for(int i = 2; i <= s.length(); i++){
        if(s.charAt(i-1) != '0'){
            dp[i] = dp[i - 1];
        }
        int num = Integer.valueOf(s.substring(i-2,i));
        if(num >= 10 && num <= 26){
            dp[i] += dp[i - 2];
        }
    }
    return dp[s.length()];
 }
