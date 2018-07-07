/** 1017. Similar RGB Color(lintcode) / 800. Similar RGB Color (leetcode - locked)
 *      In the following, every capital letter represents some hexadecimal digit from 0 to f.
 *      The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand. 
 *   For example, "#15c" is shorthand for the color "#1155cc".
 *      Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.
 *      Given the color "#ABCDEF", return a 7 character color that is most similar to #ABCDEF, 
 *   and has a shorthand (that is, it can be represented as some "#XYZ")
 *    
 *      Note:  - color is a string of length 7.
 *             - color is a valid RGB color: for i > 0, color[i] is a hexadecimal digit from 0 to f
 *             - Any answer which has the same (highest) similarity as the best answer will be accepted.
 *             - All inputs and outputs should use lowercase letters, and the output is 7 characters.
 *
 *      Example: Input: color = "#09f166" ; Output: "#11ee66"
 *              Explanation: The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
 *                           This is the highest among any shorthand color.
 */
 
 /* My method: 一个颜色有三个分量，对于每个分量，假如对应的输入为ss，那么最多有三个候选的最近颜色：
  *               string(s-1,s-1), string(s,s) 以及 string(s+1,s+1)
  *            所以对这三个候选色分别进行比较，选出距离最小的即可。
  */
 public class Solution {
    public static String similarRGB(String color) {
        // Write your code here
        StringBuilder s = new StringBuilder("#");
        int num;
        int ones;
        int tens;
        for(int i = 1; i < color.length(); i += 2){
            tens = convert(color.charAt(i));
            ones = convert(color.charAt(i+1));
            if(tens == 0){
                num = ones;
                if(Math.abs(17 - num) < num){
                    s.append("11");
                }
                else{
                    s.append("00");
                }
            }
            else{
                num = tens * 16 + ones;
                int num1,num2,num3,temp;
                num1 =  tens * 16 +  tens;
                temp = convert((char)(color.charAt(i) + 1));
                if(temp != -1){
                    num2 = temp * 16 + temp;
                }
                else{
                    num2 = 256;
                }
                temp = convert((char)(color.charAt(i) - 1));
                num3 = temp * 16 + temp;
                
                if(num2 != 256){
                	if(Math.abs(num1 - num) < Math.abs(num2 - num) && Math.abs(num1 - num) < Math.abs(num3 - num)){ // num1 
                        s.append(color.charAt(i));
                        s.append(color.charAt(i));
                    }
                    else if(Math.abs(num2 - num) < Math.abs(num3 - num) && Math.abs(num2 - num) < Math.abs(num1 - num)){//num2
                        char c = (char)(color.charAt(i) + 1);
                        s.append(c);
                        s.append(c);
                    }
                    else{
                        char c = (char)(color.charAt(i) - 1);
                        s.append(c);
                        s.append(c);
                    }
                }
                else{
                	if(Math.abs(num1 - num) < Math.abs(num3 - num)){
                		s.append(color.charAt(i));
                		s.append(color.charAt(i));
                	}
                	else{
                		char c = (char)(color.charAt(i) - 1);
                        s.append(c);
                        s.append(c);
                	}
                }
            }
        }
        
        return s.toString();
    }
    
    public static int convert(char a){
        int num = -1;
        switch(a){
            case '0': num = 0; break;
            case '1': num = 1; break;
            case '2': num = 2; break;
            case '3': num = 3; break;
            case '4': num = 4; break;
            case '5': num = 5; break;
            case '6': num = 6; break;
            case '7': num = 7; break;
            case '8': num = 8; break;
            case '9': num = 9; break;
            case 'a': num = 10; break;
            case 'b': num = 11; break;
            case 'c': num = 12; break;
            case 'd': num = 13; break;
            case 'e': num = 14; break;
            case 'f': num = 15; break;
            default: break;
        }
        return num;
    }
}
