/** 1174. Next Greater Element III(lintcode)/556. Next Greater Element III(leetcode)
 *      Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly 
 *   the same digits existing in the integer n and is greater in value than n. 
 *      If no such positive 32-bit integer exists, you need to return -1.
 *      
 *      Example: 1) Input: 12; Output: 21
 *               2) Input: 21; Output: -1
 */

//My Method: 找下一个排列数，如果这个排列数大于32bit整型可以表达的最大值，或者等于自身，返回-1。
class Solution {
    public int nextGreaterElement(int n) {
        StringBuilder s = new StringBuilder(String.valueOf(n));
        int i = s.length() - 1;
        
        while(i-1 >= 0 && s.charAt(i) - '0' <= s.charAt(i-1) - '0'){
            i--;
        }
        if(i - 1 >= 0){
            int j = s.length() - 1;
            for(; j >= i; j--){
                if(s.charAt(j) - '0' > s.charAt(i - 1) - '0'){
                    break;
                }
            }
            char temp = s.charAt(i - 1);
            s.setCharAt(i-1, s.charAt(j));
            s.setCharAt(j,temp);
            sort(s,i);
        }
        int res = 0;
        try{
            res = Integer.valueOf(s.toString());
        }
        catch(NumberFormatException e){
            return -1;
        }
        
        if(res == n){
            return -1;
        }
        return res;
    }
    
    public void sort(StringBuilder s, int start){
        String str = s.substring(start);
        char[] c = str.toCharArray();
        Arrays.sort(c);
        for(int i = start; i < s.length(); i++){
            if(s.charAt(i) != c[i - start]){
                s.setCharAt(i, c[i - start]);
            }
        }
    }
}
