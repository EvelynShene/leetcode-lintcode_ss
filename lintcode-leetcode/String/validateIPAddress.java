/** 1222. Validate IP Address(lintcode) / 468. Validate IP Address(leetcode)
 *     Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
 *
 *     IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers,
 *  each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
 *
 *     Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
 *
 *     IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. 
 *  The groups are separated by colons (":").
 *     For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. 
 *  Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters 
 *  in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address
 *  (Omit leading zeros and using upper cases).
 *
 *     However, we don't replace a consecutive group of zero value with a single empty group using two consecutive
 *  colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
 *     Besides, extra leading zeros in the IPv6 is also invalid. 
 *  For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
 *
 *     Note: You may assume there is no extra space or special characters in the input string.
 *
 *     Example: 1) Input: "172.16.254.1" ; Output: "IPv4"
 *              2) Input: "2001:0db8:85a3:0:0:8A2E:0370:7334" ; Output: "IPv6"
 *              3) Input: "256.256.256.256" ; Output: "Neither"
 */
 
 //Method: 
 class Solution {
    public String validIPAddress(String IP){
        if(IP == null || IP.length() == 0){
            return "Neither";
        }
        if(IP.contains(".")){
            return isIPv4(IP);
        }
        else{
            return isIPv6(IP);
        }
    }
    
    public String isIPv4(String IP){
        if(IP.charAt(0)=='.' || IP.charAt(IP.length()-1)=='.'){
            return "Neither";
        }	
        String[] ip_seg = IP.split("\\."); //以'.'、'|' 和 '*' 等转义字符，必须得加 \\。
        if(ip_seg.length != 4){
            return "Neither";
        }
        for(int i = 0; i < 4; i++){
            if(ip_seg[i].length() == 0 || ip_seg[i].length() > 3){ 
                return "Neither";
            }
            if(ip_seg[i].length() != 1 && ip_seg[i].charAt(0) == '0'){ // deal case like "01"
                return "Neither";
            }
            for(int j = 0; j < ip_seg[i].length(); j++){
                if(ip_seg[i].charAt(j) < '0' || ip_seg[i].charAt(j) >'9'){
                    return "Neither";
                }
            }
            int num = Integer.valueOf(ip_seg[i]);
            if(num > 255){
                return "Neither";
            }
        }
        return "IPv4";
    }
    
    public String isIPv6(String IP){
        if(IP.charAt(0)==':' || IP.charAt(IP.length()-1)==':'){
            return "Neither";
        }
        String[] ip_seg = IP.split(":");
        if(ip_seg.length != 8){
            return "Neither";
        }
        for(int i = 0; i < 8; i++){
            if(ip_seg[i].length() == 0 || ip_seg[i].length() > 4){
                return "Neither";
            }
            for(int j = 0; j < ip_seg[i].length(); j++){
                if((ip_seg[i].charAt(j) >= '0' && ip_seg[i].charAt(j) <='9') || (ip_seg[i].charAt(j) >= 'a' && ip_seg[i].charAt(j) <='f') || (ip_seg[i].charAt(j) >= 'A' && ip_seg[i].charAt(j) <='F')){
                    continue;
                }
                else{
                    return "Neither";
                }
            }
        }
        return "IPv6";
    }
}
