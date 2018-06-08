/** 1138. Can Place Flowers(lintcode *)/605. Can Place Flowers(leetcode)
  * Suppose you have a long flowerbed in which some of the plots are planted and some are not. 
  * However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.
  *
  * Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), 
  * and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
  * 
  */

public boolean canPlaceFlowers(int[] flowerbed, int n) {
    int availble = 0;
    boolean first0 = false;
    boolean has1 = false;
    int zeron = 0;

    for(int i = 0; i < flowerbed.length; i++){
        if(flowerbed[i] == 0){
            zeron ++;
            if(i == 0){
                first0 = true;
            }
        }
        if(flowerbed[i] == 1){
            has1 = true;
            if(first0){ // consider case like [0,0,1,...]
                availble = availble + zeron/2;
                first0 = false;
            }
            else{
                availble = availble + (zeron-1)/2;
            }
            zeron = 0;
        }
    }
    if(zeron != 0){
        if(has1){ // consider case like [...1,0,0,0]
            availble = availble + zeron/2;
        }
        else{ // no 1 in original flowerbed
            availble = availble + (zeron+1)/2;
        }
    }

    if(availble >= n)
        return true;
    else
        return false;
}

/**
 * Note: (lintcode *) means it is easy to get accepted in lintcode, 
 *        lack of test cases to test cases like [...1,0,0,0], [0,0,1,...] and [0,0,0].
 */
