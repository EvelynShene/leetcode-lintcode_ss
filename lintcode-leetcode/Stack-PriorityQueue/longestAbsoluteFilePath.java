/** 388. Longest Absolute File Path(leetcode) / 643. Longest Absolute File Path(lintcode)
 *    https://leetcode.com/problems/longest-absolute-file-path/description/
 */
 
 //My Method: 
 public int lengthLongestPath(String input) {
    if(input == null || input.length() == 0){
        return 0;
    }
    Stack<Integer> stk_path = new Stack<Integer>();
    int start = 0;
    int level = 0;
    int max = 0;
    int n = input.length();

    for(int i = 0; i < n; i++){
        if(input.charAt(i) == '\n'){
            while(level < stk_path.size()){
                stk_path.pop();
            }                
            String file = input.substring(start, i);
            if(level == 0){
                stk_path.push(i - start);
            }
            else{
                stk_path.push(stk_path.peek() + 1 + i - start); 
            }
            if(file.contains(".")){
                max = Math.max(max, stk_path.peek());
            }
            //update start, level, and i
            level = 0;
            start = i + 1;
            if(i + 1 < n && input.charAt(i + 1) == ' '){
                int space = 0;
                while(i + 1 < n && input.charAt(i + 1) == ' '){
                    i++;
                    space++;
                }
                if(space >= 4){
                    level++;
                    start += 4;
                }
            }
            else{
                while(i + 1 < n && input.charAt(i + 1) == '\t'){
                    level++;
                    i++;
                }
                start = i + 1;
            }
        }
    }
    if(start < n){
        if(input.substring(start).contains(".")){
            while(level < stk_path.size()){
                stk_path.pop();
            }
            int res = n - start;
            if(start != 0){
                res++;
            }
            if(!stk_path.isEmpty()){
                res += stk_path.peek();
            }
            max = Math.max(max, res);
        }
    }
    return max;
}

//Method 2: Use split() to make code more concise
public int lengthLongestPath(String input) {
    if(input == null || input.length() == 0){
        return 0;
    }
    Stack<Integer> stk_path = new Stack<Integer>();
    int max = 0;
    String[] files = input.split("\n");
    for(String s: files){
        int level = s.lastIndexOf("\t") + 1;
        while(level < stk_path.size()){
            stk_path.pop();
        }
        if(stk_path.isEmpty()){
            stk_path.push(s.length());
        }
        else{
            stk_path.push(stk_path.peek() + s.length() - level + 1);
        }
        if(s.contains(".")){
            max = Math.max(max, stk_path.peek());
        }
    }
    return max;
}
