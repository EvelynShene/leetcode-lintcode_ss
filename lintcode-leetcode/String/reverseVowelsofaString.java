/** 1282. Reverse Vowels of a String (lintcode) / 345. Reverse Vowels of a String (leetcode)
 *    Write a function that takes a string as input and reverse only the vowels of a string.
 *    
 *    Note: The vowels does not include the letter "y". 
 *
 *    Example: 1) Given s = "hello", return "holle".
 *             2) Given s = "lintcode", return "lentcodi".
 */
 
 //My Method: O(n) time
 public String reverseVowels(String s) {
    // write your code here
    if(s == null || s == "" || s.length() == 0){
        return s;
    }
    StringBuilder str = new StringBuilder(s);
    int left = 0;
    int right = s.length() - 1;
    while(left < right){
        while(left < s.length()){
            if(s.charAt(left) == 'a' || s.charAt(left) == 'e' || s.charAt(left) == 'i' || s.charAt(left) == 'o' || s.charAt(left) == 'u' || s.charAt(left) == 'A' || s.charAt(left) == 'E' || s.charAt(left) == 'I' || s.charAt(left) == 'O' || s.charAt(left) == 'U'){
                break;
            }
            left++;
        }
        while(right >= 0){
            if(s.charAt(right) == 'a' || s.charAt(right) == 'e' || s.charAt(right) == 'i' || s.charAt(right) == 'o' || s.charAt(right) == 'u' || s.charAt(right) == 'A' || s.charAt(right) == 'E' || s.charAt(right) == 'I' || s.charAt(right) == 'O' || s.charAt(right) == 'U'){
                break;
            }
            right--;
        }
        if(left < right){
            str.setCharAt(left,s.charAt(right));
            str.setCharAt(right,s.charAt(left));
            left++;
            right--;
        }
    }
    return str.toString();
 }
 
 //Method 2: Use HashSet [From jiuzhang] - O(n) time and O(n) space
 public String reverseVowels(String s) {
    if(s == null || s == "" || s.length() == 0){
        return s;
    }

    HashSet<Character> vowels = new HashSet<Character>();
    vowels.add('a');
    vowels.add('e');
    vowels.add('i');
    vowels.add('o');
    vowels.add('u');
    vowels.add('A');
    vowels.add('E');
    vowels.add('I');
    vowels.add('O');
    vowels.add('U');

    int[] vowel_pos = new int[s.length()];
    int index = 0;
    for(int i = 0; i < s.length(); i++){
        if(vowels.contains(s.charAt(i))){
            vowel_pos[index] = i;
            index++;
        }
    }

    StringBuilder str = new StringBuilder(s);
    for(int i = 0; i < index; i++){
        str.setCharAt(vowel_pos[i],s.charAt(vowel_pos[index-i-1]));
    }
    return str.toString();
 }
