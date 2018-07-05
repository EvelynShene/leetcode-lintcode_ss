/** 212. Space Replacement(lintcode)
 *      Write a method to replace all spaces in a string with %20. The string is given in a characters array, 
 *   you can assume it has enough space for replacement and you are given the true length of the string.
 *      You code should also return the new length of the string after replacement.
 *
 *      Note: If you are using Java or Python，please use characters array instead of string.
 *
 *      Example: Given "Mr John Smith", length = 13.
 *               The string after replacement should be "Mr%20John%20Smith", you need to change the string in-place 
 *           and return the new length 17.
 *  
 *      Challenge: Do it in-place.
 */
 
 public int replaceBlank(char[] string, int length) {
    // write your code here
    if(length == 0){
        return 0;
    }
    // int[] space_pos = new int[length];
    int index = 0;
    for(int i = 0; i < length; i++){
        if(string[i] == ' '){
            // space_pos[index] = i;
            index++;
        }
    }
    int new_len = length + 2 * index;
    for(int i = new_len - 1, j = length - 1; i >= 0 && j >= 0; i--, j--){
        if(string[j] != ' '){
            string[i] = string[j];
        }
        else{
            string[i] = '0';
            string[i - 1] = '2';
            string[i - 2] = '%';
            i = i - 2;
        }
    }
    return new_len;
}
