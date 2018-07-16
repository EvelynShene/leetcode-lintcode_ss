/** 640. One Edit Distance(lintcode) / 161. One Edit Distance(leetcode)
 *    Given two strings S and T, determine if they are both one edit distance apart.
 *
 *    Given s = "aDb", t = "adb" ; return true
 */
 
 //My Method: 
 public boolean isOneEditDistance(String s, String t) {
    if(s == null || t == null || Math.abs(s.length() - t.length()) > 1 || s.equals(t)){
        return false;
    }
    if(s.length() == 0 && t.length() == 0){
        return true;
    }

    int i = 0; 
    int j = 0;
    while(i < s.length() && j < t.length()){
        if(s.charAt(i) != t.charAt(j)){
            if(s.substring(i+1).equals(t.substring(j+1)) 
                || s.substring(i).equals(t.substring(j+1)) 
                || s.substring(i+1).equals(t.substring(j)))
            {
                return true;
            }
            else{
                return false;
            }
        }
        i++;
        j++;
    }
    return true; //前面全都相等，说明只有最后一位不相等，那就返回true
 }
