/** 904. Fruit Into Baskets(leetcode)
 *      In a row of trees, the i-th tree produces fruit with type tree[i].
 *      You start at any tree of your choice, then repeatedly perform the following steps:
 *        1) Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 *        2) Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 *      Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, 
 *   then step 2, then back to step 1, then step 2, and so on until you stop.
 *
 *      You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only 
 *   carry one type of fruit each.
 *
 *      What is the total amount of fruit you can collect with this procedure?
 */
 
 public int totalFruit(int[] tree) {
    if(tree == null || tree.length == 0){
        return 0;
    }
    int res = 0;
    int c = 0;
    Map<Integer,Integer> fruit = new HashMap<Integer,Integer>();
    for(int i = 0; i < tree.length; i++){
        if(fruit.containsKey(tree[i])){
            if(tree[i] == tree[i - 1]){
                fruit.put(tree[i], fruit.get(tree[i]) + 1);
            }
            else{
                fruit.put(tree[i], 1);
            }
            c++;
        }
        else if(fruit.size() < 2){
            fruit.put(tree[i], 1);
            c++;
        }
        else{
            res = Math.max(res,c);
            c = fruit.get(tree[i - 1]);
            fruit.clear();
            fruit.put(tree[i - 1], c);
            fruit.put(tree[i], 1);
            c++;
        }
    }
    res = Math.max(res,c);
    return res;
}
