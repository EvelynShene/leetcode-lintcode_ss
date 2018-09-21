/** 347. Top K Frequent Elements(leetcode)
 *    Given a non-empty array of integers, return the k most frequent elements.
 *
 *    Example: 1) Input: nums = [1,1,1,2,2,3], k = 2 ; Output: [1,2]
 *             2) Input: nums = [1], k = 1 ; Output: [1]
 *
 *    Note: 1) You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 *          2) Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
 
 //My Method:
 class Solution {
    class NumFreq {
        int num;
        int freq;
        public NumFreq(int n, int f){
            this.num = n;
            this.freq = f;
        }
    }
    
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], 1);
            }
            else{
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        PriorityQueue<NumFreq> max_heap = new PriorityQueue<NumFreq>(new Comparator<NumFreq>(){
            public int compare(NumFreq a, NumFreq b){
                return b.freq - a.freq;
            }
        });
        
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            NumFreq tmp = new NumFreq(entry.getKey(), entry.getValue());
            max_heap.offer(tmp);
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < k; i++){
            NumFreq tmp = max_heap.poll();
            res.add(tmp.num);
        }
        return res;
    }
}
