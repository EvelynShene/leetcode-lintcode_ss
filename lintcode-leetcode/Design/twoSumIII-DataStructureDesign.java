/** 170. Two Sum III - Data structure design(leetcode) / 607. Two Sum III - Data structure design(lintcode)
 *      Design and implement a TwoSum class. It should support the following operations: add and find.
 *           add - Add the number to an internal data structure.
 *           find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 *      Example: add(1); add(3); add(5);
 *               find(4) -> true
 *               find(7) -> false
 */

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
 
 //My Method: Use HashMap - time complexity = O(1) add and O(n) find; space complexity = O(n)
 class TwoSum {
    List<Integer> list;
    /** Initialize your data structure here. */
    public TwoSum() {
        list = new ArrayList<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        list.add(number);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < list.size(); i++){
            if(set.contains(list.get(i))){
                return true;
            }
            else{
                set.add(value - list.get(i));
            }
        }
        return false;
    }
}
