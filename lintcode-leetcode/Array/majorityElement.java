/** 46.Majority Element(lintcode) / 169. Majority Element(leetcode)
 *     Given an array of size n, find the majority element. The majority element is the element that appears 
 *  more than ⌊ n/2 ⌋ times.
 *     You may assume that the array is non-empty and the majority element always exist in the array.
 *
 *     Example: Given [1, 1, 1, 1, 2, 2, 2], return 1
 *
 *     Challenge: O(n) time and O(1) extra space
 */
 
 //Method 1: HashMap - O(n) time and space complexity
 public int majorityElement(int[] nums) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    int n = nums.length;
    for(int i = 0; i < n; i++){
        if(!map.containsKey(nums[i])){
            map.put(nums[i],1);
        }
        else{
            map.put(nums[i], map.get(nums[i]) + 1);
        }
    }
    int res = 0;
    for(Map.Entry<Integer,Integer> entry: map.entrySet()){
        if(entry.getValue() > n / 2){
            res = entry.getKey();
            break;
        }
    }
    return res;
 }
 
 //Method 2: Sort Array - O(nlogn) time and O(1) space complexity
 public int majorityElement(int[] nums) {
    int n = nums.length;
    Arrays.sort(nums);
    int count = 1;
    int res = nums[0];
    for(int i = 1; i < n; i++){
        if(count > n / 2){
            res = nums[i-1];
            break;
        }
        if(nums[i] != nums[i-1]){
            count = 1;
        }
        count++;
    }
    return res;
 }
 //简化：
 public int majorityElement(int[] nums) {
    int n = nums.length;
    Arrays.sort(nums);
    return nums[n / 2];
 }
 
 /* Method 3: Boyer-Moore Voting Algorithm - O(n) time and O(1) complexity [from leetcode solution]
  *   Boyer-Moore Voting Algorithm:
  *     If we had some way of counting instances of the majority element as +1+1 and instances of any other 
  *   element as -1, summing them would make it obvious that the majority element is indeed the majority element.
  *    【如果majority元素存在（majority元素个数大于n/2,个数超过数组长度一半），那么无论它的各个元素位置是如何分布的，
  *   其count经过抵消和增加后，最后一定是大于0的。（此原理需要保证majority存在）】
  *
  */ 
  public int majorityElement(int[] nums) {
    int n = nums.length;
    int count = 1;
    int res = nums[0];
    for(int i = 1; i < n; i++){
        //count == 0 => 那么原来的res肯定不是majority, 但是i之前的也肯定不是，因为如果i之前有majority，一定有count<0
        if(count == 0){ 
            res = nums[i];
            count = 1;
        }
        else{
            count += (res == nums[i])? 1: -1;
        }
    }
    return res;
 } 
