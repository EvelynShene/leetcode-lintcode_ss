/** 417. Valid Number(lintcode) / 65. Valid Number(leetcode) 
 *     Validate if a given string is numeric.
 *     Some examples:
 *        "0" => true
 *        " 0.1 " => true
 *        "abc" => false
 *        "1 a" => false
 *        "2e10" => true
 *
 *     Note: It is intended for the problem statement to be ambiguous. 
 *            You should gather all requirements up front before implementing one.
 *
 *     关键：搞清楚numeric的条件： 
 *          [-+]?(([0-9]+)([.]?|([.][0-9]+)?)|([.][0-9]+))(e[-+]?[0-9]+)?
 */
 
 //Method: 
 class Solution {
    public boolean isNumber(String s) {
        if(s == null){
            return false;
        }
        s = s.trim();
        if(s.length() == 0){
            return false;
        }
        
        boolean afterE = false;
        boolean afterP = false;
        boolean afterEsign = false;
        
        int i = 0;
        if(s.charAt(i) == '+' || s.charAt(i) == '-'){
            i++;
        }
        if(i + 2 < s.length() && s.substring(i,i+2).equals(".e")){
            return false;
        }
        if(s.charAt(s.length()-1) == 'e' || s.charAt(i) == 'e' || (s.length() - 1 == i && s.charAt(i) == '.')){
            return false;
        }
        for(; i < s.length(); i++){
            if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != 'e' && s.charAt(i) != '.'){
                if(afterE && !afterEsign && (s.charAt(i) == '+' || s.charAt(i) == '-') && i + 1 != s.length()){
                    afterEsign = true;
                    continue;
                }
                return false;
            }
            if(s.charAt(i) == 'e'){
                if(afterE){
                    return false;
                }
                afterE = true;
            }
            if(s.charAt(i) == '.'){
                if(afterE || afterP){
                    return false;
                }
                afterP = true;
            }
        }
        
        return true;
    }    
}
