/** 916. Palindrome Permutation(lintcode) / 266. Palindrome Permutation(leetcode - locked)
 *    Given a string, determine if a permutation of the string could form a palindrome.
 *
 *    Example:1) Given s = "code", return False.
 *            2) Given s = "aab", return True.
 *            3) Given s = "carerac", return True. 
 */
 
 //My Method: 所有的字符要么全是偶数个(s.length是偶数);要么只有一个字符的个数是奇数个(s.length是奇数)
 public boolean canPermutePalindrome(String s) {
    if(s == null || s.length() == 0)
        return false;
    int[] c = new int[256];
    int n = s.length();
    for(int i = 0; i < n; i++){
        c[s.charAt(i)]++;
    }

    int odd = 0;
    for(int i = 0; i < 256; i++){
        if(c[i] % 2 == 1){
            odd++;
        }
        if(odd > 2)
            return false;
    }

    if(n % 2 == 0 && odd == 0)
        return true;
    if(n % 2 == 1 && odd == 1)
        return true;
    return false;
 }
 
 /* Method 2: 
  *     用到了一个set，遍历字符串，如果某个字母不在set中，加入这个字母，如果字母已经存在，我们删除该字母，
  *   那么最终如果set中没有字母或是只有一个字母时，说明可以将字符串重排列成回文串。
  */
  
 /* Method 3:[from Internet] 本质同 Method 1
  *     一种bitset的解法: 建立一个256大小的bitset，每个字母根据其ASCII码值的不同都有其对应的位置，
  *   然后遍历整个字符串，遇到一个字符，就将其对应的位置的二进制数flip一下，就是0变1，1变0，那么遍历完成后，所有出现次数为偶数的对应
  *   位置还应该为0，而出现次数为奇数的时候，对应位置就为1了，那么我们最后只要统计1的个数，就知道出现次数为奇数的字母的个数了，
  *   只要个数小于2就是回文数
  */
