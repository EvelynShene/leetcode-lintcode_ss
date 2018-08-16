/** 145. Binary Tree Postorder Traversal(leetcode) / 68. Binary Tree Postorder Traversal(lintcode)
 *     Given a binary tree, return the postorder traversal of its nodes' values.
 *
 *     Example: Input: [1,null,2,3]
 *                      1
 *                       \
 *                        2
 *                       /
 *                      3
 *              Output: [3,2,1]
 *  
 *     Follow up: Recursive solution is trivial, could you do it iteratively?   
 */
 
 //Method 1: Recursive
 class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root != null){
            postorder(root.left, res);
            postorder(root.right, res);
            res.add(root.val);
        }
        return res;
    }
    
    public void postorder(TreeNode root, List<Integer> res) {
        if(root != null){
            postorder(root.left, res);
            postorder(root.right, res);
            res.add(root.val);
        }
    }
}

/* Method 2: Iterative - Use Two Stack
 *    思路:
 *      1.申请一个栈s1，然后将头节点root压入s1中；  
 *      2.从s1中弹出的栈顶元素，然后依次将p的左孩子和右孩子(不为空的话)压入s1中; 
 *     【注意顺序是先压入左孩子，再压入右孩子，这样右孩子会先弹出存入s2栈中，栈的先进后出原则会使得右孩子在左孩子之后被读到】
 *      3.每次从s1中弹出的节点都放入s2中；  
 *      4.不断重复步骤2和步骤3，直到s1为空，过程结束。  
 *      5.最后，从s2中依次弹出节点存入list即可。  
 */
 public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    if(root == null){
        return res;
    }
    Stack<TreeNode> s1 = new Stack<TreeNode>();
    Stack<TreeNode> s2 = new Stack<TreeNode>();
    s1.push(root);
    while(!s1.isEmpty()){
        TreeNode cur = s1.pop();
        s2.push(cur);
        if(cur.left != null){
            s1.push(cur.left);
        }
        if(cur.right != null){
            s1.push(cur.right);
        }
    }
    while(!s2.isEmpty()){
        res.add(s2.pop().val);
    }
    return res;
 }

/* Method 3: Iterative - Use one stack
 *   思路：假设一棵树如右：　　　  　　　　　　1
 *　 　　　　　　　　　　　　　　　　　　　 　/　\
 *　　　　　　　　　　　　　　　　　　　　 　2　　 3
 *　　　　　　　　　　　　　　　　　　　　 /　\    \
 * 　　　　　　　　　　　　　　　　　　　 4　　5    6
 *    使用一个栈。分几个步骤：
 *　　　　1) 将根节点入栈，并将根节点的孩子入栈，入栈顺序为：先入右孩子，再入左孩子! => {1,3,2,5,4}
 *         【先右后左那么出栈的时候就是先弹出左孩子 - 即后序遍历顺序】
 *　　　  2) 4没有左右孩子，将4弹出计入list，同时使用pre记录下4这个节点; 5没有左右孩子，将5弹出计入list，pre记录下5这个节点;
 *       3) 4,5出栈后，栈为{1,3,2}，此时2在栈顶，2虽然存在左右孩子，但是已经遍历过了，所以只需出栈计入list => {1, 3}。
 *         【怎么知道2已经遍历过子节点了呢？用pre判读，prev为栈顶元素的孩子，则说明栈顶元素的孩子已经遍历完成。此时pre记录为2】
 *　　　　4) 栈顶为3，pre记录的2不是3的子节点，所以继续查看3的子节点，有右节点6，压入栈中 => {1, 3, 6}
 *　　　  5) 6没有左右孩子，将6弹出计入list，同时使用pre记录下6这个节点; => {1, 3}
 *       6) pre = 6 是栈顶3的孩子，所以3弹出计入list, pre记录3；
 *       7) 此时栈为{1}，为树根，pre = 3是其孩子，说明左右子树都遍历完了，弹出即可；栈空，退出循环。
 */
public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    if(root == null){
        return res;
    }
    Stack<TreeNode> stk = new Stack<TreeNode>();
    TreeNode pre = root;
    stk.push(root);
    while(!stk.isEmpty()){
        TreeNode cur = stk.peek();
        if(cur.left == null && cur.right == null){
            res.add(cur.val);
            stk.pop();
            pre = cur;
        }
        else if(pre == cur.left || pre == cur.right){
            res.add(cur.val);
            stk.pop();
            pre = cur;
        }
        else{
            if(cur.right != null){
                stk.push(cur.right);
            }
            if(cur.left != null){
                stk.push(cur.left);
            }
        }
    }
    return res;
 }
