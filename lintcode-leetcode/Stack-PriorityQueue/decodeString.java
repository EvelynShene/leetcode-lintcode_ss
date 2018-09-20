/** 394. Decode String(leetcode) / 575. Decode String(lintcode)
 *      Given an encoded string, return it's decoded string.
 *      The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being 
 *  repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *      You may assume that the input string is always valid; No extra white spaces, square brackets are 
 *  well-formed, etc.
 *      Furthermore, you may assume that the original data does not contain any digits and that digits are only 
 *  for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 *      Example: 1) s = "3[a]2[bc]", return "aaabcbc".
 *               2) s = "3[a2[c]]", return "accaccacc".
 *               3) s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */ 
 
 //Method 1: Recursive
 class Solution {
    int i = 0;
    public String decodeString(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        return decode(s);
    }
    
    public String decode(String s){
        StringBuilder str = new StringBuilder();
        
        while(i < s.length() && s.charAt(i) != ']'){
            if(Character.isLetter(s.charAt(i))){
                str.append(s.charAt(i));
                i++;
            }
            else if(Character.isDigit(s.charAt(i))){
                int num = 0;         
                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    num = num * 10 + (s.charAt(i) - '0'); 
                    i++;
                }
                i++;
                String sub = decode(s);
                i++;
                for(int x = 0; x < num; x++){
                    str.append(sub);
                }
            }
        }
        return str.toString();
    }
}

/* Method 2: Use Stack 
 *    把所有字符先一个个放到 stack 里， 如果碰到了"]"，就从 stack 找到对应的字符串和重复次数，decode 之后再重新放回 stack 里
 */
public String expressionExpand(String s) {
    if(s == null || s.length() == 0){
        return s;
    }
    StringBuilder res = new StringBuilder();
    Stack<String> stk = new Stack<String>();

    for(int i = 0; i < s.length(); i++){
        if(s.charAt(i) != ']'){
            stk.push("" + s.charAt(i));
        }
        else{
            StringBuilder substr = new StringBuilder();
            while(!stk.isEmpty() && !stk.peek().equals("[")){
                substr.insert(0,stk.pop());
            }
            stk.pop();
            StringBuilder n = new StringBuilder();
            while(!stk.isEmpty() && Character.isDigit(stk.peek().charAt(0))){
                n.insert(0, stk.pop());
            }
            int num = Integer.valueOf(n.toString());
            StringBuilder substr2 = new StringBuilder();
            for(int x = 0; x < num; x++){
                substr2.append(substr);
            }
            if(substr2.length() != 0){ //注意重复数字为0时，不需要压栈了
                stk.push(substr2.toString());
            }
        }
    }

    while(!stk.isEmpty()){
        res.insert(0,stk.pop());
    }
    return res.toString();
}
