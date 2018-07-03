/** 55. Compare Strings(lintcode)
 *     Compare two strings A and B, determine whether A contains all of the characters in B.
 *  The characters in string A and B are all Upper Case letters.
 *
 *     Note: The characters of B in A are not necessary continuous or ordered.
 *
 *     Example 1) For A = "ABCD", B = "ACD", return true.
 *             2) For A = "ABCD", B = "AABC", return false.
 */
 
 //My Method: Use HashMap
 public boolean compareStrings(String A, String B) {
    // write your code here
    if(A == null || B == null){
        return false;
    }
    if(B == "" || B.length() == 0){
        return true;
    }
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    for(int i = 0; i < A.length(); i++){
        int count = 1;
        if(map.containsKey(A.charAt(i))){
            count = map.get(A.charAt(i)) + 1;
        }
        map.put(A.charAt(i), count);
    }
    boolean contain = true;
    for(int i = 0; i < B.length(); i++){
        if(!map.containsKey(B.charAt(i))){
            contain = false;
            break;
        }
        int count = map.get(B.charAt(i));
        count--;
        if(count == 0){
            map.remove(B.charAt(i));
        }
        else{
            map.put(B.charAt(i),count);
        }
    }
    return contain;
 }
 
 /* Method 2: [Idea from Jiuzhang]
  *  所有可能的字符都是大写字母，所以用一个数组count[]记录26个字母中每个字母出现的次数。
  */
 public boolean compareStrings(String A, String B) {
    // write your code here
    if(A == null || B == null){
        return false;
    }
    if(B == "" || B.length() == 0){
        return true;
    }

    int[] count = new int[26];
    for(int i = 0; i < A.length(); i++){
        count[A.charAt(i) - 'A']++;
    }
    for(int i = 0; i < B.length(); i++){
        count[B.charAt(i) - 'A']--;
        if(count[B.charAt(i) - 'A'] < 0){
            return false;
        }
    }
    return true;
 }
