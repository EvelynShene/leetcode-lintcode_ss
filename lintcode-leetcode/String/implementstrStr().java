/** 13. Implement strStr()(lintcode) / 28. Implement strStr()
 *   For a given source string and a target string, you should output the first index(from 0) of target string in source string.
 *   If target does not exist in source, just return -1.
 *
 *   Example: 1) If source = "source" and target = "target", return -1.
 *            2) If source = "abcdabcdefg" and target = "bcd", return 1.
 *
 *   Challenge: O(n2) is acceptable. Can you implement an O(n) algorithm? (hint: KMP)
 *
 *   Question: What should we return when needle is an empty string? This is a great question to ask during an interview.
 *     Answer: For the purpose of this problem, we will return 0 when needle is an empty string. 
 *              This is consistent to C's strstr() and Java's indexOf().
 */
 
 //Method: O(n^2) 
public int strStr(String source, String target) {
    // write your code here
    if(target == null){
        return -1;
    }
    if( target == "" || target.length() == 0 ){
        return 0;
    }
    if( source == null || source.length() == 0 ){
        return -1;
    }
    if(source.length() < target.length()){
        return -1;
    }
    for(int i = 0; i <= source.length() - target.length(); i++){
        if(source.charAt(i) == target.charAt(0)){
            int j = 1;
            while(j < target.length()){
                if(source.charAt(i+j) != target.charAt(j)){
                    break;
                }
                j++;
            }
            if(j >= target.length()){
                return i;
            }
        }
    }
    return -1;
}
