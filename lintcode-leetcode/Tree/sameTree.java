/** 100. Same Tree(leetcode) / 469. Same Tree(lintcode)
 *    Given two binary trees, write a function to check if they are the same or not.
 *    Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 *  
 *    Example: 1)Input:     1         1
 *                         / \       / \
 *                        2   3     2   3
 *                       [1,2,3],   [1,2,3]
 *               Output: true
 *
 *             2)Input:     1         1
 *                         /           \
 *                        2             2
 *                       [1,2],     [1,null,2]
 *               Output: false
 *
 *             3)Input:     1         1
 *                         / \       / \
 *                        2   1     1   2
 *                       [1,2,1],   [1,1,2]
 *               Output: false
 */
 
 //Method 1: Recursion
 public boolean isSameTree(TreeNode p, TreeNode q) {
    if(p == null && q == null){
        return true;
    }
    if(p == null || q == null){
        return false;
    }
    if(p.val != q.val){
        return false;
    }
    return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
 }
 
 //Method 2: Iterative
 public boolean isSameTree(TreeNode p, TreeNode q) {
    if(p == null && q == null){
        return true;
    }
    if(p == null || q == null){
        return false;
    }
    Queue<TreeNode> s1 = new LinkedList<TreeNode>();
    Queue<TreeNode> s2 = new LinkedList<TreeNode>();
    s1.offer(p);
    s2.offer(q);
    while(!s1.isEmpty() && !s2.isEmpty()){
        p = s1.poll();
        q = s2.poll();
        if(p.val == q.val){
            if(p.left != null && q.left != null){
                s1.offer(p.left);
                s2.offer(q.left);
            }
            else if(p.left != null || q.left != null){
                return false;
            }
            if(p.right != null && q.right != null){
                s1.offer(p.right);
                s2.offer(q.right);
            }
            else if(p.right != null || q.right != null){ 
                return false;
            }
        }
        else{
            return false;
        }
    }
    if(s1.isEmpty() && s2.isEmpty()){
        return true;
    }
    return false;
}
