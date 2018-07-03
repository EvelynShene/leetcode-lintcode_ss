/** 702. Concatenated String with Uncommon Characters of Two Strings(lintcode)
 *      Two strings are given and you have to modify 1st string such that all the common characters of the 2nd strings have to 
 *  be removed and the uncommon characters of the 2nd string have to be concatenated with uncommon characters of the 1st string.
 *
 *      Example: 1) Given s1 = aacdb, s2 = gafd ; return cbgf
 *               2) Given s1 = abcs, s2 = cxzca;  return bsxz
 *
 *      Note: 在是s1中重复出现的，但是没有在s2中出现的字符，出现几次就要留下几次，而且顺序要一致
 */
 
 //Method:
 public String concatenetedString(String s1, String s2) {
    // write your code here
    if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0){
        return s1+s2;
    }
    Set<Character> ins2 = new HashSet<Character>();
    for(int i = 0; i < s2.length(); i++){
        if(!ins2.contains(s2.charAt(i))){
            ins2.add(s2.charAt(i));
        }
    }
    Set<Character> ins12 = new HashSet<Character>();
    // StringBuilder s = new StringBuilder();
    String s = "";
    for(int i = 0; i < s1.length(); i++){
        if(ins2.contains(s1.charAt(i))){
            if(!ins12.contains(s1.charAt(i)))
                ins12.add(s1.charAt(i));
        }
        else{
            s += s1.charAt(i);
        }
    }
    for(int i = 0; i < s2.length(); i++){
        if(!ins12.contains(s2.charAt(i))){
            s += s2.charAt(i);
        }
    }
    return s;
}
