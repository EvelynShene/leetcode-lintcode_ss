/** 179. Largest Number(leetcode) / 184. Largest Number(lintcode)
 *    Given a list of non negative integers, arrange them such that they form the largest number.
 *
 *    Example: 1) Input: [1, 20, 23, 4, 8] ; Output: 8423201
 *             2) Input: [3,30,34,5,9] ; Output: "9534330"
 *
 *    Note: The result may be very large, so you need to return a string instead of an integer.
 *
 *    Challenge: Do it in O(nlogn) time complexity.
 */
 
 //My Method: Sort via Custom Comparator - O(nlogn) time and O(n) space complexity
 public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0){
            return "";
        }
        int n = nums.length;
        List<String> str = new ArrayList<String>();
        for(int i = 0; i < n; i++){
            str.add(String.valueOf(nums[i]));
        }
        Collections.sort(str, new Comparator<String>(){
        public int compare(String o1, String o2) {
            // TODO Auto-generated method stub
            int n = o1.length();
            int m = o2.length();
            int len = Math.min(n, m);
            for(int i = 0; i < len; i++){
                if(o1.charAt(i) - '0'< o2.charAt(i) - '0'){
                    return 1;
                }
                else if(o1.charAt(i) - '0' > o2.charAt(i) - '0'){
                    return -1;
                }
            }
            if(n == m)
                return 0;
            else{ // 332,3 => 3332; 23,2332 => 233223
                String s1 = o1 + o2; 
                String s2 = o2 + o1;
                for(int i = 0; i < m+n; i++){
                    if(s1.charAt(i) - '0'> s2.charAt(i) - '0'){
                        return -1;
                    }
                    else if(s1.charAt(i) - '0'< s2.charAt(i) - '0'){
                        return 1;
                    }
                }
                return 1;
            }
        }});
        StringBuilder newnum = new StringBuilder();
        for(int i = 0; i < str.size(); i++){
            if(str.get(i).equals("0") && newnum.toString().equals("0")) // 处理[0,0]
                break;
            newnum.append(str.get(i));
        }
        return newnum.toString();
    }
