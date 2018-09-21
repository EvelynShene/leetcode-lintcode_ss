/** 313. Super Ugly Number(leetcode) / 518. Super Ugly Number(lintcode)
 *    Write a program to find the nth super ugly number.
 *    Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
 *
 *    Example: Input: n = 12, primes = [2,7,13,19] ; Output: 32 
 *             Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 
 *                      super ugly numbers given primes = [2,7,13,19] of size 4.
 *
 *    Note: 1) 1 is a super ugly number for any given primes.
 *          2) The given numbers in primes are in ascending order.
 *          3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 *          4) The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 */
 
 //My Method: Use PriorityQueue
 class Solution {
    class Factor{
        int prime;
        int index;
        int next;
        public Factor(int prime, int index, int next){
            this.prime = prime;
            this.index = index;
            this.next = next;
        }
    }
    
    public int nthSuperUglyNumber(int n, int[] primes) {
        if(n == 1){
            return 1;
        }
        List<Integer> p = new ArrayList<>();
        Set<Integer> set = new HashSet<Integer>();
        p.add(1);
        PriorityQueue<Factor> heap = new PriorityQueue<Factor>(new Comparator<Factor>(){
            public int compare(Factor o1, Factor o2){
                return o1.prime - o2.prime;
            }
        });
        for(int i = 0; i < primes.length; i++){
            Factor tmp = new Factor(primes[i], i, 1);
            heap.offer(tmp);
        }
        int c = 1;
        while(c != n){
            Factor tmp = heap.poll();
            if(!set.contains(tmp.prime)){
                set.add(tmp.prime);
                p.add(tmp.prime);
                c++;
            }
            tmp.prime = p.get(tmp.next) * primes[tmp.index];
            tmp.next++;
            heap.offer(tmp);
        }
        return p.get(n - 1);
    }
}
