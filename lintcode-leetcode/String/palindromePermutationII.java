/** 917. Palindrome Permutation II(lintcode) / 267. Palindrome Permutation II(leetcode - locked)
 *      Given a string s, return all the palindromic permutations (without duplicates) of it. 
 *   Return an empty list if no palindromic permutation could be form.
 *
 *      Example: 1) Given s = "aabb", return ["abba","baab"].
 *               2) Given s = "abc", return [].
 */
 
 //My Method: DFS/Backtracking - 每一次向下迭代，同一个字符串只取一次
 public class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> list = new ArrayList<String>();
        if(s == null || s.length() == 0){
            return list;
        }
        if(s.length() == 1){
            list.add(s);
            return list;
        }
        if(!canPalindromes(s)){
            return list;
        }
        int n = s.length();
        int[] c = new int[256];
        for(int i = 0; i < n; i++){
            c[s.charAt(i)]++;
        }
        for(int i = 0; i < 256; i++){
            if(c[i] > 1){
                String str = "" + (char)i + (char)i;
                c[i] = c[i] - 2;
                dfs(list, str, c, n);
                c[i] = c[i] + 2;
            }
        }
        return list;
    }
    
    public void dfs(List<String> list, String s, int[] c, int n){
        if(s.length() == n){
            list.add(s);
            return;
        }
        if(s.length() == n-1){
            for(int i = 0; i < 256; i++){
                if(c[i] != 0){
                    String str = s.substring(0,s.length() / 2) + (char)i + s.substring(s.length() / 2);
                    list.add(str);
                    break;
                }
            }
            return;
        }
        for(int i = 0; i < 256; i++){
            if(c[i] > 1){
                String str = s.substring(0, s.length() / 2) + (char)i + (char)i + s.substring(s.length() / 2);
                c[i] -= 2;
                dfs(list,str,c,n);
                c[i] += 2;
            }
        }
    }
    
    public boolean canPalindromes(String s){
        int n = s.length();
        Set<Character> set = new HashSet<Character>();
        for(int i = 0; i < n; i++){
            if(set.contains(s.charAt(i))){
                set.remove(s.charAt(i));
            }
            else{
                set.add(s.charAt(i));
            }
        }
        if(set.size() > 1){
            return false;
        }
        return true;
    }
}
