/** 880. Construct Binary Tree from String(lintcode) / 536. Construct Binary Tree from String (leetcode - locked)
 *       You need to construct a binary tree from a string consisting of parenthesis and integers.
 *       The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs 
 *    of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary 
 *    tree with the same structure.
 *       You always start to construct the left child node of the parent first if it exists.
 *
 *      Note: - There will only be '(', ')', '-' and '0' ~ '9' in the input string.
 *            - An empty tree is represented by "" instead of "()".
 *
 *      Example: Given s = "4(2(3)(1))(6(5))", return the tree root node representing the following tree:
 *                 4
 *               /   \
 *              2     6
 *             / \   / 
 *            3   1 5   
 */

//Method: Use stack
public TreeNode str2tree(String s) {
    // write your code here
    int left_p = 0;
    int right_p = 0;
    if(s == null || s.length() == 0){
        return null;
    }
    Stack<TreeNode> stk = new Stack<TreeNode>();
    boolean negative = false;
    for(int i = 0; i < s.length(); i++){
        if(s.charAt(i) == '-'){
            negative = true;
        }
        else if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
            int num = 0;
            if(s.charAt(i) != '0'){
                while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    num = num*10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
            }
            TreeNode node = null;
            if(negative){
                node = new TreeNode(-num);
                negative = false;
            }
            else{
                node = new TreeNode(num);
            }
            if(!stk.isEmpty()){
                TreeNode root = stk.peek();
                if(root.left == null){
                    root.left = node;
                }
                else if(root.right == null){
                   root.right = node;
                }
            }
            stk.push(node);
        }
        else if(s.charAt(i) == ')'){
            stk.pop();
        }
    }
    return stk.pop();
 }
