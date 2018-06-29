/** 412. Candy (lintcode) / 135. Candy (leetcode)
 *    There are N children standing in a line. Each child is assigned a rating value.
 *    You are giving candies to these children subjected to the following requirements:
 *        - Each child must have at least one candy.
 *        - Children with a higher rating get more candies than their neighbors.
 *    What is the minimum candies you must give?
 *
 *    Example: 1) Input: [1,0,2] ; Output: 5
 *              Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 *             2) Input: [1,2,2] ; Output: 4
 *              Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 *                           The third child gets 1 candy because it satisfies the above two conditions.
 */
 
 //Method:
 public class Solution {
    /**
     * @param ratings: Children's ratings
     * @return: the minimum candies you must give
     */
    public int candy(int[] ratings) {
        // write your code here
        if(ratings == null || ratings.length == 0){
            return 0;
        }
        int[] candies = new int[ratings.length];
        
        for(int i = 0; i < ratings.length; i++){ //前向遍历，保证一个每个小孩比他左边/前面的小孩糖果多
            if(i - 1 >= 0 && ratings[i] > ratings[i - 1]){
                candies[i] = candies[i -1] + 1;
            }
        }
        for(int i = ratings.length -1 ; i >= 0; i--){ //后向遍历，保证一个每个小孩比他右边/后面的小孩糖果多
            if(i + 1 < ratings.length && ratings[i] > ratings[i + 1]){
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        
        int num = ratings.length; //保证每个小孩至少一个糖果
        for(int i = 0; i < ratings.length; i++){
            num += candies[i];
        }
        return num;
    }
}
