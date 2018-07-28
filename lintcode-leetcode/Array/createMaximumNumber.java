/** 552. Create Maximum Number(lintcode) / 321. Create Maximum Number(leetcode)
 *     Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of 
 *  length k <= m + n from digits of the two. The relative order of the digits from the same array must be 
 *  preserved. Return an array of the k digits.
 *
 *     Note: You should try to optimize your time and space complexity.
 *
 *     Example: 1) Given nums1 = [3, 4, 6, 5], nums2 = [9, 1, 2, 5, 8, 3], k = 5
 *                 return [9, 8, 6, 5, 3]
 *              2) Given nums1 = [6, 7], nums2 = [6, 0, 4], k = 5
 *                 return [6, 7, 6, 0, 4]
 *              3) Given nums1 = [3, 9], nums2 = [8, 9], k = 3
 *                 return [9, 8, 9]
 */
 
 /* Method:
  *  第一步：从一个数组里挑k个数字组成最大的数 maxNumberWithKLen(int[] nums, int k) ——— 借助栈和贪心算法来实现，步骤如下：
  *   1）新建一个空栈stack；遍历数组 nums
  *       a)弹出栈顶元素如果栈顶元素比nums[i]小,直到
  *         1、栈空
  *         2、剩余的数组不足以填满栈至 k
  *       b)如果栈的大小小于 k ，则压入nums[i]
  *   2) 遍历结束，返回栈stack
  *  【因为栈的长度是k，所以也可以用数组来实现这个栈。时间复杂度为O(n)，因为每个元素最多被push和pop一次。】
  *
  *  第二步：给定两个数组，长度分别为m 和n，要得到长度为 k = m+n 的最大数。这是一个原问题的特殊情况，用贪心算法。
  *   1) 同时遍历两个数组，每次都选取较大的数放到新数中
  *   2) 如果当前两个数相等，向后继续比较，知道不想等时取大的数所在的那一边的那个相等的数。
  *  【如果一直相等，然后其中一个数组遍历到最后了，那么取长度长的那个数组里的那个相等的数】
  *
  *  第三步：第二步的特殊情况一般化：
  *   1) 首先把k个数分成两部分，i 和 k-i，用第一步中的函数求出两个数组中的长度为 i 和 k-i 的最大值。
  *   2) 然后用第二步的方法将它们融合。最后我们从所有的结果中找出最大值。
  */
 class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        if(m + n == k){
            return mergeArray(nums1,nums2);
        }
        else{// m + n > k
            int[] arr = new int[k];
            int[] temp = new int[k];
            for(int i = 0; i <= k; i++){ // Take i elements from nums1 and k-i elements from nums2
                if(m < i || n < k - i){
                    continue;
                }
                if(i == 0){
                    arr = maxNumberWithKLen(nums2,k);
                    temp = arr;
                }
                else if(i == k){
                    temp = maxNumberWithKLen(nums1,k);
                }
                else{
                    temp = mergeArray(maxNumberWithKLen(nums1,i),maxNumberWithKLen(nums2,k-i));
                }
                if(isGreat(temp,arr,k)){
                    arr = temp;
                }
            }
            return arr;
        }
        
    }
    public boolean isGreat(int[] nums1, int[] nums2, int k){// Check if nums1 > nums2, return true if nums1 > nums2
        for(int i = 0; i < k; i++){
            if(nums1[i] > nums2[i]){
                return true;
            }
            else if(nums2[i] > nums1[i]){
                return false;
            }
        }
        return false;
    }
    public int[] mergeArray(int[] nums1, int[] nums2){
        int m = nums1.length;
        int n = nums2.length;
        int i = 0;
        int j = 0;
        int index = 0;
        int[] arr = new int[m+n];
        while(i < m && j < n){
            if(nums1[i] < nums2[j]){
                arr[index] = nums2[j];
                j++;
            }
            else if(nums1[i] > nums2[j]){
                arr[index] = nums1[i];
                i++;
            }
            else{
                int x = i;
                int y = j;
                while(x < m && y < n && nums1[x] == nums2[y]){
                    x++;
                    y++;
                }
                if((x < m && y < n && nums1[x] > nums2[y]) || (x < m && y >= n)){
                    arr[index] = nums1[i];
                    i++;
                }
                else{
                    arr[index] = nums2[j];
                    j++;
                }
            }
            index++;
        }
        while(i < m){
            arr[index] = nums1[i];
            i++;
            index++;
        }
        while(j < n){
            arr[index] = nums2[j];
            j++;
            index++;
        }
        return arr;
    }
    public int[] maxNumberWithKLen(int[] nums, int k){// must have k <= nums.length
        int n = nums.length;
        if(k == n)
            return nums;
        else{// k < n
            int[] arr = new int[k];
            Stack<Integer> s = new Stack<Integer>();
            int i = 0;
            while(i < n){
                if(s.isEmpty()){
                    s.push(nums[i]);
                    i++;
                }
                else if(s.size() + n - i == k){
                    s.push(nums[i]);
                    i++;
                }
                else{
                    while(!s.isEmpty() && s.peek() < nums[i] && s.size()-1 + n - i >= k){
                        s.pop();
                    }
                    if(s.size() < k)
                    	s.push(nums[i]);
                    i++;
                }
            }
            for(i = k - 1; i >= 0; i--){
                arr[i] = s.pop();
            }
            return arr;
        }
    }
}
//More concise code from Jiuzhang
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        // Write your code here
        if (k == 0)
            return new int[0];

        int m = nums1.length, n = nums2.length;
        if (m + n < k) return null;
        if (m + n == k) {
            int[] results = merge(nums1, nums2, k);
            return results;
        } else {
            int max = m >= k ? k : m;
            int min = n >= k ? 0 : k - n;

            int[] results = new int[k];
            for(int i=0; i < k; ++i)
                results[i] = -0x7ffffff;
            for(int i = min; i <= max; ++i) {
                int[] temp = merge(getMax(nums1, i), getMax(nums2, k - i), k);
                results = isGreater(results, 0, temp, 0) ? results : temp;
            }
            return results;
        }
    }

    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] results = new int[k];
        if (k == 0) return results;
        int i = 0, j = 0;
        for(int l = 0; l < k; ++l) {
            results[l] = isGreater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return results;
    }

    private boolean isGreater(int[] nums1, int i, int[] nums2, int j) {
        for(; i < nums1.length && j < nums2.length; ++i, ++j) {
            if (nums1[i] > nums2[j])
                return true;
            if (nums1[i] < nums2[j])
                return false;
        }
        return i != nums1.length;
    }

    private int[] getMax(int[] nums, int k) {
        if (k == 0)
            return new int[0];
        int[] results = new int[k];
        int i = 0;
        for(int j = 0; j < nums.length; ++j) {
            while(nums.length - j + i > k && i > 0 && results[i-1] < nums[j])
                i--;
            if (i < k)
                results[i++] = nums[j];
        }
        return results;
    }
}
