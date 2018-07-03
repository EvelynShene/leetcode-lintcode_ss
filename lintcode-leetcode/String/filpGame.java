/** 914. Flip Game(lintcode) / 293. Flip Game (leetcode)
 *    You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, 
 *  you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move 
 *  and therefore the other person will be the winner.
 *    Write a function to compute all possible states of the string after one valid move.
 *
 *    Example: Given s = "++++", after one move, it may become one of the following states:
 *                [
 *                  "--++",
 *                  "+--+",
 *                  "++--"
 *                ]
 *              If there is no valid move, return an empty list [].
 */
 
 // Method: O(n)
 public List<String> generatePossibleNextMoves(String s) {
    // write your code here
    List<String> list = new ArrayList<String>();
    if(s == null || s == "" || s.length() == 0){
        return list;
    }
    for(int i = 0; i < s.length()-1; i++){
        if(s.charAt(i) == '+' && s.charAt(i+1) == '+'){
            StringBuilder newstr  = new StringBuilder();
            newstr.append(s.substring(0,i));
            newstr.append("--");
            if(i-2 < s.length()){
                newstr.append(s.substring(i+2));
            }
            list.add(newstr.toString());
        }
    }
    return list;
 }
