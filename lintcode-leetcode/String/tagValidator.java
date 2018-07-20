/** 1151. Tag Validator(lintcode) / 591. Tag Validator(leetcode)
 *      Given a string representing a code snippet, you need to implement a tag validator to parse the code and 
 *  return whether it is valid. A code snippet is valid if all the following rules hold:
 *     1) The code must be wrapped in a valid closed tag. Otherwise, the code is invalid.
 *     2) A closed tag (not necessarily valid) has exactly the following format : <TAG_NAME>TAG_CONTENT</TAG_NAME>.
 *  Among them, <TAG_NAME> is the start tag, and </TAG_NAME> is the end tag. The TAG_NAME in start and end tags 
 *  should be the same. A closed tag is valid if and only if the TAG_NAME and TAG_CONTENT are valid.
 *     3) A valid TAG_NAME only contain upper-case letters, and has length in range [1,9]. 
 *  Otherwise, the TAG_NAME is invalid.
 *     4) A valid TAG_CONTENT may contain other valid closed tags, cdata and any characters (see note1) 
 *  EXCEPT unmatched <, unmatched start and end tag, and unmatched or closed tags with invalid TAG_NAME. 
 *  Otherwise, the TAG_CONTENT is invalid.
 *     5) A start tag is unmatched if no end tag exists with the same TAG_NAME, and vice versa. 
 *  However, you also need to consider the issue of unbalanced when tags are nested.
 *     6) A < is unmatched if you cannot find a subsequent >. And when you find a < or </, all the subsequent 
 *  characters until the next > should be parsed as TAG_NAME (not necessarily valid).
 *     7) The cdata has the following format : <![CDATA[CDATA_CONTENT]]>. The range of CDATA_CONTENT is defined as
 *  the characters between <![CDATA[ and the first subsequent ]]>.
 *     8) CDATA_CONTENT may contain any characters. The function of cdata is to forbid the validator to 
 *  parse CDATA_CONTENT, so even it has some characters that can be parsed as tag (no matter valid or invalid), 
 *  you should treat it as regular characters.
 *
 *     Note: For simplicity, you could assume the input code (including the any characters mentioned above) only 
 *           contain letters, digits, '<','>','/','!','[',']' and ' '.
 *
 *     Example: https://leetcode.com/problems/tag-validator/description/
 */
 
 //My Code: Use Stack
 class Solution {
    public boolean isValid(String code) {
        if(code == null || code.length() == 0){
            return false;
        }
        if(code.charAt(0) != '<'){
            return false;
        }
        Stack<String> stk = new Stack<String>();
        int index = checkTagName(code, 1, stk, false);
        if(index == -1){
            return false;
        }
        while(index < code.length()){
            if(code.charAt(index) == '<'){
                if(code.substring(index).indexOf("<![CDATA[") == 0){//cdata	
                	index += code.substring(index).indexOf("]]>");          
                    if(index == -1){
                        return false;
                    }
                    else{
                        index = index + 3;
                    }
                }
                else if(index + 1 < code.length() && code.charAt(index+1) == '/'){
                	if(stk.isEmpty()){ //Avoid <A></A></A>
                		return false;
                	}
                    index = checkTagName(code, index+2, stk, true);
                    if(index == -1){
                        return false;
                    }
                }
                else{
                    index = checkTagName(code, index+1, stk, false);
                    if(index == -1){
                        return false;
                    }
                }
            }
            else{
                index++;
            }
        }
        if(!stk.isEmpty()){
            return false;
        }
        return true;

    }
    
    public static int checkTagName(String code, int start , Stack<String> stk, boolean endtag){
        StringBuilder name = new StringBuilder("</");
        int i = start;
        while(i < code.length()){
            if(code.charAt(i) >= 'A' && code.charAt(i) <= 'Z'){
                name.append(code.charAt(i));
            }
            else if(code.charAt(i) == '>'){
                name.append(code.charAt(i));
                break;
            }
            else{
                return -1;
            }
            i++;
        }
        if(i >= code.length() || i == start || i - start > 9){//avoid <></> and TagName has length in [1,9]
            return -1;
        }
        if(!endtag){
        	stk.push(name.toString());
        }
        else{
            String e = stk.pop();
            if(!e.equals(name.toString())){
                return -1;
            }
            if(stk.isEmpty() && i+1 < code.length()){ //Avoid <A></A><B></B>
            	return -1;
            }
        }
        return i + 1;
    }
}

//Note: 注释的部分都是lintcode test cases缺少的测试部分
