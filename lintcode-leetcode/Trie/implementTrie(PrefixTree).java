/** 208. Implement Trie (Prefix Tree)(leetcode) / 442. Implement Trie (Prefix Tree)(lintcode)
 *      Implement a trie with insert, search, and startsWith methods.
 *
 *      Example:
 *             Trie trie = new Trie();
 *             trie.insert("apple");
 *             trie.search("apple");   // returns true
 *             trie.search("app");     // returns false
 *             trie.startsWith("app"); // returns true
 *             trie.insert("app");   
 *             trie.search("app");     // returns true
 *
 *      Note: 1) You may assume that all inputs are consist of lowercase letters a-z.
 *            2) All inputs are guaranteed to be non-empty strings.
 *
 *      [About Trie Tree - https://www.youtube.com/watch?v=AXjmTQ8LEoI]
 */


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
 
 //My Method: 
 class Trie {
    
    Trie[] children;
    boolean endOfWord;
    /** Initialize your data structure here. */
    public Trie() {
        endOfWord = false;
        children = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
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
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie cur = this;
        for(int i = 0; i < word.length(); i++){
            int pos = word.charAt(i) - 'a';
            if(cur.children[pos] == null){
                return false;
            }
            cur = cur.children[pos];
        }
        return cur.endOfWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie cur = this;
        for(int i = 0; i < prefix.length(); i++){
            int pos = prefix.charAt(i) - 'a';
            if(cur.children[pos] == null){
                return false;
            }
            cur = cur.children[pos];
        }
        return true;
    }
}
