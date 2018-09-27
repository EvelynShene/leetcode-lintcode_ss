/** 89. Gray Code(leetcode) / 411. Gray Code(lintcode)
 *      The gray code is a binary numeral system where two successive values differ in only one bit.
 *      Given a non-negative integer n representing the total number of bits in the code, print the sequence of 
 *   gray code. A gray code sequence must begin with 0.
 *
 *      Example: 1) Input: 2 ; Output: [0,1,3,2]
 *                  Explanation:
 *                   00 - 0
 *                   01 - 1
 *                   11 - 3
 *                   10 - 2
 *                  For a given n, a gray code sequence may not be uniquely defined.
 *                  For example, [0,2,3,1] is also a valid gray code sequence.
 *                   00 - 0
 *                   10 - 2
 *                   11 - 3
 *                   01 - 1
 *               2) Input: 0 ; Output: [0]
 *                  Explanation: We define the gray code sequence to begin with 0.
 *                                A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
 *                                Therefore, for n = 0 the gray code sequence is [0].
 */
 
 /* Method 1: 直接排列法 - O(2^n) time complexity
  *     以二进制为0值的格雷码为第0项，第1项改变最右边的位元(0->1 or 1->0)，
  *     第二项改变右起第一个为1的位元的左边位元(eg: 010->110 or 111 -> 101)，
  *     第三、四项方法同第一、二项，如此反复，即可排列出n个位元的格雷码。
  */
 public List<Integer> grayCode(int n) {
    List<Integer> res = new ArrayList<>();
    res.add(0);
    int num = (int)Math.pow(2,n);
    while(res.size() < num){
        int n1 = res.get(res.size() - 1);
        if((n1 & 1) == 0){
            n1++;
        }
        else{
            n1--;
        }
        res.add(n1);
        if(res.size() < num){
            int c = 1;
            while((n1 & 1) != 1){
                c++;
                n1 = n1 >> 1; 
            }
            int deta = 1 << c;
            if(((n1 >> 1) & 1) == 1){
                n1 = res.get(res.size() - 1) - deta;
            }
            else{
                n1 = res.get(res.size() - 1) + deta;
            }
            res.add(n1);
        }
    }
    return res;
}

/* Method 2: 镜面排列法
 *    思路：n位元的格雷码可以从n-1位元的格雷码以上下镜射后加上新位元的方式快速的得到
 *     n = 1       n = 2       n = 3
 *        0  ->    0  00       00  000
 *        1  ->    1  01  ->   01  001
 *                 1  11  ->   11  011
 *                 0  10       10  010
 *                             10  110
 *                             11  111
 *                             01  101
 *                             00  100
 */
public List<Integer> grayCode(int n) {
    List<Integer> res = new ArrayList<>();
    res.add(0);
    if(n == 0){
        return res;
    }
    for(int i = 0; i < n; i++){
        int size = res.size();
        for(int j = size - 1; j >= 0; j--){
            int num = (1 << i) | res.get(j);
            res.add(num);
        }
    }
    return res;
}
