/** 324. Wiggle Sort II(leetcode) / 507. Wiggle Sort II(lintcode)
 *    Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 *    Example: 1) Input: nums = [1, 5, 1, 1, 6, 4]
 *                Output: One possible answer is [1, 4, 1, 5, 1, 6].
 *             2) Input: nums = [1, 3, 2, 2, 3, 1]
 *                Output: One possible answer is [2, 3, 1, 3, 1, 2].
 *
 *    Note: You may assume all input has valid answer.
 *
 *    Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
 
 /* Method 1: O(nlogn) time and O(n) space complexity
  *     Idea: 1) 给数组排序，然后找到数组的中间数median，median数相当于把有序数组平分成两部分；
  *           2) 然后从前半段的末尾取一个，在从后半的末尾去一个，这样保证了第一个数小于第二个数，然后从前半段取倒数第二个，
  *           从后半段取倒数第二个，这保证了第二个数大于第三个数，且第三个数小于第四个数，以此类推直至都取完。  
  */
 public void wiggleSort(int[] nums) {
    if(nums == null || nums.length < 2){
        return;
    }
    int[] arr = Arrays.copyOf(nums,nums.length);
    Arrays.sort(arr);
    int mid = (nums.length + 1) / 2;
    int n = nums.length - 1;
    for(int i = 0; i < nums.length; i = i + 2){
        nums[i] = arr[mid - 1];
        if(i + 1 < nums.length)
            nums[i+1] = arr[n];
        mid--;
        n--;
    }
 }
 
 /* Method 2:
  *
  */
