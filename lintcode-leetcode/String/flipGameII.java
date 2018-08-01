/** 913. Flip Game II(lintcode) / 294. Flip Game II(leetcode - locked)
 *      You are playing the following Flip Game with your friend: Given a string that contains only these two 
 *  characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". 
 *  The game ends when a person can no longer make a move and therefore the other person will be the winner.
 *      Write a function to determine if the starting player can guarantee a win.
 *
 *      Example: Given s = "++++", return true.
 *          Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 *
 *      Challenge: Derive your algorithm's runtime complexity.
 */

/* My Method: 递归 - O(2^n) time and space complexity
 *       canWin()表示玩家做出最优决策必赢的结果，所以有“全集合 = 先手玩家必赢 + 后手玩家必赢”
 *  或者：canWin是能不能保证第一个人赢的结果，对于第一个人只要他能有一个方法赢就行；而第二个人必须所有方法都无法阻止第一个人赢才行。
 */
public boolean canWin(String s) {
    if(s == null || s.length() == 0){
        return false;
    }
    int n = s.length();
    for(int i = 0; i < n - 1; i++){
        if(s.charAt(i) == '+' && s.charAt(i+1) == '+'){
            StringBuilder tmp = new StringBuilder(s.substring(0,i));
            tmp.append("--");
            if(i + 1 != n){
                tmp.append(s.substring(i+2));
            }
            if(!canWin(tmp.toString())){ //the other person cannot win == the starting player can guarantee a win
                return true;
            }
        }
    }
    return false;
}

//Method 2: DP  —— http://www.1point3acres.com/bbs/thread-144510-1-1.html(没太明白，先存着)
