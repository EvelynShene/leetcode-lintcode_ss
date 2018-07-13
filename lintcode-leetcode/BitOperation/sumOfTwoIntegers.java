/** 371. Sum of Two Integers(leetcode)
 *  Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 *
 *  Example:  Given a = 1 and b = 2, return 3.
 *
 *  思路：使用位运算，先看一位：0 + 0 = 0, 0 + 1 = 1, 1 + 0 = 1, 1 + 1 = 10
 *             1）对于每一位，做加法后，本位数组满足0、1异或关系；然后可能有后一位／下一位进位带来的1，如果有进位，再加上进位的1
 *             2）只有在都是1的时候，才会产生进位，产生进位的过程可以用与(&)运算再左移一位表示，即 (a&b)<<1 (左移动是因为向前进位1) 
 *          
 */
 
 //Method 1: 递归
 public int getSum(int a, int b) {
    int sum = a ^ b;
    int c = (a & b) << 1;
    while(c != 0){
        return getSum(sum, c);
    }
    return sum;
 }
 
 //Method 2: 非递归
 public int getSum(int a, int b) {
    int sum = a ^ b;
    int c = (a & b) << 1;

    while(c != 0){
        int temp = c;
        c = (c & sum) << 1;
        sum = temp ^ sum;
    }
    return sum;
 }
