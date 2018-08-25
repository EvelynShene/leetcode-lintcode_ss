/** 901. Closest Binary Search Tree Value II(lintcode) / 272. Closest Binary Search Tree Value II(leetcode - locked)
 *      Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the 
 *  target.
 *
 *      Note: 1) Given target value is a floating point.
 *            2) You may assume k is always valid, that is: k ≤ total nodes.
 *            3) You are guaranteed to have only one unique set of k values in the BST that are closest to the 
 *            target.
 *
 *      Example: Given root = {1}, target = 0.000000, k = 1, return [1].
 *
 *      Challenge: Assume that the BST is balanced, could you solve it in less than O(n) runtime 
 *                      (where n = total nodes)?
 */
 
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
 
 //My Method: O(n) time and space complexity
 public List<Integer> closestKValues(TreeNode root, double target, int k) {
    List<Integer> res = new ArrayList<Integer>();
    if(root == null || k == 0){
        return res;
    }

    int lower_index = -1;
    List<Integer> values = new ArrayList<Integer>();
    Stack<TreeNode> stk = new Stack<TreeNode>();
    TreeNode cur = root;
    while(cur != null){
        stk.push(cur);
        cur = cur.left;
    }
    while(!stk.isEmpty()){
        cur = stk.pop();
        values.add(cur.val);
        if(cur.val < target){
            lower_index = values.size() - 1;
        }
        TreeNode right = cur.right;
        while(right != null){
            stk.push(right);
            right = right.left;
        }
    }

    int upper_index = lower_index + 1;
    while(res.size() != k){
        if(upper_index >= values.size()){
            res.add(values.get(lower_index));
            lower_index--;
        }
        else if(lower_index == -1){
            res.add(values.get(upper_index));
            upper_index++;
        }
        else{
            if(target - values.get(lower_index) < values.get(upper_index) - target){
                res.add(values.get(lower_index));
                lower_index--;
            }
            else{
                res.add(values.get(upper_index));
                upper_index++;
            }
        }
    }
    return res;
 }
