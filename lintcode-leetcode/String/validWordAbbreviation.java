/** 637. Valid Word Abbreviation(lintcode) / 408. Valid Word Abbreviation(leetcode - locked)
 *    Given a non-empty string word and an abbreviation abbr, return whether the string matches with the given abbreviation.
 *    A string such as "word" contains only the following valid abbreviations:
 *        ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 *       Notice that only the above abbreviations are valid abbreviations of the string word.
 *    Any other string is not a valid abbreviation of word.
 *    
 *
 *    Example: 1) Given s = "internationalization", abbr = "i12iz4n": Return true.
 *             2) Given s = "apple", abbr = "a2e": Return false.
 */
 
 //Method: 
 public boolean validWordAbbreviation(String word, String abbr) {
    if(abbr == null || abbr == "" || abbr.length() == 0){
        return false;
    }
    int i = 0;
    int j = 0;
    while(i < abbr.length() && j < word.length()){
        StringBuilder num = new StringBuilder();
        while(i < abbr.length() && Character.isDigit(abbr.charAt(i))){
            if(num.toString().length() == 0 && abbr.charAt(i) == '0'){
                return false;
            }
            num.append(abbr.charAt(i));
            i++;
        }
        if(i == abbr.length()){
            j += Integer.valueOf(num.toString());
            if(j == word.length()){
                return true;
            }
            return false;
        }
        if(num.toString().length() != 0){
            j += Integer.valueOf(num.toString());
        }
        if(i < abbr.length() && j < word.length() && abbr.charAt(i) != word.charAt(j)){
            return false;
        }
        else{
            i++;
            j++;
        }
    }
    if(j > word.length()){
        return false;   
    }
    return true;
 }
 
 //More clear and concise code:
 public boolean validWordAbbreviation(String word, String abbr) {
    // write your code here
    if(abbr == null || abbr == "" || abbr.length() == 0){
        return false;
    }
    int i = 0;
    int j = 0;
    while(i < abbr.length() && j < word.length()){ 
        if(abbr.charAt(i) == word.charAt(j)){
            i++;
            j++;
        }
        else if(abbr.charAt(i) > '0' && abbr.charAt(i) <= '9'){ //0不能是一个数字的开头
            StringBuilder num = new StringBuilder();
            while(i < abbr.length() && Character.isDigit(abbr.charAt(i))){
                num.append(abbr.charAt(i));
                i++;
            }
            j += Integer.valueOf(num.toString());
        }
        else{
            return false;
        }
    }
    return (i == abbr.length() && j == word.length());
 }
