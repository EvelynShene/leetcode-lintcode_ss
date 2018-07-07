/** 671. Rotate Words (lintcode)
 *      The words are same rotate words if rotate the word to the right by loop, and get another. 
 *    Count how many different rotate word sets in dictionary.
 *      E.g. picture and turepic are same rotate words.
 *      
 *      Note: All words are lowercase.
 *
 *      Example: Given dict = ["picture", "turepic", "icturep", "word", "ordw", "lint"] ; return 3.
 *                - "picture", "turepic", "icturep" are same ratote words.
 *                - "word", "ordw" are same too.
 *                - "lint" is the third word that different from the previous two words.
 */

//Method: 求某一个字符串word的rotate words，可以先将word变成 word + word，如果是其rotate word，新字符串一定包含这个字符串
public int countRotateWords(List<String> words) {
    // Write your code here
    if(words == null || words.size() == 0){
        return 0;
    }
    boolean[] rotatesame = new boolean[words.size()];
    int count = 0;
    for(int i = 0; i < words.size(); i++){
        if(!rotatesame[i]){
            count++;
            rotatesame[i] = true;
            String s = words.get(i) + words.get(i);
            for(int j = i + 1; j < words.size(); j++){
                if(s.length() /2 == words.get(j).length() && s.contains(words.get(j)))
                    rotatesame[j] = true;
            }
        }
    }
    return count;
 }
