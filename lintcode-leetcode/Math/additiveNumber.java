/** 306. Additive Number(leetcode) / 979. Additive Number(lintcode)
 *     Additive number is a string whose digits can form additive sequence.
 *     A valid additive sequence should contain at least three numbers. Except for the first two numbers, 
 *  each subsequent number in the sequence must be the sum of the preceding two.
 *     Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 *
 *     Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is 
 *  invalid.
 *    
 *     Example:1) Input: "112358" ; Output: true 
 *               Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
 *                             1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *             2) Input: "199100199" ; Output: true 
 *               Explanation: The additive sequence is: 1, 99, 100, 199. 
 *                             1 + 99 = 100, 99 + 100 = 199
 *
 *     Follow up: How would you handle overflow for very large input integers?
 */
 
 //Method: 递归 - Brute Force
 class Solution {
    public boolean isAdditiveNumber(String num) {
        if(num == null || num.length() < 3){
            return false;
        }
        int n = num.length();
        for(int i = 1; i <= n / 2; i++){
            //因为0开头，add1只能取0，只能取num.substring(0,1) = '0'，其他的substring(0,i)(i > 1) 都不成立
            if(num.charAt(0) == '0' && i > 1){ 
                break;
            }
            long add1 = Long.valueOf(num.substring(0,i));
            for(int j = i+1; j <= n; j++){
                //同样因为0开头，add2只能取0，只能取num.substring(i,i+1) = '0'，其他的substring(i,j)(j >i + 1) 都不成立
                if(num.charAt(i) == '0' && j > i+1){
                    break;
                }
                Long add2 = Long.valueOf(num.substring(i,j));
                if(isAdditive(num.substring(j),add1,add2)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isAdditive(String num, long add1, long add2){
        long add = add1 + add2;
        String s = "" + add;
        if(num.equals(s)){
            return true;
        }
        else if(num.indexOf(s) != 0){
            return false;
        }
        else{
            return isAdditive(num.substring(s.length()),add2,add);
        }
    }
}
 
