/** 350. Intersection of Two Arrays II(leetcode) / 548. Intersection of Two Arrays II(lintcode)
 *      Given two arrays, write a function to compute their intersection.
 *
 *      Example: Input: nums1 = [1,2,2,1], nums2 = [2,2]; Output: [2,2]
 *
 *      Note: 1) Each element in the result should appear as many times as it shows in both arrays.
 *            2) The result can be in any order.
 *
 *      Follow up: 1) What if the given array is already sorted? How would you optimize your algorithm?
 *                 2) What if nums1's size is small compared to nums2's size? Which algorithm is better?
 *                 3) What if elements of nums2 are stored on disk, and the memory is limited 
 *              such that you cannot load all elements into the memory at once?
 *               Ans 3): https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/82243/Solution-to-3rd-follow-up-question
 */
 
 //Method 1: Use Map - O(n) time and space complexity
 public int[] intersect(int[] nums1, int[] nums2) {
    if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0){
        return new int[0];
    }

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for(int i = 0; i < nums1.length; i++){
        if(!map.containsKey(nums1[i])){
            map.put(nums1[i], 1);
        }
        else{
            map.put(nums1[i], map.get(nums1[i]) + 1);
        }
    }

    List<Integer> r = new ArrayList<>();
    for(int i = 0; i < nums2.length; i++){
        if(map.containsKey(nums2[i])){
            r.add(nums2[i]);
            if(map.get(nums2[i]) == 1){
                map.remove(nums2[i]);
            }
            else{
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }
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
            i++;
            j++;
        }
        else if(nums1[i] < nums2[j]){
            i++;
        }
        else{
            j++;
        }
    }

    int[] res = new int[r.size()];
    for(i = 0; i < r.size(); i++){
        res[i] = r.get(i);
    }
    return res;
}
