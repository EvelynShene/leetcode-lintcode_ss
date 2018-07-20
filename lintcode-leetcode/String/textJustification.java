/** 1361. Text Justification(lintcode) / 68. Text Justification(leetcode)
 *     Given an array of words and a width maxWidth, format the text such that each line has exactly 
 *  maxWidth characters and is fully (left and right) justified.
 *     You should pack your words in a greedy approach; that is, pack as many words as you can in each line. 
 *  Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *     Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line 
 *  do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on
 *  the right.
 *     For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 *     Note: 1) A word is defined as a character sequence consisting of non-space characters only.
 *           2) Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 *           3) The input array words contains at least one word.
 *
 *     Example: Input: words = ["This", "is", "an", "example", "of", "text", "justification."]; maxWidth = 16
 *              Output:
 *                    [
 *                       "This    is    an",
 *                       "example  of text",
 *                       "justification.  "
 *                    ]
 *
 *              Input: words = ["What","must","be","acknowledgment","shall","be"]; maxWidth = 16
 *              Output:
 *                    [
 *                      "What   must   be",
 *                      "acknowledgment  ",
 *                      "shall be        "
 *                    ]
 *              Explanation: Note that the last line is "shall be    " instead of "shall     be",
 *                           because the last line must be left-justified instead of fully-justified.
 *                           Note that the second line is also left-justified becase it contains only one word.
 */
 
 //My Method: 
 class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<String>();
        int len = words[0].length();
        int i = 1;
        int num = 1;
        while(i < words.length){
            if(len + 1 + words[i].length() <= maxWidth){// 1 = space
                len += 1 + words[i].length();
                num++;
            }
            else{
                if(num != 1){//more than one words
                    int extra_space = (maxWidth - len + num - 1) % (num - 1);
                    int space = (maxWidth - len + num - 1) / (num - 1);
                    StringBuilder s = new StringBuilder();
                    for(int j = i - num; j < i - 1; j++){
                        s.append(words[j]);
                        for(int x = 0; x < space; x++){
                            s.append(" ");
                        }
                        if(extra_space != 0){
                            s.append(" ");
                            extra_space--;
                        }
                    }
                    s.append(words[i-1]);
                    list.add(s.toString());
                }
                else{
                    StringBuilder s = new StringBuilder(words[i-1]);
                    for(int x = 0; x < maxWidth - words[i-1].length(); x++){
                        s.append(" ");
                    }
                    list.add(s.toString());
                }
                len = words[i].length();
                num = 1;
            }
            i++;
        }
        StringBuilder s = new StringBuilder(words[i-num]);
        for(num = num - 1; num > 0; num--){
            s.append(" "+words[i - num]);
        }
        int extra_space = maxWidth - s.length();
        for(int x = 0; x < extra_space; x++){
            s.append(" ");
        }
        list.add(s.toString());
        return list;
    }
}
