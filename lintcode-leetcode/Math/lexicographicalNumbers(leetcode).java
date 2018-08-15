/** 386. Lexicographical Numbers(leetcode)
 *    Given an integer n, return 1 - n in lexicographical order.
 *
 *    For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 *
 *    Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 */
 
 //Method 1: Sort - O(nlogn) time and O(n) space complexity
 public List<Integer> lexicalOrder(int n) {
    String[] s = new String[n];
    for(int i = 1; i <= n; i++){
        s[i - 1] = "" + i;
    }
    Arrays.sort(s);
    List<Integer> list = new ArrayList<Integer>();
    for(int i = 0; i < n; i++){
        list.add(Integer.valueOf(s[i]));
    }
    return list;
 }
 
 //Method 2: Priority Queue ?? TLE (因为priority的offer()和poll()操作也是O(logn)的，所以以为可以AC，没想到超时了)
 
 /* Method 3: 迭代 - [from leetcode]
  *    从高位开始，一位一位的向下比较并排序
  */
 public List<Integer> lexicalOrder(int n) {
    List<Integer> list = new ArrayList<>(n);
    int curr = 1;
    for (int i = 1; i <= n; i++) {
        list.add(curr);
        if (curr * 10 <= n) {
            curr *= 10;
        } else if (curr % 10 != 9 && curr + 1 <= n) {
            curr++;
        } else {
            while ((curr / 10) % 10 == 9) {
                curr /= 10;
            }
            curr = curr / 10 + 1;
        }
    }
    return list;
 }
 
 /* Method 4: DFS - 本质思路同 Method 3 [from leetcode]
  *            1        2        3    ...
  *           /\        /\       /\
  *        10 ...19  20...29  30...39   ....
  *        /\        /\       /\
  *     101..109  201..209 301..309   ....
  */
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i <= 9; i++){
            if(i > n){
                break;
            }
            list.add(i);
            dfs(list, i*10, n);
        }
        return list;
    }
    
    public void dfs(List<Integer> list, int base, int n){
        for(int i = 0; i < 10; i++){
            int num = base + i;
            if(num > n){
                break;
            }
            list.add(num);
            dfs(list, num*10, n);
        }
    }
}
