/**  426. Restore IP Addresses(lintcode) / 93. Restore IP Addresses(leetcode)
 *      Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *    
 *      Example: Input: "25525511135" ; Output: ["255.255.11.135", "255.255.111.35"]
 */

//Method 1: DFS
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> list = new ArrayList<String>();
        if(s == null || s.length() < 4 || s.length() > 12){
            return list;
        }
        String[] ip = new String[4];
        dfs(0, s, ip, 0, list);
        return list;
    }
    
    public void dfs(int seg, String s, String[] ip, int start, List<String> list){
        if(seg < 4 && start < s.length()){
            if(seg == 3){
                if(s.charAt(start) == '0' && start < s.length() - 1){
                    return;
                }
                if(Integer.valueOf(s.substring(start)) < 256){
                    String x = ip[0] + "." + ip[1] + "." + ip[2] + "." + s.substring(start);
                    list.add(x);
                }
            }
            else{
                if(s.charAt(start) == '0'){
                    ip[seg] = "0";
                    dfs(seg + 1, s, ip, start + 1, list);
                }
                else{
                    for(int i = 1; i <= 3; i++){
                        if(start + i < s.length() && Integer.valueOf(s.substring(start,start+i)) < 256){
                            ip[seg] = s.substring(start,start+i);
                            dfs(seg + 1, s, ip, start + i, list);
                        }
                    }
                }
            }
        }
    }
}
