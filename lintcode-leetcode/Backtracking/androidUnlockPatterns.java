/** 909. Android Unlock Patterns(lintcode) / 909. Android Unlock Patterns(leetcode - locked)
 *      Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number 
 *  of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
 *      Rules for a valid pattern:
 *           1) Each pattern must connect at least m keys and at most n keys.
 *           2) All the keys must be distinct.
 *           3) If the line connecting two consecutive keys in the pattern passes through any other keys, 
 *         the other keys must have previously selected in the pattern. No jumps through non selected key is 
 *         allowed.
 *           4) The order of keys used matters.
 *
 *      Example see https://lintcode.com/problem/android-unlock-patterns/description
 */
 
 //My Method:
 public class Solution {
    /**
     * @param m: an integer
     * @param n: an integer
     * @return: the total number of unlock patterns of the Android lock screen
     */
    public int numberOfPatterns(int m, int n) {
        int count = 0;
        for(int i = m; i <= n; i++){
            List<List<Integer>> p = new ArrayList<>();
            boolean[] digit = new boolean[10];
            count += countPatterns(i, new ArrayList<>(), digit);
        }
        return count;
    }
    
    public int countPatterns(int key_num, List<Integer> l, boolean[] digit){
        if(l.size() == key_num){
            return 1;
        }
        int count = 0;
        for(int i = 1; i < 10; i++){
            if(!digit[i]){
                if(l.size() == 0 || check(l.get(l.size() - 1), i, digit)){
                    l.add(i);
                    digit[i] = true;
                    count += countPatterns(key_num, l, digit);
                    l.remove(l.size() - 1);
                    digit[i] = false;
                }
            }
        }
        return count;
    }
    
    public boolean check(int i, int j, boolean[] digit){
        if(i + j == 10){
            if(digit[5]){
                return true;
            }
            return false;
        }
        if((i + j == 4 && (i == 1 || j == 1)) || (i + j == 8 && (i == 1 || j == 1)) || 
                        (i + j == 12 && (i == 9 || j == 9)) || (i + j == 16 && (i == 9 || j == 9))){
            if(digit[(i+j) / 2]){
                return true;
            }
            return false;
        }
        return true;
    }
}
