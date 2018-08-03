/** 316. Remove Duplicate Letters(leetcode) / 834. Remove Duplicate Letters(lintcode)
 *      Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear 
 *  once and only once. You must make sure your result is the smallest in lexicographical order among all possible
 *  results.
 *
 *      Example: 1) Given "bcabc" ; Return "abc"
 *               2) Given "cbacdcbc" ; Return "acdb"
 */
 
 /* Method 1: [idea from leetcode] -- O(n) time and space complexity
  *   1) 先找到每个以字母出现的最后一个位置，以“abacb”为例：
  *         a - 2; b - 4; c - 3
  *   2) 循环：找最小的最后位置，从前一次循环取得的字符的后一个位置开始，到最小最后位置，找其中的未取过的最小字母，即新字符的下一个字母。
  *         i) min_last_pos = 2; 遍历 0 -> min_last_pos，最小字母是a，所以新字符串的第一个字母是a；
  *         ii) min_last_pos = 3; 遍历 1 -> min_last_pos，未取过的最小字母是b，所以新字符串第二个字母是b；
  *        【从位置1开始遍历，是因为上一个循环取了0位置的a，所以1之后的位置在当前循环都有可能取(满足顺序的)；
  *          上一个循环不能取位置2的a，因为这样会导致位置1的b丢失，只能取到acb，但实际上abc是可行的。】
  *         iii) min_last_pos = 3; 遍历 2 -> min_last_pos, 最小的未取字符只有c，所以新字符串是"abc"。
  *        【min_last_pos还是3，因为上一个循环中并未取c，所以在未取字符中，最小的最后位置还是c的位置3。】
  */
  
  //code 1: Use Array
  public String removeDuplicateLetters(String s) {
    if(s == null || s.length() == 0){
        return s;
    }
    int[] last_pos = new int[26];
    Arrays.fill(last_pos,-1);
    boolean[] visited = new boolean[26];
    int count = 0;
    for(int i = 0; i < s.length(); i++){
        if(last_pos[s.charAt(i) - 'a'] == -1)
            count++;
        last_pos[s.charAt(i) - 'a'] = i;
    }
    int index = 0;
    StringBuilder str = new StringBuilder();

    while(str.length() < count){
        int min_last_pos = s.length()-1;
        for(int i = 0; i < 26; i++){
            if(last_pos[i] != -1 && !visited[i]){
                min_last_pos = Math.min(min_last_pos, last_pos[i]);
            }
        }
        char c = 'z' + 1;
        for(int i = index; i <= min_last_pos; i++){
            if(c > s.charAt(i) && !visited[s.charAt(i) - 'a']){
                c = s.charAt(i);
                index = i;
            }
        }
        index++;
        str.append(""+c);
        visited[c - 'a'] = true;
    }
    return str.toString();
 }
 
 //code 2: Use HashMap
 public String removeDuplicateLetters(String s) {
    // write your code here
    if(s == null || s.length() == 0)
        return s;
    int n = s.length();
    Map<Character, Integer> last_pos = new HashMap<Character, Integer>();
    for(int i = 0; i < n; i++){
        last_pos.put(s.charAt(i),i);
    }
    int count = last_pos.size();
    StringBuilder str = new StringBuilder();
    int start = 0;
    while(str.length() < count){
        int min_pos = n;
        for(Map.Entry<Character, Integer> entry: last_pos.entrySet()){
            if(entry.getValue() < min_pos){
                min_pos = entry.getValue();
            }
        }
        char c = 'z' + 1;
        for(int i = start; i <= min_pos; i++){
            if(last_pos.containsKey(s.charAt(i)) && s.charAt(i) < c){
                c = s.charAt(i);
                start = i;
            }
        }
        start++;
        str.append(""+c);
        last_pos.remove(c);
    }
    return str.toString();
 }
 
 /* Method 2: Use Stack [Idea and code from leetcode]
  *     感觉本质上和Method 1的思路是一致的。
  * —— https://leetcode.com/problems/remove-duplicate-letters/discuss/76769/Java-solution-using-Stack-with-comments
  */
 public String removeDuplicateLetters(String sr) {
    int[] res = new int[26]; //will contain number of occurences of character (i+'a')
    boolean[] visited = new boolean[26]; //will contain if character (i+'a') is present in current result Stack
    char[] ch = sr.toCharArray();
    for(char c: ch){  //count number of occurences of character 
        res[c-'a']++;
    }
    Stack<Character> st = new Stack<>(); // answer stack
    int index;
    for(char s:ch){ 
        index= s-'a';
        res[index]--;   //decrement number of characters remaining in the string to be analysed
        if(visited[index]) //if character is already present in stack, dont bother
            continue;
        //if current character is smaller than last character in stack which occurs later in the string again
        //it can be removed and  added later e.g stack = bc remaining string abc then a can pop b and then c
        while(!st.isEmpty() && s<st.peek() && res[st.peek()-'a']!=0){ 
            visited[st.pop()-'a']=false;
        }
        st.push(s); //add current character and mark it as visited
        visited[index]=true;
    }

    StringBuilder sb = new StringBuilder();
    //pop character from stack and build answer string from back
    while(!st.isEmpty()){
        sb.insert(0,st.pop());
    }
    return sb.toString();
 }
