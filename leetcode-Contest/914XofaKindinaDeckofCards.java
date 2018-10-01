/** 914. X of a Kind in a Deck of Cards(leetcode)
 *      In a deck of cards, each card has an integer written on it.
 *      Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 
 *  or more groups of cards, where:
 *        1) Each group has exactly X cards.
 *        2) All the cards in each group have the same integer.
 *
 *      Example: 1) Input: [1,1,2,2,2,2] ; Output: true
 *                  Explanation: Possible partition [1,1],[2,2],[2,2]
 *               2) Input: [1,1,1,1,2,2,2,2,2,2] ; Output: true
 *                  Explanation: Possible partition [1,1],[1,1],[2,2],[2,2],[2,2]
 *
 *      Note: 1) 1 <= deck.length <= 10000
 *            2) 0 <= deck[i] < 10000
 */
 
 class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        if(deck == null || deck.length < 2){
            return false;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < deck.length; i++){
            if(!map.containsKey(deck[i])){
                map.put(deck[i], 1);
            }
            else{
                map.put(deck[i], map.get(deck[i]) + 1);
            }
        }
        int min = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            min = Math.min(entry.getValue(), min);
        }
        if(min == 1){
            return false;
        }
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(gcd(entry.getValue(), min) == 1){
                return false;
            }
        }
        return true;
    }
    
    public int gcd(int a, int b){
        if(a < b){
            return gcd(b, a);
        }
        int tmp = b;
        while(a % b != 0){
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return tmp;
    }
}
