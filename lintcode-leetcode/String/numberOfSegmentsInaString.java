/** 1243. Number of Segments in a String(lintcode)/ 434. Number of Segments in a String(leetcode)
 *    Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.
 *    Please note that the string does not contain any non-printable characters.
 *
 *    Example: Input: "Hello, my name is John" ; Output: 5 
 */
 
 public int countSegments(String s) {
    // write yout code here
    if(s == null || s.length() == 0){
        return 0;
    }
    int count = 0;
    boolean seg = false;
    for(int i = 0; i < s.length(); i++){
        if(!seg && s.charAt(i) != ' '){
            seg = true;
            count++;
        }
        else if(seg && s.charAt(i) == ' '){
            seg = false;
        }
    }
    return count;
 }
