/** 241. Different Ways to Add Parentheses(leetcode) / 1309. Different Ways to Add Parentheses(lintcode)
 *      Given a string of numbers and operators, return all possible results from computing all the different 
 *  possible ways to group numbers and operators. The valid operators are +, - and *.
 *
 *      Example: 1) Input: "2-1-1" ; Output: [0, 2]
 *                  Explanation: ((2-1)-1) = 0 
 *                               (2-(1-1)) = 2
 *
 *               2) Input: "2*3-4*5" ; Output: [-34, -14, -10, -10, 10]
 *                  Explanation: 
 *                           (2*(3-(4*5))) = -34 
 *                           ((2*3)-(4*5)) = -14 
 *                           ((2*(3-4))*5) = -10 
 *                           (2*((3-4)*5)) = -10 
 *                           (((2*3)-4)*5) = 10
 */
 
 /* Method 1: Recursive/ Divide and Conquer
  *      思路：递归，从前往后遍历，对于每一个运算符将其左右子字符串分成两部分，然后计算两边的各种不同的结果来，然后和在一起。
  *   这种方法不会又相同的括号添加方法发生。
  */
 public List<Integer> diffWaysToCompute(String input) {
    List<Integer> res = new ArrayList<Integer>();
    if(input == null || input.length() == 0){
        return res;
    }
    int n = input.length();
    if(input.indexOf('+') == -1 && input.indexOf('-') == -1 && input.indexOf('*') == -1){
        res.add(Integer.valueOf(input));
        return res;
    }
    for(int i = 0; i < n; i++){
        if(!Character.isDigit(input.charAt(i))){
            List<Integer> left = diffWaysToCompute(input.substring(0,i));
            List<Integer> right = diffWaysToCompute(input.substring(i+1));
            for(int j = 0; j < left.size(); j++){
                for(int k = 0; k < right.size(); k++){
                    if(input.charAt(i) == '+'){
                        res.add(left.get(j) + right.get(k));
                    }
                    else if(input.charAt(i) == '-'){
                        res.add(left.get(j) - right.get(k));
                    }
                    else{
                        res.add(left.get(j) * right.get(k));
                    }
                }
            }

        }
    }
    return res;
 }
 
 //Method 2: 同样是递归调用，但是用map存储已经搜索过的子字符串的结果值，可以提高运行速度。但需要更多的存储空间
 class Solution {
    Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<Integer>();
        if(input == null || input.length() == 0){
            return res;
        }
        int n = input.length();
        if(input.indexOf('+') == -1 && input.indexOf('-') == -1 && input.indexOf('*') == -1){
            res.add(Integer.valueOf(input));
            return res;
        }
        
        for(int i = 0; i < n; i++){
            if(!Character.isDigit(input.charAt(i))){
                String l = input.substring(0,i);
                String r = input.substring(i+1);
                List<Integer> left = new ArrayList<Integer>();
                List<Integer> right = new ArrayList<Integer>();
                if(map.containsKey(l)){
                    left = map.get(l);
                }
                else{
                    left = diffWaysToCompute(l);
                    map.put(l,left);
                }
                if(map.containsKey(r)){
                    right = map.get(r);
                }
                else{
                    right = diffWaysToCompute(r);
                    map.put(r,right);
                }
                
                for(int j = 0; j < left.size(); j++){
                    for(int k = 0; k < right.size(); k++){
                        if(input.charAt(i) == '+'){
                            res.add(left.get(j) + right.get(k));
                        }
                        else if(input.charAt(i) == '-'){
                            res.add(left.get(j) - right.get(k));
                        }
                        else{
                            res.add(left.get(j) * right.get(k));
                        }
                    }
                }
            }
        }
        return res;
    }
}
