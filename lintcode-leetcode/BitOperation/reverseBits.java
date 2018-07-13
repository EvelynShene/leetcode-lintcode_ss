/** 190. Reverse Bits(leetcode)
 *    Reverse bits of a given 32 bits unsigned integer.
 *
 *    Example: Input: 43261596 ; Output: 964176192
 *        Explanation: 43261596 represented in binary as 00000010100101000001111010011100, 
 *              return 964176192 represented in binary as 00111001011110000010100101000000.
 *
 *    Follow up: If this function is called many times, how would you optimize it?
 */
 
 //Method: 
 public int reverseBits(int n) {
    int res = 0;
    for(int i = 0 ; i < 32; i++){
        if((n & 1) == 1){
            res = (res << 1) + 1;
        }
        else{
            res = res << 1;
        }
        n = n >> 1;
    }
    return res;
 }
 
 //More concise code from leetcode
 public int reverseBits(int n) {
    int ret = 0;
    for (int i = 0; i < 32; i++) {
        int curBit = (n >> i) & 1;
        ret += (curBit << (31-i));
    }
    return ret;
 }
