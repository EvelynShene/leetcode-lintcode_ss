/** 659. Encode and Decode Strings(lintcode) / 271. Encode and Decode Strings(leetcode - locked)
 *      Design an algorithm to encode a list of strings to a string. 
 *  The encoded string is then sent over the network and is decoded back to the original list of strings.
 *      Please implement encode and decode
 *
 *      Example: Given strs = ["lint","code","love","you"] 
 *                 string encoded_string = encode(strs)
 *               return ["lint","code","love","you"] when you call decode(encoded_string)
 */
 
 //Method: 自行设计编码时的分隔符号
 public class Solution {
    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        // write your code here
        if(strs == null || strs.size() == 0){
            return "";
        }
        StringBuilder newstr = new StringBuilder();
        for(int i = 0; i < strs.size(); i++){
            char[] c = strs.get(i).toCharArray();
            for(int j = 0; j < c.length; j++){
                if(c[j] == ':'){
                    newstr.append("::");
                }
                else{
                    newstr.append(c[j]);
                }
            }
            newstr.append(":,");
        }
        return newstr.toString();
    }

    /*
     * @param str: A string
     * @return: dcodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        // write your code here
        List<String> list = new ArrayList<String>();
        if(str == null || str.length() == 0){
            return list;
        }
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            if(i + 1 < str.length() && str.charAt(i) == ':'){
                if(str.charAt(i + 1) == ','){
                    list.add(s.toString());
                    s = new StringBuilder();
                }
                else{
                    s.append(str.charAt(i));
                }
                i++;
            }
            else{
                s.append(str.charAt(i));
            }
        }
        return list;
    }
}
