/** 360. Sort Transformed Array(leetcode - locked) / 906. Sort Transformed Array(lintcode)
 *      Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the 
 *  form f(x) = ax2 + bx + c to each element x in the array.
 *      The returned array must be in sorted order.
 *
 *      Note: Expected time complexity: O(n)
 *
 *      Example: 1) Given nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5, return [3, 9, 15, 33]
 *               2) Given nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5, return [-23, -5, 1, 7]
 */
 
 //Method 1: Sort Array - O(nlogn) time complexity
 public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
    int n = nums.length;
    int[] res = new int[n];
    for(int i = 0; i < n; i++){
        res[i] = a * nums[i] * nums[i] + b * nums[i] + c;
    }
    Arrays.sort(res);
    return res;
 }
 
 /* Method 2: 数学公式 - O(n) time complexity
  *   对于一个方程f(x) = ax2 + bx + c:
  *     1) 如果a>0，则抛物线开口朝上，那么两端的值比中间的大;
  *     2) 如果a<0，则抛物线开口朝下，则两端的值比中间的小;
  *     3) 当a=0时，则为直线方法，是单调递增或递减的。
  *     所以可以设置两个指针left，right指向数组的两端，也就是抛物线的两端。如果a>0,当前最大值一定在两端出现；如果a<0，当前最小值也
  *   一定在两端出现。然后指针向中间移，重复比较过程，直到把res数组都填满。
  */
  
 public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
    int n = nums.length;
    int[] res = new int[n];
    int left = 0;
    int right = n - 1;

    if(a > 0){
        int index = n - 1;
        while(left <= right){
            int n1 = a * nums[left] * nums[left] + b * nums[left] + c;
            int n2 = a * nums[right] * nums[right] + b * nums[right] + c;
            if(n1 > n2){
                res[index] = n1;
                left++;
            }
            else{
                res[index] = n2;
                right--;
            }
            index--;
        }
    }
    else if(a < 0){
        int index = 0;
        while(left <= right){
            int n1 = a * nums[left] * nums[left] + b * nums[left] + c;
            int n2 = a * nums[right] * nums[right] + b * nums[right] + c;
            if(n1 < n2){
                res[index] = n1;
                left++;
            }
            else{
                res[index] = n2;
                right--;
            }
            index++;
        }
    }
    else{
        if(b > 0){
            for(int i = 0; i < n; i++){
                res[i] = b * nums[i] + c;
            }
        }
        else{
            for(int i = n - 1; i >= 0; i--){
                res[i] = b * nums[n - i - 1] + c;
            }
        }
    }
    return res;
 }
