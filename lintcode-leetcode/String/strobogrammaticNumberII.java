/** 776. Strobogrammatic Number II(lintcode) / 247. Strobogrammatic Number II(leetcode - locked)
 *    A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *    Find all strobogrammatic numbers that are of length = n.
 *
 *    Example: Given n = 2, return ["11","69","88","96"].
 */ 

//My Method: DFS
public class Solution {
    char[][] m = {{'0','1','8','6','9'},{'0','1','8','9','6'}};
    public List<String> findStrobogrammatic(int n) {
        List<String> list = new ArrayList<String>();
        if(n == 0){
            list.add("");
            return list;
        }
        if(n == 1){
            list.add("0");
            list.add("1");
            list.add("8");
            return list;
        }
        for(int i = 1; i < m[0].length; i++){
            String str = "" + m[0][i] + m[1][i];
            getNextStrobogrammatic(list, str, 1, n-2);
        }
        return list;
    }
    public void getNextStrobogrammatic(List<String> list, String s, int insert_pos, int n){
        if(n == 0){
            list.add(s);
        }
        else if(n == 1){
            for(int i = 0; i < 3; i++){
                String str =  s.substring(0,insert_pos) + m[0][i] + s.substring(insert_pos);
                list.add(str);
            }
        }
        else{//n >= 2
            for(int i = 0; i < m[0].length; i++){
                String str =s.substring(0,insert_pos) + m[0][i] + m[1][i] + s.substring(insert_pos);
                getNextStrobogrammatic(list, str, insert_pos + 1, n-2);
            }
        }
    }
}

/* Method 2: 迭代 —— 考虑n是奇数和偶数
 *    1) 初始时，n == 0，则 s = ""; n == 1，则 s可以是"0", "1", "8";
 *    2) 若n >= 2；考虑n的奇偶性，
 *       a) 若n是奇数，则从n == 1开始，左右分别添加满足要求的映射数对，直到达到n;
 *       b) 若n是偶数，则从n == 0开始，左右分别添加满足要求的映射数对，直到达到n。
 *   【映射数对：{0,0},{1,1},{6,9},{8,8},{9,6}】
 *／
 public List<String> findStrobogrammatic(int n) {
    String[][] m = {{"0","1","8","6","9"},{"0","1","8","9","6"}};
    List<String> list = new ArrayList<String>();
    if(n % 2 == 0)
        list.add("");
    else{
        list.add("0");
        list.add("1");
        list.add("8");
    }

    for(int i = 0; i < n / 2; i++){
        List<String> tmp = new ArrayList<String>();
        for(int j = 0; j < list.size(); j++){
            for(int k = 0; k < 5; k++){
                if(i == n / 2 - 1 && k == 0)//数字不能以0开头
                    continue;
                tmp.add(m[0][k] + list.get(j) + m[1][k]);
            }
        }
        list = tmp;
    }
    return list;
 }
