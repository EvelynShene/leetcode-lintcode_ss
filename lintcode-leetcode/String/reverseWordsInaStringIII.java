/** 1173. Reverse Words in a String III(lintcode) / 557. Reverse Words in a String III(leetcode)
 *      Given a string, you need to reverse the order of characters in each word within a sentence 
 *   while still preserving whitespace and initial word order.
 *
 *      Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 *
 *      Example: Input: "Let's take LeetCode contest"
 *               Output: "s'teL ekat edoCteeL tsetnoc"
 */
 
 class Solution {
    public String reverseWords(String s) {
        if(s == null || s.length() < 2){
            return s;
        }
        StringBuilder str = new StringBuilder();
        int start = 0;
        int i = 0;
        for(i = 1; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                str.append(reverse(s,start,i-1));
                str.append(' ');
                start = i + 1;
            }
        }
        if(start < i){
            str.append(reverse(s,start,i-1));
        }
        return str.toString();
    }
    public String reverse(String s, int start, int end){
        StringBuilder str = new StringBuilder();
        for(int i = end; i >= start; i--){
            str.append(s.charAt(i));
        }
        return str.toString();
    }
}
