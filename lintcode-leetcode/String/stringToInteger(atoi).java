/** 54. String to Integer (atoi)(lintcode)
 *      Implement function atoi to convert a string to an integer.
 *      If no valid conversion could be performed, a zero value is returned.
 *      If the correct value is out of the range of representable values, 
 *   INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 *
 *      Example: "10" => 10
 *               "-1" => -1
 *               "123123123123123" => 2147483647
 *               "1.0" => 1
 *               "+-1111" => 0
 *               "    -534" => -534
 *               "52lintcode  " => 52
 */
 
 //Method 1:
 public int atoi(String str) {
    // write your code here
    if(str == null || str.length() == 0){
        return 0;
    }
    // 以下两个for循环去掉首尾空格，即等价于str = str.trim();
    for(int i = 0; i < str.length(); i++){
        if(str.charAt(i) != ' '){
            str = str.substring(i);
            break;
        }
    }
    for(int i = str.length() - 1; i >= 0; i--){
        if(str.charAt(i) != ' '){
            str = str.substring(0,i+1);
            break;
        }
    }
    
    if(!Character.isDigit(str.charAt(0)) && str.charAt(0) != '+' && str.charAt(0) != '-' ){
        return 0;
    }
    int signed = 0; // 0 - no sign; 1 - "+"; 2 - "-"
    int num = 0;
    int i = 0;
    while(i < str.length()){
        if(i == 0){
            if(str.charAt(i) == '-'){
                signed = 2;
            }
            else if(str.charAt(i) == '+'){
                signed = 1;
            }
            else{
                num = num*10 + (str.charAt(i) - '0');
            }
            i++;
        }
        else if(!Character.isDigit(str.charAt(i))){
            break;
        }
        else{
            num = num * 10 + (str.charAt(i) - '0');
            if(num < 0){ //溢出了
                if(signed == 2){
                    return Integer.MIN_VALUE;
                }
                return Integer.MAX_VALUE;
            }
            i++;
        }
    }
    if(signed == 2){
        return (-num);
    }
    return num;
 }
 
 //Method 2: 用long类型定义num,来判断是否溢出
 public int atoi(String str) {
    // write your code here
    if(str == null || str.length() == 0){
        return 0;
    }
    // str = str.trim();
    for(int i = 0; i < str.length(); i++){
        if(str.charAt(i) != ' '){
            str = str.substring(i);
            break;
        }
    }
    for(int i = str.length() - 1; i >= 0; i--){
        if(str.charAt(i) != ' '){
            str = str.substring(0,i+1);
            break;
        }
    }

    int signed = 1; // 1 - "+"/no sign; -1 - "-"
    long num = 0;
    int i = 0;
    if(str.charAt(i) == '-'){
        signed = -1;
        i++;
    }
    else if(str.charAt(i) == '+') {
        i++;
    }
    while(i < str.length()){
        if(!Character.isDigit(str.charAt(i))){
            break;
        }
        num = num * 10 + (str.charAt(i) - '0');
        if(num > Integer.MAX_VALUE ){ //一旦大于32bit整型最大值，就可以退出循环了
            break;
        }
        i++;
    }
    if(num * signed >= Integer.MAX_VALUE){
        return Integer.MAX_VALUE;
    }
    else if(num * signed <= Integer.MIN_VALUE){
        return Integer.MIN_VALUE;
    }
    return (int)num * signed;
 }
