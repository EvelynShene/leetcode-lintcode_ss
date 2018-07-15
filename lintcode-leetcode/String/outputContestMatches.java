/** 879. Output Contest Matches(lintcode) / 544. Output Contest Matches(leetcode - locked)
 *      During the NBA playoffs, we always arrange the rather strong team to play with the rather weak team, 
 *  like make the rank 1 team play with the rank nth team, which is a good strategy to make the contest more interesting.
 *  Now, you're given n teams, you need to output their final contest matches in the form of a string.
 *
 *      The n teams are given in the form of positive integers from 1 to n, which represents their initial rank.
 *  (Rank 1 is the strongest team and Rank n is the weakest team.) We'll use parentheses('(', ')') and commas(',')
 *  to represent the contest team pairing - parentheses('(' , ')') for pairing and commas(',') for partition. 
 *  During the pairing process in each round, you always need to follow the strategy of making the rather strong 
 *  one pair with the rather weak one.
 *
 *      Note: - The n is in range [2, 2^12].
 *            - We ensure that the input n can be converted into the form 2^k, where k is a positive integer.
 *
 *      Example: Given n = 4, return "((1,4),(2,3))".
 *        Explanation:  In the first round, we pair the team 1 and 4, the team 2 and 3 together, 
 *                  as we need to make the strong team and weak team together. And we got (1,4),(2,3).
 *                      In the second round, the winners of (1,4) and (2,3) need to play again to generate 
 *                  the final winner, so you need to add the paratheses outside them.
 *                  And we got the final answer ((1,4),(2,3)).
 */
 
 //My Method: 
 public String findContestMatch(int n) {
    // write your code here
    List<String> list = new ArrayList<String>();
    for(int i = 1; i <= n/2; i++){
        String s = "(" + i + "," + (n - i + 1) + ")";
        list.add(s);
    }
    n = n / 2;
    while(n != 1){
        for(int i = 0; i < n/2; i++){
            int size = list.size();
            String s = "(" + list.get(i) + "," + list.get(size - 1) + ")";
            list.set(i, s);
            list.remove(size - 1);
        }
        n = n / 2;
    }    
    return list.get(0);
 }
