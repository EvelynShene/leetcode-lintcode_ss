/** 133. Longest Word(lintcode)
 *    Given a dictionary, find all of the longest words in the dictionary.
 *
 *    Example: 1) Given
 *                  {
 *                    "dog",
 *                    "google",
 *                    "facebook",
 *                    "internationalization",
 *                    "blabla"
 *                  }
 *               the longest words are(is) ["internationalization"].
 *             2) Given
 *                  {
 *                    "like",
 *                    "love",
 *                    "hate",
 *                    "yes"
 *                  }
 *               the longest words are ["like", "love", "hate"].
 *
 *    Challenge: It's easy to solve it in two passes, can you do it in one pass?
 */
 
 //Method 1: Two Pass
 public List<String> longestWords(String[] dictionary) {
    List<String> longestwords = new ArrayList<String>();
    if(dictionary == null || dictionary.length == 0){
        return longestwords;
    }
    int max = 0;
    for(int i = 0; i < dictionary.length; i++){
        max = Math.max(max,dictionary[i].length());
    }
    for(int i = 0; i < dictionary.length; i++){
        if(max == dictionary[i].length()){
            longestwords.add(dictionary[i]);
        }
    }
    return longestwords;
 }
 
 //Method 2: One Pass
 public List<String> longestWords(String[] dictionary) {
    // write your code here
    List<String> longestwords = new ArrayList<String>();
    if(dictionary == null || dictionary.length == 0){
        return longestwords;
    }
    int max = 0;
    for(int i = 0; i < dictionary.length; i++){
        if(dictionary[i].length() > max){
            max = dictionary[i].length();
            longestwords.clear();
            longestwords.add(dictionary[i]);
        }
        else if(dictionary[i].length() == max){
            longestwords.add(dictionary[i]);
        }
    }
    return longestwords;
 }
