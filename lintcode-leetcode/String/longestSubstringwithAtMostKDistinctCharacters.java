/** 386. Longest Substring with At Most K Distinct Characters(lintcode) /340. Longest Substring with At Most K Distinct Characters(leetcode - locked)
 *    Given a string s, find the length of the longest substring T that contains at most k distinct characters.
 *
 *    Example: Given s = "eceba", k = 3, 
 *         Explaination: T is "eceb" which its length is 4.
 *
 *    Challenge : O(n), n is the size of the string s.
 */
 
 //Method: Use HashMap
 public int lengthOfLongestSubstringKDistinct(String s, int k) {
    // write your code here
    if(s == null || s.length() == 0 || k == 0){
        return 0;
    }
    if(s.length() <= k){
        return s.length();
    }
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    int longest = 0;
    int left = 0;
    for(int i = 0; i < s.length(); i++){
        if(!map.containsKey(s.charAt(i))){
            map.put(s.charAt(i),1);
        }
        else{
            map.put(s.charAt(i),map.get(s.charAt(i)) + 1);
        }

        while(map.size() > k){
            if(map.get(s.charAt(left)) == 1){
                map.remove(s.charAt(left));
            }
            else{
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
            }
            left++;
        }
        longest = Math.max(longest,i - left + 1);
    }
    return longest;
 }
