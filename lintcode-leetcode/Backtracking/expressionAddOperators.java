/** 282. Expression Add Operators(leetcode) / 653. Expression Add Operators(lintcode)
 *      Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary 
 *  operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 *
 *      Example: 1) "123", 6 -> ["1+2+3", "1*2*3"] 
 *               2) "232", 8 -> ["2*3+2", "2+3*2"]
 *               3) "105", 5 -> ["1*0+5","10-5"]
 *               4) "00", 0 -> ["0+0", "0-0", "0*0"]
 *               5) "3456237490", 9191 -> []
 */
 
 //Method:
 class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<String>();
        for(int i = 1; i <= num.length(); i++){
            long number = Long.valueOf(num.substring(0,i));
            addOp(num.substring(i), target, number, "" + number, res, number);
            
            if(number == 0){ // avoid 00
                break;
            }
        }
        return res;
    }
    
    public void addOp(String num, int target, long sum, String s, List<String> res, long last_num){
        if(num.length() == 0){
            if(target == sum){
                res.add(s);
            }
            return;
        }
        
        for(int i = 1; i <= num.length(); i++){
            long number = Long.valueOf(num.substring(0,i));
            
            addOp(num.substring(i), target, sum + number, s + "+" + number, res, number);
            // sum - number = sum + (-number)
            addOp(num.substring(i), target, sum - number, s + "-" + number, res, -number); 
            addOp(num.substring(i), target, sum - last_num + last_num * number, s + "*" + number, res, last_num * number);
            if(number == 0){
                break;
            }
        }
    }
}
