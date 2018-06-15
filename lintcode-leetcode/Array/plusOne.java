/** 407. Plus One(lintcode)/66. Plus One(leetcode)
 *    Given a non-negative number represented as an array of digits, plus one to the number.
 *    The digits are stored such that the most significant digit is at the head of the list.
 *    Example: Given [1,2,3] which represents 123, return [1,2,4].
 *             Given [9,9,9] which represents 999, return [1,0,0,0].
 */
 
 //My Method(Accepted in lintcode but not in leetcode)
 public int[] plusOne(int[] digits) {
    // write your code here

    if(digits[digits.length-1] != 9){
        digits[digits.length-1]++;
        return digits;
    }
    else{
        int sum  = 0;
        for(int i = 0; i < digits.length; i++){
            sum = sum*10 + digits[i];
        }
        sum++;
        String str = String.valueOf(sum);
        int[] newdigits = new int[str.length()];
        for(int i = 0; i < str.length(); i++){
            newdigits[i] = Integer.parseInt(str.charAt(i)+""); //Integer.parseInt(String)->int
        }
        return newdigits;
    }
}
//在leetcode，若数组很大，转换sum时就会溢出为负数，代码报错


/* Method 2(from Leetcode)
 *   The 1 is added in front only if all digits are 9. For example 999 + 1 = 1000. 
 *   If not all are 9s, then the new (num+1) will be "returned" somewhere in the for loop and no 1 will be added anymore.
 */
public int[] plusOne(int[] digits) {       
    int n = digits.length;
    for(int i=n-1; i>=0; i--) {
        if(digits[i] < 9) {
            digits[i]++;
            return digits;
        }
        
        digits[i] = 0;
    }
    
    int[] newNumber = new int [n+1];
    newNumber[0] = 1;
    
    return newNumber;
}
