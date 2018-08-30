/** 425. Letter Combinations of a Phone Number(lintcode) / 17. Letter Combinations of a Phone Number(leetcode)
 *    Given a digit string excluded 01, return all possible letter combinations that the number could represent.
 *    A mapping of digit to letters is just like on the telephone buttons. (九键键盘)
 *
 *    Example: Given "23" ; Return ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 *    
 *    Note: Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
 
 //My Method: DFS/Backtracking
 class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<String>();
        if(digits == null || digits.length() == 0){
            return list;
        }
        String[] dig = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv","wxyz"};
        dfs(list, "", 0, digits, dig);
        
        return list;
    }
    
    public void dfs(List<String> list, String s, int index, String digits, String[] dig){
        if(index == digits.length()){
            list.add(s);
            return;
        }
        int number = digits.charAt(index) - '0';
        for(int i = 0; i < dig[number].length(); i++){
            dfs(list, s + dig[number].charAt(i), index + 1, digits, dig);
        }
    }
}
