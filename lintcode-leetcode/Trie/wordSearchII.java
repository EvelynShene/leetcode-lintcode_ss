/** 212. Word Search II(leetcode) / 132. Word Search II(lintcode)
 *      Given a 2D board and a list of words from the dictionary, find all words in the board.
 *      Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 *  horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 *      Example: Input: words = ["oath","pea","eat","rain"] and board =
 *                     [
 *                       ['o','a','a','n'],
 *                       ['e','t','a','e'],
 *                       ['i','h','k','r'],
 *                       ['i','f','l','v']
 *                     ]
 *               Output: ["eat","oath"]
 *
 *      Note: You may assume that all inputs are consist of lowercase letters a-z.
 */
 
 /* My Method:
  *     idea: Use words to build a trie tree; and then for each element in the board, use dfs to check if it can
  *          be the root of any word in the tree. 
  */ 
class Solution {
    class Trie{
        Trie[] children;
        boolean endOfWord;
        public Trie(){
            children = new Trie[26];
            endOfWord = false;
        }
        
        public void addWord(String word){
            Trie cur = this;
            for(int i = 0; i < word.length(); i++){
                int pos = word.charAt(i) - 'a';
                if(cur.children[pos] == null){
                    cur.children[pos] = new Trie();
                }
                cur = cur.children[pos];
            }
            cur.endOfWord = true;
        }
        
        public Trie add(Character c, Trie root){
            int pos = c - 'a';
            if(root.children[pos] == null){
                root.children[pos] = new Trie();
            }
            return root.children[pos];
        }
        
        public boolean search(String word){
            Trie cur = this;
            for(int i = 0; i < word.length(); i++){
                int pos = word.charAt(i) - 'a';
                if(cur.children[pos] == null){
                    return false;
                }
                cur = cur.children[pos];
            }
            return true;
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if(board == null || board.length == 0){
            return res;
        }
        
        //Construct Tire Tree from words
        Trie tree = new Trie();
        for(int i = 0; i < words.length; i++){
            tree.addWord(words[i]);
        }
        
        int m = board.length;
        int n = board[0].length;
        Set<String> set = new HashSet<>();
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                Trie cur = tree;
                int pos = board[i][j] - 'a';
                if(cur.children[pos] != null){
                    StringBuilder str = new StringBuilder("" + board[i][j]);
                    boolean[][] visited = new boolean[m][n];
                    visited[i][j] = true;
                    dfs(i, j, visited, board, cur.children[pos], set, str);
                }
            }
        }
        
        for(String w: set){
            res.add(w);
        }
        return res;
    }
    public void dfs(int row, int col, boolean[][] visited, char[][] board, Trie cur, Set<String> set, StringBuilder str){
        if(cur.endOfWord){
            set.add(str.toString());
        }
        
        int m = board.length;
        int n = board[0].length;
        int[] deta = {-1, 0, 1, 0, -1};
        
        for(int i = 0; i < 4; i++){
            int r = row + deta[i];
            int c = col + deta[i + 1];
            if(r >= 0 && r < m && c >= 0 && c < n && !visited[r][c]){
                int pos = board[r][c] - 'a';
                if(cur.children[pos] != null){
                    str.append("" + board[r][c]);
                    visited[r][c] = true;
                    dfs(r, c, visited, board, cur.children[pos], set, str);
                    str.deleteCharAt(str.length() - 1);
                    visited[r][c] = false;
                }
            }
        }
    }
}
