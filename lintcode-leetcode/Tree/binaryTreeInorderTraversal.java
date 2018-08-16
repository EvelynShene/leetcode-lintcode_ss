/** 94. Binary Tree Inorder Traversal(leetcode) / 67. Binary Tree Inorder Traversal(lintcode)
 *     Given a binary tree, return the inorder traversal of its nodes' values.
 *
 *     Example: Input: [1,null,2,3]
 *                      1
 *                       \
 *                        2
 *                       /
 *                      3
 *              Output: [1,3,2]
 *  
 *     Follow up: Recursive solution is trivial, could you do it iteratively?   
 */
 
 //Method 1: Recursive
 class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root != null){
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right,res);
        }
        return res;
    }
    
    public void inorder(TreeNode root, List<Integer> res){
        if(root != null){
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right,res);
        }
    }
}

//Method 2: Iterative - [idea from leetcode solution]
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    if(root == null){
        return res;
    }
    Stack<TreeNode> stk = new Stack<TreeNode>();
    TreeNode cur = root;
    stk.push(cur);
    while(!stk.isEmpty()){
        cur = stk.peek();
        if(cur.left != null){
            cur = cur.left;
            stk.push(cur);
        }
        else{
            res.add(cur.val);
            stk.pop();
            while(cur.right == null && !stk.isEmpty()){
                cur = stk.pop();
                res.add(cur.val);
            }
            if(cur.right != null){
                stk.push(cur.right);
            }
        }

    }
    return res;
}
