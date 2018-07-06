/** 891. Valid Palindrome II(lintcode) / 680. Valid Palindrome II(leetcode)
 *    Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 *    Note: The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 *    
 *    Example: 1) Input: "aba" ; Output: True
 *             2) Input: "abca" ; Output: True
 *             Explanation: You could delete the character 'c'.
 *    (lintcode中的test case有漏洞，没有对"cupufupucu"这种例子的测试，即s[left] == s[right-1] 和 s[left+1] == s[right]都成立的情况)
 */
 
 //Method: Two Pointer
 public class Solution {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        boolean delete = false;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
            	if(!delete && s.charAt(left + 1) == s.charAt(right) && s.charAt(left) == s.charAt(right - 1)){
            		boolean res = palindrome(s,left + 2,right - 1);
            		if(!res){
            			res = palindrome(s, left + 1, right - 2);
            		}
            		return res;
            	}
            	else if(!delete && s.charAt(left + 1) == s.charAt(right)){
                    left++;
                    delete = true;
                }
                else if(!delete && s.charAt(left) == s.charAt(right - 1)){
                    right--;
                    delete = true;
                }
                else{
                    return false;
                }
            }
            left++;
            right--;
        }
        return true;
    }
	public boolean palindrome(String s, int left, int right) {
		while(left < right){
			if(s.charAt(left) != s.charAt(right)){
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}

//简化代码
class Solution {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
            	break;
            }
            left++;
            right--;
        }
        if(left < right){
            return (palindrome(s,left+1,right) || palindrome(s,left, right-1));
        }
        return true;
    }
	public static boolean palindrome(String s, int left, int right) {
		while(left < right){
			if(s.charAt(left) != s.charAt(right)){
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}
