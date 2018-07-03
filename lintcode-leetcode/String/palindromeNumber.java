/** 491. Palindrome Number(lintcode) 
 *    Check a positive number is a palindrome or not.
 *    A palindrome number is that if you reverse the whole number you will get exactly the same number.
 *    
 *    Note: It's guaranteed the input number is a 32-bit integer, but after reversion, the number may exceed the 32-bit integer.
 *
 *    Example : 11, 121, 1, 12321 are palindrome numbers.
 *              23, 32, 1232 are not palindrome numbers.
 */
 
 //Method: convert number to string
 public boolean isPalindrome(int num) {
    // write your code here
    if(num < 10)
        return true;
    String s = String.valueOf(num);
    int left = 0;
    int right = s.length()-1;
    while(left <= right){
        if(s.charAt(left) == s.charAt(right)){
            left++;
            right--;
        }
        else{
            break;
        }
    }
    if(left > right){
        return true;
    }
    return false;
 }
 
 /** 9. Palindrome Number(leetcode)
 *    Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 *
 *    Example : Input: 121 ; Output: true
 *              Input: -121 ; Output: false (-121 != 121-)
 *
 *    Follow up: Coud you solve it without converting the integer to a string?
 */
 
 //Method 1: Convert number to string
 public boolean isPalindrome(int num) {
    String s = String.valueOf(num);
    int left = 0;
    int right = s.length()-1;
    while(left <= right){
        if(s.charAt(left) == s.charAt(right)){
            left++;
            right--;
        }
        else{
            break;
        }
    }
    if(left > right){
        return true;
    }
    return false;
 }

//Method 2: without convert the integer to string
public boolean isPalindrome(int x) {
    if(x < 0){
        return false;
    }
    if(x < 10){
        return true;
    }
    int len = 0;
    int num = x;
    while(num > 0){
        len++;
        num = num / 10;
    }
    len = len - 1;
    num = x;
    while(num > 0){
        if(num % 10 == num / (int)Math.pow(10,len)){
            num = (num % (int)Math.pow(10,len)) / 10;
            len = len - 2;
        }
        else{
            return false;
        }
    }
    return true;
}

public boolean isPalindrome(int x) {
    if(x < 0){
        return false;
    }
    if(x < 10){
        return true;
    }
    int reverse = 0;
    int num = x;
    while(x > 0){
        reverse = reverse*10 + x % 10;
        x = x / 10;
    }
    if(reverse == num){
        return true;
    }
    return false;
}
