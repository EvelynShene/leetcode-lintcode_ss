/** 482. License Key Formatting(leetcode) / 
 *      You are given a license key represented as a string S which consists only alphanumeric character and 
 *  dashes. The string is separated into N+1 groups by N dashes.
 *      Given a number K, we would want to reformat the strings such that each group contains exactly K characters,
 *  except for the first group which could be shorter than K, but still must contain at least one character. 
 *  Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted 
 *  to uppercase.
 *      Given a non-empty string S and a number K, format the string according to the rules described above.
 *
 *      Example: 1) Input: S = "5F3Z-2e-9-w", K = 4 ; Output: "5F3Z-2E9W"
 *                  
 *                  Explanation: The string S has been split into two parts, each part has 4 characters.
 *                  Note that the two extra dashes are not needed and can be removed.
 *               2) Input: S = "---", K = 3 ; Output: ""
 */
 
 //Method 1: Use Stack
 class Solution {
    public String licenseKeyFormatting(String S, int K) {
        
        Stack<Character> stk = new Stack<Character>();
        for(int i = 0; i < S.length(); i++){
            if(S.charAt(i) != '-'){
                char c = S.charAt(i);
                if(c >= 'a' && c <= 'z'){
                    c = (char)(S.charAt(i) - 'a' + 'A');
                }
                stk.push(c);
            }
        }
        StringBuilder newstring = new StringBuilder();
        int index = 0;
        while(!stk.isEmpty()){
            newstring.insert(0, "" + stk.pop());
            index = (index + 1) % K;
            if(index == 0){
                newstring.insert(0, "-");
            }
        }
        if(newstring == null || newstring.length() == 0){
            return newstring.toString();
        }
        
        if(newstring.charAt(0) == '-'){
            newstring.deleteCharAt(0);
        }
        return newstring.toString();
    }
}
 
//Method 2: O(1) space complexity
public String licenseKeyFormatting(String S, int K) {
    int n = S.length();
    StringBuilder str = new StringBuilder();
    int index = 0;
    for(int i = n - 1; i >= 0; i--){
        if(S.charAt(i) != '-'){
            char c = S.charAt(i);
            if(c >= 'a' && c <= 'z'){
                c = (char)(c - 'a' + 'A');
            }
            str.insert(0, "" + c);
            index = (index + 1) % K;
            if(index == 0){
                str.insert(0, "-");
            }
        }
    }

    if(str.length() == 0){
        return str.toString();
    }
    if(str.charAt(0) == '-'){
        str.deleteCharAt(0);
    }
    return str.toString();
}
