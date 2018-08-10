/** 56. Two Sum (lintcode)/ 1. Two Sum(leetcode)
 *   Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *   You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *   Output: [index1, index2] and index1 < index2
 */


public int[] twoSum(int[] numbers, int target) {
    // write your code here
    HashMap<Integer,Integer> map = new HashMap<>();

    for(int i = 0; i < numbers.length; i++){
        if(map.get(numbers[i]) != null){
            int[] res = {map.get(numbers[i]),i};
            return res;
        }
        map.put(target-numbers[i],i);
    }

    int[] res={};
    return res;
}
