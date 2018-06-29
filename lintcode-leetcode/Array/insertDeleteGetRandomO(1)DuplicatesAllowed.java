/** 954. Insert Delete GetRandom O(1) - Duplicates allowed (lintcode)/ 381. Insert Delete GetRandom O(1) - Duplicates allowed(leetcode)
 *    Design a data structure that supports all following operations in average O(1) time.
 *    Note: Duplicate elements are allowed.
 *          1. insert(val): Inserts an item val to the collection.
 *          2. remove(val): Removes an item val from the collection if present.
 *          3. getRandom: Returns a random element from current collection of elements. 
 *    (The probability of each element being returned is linearly related to the number of same value the collection contains.)
 *    
 *    Example: 
 *        // Init an empty collection.
 *         RandomizedCollection collection = new RandomizedCollection();
 *
 *        // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 *         collection.insert(1);
 *
 *        // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
 *         collection.insert(1);
 *
 *        // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 *         collection.insert(2);
 *
 *        // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 *         collection.getRandom();
 *
 *        // Removes 1 from the collection, returns true. Collection now contains [1,2].
 *         collection.remove(1);
 *
 *        // getRandom should return 1 and 2 both equally likely.
 *         collection.getRandom();
 */
 
 /* Method:
  *   需要一个数组elements来记录插入的所有数，这样才能保证使用getRandom得到的值的概率与它的出现频率有关，且时间复杂度是O(1)
  *   插入和删除的时间复杂度要是O(1)，则可以维护一张映射表<val,index集合>,其中index是一个包含所有val所在的位置的数据结构。
  *   对index使用集合表示，因为在删除过程中，需要同步elements数组，时间为O(1)的操作是直接交换需要删除的数值与elements中最后一个数值。
  *   值交换后，相应的index也需要交换，多次交换后可能出现重复index，所以用集合的元素互异性可以避免。
  *     （eg: insert:11222,remove112,insert1 => 会有两个index=2 出现在val=2的index集合中）
  *
  *   [以下是使用两种集合Set和LinkedHashSet的方法]
  */
  
  //Code from leetcode discuss - Set
  public class RandomizedCollection {

    List<Integer> nums;
    Map<Integer, Set<Integer>> map;
    java.util.Random random;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        nums = new ArrayList<>();
        map = new HashMap<>();
        random = new java.util.Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean doesContain = map.containsKey(val);
        if(!doesContain) map.put(val, new HashSet<>());
        map.get(val).add(nums.size());
        nums.add(val);
        return !doesContain;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        if(!map.get(val).contains(nums.size()-1)) {
            int currPos = map.get(val).iterator().next();
            int lastVal = nums.get(nums.size() - 1);
            map.get(lastVal).remove(nums.size() - 1);
            map.get(lastVal).add(currPos);
            map.get(val).remove(currPos);
            map.get(val).add(nums.size() - 1);
            nums.set(currPos, lastVal);
        }
        map.get(val).remove(nums.size()-1);
        if(map.get(val).isEmpty()) map.remove(val);
        nums.remove(nums.size()-1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
}
  
  
  //Code from jiuzhang - LinkedHashSet
  public class RandomizedCollection {

    ArrayList<Integer> result;
    HashMap<Integer, LinkedHashSet<Integer>> map;
    
    public RandomizedCollection() {
        result = new ArrayList<Integer>();
        map = new HashMap<Integer, LinkedHashSet<Integer>>();
    }

    public boolean insert(int val) {
        boolean alreadyExists = map.containsKey(val);
        if(!alreadyExists) {
            map.put(val, new LinkedHashSet<Integer>());
        }
        map.get(val).add(result.size());
        result.add(val);
        return !alreadyExists;
    }
    
    public boolean remove(int val) {
        if(!map.containsKey(val)) {
            return false;
        }
        LinkedHashSet<Integer> valSet = map.get(val);
        int indexToReplace = valSet.iterator().next();
        
        int numAtLastPlace = result.get(result.size() - 1);
        LinkedHashSet<Integer> replaceWith = map.get(numAtLastPlace);
        
        result.set(indexToReplace, numAtLastPlace);
        
        valSet.remove(indexToReplace);
        
        if(indexToReplace != result.size() - 1) {
            replaceWith.remove(result.size() - 1);
            replaceWith.add(indexToReplace);
        }
        result.remove(result.size() - 1);
        
        if(valSet.isEmpty()) {
            map.remove(val);
        }
        return true;
    }
    public int getRandom() {
        return result.get((int)(Math.random() * result.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
    
