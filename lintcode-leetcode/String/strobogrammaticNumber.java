/** 644. Strobogrammatic Number(lintcode) / 246. Strobogrammatic Number(leetcode - locked)
 *    A mirror number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *    Write a function to determine if a number is mirror. The number is represented as a string.
 *
 *    Example: For example, the numbers "69", "88", and "818" are all mirror numbers.
 *             1) Given num = "69" return true
 *             2) Given num = "68" return false
 */
 
 //Method:
 public class Solution {
    public boolean isStrobogrammatic(String num) {
        // write your code here
        if(num == null || num.length() == 0){
            return false;
        }
        int left = 0;
        int right = num.length() - 1;
        char[] res = new char[num.length()];
        while(left <= right){
            int left_num = getStrobogrammatic(num.charAt(left) - '0');
            if(left_num == -1 || left_num != num.charAt(right) - '0')
                return false;
            if(right != left){
                int right_num = getStrobogrammatic(num.charAt(right) - '0');
                if(right_num == -1 || right_num != num.charAt(left) - '0')
                    return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    public int getStrobogrammatic(int n){
        switch(n){
            case 0: return 0;
            case 1: return 1;
            case 6: return 9;
            case 9: return 6;
            case 8: return 8;
            case 2:
            case 3:
            case 4:
            case 5:
            case 7: break;
        }
        return -1;
    }
}
