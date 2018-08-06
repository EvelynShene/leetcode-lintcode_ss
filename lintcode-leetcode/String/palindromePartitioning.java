/** 131. Palindrome Partitioning(leetcode) / 136. Palindrome Partitioning(lintcode)
 *      Given a string s, partition s such that every substring of the partition is a palindrome.
 *    Return all possible palindrome partitioning of s.
 *
 *      Example:1) Input: "aab"
 *                 Output:
 *                    [
 *                      ["aa","b"],
 *                      ["a","a","b"]
 *                    ]
 *              2) Input: "abbab"
 *                 Output: 
 *                    [
 *                      ["abba","b"],
 *                      ["a","b","bab"],
 *                      ["a","bb","a","b"],
 *                      ["a","b","b","a","b"]
 *                    ]
 */
 
 /* Method: 递归调用 -- 每次把第一个字符逐个与它后面字符形成的回文串集中的每一个字符组合，检查是不是回文子串。  
  *     例如“bab”:子串ab会返回["a","b"], 把第一个字符b逐个与后面的字符串组合：
  *               "ba"不是；"bab"是，所以加入到res中。
  *     缺点：效率低
  */
 class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<List<String>>();
        if(s == null || s.length() == 0){
            return list;
        }
        int n = s.length();
        List<String> l = new ArrayList<String>();
        if(n == 1){
            l.add(s);
            list.add(l);
            return list;
        }
        list = partition(s.substring(1));
        List<List<String>> res = new ArrayList<List<String>>();
        for(int i = 0; i < list.size(); i++){
            List<String> tmp = list.get(i);
            tmp.add(0, "" + s.charAt(0));
            res.add(new ArrayList<String>(tmp));
            tmp.remove(0);
            StringBuilder str = new StringBuilder("" + s.charAt(0));
            while(tmp.size() > 0){
                str.append(tmp.get(0));
                tmp.remove(0);
                if(isPalindrome(str.toString())){
                    tmp.add(0,str.toString());
                    res.add(new ArrayList<String>(tmp));
                    tmp.remove(0);
                }
            }
        }
        Set<List<String>> set = new HashSet<List<String>>();
        for(int i = 0; i < res.size(); i++){
        	if(!set.contains(res.get(i))){
        		set.add(res.get(i));
        	}
        	else{
        		res.remove(i);
        		i--;
        	}
        }
        return res;
    }
    
    public boolean isPalindrome(String s){
        int n = s.length();
        if(n <= 1)
            return true;
        int left = 0;
        int right = n - 1;
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
//More concise code from leetcode https://leetcode.com/problems/palindrome-partitioning/discuss/41963/Java:-Backtracking-solution.
public class Solution {
        List<List<String>> resultLst;
	    ArrayList<String> currLst;
	    public List<List<String>> partition(String s) {
	        resultLst = new ArrayList<List<String>>();
	        currLst = new ArrayList<String>();
	        backTrack(s,0);
	        return resultLst;
	    }
	    public void backTrack(String s, int l){
	        if(currLst.size()>0 //the initial str could be palindrome
	            && l>=s.length()){
	                List<String> r = (ArrayList<String>) currLst.clone();
	                resultLst.add(r);
	        }
	        for(int i=l;i<s.length();i++){
	            if(isPalindrome(s,l,i)){
	                if(l==i)
	                    currLst.add(Character.toString(s.charAt(i)));
	                else
	                    currLst.add(s.substring(l,i+1));
	                backTrack(s,i+1);
	                currLst.remove(currLst.size()-1);
	            }
	        }
	    }
	    public boolean isPalindrome(String str, int l, int r){
	        if(l==r) return true;
	        while(l<r){
	            if(str.charAt(l)!=str.charAt(r)) return false;
	            l++;r--;
	        }
	        return true;
	    }
}
//Method 2: DP + DFS: https://leetcode.com/problems/palindrome-partitioning/discuss/41982/Java-DP-+-DFS-solution
