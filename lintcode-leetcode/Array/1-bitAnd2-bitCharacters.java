/** 973. 1-bit and 2-bit Characters(lintcode)/717. 1-bit and 2-bit Characters(leetcode)
 *  We have two special characters. The first character can be represented by one bit 0. 
 *    The second character can be represented by two bits (10 or 11).
 *  
 *  Now given a string represented by several bits. Return whether the last character must be a one-bit character or not. 
 *    The given string will always end with a zero.
 */
 
 //My Method:
 public boolean isOneBitCharacter(int[] bits) {
    if(bits == null || bits.length == 0){
        return false;
    }
    if(bits.length == 1){
        return true;
    }
    int i = 0;
    boolean end = false;
    while(i < bits.length){
        if(bits[i] == 0){
            i++;
            end = true;
        }
        else{
            i += 2;
            end = false;
        }
    }
    return end;
}

//Method 2(from leetcode): more concise
public boolean isOneBitCharacter(int[] bits) {
    // Write your code here
    int i = 0;
    while (i < bits.length - 1) {
        i += bits[i] + 1;
    }
    return i == bits.length - 1;
}
