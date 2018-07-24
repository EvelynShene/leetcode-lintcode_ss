/** 660. Read N Characters Given Read4 II - Call multiple times(lintcode) 
 *                              / 158. Read N Characters Given Read4 II - Call multiple times(leetcode - locked)
 *      The API: int read4(char *buf) reads 4 characters at a time from a file.
 *      The return value is the actual number of characters read. 
 *      For example, it returns 3 if there is only 3 characters left in the file.
 *      By using the read4 API, implement the function int read(char *buf, int n) that reads n characters 
 *   from the file.
 *
 *      Note: The read function may be called multiple times.(可多次调用read)
 *
 *      理解题意：这道题和157唯一的不同是read可以多次调用，那么比如第一次调用read(buf,3),第二次调用read(buf,5), 
 *            那么第一次调用会多读入一个字符，这个字符需要被记录，第二次调用时需要把多调用的字符加入。
 *
 *      基本思路：char *buf 是从文件中读取到的文件内容，n是能从文件中读出来的最多的字符个数；返回值是实际读得的字符个数。
 *        eg: 调用read(char[] buf, 10); 则函数从文件中读取最多10个字符存入buf数组中，并返回实际读到的字符个数：
 *            1）如果文件内容大于10个字符，则buf只读(存入)10个字符，返回值是10；
 *            2）如果文件内容小于10个字符，如8个，则buf只存入8个字符，返回值是8.
 */
 
 public class Solution extends Reader4 {
    /**
     * @param buf destination buffer
     * @param n maximum number of characters to read
     * @return the number of characters read
     */
    List<Character> cache = new ArrayList<Character>(); // 存储前次调用read时，多读入的字符
    
    public int read(char[] buf, int n) {
        // Write your code here
        int total = 0;
        while(cache.size() != 0){
            buf[cache.size()-1] = cache.get(cache.size()-1);
            cache.remove(cache.size()-1);
            total++;
        }
        int num = 0;
        char[] temp = new char[4];
        while(total < n){
            num = read4(temp);
            if(num < 4 || total + num >= n){
                break;
            }
            for(int i = 0; i < 4; i++){
                buf[total] = temp[i];
                total++;
            }
        }
        int len = Math.min(num, n - total);
        int i = 0;
        for(; i < len; i++){
            buf[total] = temp[i];
            total++;
        }
        while(i < num){
            cache.add(temp[i]);
            i++;
        }
        
        return total;
    }
}
