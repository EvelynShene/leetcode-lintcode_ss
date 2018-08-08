/** 258. Add Digit(leetcode) / 569. Add Digit(lintcode)
 *      Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 *
 *      Example: Input: 38 ; Output: 2 
 *               Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 *
 *      Follow up: Could you do it without any loop/recursion in O(1) runtime?
 */
 
 //My Method:
 public int addDigits(int num) {
    while(num >= 10){
        int res = 0;
        String x = String.valueOf(num);
        for(int i = 0; i < x.length(); i++){
            res += x.charAt(i) - '0';
        }
        num = res;
    }
    return num;
 }
 
 //Follow up: https://leetcode.com/problems/add-digits/discuss/68580/Accepted-C++-O(1)-time-O(1)-space-1-Line-Solution-with-Detail-Explanations
