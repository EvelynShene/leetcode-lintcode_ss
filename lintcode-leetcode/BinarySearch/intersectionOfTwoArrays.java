/** 349. Intersection of Two Arrays(leetcode) / 547. Intersection of Two Arrays(lintcode) 
 *      Given two arrays, write a function to compute their intersection.
 * 
 *      Example: 1) Input: nums1 = [1,2,2,1], nums2 = [2,2] ; Output: [2]
 *               2) Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4] ; Output: [9,4]
 *  
 *      Note: 1) Each element in the result must be unique.
 *            2) The result can be in any order.
 *
 *      Challenge: Can you implement it in three different algorithms?
 */
 
 //Method 1: Use Set - O(n) time and space complexity
 public int[] intersection(int[] nums1, int[] nums2) {
    if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0){
        return new int[0];
    }
    if(nums1.length < nums2.length){ // nums1 always be the longer one
        return intersection(nums2, nums1);
    }
    Set<Integer> set = new HashSet<Integer>();
    for(int i = 0; i < nums2.length; i++){
        set.add(nums2[i]);
    }
    List<Integer> r = new ArrayList<>();
    for(int i = 0; i < nums1.length; i++){
        if(set.contains(nums1[i])){
            r.add(nums1[i]);
            set.remove(nums1[i]);
        }
    }

    int[] res = new int[r.size()];
    for(int i = 0; i < r.size(); i++){
        res[i] = r.get(i);
    }  
    return res;
 }
 
 //Method 2: Sort Array and use two pointer - O(nlogn) time and O(n) space complexity
 public int[] intersection(int[] nums1, int[] nums2) {
    if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0){
        return new int[0];
    }
    Arrays.sort(nums1);
    Arrays.sort(nums2);

    List<Integer> r = new ArrayList<Integer>();
    int i = 0; 
    int j = 0;
    while(i < nums1.length && j < nums2.length){
        if(nums1[i] == nums2[j]){
            r.add(nums1[i]);
            while(i + 1 < nums1.length && nums1[i + 1] == nums1[i]){
                i++;
            }
            while(j + 1 < nums2.length && nums2[j + 1] == nums2[j]){
                j++;
            }
            i++;
            j++;
        }
        else if(nums1[i] < nums2[j]){
            while(i + 1 < nums1.length && nums1[i + 1] == nums1[i]){
                i++;
            }
            i++;
        }
        else{
            while(j + 1 < nums2.length && nums2[j + 1] == nums2[j]){
                j++;
            }
            j++;
        }
    }

    int[] res = new int[r.size()];
    for(i = 0; i < r.size(); i++){
        res[i] = r.get(i);
    }
    return res;
}

/* Method 3: Binary Search 
 *     思路：首先将一个数组排序，然后遍历另一个数组，把遍历到的每个数字在排序号的数组中用二分查找法搜索，
 *       如果能找到则放入结果set中，这里我们用到了set的去重复的特性，最后我们将set转为
 */
