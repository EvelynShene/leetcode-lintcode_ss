/** 1079. Count Binary Substrings(lintcode) / 696. Count Binary Substrings(leetcode)
 *      Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, 
 *   and all the 0's and all the 1's in these substrings are grouped consecutively.
 *      Substrings that occur multiple times are counted the number of times they occur.
 *
 *      Note: 1) s.length will be between 1 and 50,000.
 *            2) s will only consist of "0" or "1" characters.
 *
 *      Example: Input: "00110011" ; Output: 6
 *           Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's:
 *                            "0011", "01", "1100", "10", "0011", and "01".
 *                        Notice that some of these substrings repeat and are counted the number of times they occur.
 *                        Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 */
 
 //My Method: O(n) time and O(1) space complexity
 public int countBinarySubstrings(String s) {
    if(s == null || s.length() == 0){
        return 0;
    }
    int num = 0;
    int zero = 0;
    int ins0 = -1;
    int one = 0;
    int ins1 = -1;
    int i = 0;
    if(s.charAt(0) == '0'){
        ins0 = 0;
        while(i < s.length() && s.charAt(i) =='0'){
            i++;
            zero++;
        }
    }
    else{
        ins1 = 0;
        while(i < s.length() && s.charAt(i) =='1'){
            i++;
            one++;
        }
    }

    while(i < s.length()){
        if(ins0 < ins1){
            ins0 = i;
            zero = 0;
            while(i < s.length() && s.charAt(i) == '0'){
                i++;
                zero++;
            }
        }
        else{//ins0 > ins1
            ins1 = i;
            one = 0;
            while(i < s.length() && s.charAt(i) == '1'){
                i++;
                one++;
            }
        }
        num += Math.min(zero,one);
    }
    return num;
 }
 
 //More concise code
 public int countBinarySubstrings(String s) {
     if(s == null || s.length() == 0){
         return 0;
     }
     int num = 0;
     int prev = 0;
     int change = 0;
     for(int i = 1; i < s.length(); i++){
         if(s.charAt(i) != s.charAt(i-1)){
             if(prev != change){
                 num += Math.min(change - prev, i - change);
                 prev = change;
             }
             change = i;
         }
     }
     if(prev != change){
         num += Math.min(change - prev, s.length() - change);
     }
     return num;
 }
