/** 288. Unique Word Abbreviation(leetcode) / 648. Unique Word Abbreviation(lintcode)
 *      An abbreviation of a word follows the form <first letter><number><last letter>. 
 *  Below are some examples of word abbreviations:
 *           a) it                      --> it    (no abbreviation)
 *
 *                1
 *                ↓
 *           b) d|o|g                   --> d1g
 *
 *                         1    1  1
 *                1---5----0----5--8
 *                ↓   ↓    ↓    ↓  ↓    
 *           c) i|nternationalizatio|n  --> i18n
 *
 *                         1
 *                1---5----0
 *                ↓   ↓    ↓
 *           d) l|ocalizatio|n          --> l10n
 *      Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
 *  A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
 *
 *      Example: Given dictionary = [ "deer", "door", "cake", "card" ]
 *                 isUnique("dear") -> false
 *                 isUnique("cart") -> true
 *                 isUnique("cane") -> false
 *                 isUnique("make") -> true
 */

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */
 
 //My Method:
 class ValidWordAbbr {
    Set<String> unique_abbreviation;
    Set<String> non_unique_abbreviation;
    Set<String> dict;
    
    public ValidWordAbbr(String[] dictionary) {
        unique_abbreviation = new HashSet<String>();
        non_unique_abbreviation = new HashSet<String>();
        dict = new HashSet<>();
        
        for(int i = 0; i < dictionary.length; i++){
            if(!dict.contains(dictionary[i])){
                dict.add(dictionary[i]);
                String abbr = dictionary[i];
                if(dictionary[i].length() > 2){
                    abbr = "" + abbr.charAt(0) + (abbr.length() - 2) + abbr.charAt(abbr.length() - 1);
                }

                if(!non_unique_abbreviation.contains(abbr)){
                    if(unique_abbreviation.contains(abbr)){
                        unique_abbreviation.remove(abbr);
                        non_unique_abbreviation.add(abbr);
                    }
                    else{
                        unique_abbreviation.add(abbr);
                    }
                }
            }
        }
    }
    
    public boolean isUnique(String word) {
        
        String abbr = word;
        if(word.length() > 2){
            abbr = "" + word.charAt(0) + (word.length() - 2) + word.charAt(word.length() - 1);
        }
        
        if(non_unique_abbreviation.contains(abbr)){
            return false;
        }
        if(unique_abbreviation.contains(abbr) && !dict.contains(word)){
            return false;
        }
        return true;
    }
}

//Method 2:
class ValidWordAbbr {
    // abbreviation<abbr, word>: 
    //   -> if there are more than one word in the dictionary that has same abbreviation, set value "word" be ""
    Map<String, String> abbreviation;
    
    public ValidWordAbbr(String[] dictionary) {
        abbreviation = new HashMap<String, String>(); 
        
        for(int i = 0; i < dictionary.length; i++){
            String abbr = dictionary[i];
            if(abbr.length() > 2){
                abbr = "" + abbr.charAt(0) + (abbr.length() - 2) + abbr.charAt(abbr.length() - 1);
            }
            
            if(abbreviation.containsKey(abbr)){
                if(!abbreviation.get(abbr).equals(dictionary[i])){
                    abbreviation.put(abbr, "");
                }
            }
            else{
                abbreviation.put(abbr, dictionary[i]);
            }
        }
    }
    
    public boolean isUnique(String word) {
        
        String abbr = word;
        if(word.length() > 2){
            abbr = "" + word.charAt(0) + (word.length() - 2) + word.charAt(word.length() - 1);
        }
        
        return !abbreviation.containsKey(abbr) || abbreviation.get(abbr).equals(word);
        
        //same as:
        // if(!abbreviation.containsKey(abbr)){
        //     return true;
        // }
        // else{
        //     if(abbreviation.get(abbr).equals(word)){
        //         return true;
        //     }
        //     return false;
        // }
    }
}
