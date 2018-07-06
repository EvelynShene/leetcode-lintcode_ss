/** 1104. Judge Route Circle(lintcode) /657. Judge Route Circle(leetcode)
 *      Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, 
 *  which means it moves back to the original place.
 *      The move sequence is represented by a string. And each move is represent by a character. 
 *  The valid robot moves are R (Right), L (Left), U (Up) and D (down). 
 *  The output should be true or false representing whether the robot makes a circle.
 *
 *      Example: 1) Input: "UD" ; Output: true
 *               2) Input: "LL" ; Output: false
 */
 
 public boolean judgeCircle(String moves) {
    // Write your code here
    if(moves == null || moves.length() == 0){
        return false;
    }
    int horizon = 0;
    int vertical = 0;
    for(int i = 0; i < moves.length(); i++){
        switch(moves.charAt(i)){
            case 'R':horizon++; break;
            case 'L':horizon--; break;
            case 'U':vertical++; break;
            case 'D':vertical--; break;
            default: break;
        }
    }
    if(horizon == 0 && vertical == 0){
        return true;
    }
    return false;
 }
