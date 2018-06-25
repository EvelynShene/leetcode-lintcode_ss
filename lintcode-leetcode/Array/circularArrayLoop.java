/** 1229. Circular Array Loop(lintcode) / 457. Circular Array Loop(leetcode)
 *      You are given an array of positive and negative integers. If a number n at an index is positive, 
 *   then move forward n steps. Conversely, if it's negative (-n), move backward n steps. 
 *   Assume the first element of the array is forward next to the last element, 
 *   and the last element is backward next to the first element. Determine if there is a loop in this array. 
 *   A loop starts and ends at a particular index with more than 1 element along the loop. 
 *   The loop must be "forward" or "backward'.
 *
 *   Example: 1) Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.
 *            2) Given the array [-1, 2], there is no loop.
 *
 *   Note: 1）循环是指index的循环，即由元素指向的步数引起的index循环。
 *         2）必须是forward或是backward的是指循环必须是一个方向的，要么全是forward，要么全是backward。
 */
 
 /* Idea:
  *   行动步数与index形成映射，如果移动中遇到已被映射的起始位置，则有环；注意环内元素个数必须大于1.
  */ 
    public boolean circularArrayLoop(int[] nums) {
      // Write your code here
      if(nums == null || nums.length <= 1){
          return false;
      }
      boolean[] visit = new boolean[nums.length];
      boolean forward = false;
      for(int i = 0; i < nums.length; i++){
          if(!visit[i]){
              if(nums[i] > 0){
                  forward = true;
              }
              else{
                  forward = false;
              }
              int next = i;
              int prev = i;
              do{
                  int step = nums[next];
                  visit[next] = true;
                  prev = next;
                  if(forward && step > 0){
                      next = (next + step) % nums.length;
                  }
                  else if(!forward && step < 0){
                      if(next + step >= 0){
                          next = (next + step);
                      }
                      else{
                          next = nums.length + step + next;
                      }
                  }
                  else{
                      break;
                  }
                  if(prev == next) break;
                  if(i == next){
                    return true;
                  }
              }
              while(true);
          }
      }
      return false;
  }
