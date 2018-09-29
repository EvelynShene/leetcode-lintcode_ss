/** 380. Insert Delete GetRandom O(1)(leetcode) / 657. Insert Delete GetRandom O(1)(lintcode)
 *      Design a data structure that supports all following operations in average O(1) time.
 *        1) insert(val): Inserts an item val to the set if not already present.
 *        2) remove(val): Removes an item val from the set if present.
 *        3) getRandom: Returns a random element from current set of elements. Each element must have the same 
 *      probability of being returned.
 *
 *      Example: // Init an empty set.
 *               RandomizedSet randomSet = new RandomizedSet();
 *               // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 *               randomSet.insert(1);
 *               // Returns false as 2 does not exist in the set.
 *               randomSet.remove(2);
 *               // Inserts 2 to the set, returns true. Set now contains [1,2].
 *               randomSet.insert(2);
 *               // getRandom should return either 1 or 2 randomly.
 *               randomSet.getRandom();
 *               // Removes 1 from the set, returns true. Set now contains [2].
 *               randomSet.remove(1);
 *               // 2 was already in the set, so return false.
 *               randomSet.insert(2);
 *               // Since 2 is the only number in the set, getRandom always return 2.
 *               randomSet.getRandom();
 */
 
 //Method:
 class RandomizedSet {
    List<Integer> nums;
    Map<Integer, Integer> map;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        nums.add(val);
        map.put(val, nums.size() - 1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(map.containsKey(val)){
            if(nums.size() > 1){
                int rm_i = map.get(val);
                int lastval = nums.get(nums.size() - 1);
                map.put(lastval, rm_i);
                nums.set(rm_i, lastval);
            }
            nums.remove(nums.size() - 1);
            map.remove(val);
            return true;
        }
        return false;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random r = new Random();
        return nums.get(r.nextInt(nums.size()));
    }
}
