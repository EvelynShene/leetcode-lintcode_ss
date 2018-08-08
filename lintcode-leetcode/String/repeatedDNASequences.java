/** 187. Repeated DNA Sequences(leetcode) / 1335. Repeated DNA Sequences(lintcode)
 *      All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". 
 *  When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *      Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA 
 *  molecule.
 *
 *      Example: Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 *               Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */
 
 //My Method: Use HashMap - O(n) time and space complexity
 public List<String> findRepeatedDnaSequences(String s) {
    List<String> list = new ArrayList<String>();
    if(s == null || s.length() < 10){
        return list;
    }
    int n = s.length();
    Map<String,Integer> map = new HashMap<String,Integer>();
    for(int i = 0; i <= n - 10; i++){
        String sub = s.substring(i,i+10);
        if(!map.containsKey(sub)){
            map.put(sub,1);
        }
        else{
            if(map.get(sub) == 1){
                map.put(sub,2);
                list.add(sub);
            }
        }
    }
    return list;
}
