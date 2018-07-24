/** 775. Palindrome Pairs(lintcode) / 336. Palindrome Pairs(leetcode)
 *      Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
 *  so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 *      Example: 1) Given words = ["bat", "tab", "cat"] ; Return [[0, 1], [1, 0]]
 *                  The palindromes are ["battab", "tabbat"]
 *               2) Given words = ["abcd", "dcba", "lls", "s", "sssll"] ; Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 *                  The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
 
 //Method 1: Brute Force - O(n^3) [Time Limit Exceeded]
 class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(words == null || words.length < 2){
            return list;
        }
        for(int i = 0; i < words.length; i++){
            for(int j = 0; j < words.length; j++){
                if(i == j) continue;
                String s = words[i]+words[j];
                if(isPalindrome(s)){
                    List<Integer> l = new ArrayList<Integer>();
                    l.add(i);
                    l.add(j);
                    list.add(l);
                }
            }
        }
        return list;
    }
    
    public boolean isPalindrome(String s){
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

/* Method 2: 分情况讨论：
 *   1) 如果有空字符串，那么它和所有的本身就是回文串的字符串可以构成两对配对(a list of unique words - 只可能有一个空字符串)
 *   2) 对于字符串s,如果存在它的反转字符串reverse(s)，那么 s+reverse(s) 可以构成配对
 *   3) 对于其他的字符s：(n = s.length())
 *     a) 若s[0,j)是回文串，如果存在reverse(s[j,n))，那么 reverse(s[j,n)) + s 可以构成配对
 *     b) 若s[j,n)是回文串，如果存在reverse(s[0,j))，那么 s + reverse(s[0,j]) 可以构成配对
 */

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(words == null || words.length < 2){
            return list;
        }
        int len = words.length;
        Map<String, Integer> map = new HashMap<String, Integer>();
        boolean[] palindrome = new boolean[len];
        for(int i = 0; i < len; i++){
            map.put(words[i],i);
            if(isPalindrome(words[i])){
                palindrome[i] = true;
            }
        }
        
        if(map.containsKey("")){//case 1: 含空字符串，和任何回文字符串可以构成回文串
            for(int j = 0; j < len; j++){
                if(map.get("") != j && palindrome[j]){
                    list.add(new ArrayList<Integer>(Arrays.asList(map.get(""),j)));
                    list.add(new ArrayList<Integer>(Arrays.asList(j,map.get(""))));
                }
            }
        }
        
        for(int i = 0; i < len; i++){//case 2: check s and reverse(s) are both exist in the list
            StringBuilder s = new StringBuilder(words[i]);
            String reverse = s.reverse().toString();
            if(!palindrome[i] && map.containsKey(reverse)){
                list.add(new ArrayList<Integer>(Arrays.asList(i,map.get(reverse))));
            }
        }
        
        for(int i = 0; i < len; i++){
            for(int j = 1; j < words[i].length(); j++){ //case 3:
                if(isPalindrome(words[i].substring(0,j))){//words[i][0,j) 是回文串, 找reverse(words[i][j,n))
                    StringBuilder s = new StringBuilder(words[i].substring(j));
                    String reverse = s.reverse().toString();
                    if(map.containsKey(reverse)){
                        list.add(new ArrayList<Integer>(Arrays.asList(map.get(reverse),i)));
                    }
                }
            }
            for(int j = words[i].length() - 1; j > 0; j--){//case 4:
                if(isPalindrome(words[i].substring(j))){//words[i][j,length] 是回文串,找reverse(words[i][0,j))
                    StringBuilder s = new StringBuilder(words[i].substring(0,j));
                    String reverse = s.reverse().toString();
                    if(map.containsKey(reverse)){
                        list.add(new ArrayList<Integer>(Arrays.asList(i,map.get(reverse))));
                    }
                }
            }
        }
            
        return list;
    }
    
    public boolean isPalindrome(String s){
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
