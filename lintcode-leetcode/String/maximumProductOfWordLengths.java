/** 1296. Maximum Product of Word Lengths(lintcode) /318. Maximum Product of Word Lengths(leetcode)
 *      Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where 
 *   the two words do not share common letters. You may assume that each word will contain only lower case letters.
 *   If no such two words exist, return 0.
 *
 *    Example 1) Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]; Return 16
 *              The two words can be "abcw", "xtfn".
 *            2) Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]; Return 4
 *              The two words can be "ab", "cd".
 *            3) Given ["a", "aa", "aaa", "aaaa"]; Return 0
 *              No such pair of words.
 */
 
 /* Method:
  *    都是小写字母组成的字符串，所以可以用32为整型(int)的 0-25bit表示字母 a-z。
  */
 public int maxProduct(String[] words) {
    if(words == null || words.length < 2){
        return 0;
    }
    int[] letter = new int[words.length];
    for(int i = 0; i < words.length; i++){
        for(int j = 0; j < words[i].length(); j++){
            int x = 1 << words[i].charAt(j) - 'a';
            letter[i] = letter[i] | x;
        }
    }
    int max = 0;
    for(int i = 0; i < words.length; i++){
        for(int j = i+1; j < words.length; j++){
            int diff = letter[i] & letter[j];
            if( diff == 0){
                max = Math.max(max, words[i].length()*words[j].length());
            }
        }
    }
    return max;
 }
