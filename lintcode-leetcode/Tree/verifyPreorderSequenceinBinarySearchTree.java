/** 137. Verify Preorder Sequence in Binary Search Tree(lintcode) / 255. Verify Preorder Sequence in Binary Search Tree(leetcode - locked)
 *      Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search 
 *  tree.
 *      You may assume each number in the sequence is unique.
 *
 *      Follow up: Could you do it using only constant space complexity?
 */
 
 /* My Method: Recursive
  *     思路：根结点先遍历，之后出现的小于根结点值的都是它的左子树，后面的都是右子树，所有的值必须都不根节点的值大。
  *         例如1：[5,1,3,2,4,9,8,10] => [1,3,2,4]比5小，是左子树，[9,8,10]比5大，都是右子树；
  *              再分别检查[1,3,2,4]和[9,8,10]; 递归发现满足二分查找树。
  *         例如2: [5,4,9,8,3,6] => [4]比5小，是左子树，那么后面[9,8,3,6]应该都属于右子树，
  *              但是3 < 5, 所以不满足二分查找树的前序遍历。
  */
 public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        // write your code here
        return verify(preorder, 0, preorder.length-1);
    }
    
    public boolean verify(int[] preorder, int start, int end){
        if(end <= start){
            return true;
        }
        int r_val = preorder[start];
        int div = start + 1;
        for(; div <= end; div++){
            if(preorder[div] > r_val){
                break;
            }
        }
        for(int i = div + 1; i <= end; i++){
            if(preorder[i] < r_val){
                return false;
            }
        }
        return verify(preorder,start + 1, div - 1) && verify(preorder,div, end);
    }
}

/* Method 2: Use stack
 *            10
 *           /  \
 *           5    12
 *          / \
 *         2   6
 *     上面例子，在10的位置是没有最小值限定的，然后降序走到5，依然没有最小值，降序走到2，依然没有，然后开始升序了，遇到6，
 *   这时候之后的数字一定大于2，同时也大于5，所以最小值更新为之前遍历过的，且比当前数稍微小一点的那个数。用一个栈来暂存之前的路径：
 *      1）升序时就是将栈中元素不断pop出来直到栈顶大于当前数，而最小值就是最后一个pop出来的数，最后再把该数push进去。
 *      2） 对于降序的时候，直接向里面push就行了。这样，序列无效的条件就是违反了这个最小值的限定。
 */
public boolean verifyPreorder(int[] preorder) {
    if(preorder == null || preorder.length <= 1){
        return true;
    }
    Stack<Integer> s = new Stack<Integer>();
    s.push(preorder[0]);
    int min = Integer.MIN_VALUE;
    for(int i = 1; i < preorder.length; i++){
        if(preorder[i] < min){
            return false;
        }
        if(preorder[i] < s.peek()){
            s.push(preorder[i]);
        }
        else{
            while(!s.isEmpty() && s.peek() < preorder[i]){
                min = s.pop();
            }
            s.push(preorder[i]);
        }
    }
    return true;
}
