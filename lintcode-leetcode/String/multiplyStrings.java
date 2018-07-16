/** 656. Multiply Strings(lintcode) / 43. Multiply Strings(leetcode)
 *    Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2
 *
 *    Note: 1) The length of both num1 and num2 is < 110.
 *          2) Both num1 and num2 contain only digits 0-9.
 *          3) Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 *          4) You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 *    Example: Input: num1 = "123", num2 = "456" ; Output: "56088"
 */
 
 /* Method: 以 123*456 为例：  
  *                     1  2  3
  *                        4  5
  *                   -----------
  *                        1  5
  *                    1   0          =>   规律：num1[i]*num2[j] = index[i+j]*10 + index[i+j+1]
  *                0   5
  *               -----------
  *                    1   2
  *                0   8
  *            0   4
  *           --------------------
  *            0   4   15  3  5    ->   0  5  5  3  5
  *   
  */
  
  // Code:（需要注意数组的下标，以及溢出的问题）
  public String multiply(String num1, String num2) {
    if(num1 == null || num2 == null){
        return null;
    }

    int len1 = num1.length();
    int len2 = num2.length();
    int[] product = new int[len1+len2];

    for(int i = 0; i < len1; i++){
        for(int j = 0; j < len2; j++){
            int p = (num1.charAt(i) - '0')*(num2.charAt(j) - '0');
            product[i+j+1] += p % 10;
            product[i+j] += p / 10;
        }
    }

    for(int i = product.length - 1; i > 0; i--){
        if(product[i] >= 10){
            product[i-1] += product[i] / 10;
            product[i] = product[i] % 10;
        }
    }

    StringBuilder res = new StringBuilder();
    int x = 0;
    while(x < product.length && product[x] == 0){
        x++;
    }
    if(x == product.length){
        return "0";
    }
    while(x < product.length){
        res.append(""+product[x]);
        x++;
    }
    
    return res.toString();
 }
