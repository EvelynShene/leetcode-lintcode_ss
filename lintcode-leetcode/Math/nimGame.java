/** 292. Nim Game(leetcode) / 1300. Nim Game(lintcode)
 *      You are playing the following Nim Game with your friend: There is a heap of stones on the table, 
 *  each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. 
 *  You will take the first turn to remove the stones.
 *      Both of you are very clever and have optimal strategies for the game. Write a function to determine 
 *  whether you can win the game given the number of stones in the heap.
 *
 *      Example: Input: 4 ;Output: false 
 *               Explanation: If there are 4 stones in the heap, then you will never win the game;
 *                            No matter 1, 2, or 3 stones you remove, the last stone will always be 
 *                           removed by your friend.
 */
 
 /* Method: 如果先手玩家想要拿到最后一个石子n，那么必须保证对手一定拿到第n-3个石子，因为这样的话，不管对手拿几个石子，先手玩家都能拿到
  *      最后一个；继续往前，要保证先手玩家一定能拿到第n-4个石子，这样就可以控制拿到第n-4个石子时停下，让对手拿第n-3个，依次往前。
  *      可以很快发现，只要 n % 4 != 0, 先手玩家总能赢得比赛。
  */
 public boolean canWinNim(int n) {
    return n % 4 != 0;
 }
