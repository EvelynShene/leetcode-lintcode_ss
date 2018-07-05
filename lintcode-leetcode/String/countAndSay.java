/** 420. Count and Say (lintcode) / 38. Count and Say (leetcode)
 *    The count-and-say sequence is the sequence of integers beginning as follows:
 *        1, 11, 21, 1211, 111221, ...
 *        1 is read off as "one 1" or 11.
 *        11 is read off as "two 1s" or 21.
 *        21 is read off as "one 2, then one 1" or 1211.
 *    Given an integer n, generate the nth sequence.
 *
 *    Note: The sequence of integers will be represented as a string.
 *
 *    Example: Given n = 5, return "111221".
 *
 *    理解题意：每一个数都是对前一个的重新读取，如第二个数11，则第三个数读为“2个1”，即21；第四个数则是读第三数为“1个2和1个1”，即1211。第一个数是1。            
 */

//Method:
public String countAndSay(int n) {
    // write your code here
    String s = "1";

    for(int i = 1; i < n; i++){
        StringBuilder s2 = new StringBuilder();
        int j = 0;
        while(j < s.length()){
            int count = 1;
            int cur = j + 1;
            while(cur < s.length() && s.charAt(j) == s.charAt(cur)){
                count++;
                cur++;
            }
            s2.append(String.valueOf(count)+s.charAt(j));
            j = cur;
        }
        s = s2.toString();
    }
    return s;
}
