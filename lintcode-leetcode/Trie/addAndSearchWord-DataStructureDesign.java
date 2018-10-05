/** 211. Add and Search Word - Data structure design(leetcode) / 473. Add and Search Word - Data structure design(lintcode)
 *      Design a data structure that supports the following two operations:
 *          void addWord(word)
 *          bool search(word)
 *      search(word) can search a literal word or a regular expression string containing only letters a-z or '.'. 
 *   A '.' means it can represent any one letter.
 *
 *      Example: addWord("bad")
 *               addWord("dad")
 *               addWord("mad")
 *               search("pad") -> false
 *               search("bad") -> true
 *               search(".ad") -> true
 *               search("b..") -> true
 *
 *      Note: You may assume that all words are consist of lowercase letters a-z.
 */

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
 
 //My Method: Use HashMap to construct Trie Tree
 class WordDictionary {
    class TrieNode{
        Map<Character, TrieNode> children;
        boolean endOfWord;
        
        public TrieNode(){
            endOfWord = false;
            children = new HashMap<Character, TrieNode>();
        }
    }
    
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++){
            Character c = word.charAt(i);
            if(!cur.children.containsKey(c)){
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
        }
        cur.endOfWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchWord(word, 0, root);
    }
    
    public boolean searchWord(String word, int pos, TrieNode cur){
        if(pos == word.length()){
            return cur.endOfWord;
        }
        
        Character c = word.charAt(pos);
        if(c == '.'){
            Map<Character, TrieNode> child = cur.children;
            if(child.size() == 0){
                return false;
            }
            for(Map.Entry<Character, TrieNode> entry: child.entrySet()){
                if(searchWord(word, pos + 1, entry.getValue())){
                    return true;
                }
            }
            return false;
        }
        
        //c != '.', c is a lowercase letter
        if(!cur.children.containsKey(c)){
            return false;
        }
        else{
            return searchWord(word, pos + 1, cur.children.get(c));
        }
    }
}

//Method 2: Use Array to construct Trie Tree
class WordDictionary {
    class TrieNode{
        TrieNode[] children;
        boolean endOfWord;
        
        public TrieNode(){
            endOfWord = false;
            children = new TrieNode[26];
        }
    }
    
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++){
            Character c = word.charAt(i);
            if(cur.children[c - 'a'] == null){
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.endOfWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchWord(word, 0, root);
    }
    
    public boolean searchWord(String word, int pos, TrieNode cur){
        if(pos == word.length()){
            return cur.endOfWord;
        }
        
        Character c = word.charAt(pos);
        if(c == '.'){
            TrieNode[] child = cur.children;
            for(int i = 0; i < 26; i++){
                if(child[i] != null && searchWord(word, pos + 1, child[i])){
                    return true;
                }
            }
            return false;
        }
        
        //c != '.', c is a lowercase letter
        if(cur.children[c - 'a'] == null){
            return false;
        }
        else{
            return searchWord(word, pos + 1, cur.children[c - 'a']);
        }
    }
}
