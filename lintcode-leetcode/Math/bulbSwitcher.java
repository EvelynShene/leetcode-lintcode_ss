/** 319. Bulb Switcher(leetcode) / 991. Bulb Switcher(lintcode)
 *    There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second
 *  bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). 
 *  For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb. Find how 
 *  many bulbs are on after n rounds.
 *
 *    Example: Input: 3 ; Output: 1 
 *            Explanation: 
 *                 At first, the three bulbs are [off, off, off].
 *                 After first round, the three bulbs are [on, on, on].
 *                 After second round, the three bulbs are [on, off, on].
 *                 After third round, the three bulbs are [on, off, off]. 
 *              So you should return 1, because there is only one bulb is on.
 */
 
 //Method 1: Brute Force [TLE]
 public int bulbSwitch(int n) {
    int[] bulbs = new int[n+1]; // 0 - off; 1 - on
    for(int i = 1; i <= n; i++){
        if(i == 1){
            Arrays.fill(bulbs,1);
        }
        else{
            for(int j = i; j <= n; j = j + i){
                bulbs[j] = bulbs[j] == 0? 1: 0;
            }
        }
    }
    int res = 0;
    for(int i = 1; i <= n; i++){
        if(bulbs[i] == 1){
            res++;
        }
    }
    return res;
 }
 
 /* Method 2: 找规律，只有平方数的位置的灯泡最后会亮着
  *     一开始所有的灯泡都是off，当一个灯泡被执行偶数次switch操作时它是关着的，当被执行奇数次switch操作时它是开着的。
  *   而每当灯泡会改变状态，也就是 toggle 时，是因为它出现在了某个数的整数倍上。
  *     1）对于第1个灯泡：1*1，会改变1次状态，即 off -》on
  *     2）对于第2个灯泡：1*2，2*1，会改变2次状态，即 off -》on -》off
  *     3）对于第3个灯泡：1*3，3*1，会改变2次状态，即 off -》on -》off
  *     4）对于第4个灯泡：1*4，2*2，4*1，会改变3次状态，即 off -》on -》off -》on
  *   依此类推……可以发现，每当找到一个数的整数round，总会找到对称的一个整数倍，例如 1*2，就肯定会有一个2*1。唯一的例外出现在平方数上，
  *   例如 4 = 2*2，只有一次整数倍。【每次作为偶数次整数倍，最终的灯泡都会还原为 off；只有作为奇数次整数倍，最终的灯泡都会 on。】
  *   最终亮的灯泡数目由小于n的多少个平方数决定。
  */
 public int bulbSwitch(int n) {
    int res = 1;
    while(res * res <= n){
        res++;
    }
    res--;
    return res;
 }
