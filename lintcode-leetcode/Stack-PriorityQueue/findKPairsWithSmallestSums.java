/** 373. Find K Pairs with Smallest Sums(leetcode)
 *    You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *    Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 *    Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 *
 *    Example: 1) Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 *                Output: [[1,2],[1,4],[1,6]] 
 *                    Explanation: The first 3 pairs are returned from the sequence: 
 *                              [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *             2) Input: nums1 = [1,2], nums2 = [3], k = 4
 *                Output: [1,3],[2,3]
 *                    Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 */
 
 //My Method:
 class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<int[]>();
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k == 0){
            return res;
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return (a[0] + a[1]) - (b[0] + b[1]);
            }
        });
        
        for(int i = 0; i < nums1.length; i++){
            for(int j = 0; j < nums2.length; j++){
                int[] tmp = new int[2];
                tmp[0] = nums1[i];
                tmp[1] = nums2[j];
                heap.offer(tmp);
            }
        }
        for(int i = 0; i < k && !heap.isEmpty(); i++){
            res.add(heap.poll());
        }
        return res;
    }
}

//Method 2:
class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<int[]>();
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k == 0){
            return res;
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return (a[0] + a[1]) - (b[0] + b[1]);
            }
        });
        
        for(int i = 0; i < nums1.length; i++){
            heap.offer(new int[]{nums1[i], nums2[0], 0});
        }
        for(int i = 0; i < k && !heap.isEmpty(); i++){
            int[] tmp = heap.poll();
            res.add(new int[]{tmp[0], tmp[1]});
            if(tmp[2] + 1 < nums2.length){
                heap.offer(new int[]{tmp[0], nums2[tmp[2] + 1], tmp[2] + 1});
            }
        }
        return res;
    }
}
