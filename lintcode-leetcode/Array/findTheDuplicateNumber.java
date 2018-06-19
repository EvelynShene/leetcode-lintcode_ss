/** 633. Find the Duplicate Number(lintcode) / 287. Find the Duplicate Number(leetcode)
 *   Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
 *   prove that at least one duplicate number must exist. Assume that there is only one duplicate number, 
 *   find the duplicate one.
 *
 *   Note:
 *     You must not modify the array (assume the array is read only).
 *     You must use only constant, O(1) extra space.
 *     Your runtime complexity should be less than O(n2).
 *     There is only one duplicate number in the array, but it could be repeated more than once.
 */
  
/** Proof:Pigeonhole principle(鸽巢原理)
 *     Each number in nums is a "pigeon" and each distinct number that can appear in nums is a "pigeonhole".
 *  Because there are n+1n+1 numbers are nn distinct possible numbers, the pigeonhole principle implies that 
 *  at least one of the numbers is duplicated.
 */
 
/* When this problem appears in a technical interview and no such strict limitation. 
 * As an interviewer, you can use two other methods.
 *   1) First sort this array, and then any duplicate numbers will be adjacent in the sorted array. (T = O(nlgn))
 *   2) Use HashMap or HashSet to find the duplicate number. (Time complexity = O(n); Space complexity = O(n))
 */
 
 /* Method 1: Pigeonhole principle(鸽巢原理) + Binary Search
  *    Each integer is between 1 and n (inclusive). For each middle number mid: 
  *      if the number of numbers that less than mid is larger than mid, the duplicate number must between [1,mid].
  *    So to find the the duplicate number in [1,n] can be divided to find duplicate number in [1,n/2] and [n/2,n].
  */
  public int findDuplicate(int[] nums) {
      // write your code here
      int l = 1, r = nums.length-1;

      while(l < r){
          int c = 0;
          int mid = (l+r)/2;
          for(int i = 0; i < nums.length; i++){
              if(nums[i] <= mid){
                  c++;
              }
          }
          if(c > mid){
              r = mid;
          }
          else{
              l = mid + 1;
          }
      }
      return l;
  }
  
  /* 
   * Method 2: Cycle Detection - Time complexity = O(n); Space complexity = O(1) 
   *  假设数组中没有重复，可以将数组的下标和1到n每一个数一对一的映射起来（因为数值也是在范围[1,n]）。
   *  比如数组是[2,3,1],则映射关系为0->2, 1->3, 2->1。假设这个一对一映射关系是一个函数f(n)，其中n是下标，f(n)是映射到的数。
   *  从下标为0出发，根据这个函数计算出一个值，以这个值为新的下标，再用这个函数计算，以此类推，可以得到：0->2->1->3, 直到下标超界(3超界)。
   *  没有重复时，链表时没有环的。
   *  
   *  当数组中有重复时，比如[1,3,1,2],映射变成0->1, 1->3, 2->1，3->2。
   *  同样从下标开始，形成的链是：0->1->3->2->1->3->2->.....(一直循环，不会出现下标超界)，且循环圈是从1开始的，开始的1就是重复的那个数
   *  所以原题找重复的数就变成了找链中循环的起始位置的数。
   *
   *  找环起点方法：Floyd's Algorithm
   *  先用快慢两个下标都从0开始，快下标每轮走两步（即映射两次），慢下标每轮走一次（映射一次），直到两个下标再次相同。
   *  这时候保持慢下标位置不变，再用一个新的下标从0开始，这两个下标都每轮走一次，当这两个下标相遇时，就是环的起点，也就是重复的数。
   *  (code from Leetcode solution)
   */
   class Solution {
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }
}
 
