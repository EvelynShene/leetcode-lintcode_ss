/** 822. Reverse Order Storage(lintcode)
 *   Give a linked list, and store the values of linked list in reverse order (reverse order of the original array) into an array.
 */
 
 // 递归法
 public class Solution {
    /**
     * @param head: the given linked list
     * @return: the array that store the values in reverse order 
     */
    
    public List<Integer> reverseStore(ListNode head) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        
        store(head,res);
        return res;
    }
    
    public void store(ListNode p,List<Integer> res){
        if(p == null){
            return;
        }
        
        store(p.next,res);
        res.add(p.val);
    }
    
}
