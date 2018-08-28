/** 99. Recover Binary Search Tree(leetcode) / 691. Recover Binary Search Tree(lintcode)
 *    Two elements of a binary search tree (BST) are swapped by mistake.
 *    Recover the tree without changing its structure.
 *
 *    Example: Input: [1,3,null,null,2]
 *                    1
 *                   /
 *                  3
 *                   \
 *                    2
 *             Output: [3,1,null,null,2]
 *                    3
 *                   /
 *                  1
 *                   \
 *                    2
 *
 *    Follow up: A solution using O(n) space is pretty straight forward.
 *               Could you devise a constant space solution?
 */
 
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 //My Method: O(n) space complexity
 public void recoverTree(TreeNode root) {
    if(root == null){
        return;
    }
    Stack<TreeNode> s = new Stack<TreeNode>();
    List<TreeNode> nodes = new ArrayList<TreeNode>();
    TreeNode cur = root;
    while(cur != null){
        s.push(cur);
        cur = cur.left;
    }

    while(!s.isEmpty()){
        cur = s.pop();
        nodes.add(cur);
        cur = cur.right;
        while(cur != null){
            s.push(cur);
            cur = cur.left;
        }
    }

    int i = 1;
    for(; i < nodes.size(); i++){
        if(nodes.get(i).val < nodes.get(i-1).val){
            break;
        }
    }

    int j = i+1;
    i--;
    for(; j < nodes.size(); j++){
        if(nodes.get(j).val > nodes.get(i).val){
            break;
        }
    }
    j--;

    int tmp = nodes.get(i).val;
    nodes.get(i).val = nodes.get(j).val;
    nodes.get(j).val = tmp;       
}

/* Follow up: https://leetcode.com/problems/recover-binary-search-tree/discuss/32559/Detail-Explain-about-How-Morris-Traversal-Finds-two-Incorrect-Pointer
 *            http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
 */
