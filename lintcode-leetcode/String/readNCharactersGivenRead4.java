/** 157. Read N Characters Given Read4(leetcode - locked)
 *      The API: int read4(char *buf) reads 4 characters at a time from a file.
 *      The return value is the actual number of characters read. 
 *      For example, it returns 3 if there is only 3 characters left in the file.
 *      By using the read4 API, implement the function int read(char *buf, int n) that reads n characters 
 *   from the file.
 *
 *      Note: The read function will only be called once for each test case.(只调用一次read)
 *
 *      理解题意：char *buf 是从文件中读取到的文件内容，n是能从文件中读出来的最多的字符个数；返回值是实际读得的字符个数。
 *        eg: 调用read(char[] buf, 10); 则函数从文件中读取最多10个字符存入buf数组中，并返回实际读到的字符个数：
 *            1）如果文件内容大于10个字符，则buf只读(存入)10个字符，返回值是10；
 *            2）如果文件内容小于10个字符，如8个，则buf只存入8个字符，返回值是8.
 */
 
 // Method: 
 public int read(char[] buf, int n) {
    // Write your code here
    int total = 0;
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
    num = Math.min(num, n - total);
    for(int i = 0; i < num; i++){
        buf[total] = temp[i];
        total++;
    }
    return total;
 }
 
