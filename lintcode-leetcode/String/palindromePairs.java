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

//Method 2: 
