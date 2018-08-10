/** 204. Count Primes(leetcode) / 1324. Count Primes(lintcode)
 *      Count the number of prime numbers less than a non-negative number, n.
 *
 *    Example: Input: 10 ; Output: 4
 *            Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
 
 /* Method: 合数必定可以由质数相乘得到。即对任意质数i，如果其不能被sqrt(i)前的所有质数整除，则其必定为质数
  *       （小于 sqrt(i) 的合数必定可以被小于 sqrt(i) 的质数合成）
  */
 public int countPrimes(int n) {
    List<Integer> primes = new ArrayList<Integer>();
    for(int i = 2; i < n; i++){
        if(i == 2){
            primes.add(2);
        }
        else{
            int j = 0;
            for(; j < primes.size(); j++){
                if((i % primes.get(j) != 0) && (i / primes.get(j) < primes.get(j))){
                    primes.add(i);
                    break;
                }
                if(i % primes.get(j) == 0){
                    break;
                }
            }
            if(j == primes.size()){
                primes.add(i);
            }
        }
    }
    return primes.size();
 }
