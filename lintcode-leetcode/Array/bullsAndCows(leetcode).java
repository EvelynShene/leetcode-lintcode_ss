/** 299. Bulls and Cows(leetcode)
 *      You are playing the following Bulls and Cows game with your friend: 
 *   You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess,
 *   you provide a hint that indicates how many digits in said guess match your secret number exactly in 
 *   both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong 
 *   position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
 *
 *      Write a function to return a hint according to the secret number and friend's guess, 
 *   use A to indicate the bulls and B to indicate the cows. 
 *
 *      Please note that both secret number and friend's guess may contain duplicate digits.
 *
 *      Note: You may assume that the secret number and your friend's guess only contain digits, 
 *            and their lengths are always equal.
 *
 *      Example:1) Input: secret = "1807", guess = "7810" ; Output: "1A3B"
 *                 Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
 *
 *              2) Input: secret = "1123", guess = "0111" ; Output: "1A1B"
 *                 Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
 */
 
 //My Method: Two pass - O(n) time complexity
 public String getHint(String secret, String guess) {
    if(secret == null || guess == null){
        return null;
    }
    int n = secret.length();
    int A = 0;
    int B = 0;
    Map<Character,Integer> map = new HashMap<Character,Integer>();
    for(int i = 0; i < n; i++){
        if(secret.charAt(i) == guess.charAt(i)){
            A++;
        }
        if(!map.containsKey(secret.charAt(i))){
            map.put(secret.charAt(i),1);
        }
        else{
            map.put(secret.charAt(i), map.get(secret.charAt(i))+1);
        }
    }

    for(int i = 0; i < n; i++){
        char c = guess.charAt(i);
        if(map.containsKey(c)){
            B++;
            if(map.get(c) == 1){
                map.remove(c);
            }
            else{
                map.put(c, map.get(c)-1);
            }
        }
    }
    return (""+A+"A"+(B-A)+"B");
 }
 
 //Method 2: [idea from leetcode discuss]
 public String getHint(String secret, String guess) {
    int n = secret.length();
    int A = 0;
    int B = 0;
    int[] digits = new int[10];
    for(int i = 0; i < n; i++){
        int s = secret.charAt(i) - '0';
        int g = guess.charAt(i) - '0';
        if(s == g){
            A++;
        }
        else{
            if(digits[s] < 0) B++; //digits[s] < 0 => 说明之前在guess中出现过digits[s]这个数字
            if(digits[g] > 0) B++; 
            digits[g]--;
            digits[s]++;
        }
    }
    return (""+A+"A"+B+"B");
 }
