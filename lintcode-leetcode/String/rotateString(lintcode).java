/** 8. Rotate String
 *    Given a string and an offset, rotate string by offset. (rotate from left to right)
 *
 *    Example: Given "abcdefg".
 *                offset=0 => "abcdefg"
 *                offset=1 => "gabcdef"
 *                offset=2 => "fgabcde"
 *                offset=3 => "efgabcd"
 *
 *    Challenge: Rotate in-place with O(1) extra memory.
 */
 
 //My Method: Reverse
 public class Solution {
    /**
     * @param str: An array of char
     * @param offset: An integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        // write your code here
        if(str == null || str.length == 0){
            return;
        }
        if(offset > str.length)
            offset = offset % str.length;
        if(offset == 0) return;
        reverse(str, 0, str.length-1);
        reverse(str, 0, offset-1);
        reverse(str, offset, str.length-1);
    }
    
    public void reverse(char[] str, int start, int end){
        while(start < end){
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
    }
}
