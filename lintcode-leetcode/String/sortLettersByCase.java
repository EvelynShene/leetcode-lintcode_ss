/** 49. Sort Letters by Case(lintcode)
 *    Given a string which contains only letters. Sort it by lower case first and upper case second.
 *
 *    Note: It's NOT necessary to keep the original order of lower-case letters and upper case letters.
 *
 *    Example: For "abAcD", a reasonable answer is "acbAD"
 *
 *    Challenge: Do it in one-pass and in-place.
 */
 
 public void sortLetters(char[] chars) {
    // write your code here
    if(chars == null || chars.length < 2){
        return;
    }
    int left = 0;
    int right = chars.length - 1;
    while(left < right){
        while(left < right && chars[left] >= 'a' && chars[left] <= 'z'){
            left++;
        }
        while(left < right && chars[right] >= 'A' && chars[right] <= 'Z'){
            right--;
        }
        if(left < right){
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }
 }
