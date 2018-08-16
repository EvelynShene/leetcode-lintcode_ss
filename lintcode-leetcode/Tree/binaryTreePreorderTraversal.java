/** 144. Binary Tree Preorder Traversal(leetcode) / 66. Binary Tree Preorder Traversal(lintcode)
 *     Given a binary tree, return the preorder traversal of its nodes' values.
 *
 *     Example: Input: [1,null,2,3]
 *                      1
 *                       \
 *                        2
 *                       /
 *                      3
 *              Output: [1,2,3]
 *  
 *     Follow up: Recursive solution is trivial, could you do it iteratively?
 */

//Method 1: Recursion
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root != null){
            res.add(root.val);
            preorder(root.left, res);
            preorder(root.right,res);
        }
        return res;
    }
    
    public void preorder(TreeNode root, List<Integer> res){
        if(root != null){
            res.add(root.val);
            preorder(root.left, res);
            preorder(root.right,res);
        }
    }
}

//Method 2: Iterative
public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    Stack<TreeNode> stk = new Stack<TreeNode>();
    while(root != null){
        res.add(root.val);
        if(root.right != null){
            stk.push(root.right);
        }
        root = root.left;
        if(root == null && !stk.isEmpty()){
            root = stk.pop();
        }
    }

    return res;
}
