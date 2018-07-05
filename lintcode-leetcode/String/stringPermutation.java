/** 211. String Permutation(lintcode)
 *    Given two strings, write a method to decide if one is a permutation of the other.
 *
 *    Example: abcd is a permutation of bcad, but abbe is not a permutation of abe
 */
 
 //Method: 
 public boolean Permutation(String A, String B) {
    // write your code here
    if(A == null || B == null){
        return false;
    }
    if(A.length() != B.length()){
        return false;
    }
    boolean permutation = true;
    int len = A.length();
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    for(int i = 0; i < len; i++){
        if(!map.containsKey(A.charAt(i))){
            map.put(A.charAt(i),1);
        }
        else{
            int count = map.get(A.charAt(i));
            count++;
            map.put(A.charAt(i),count);
        }
    }
    for(int i = 0; i < len; i++){
        if(map.containsKey(B.charAt(i))){
            int count = map.get(B.charAt(i));
            if(count == 1){
                map.remove(B.charAt(i));
            }
            else{
                count--;
                map.put(B.charAt(i),count);
            }
        }
        else{
            permutation = false;
        }
    }
    return permutation;
 }
