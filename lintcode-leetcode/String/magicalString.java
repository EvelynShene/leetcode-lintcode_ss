/** 1215. Magical String(lintcode) / 481. Magical String(leetcode)
 *    A magical string S consists of only '1' and '2' and obeys the following rules:
 *      1) The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' 
 *    generates the string S itself.
 *      2) The first few elements of string S is the following: S = "1221121221221121122……"
 *      3) If we group the consecutive '1's and '2's in S, it will be: 1 22 11 2 1 22 1 22 11 2 11 22 ......
 *    and the occurrences of '1's or '2's in each group are: 1 2	2 1 1 2 1 2 2 1 2 2 ......
 *      You can see that the occurrence sequence above is the S itself.
 *      Given an integer N as input, return the number of '1's in the first N number in the magical string S.
 *
 *      Note: N will not exceed 100,000.
 *
 *      Example: Input: 6 ; Output: 3
 *            Explanation: The first 6 elements of magical string S is "122112" and it contains three 1's, so return 3.
 */
 
 //My Method: 
 public int magicalString(int n) {
    // write your code here
    if(n == 0){
        return 0;
    }
    int ones = 1;
    StringBuilder s = new StringBuilder("12");
    int index = 1; // index指示把s用来计算the number of contiguous occurrences of  '1' and '2' 时，取到第几个数了
    int num = 0; //记录the number of contiguous occurrences
    int i = 0; //指示当前应该加入base[i] 
    char[] base = {'1','2'};
    while(s.length() < n){
        if(index == 1){
            s.append('2');
            index++;
            num = s.charAt(index) - '0';
        }
        else{
            if(num <= 0){
                index++;
                num = s.charAt(index) - '0';
                i = (i + 1) % 2;
            }
            s.append(base[i]);
            num--;
            if(base[i] == '1'){ 
                ones++;
            }
        }
    }
    return ones;
 }
