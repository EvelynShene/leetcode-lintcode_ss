/** 578. Lowest Common Ancestor III(lintcode) - 类似 leetcode 236，添加了节点可能不存在树中的情况
 *      Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
 *      The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
 *      Return null if LCA does not exist.
 *
 *      Note: node A or node B may not exist in tree.
 *
 *      Example: For the following binary tree:
 *                     4
 *                    / \
 *                   3   7
 *                      / \
 *                     5   6
 *                LCA(3, 5) = 4
 *                LCA(5, 6) = 7
 *                LCA(6, 7) = 7
 *                LCA(1, 3) = null
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
//My Method:
public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
    if(root == null){
        return root;
    }
    Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    map.put(root,null);
    q.offer(root);
    while(!q.isEmpty()){
        if(map.containsKey(A) && map.containsKey(B)){
            break;
        }
        TreeNode cur = q.poll();
        if(cur.left != null){
            q.offer(cur.left);
            map.put(cur.left, cur);
        }
        if(cur.right != null){
            q.offer(cur.right);
            map.put(cur.right, cur);
        }
    }
    if(q.isEmpty() && (!map.containsKey(A) || !map.containsKey(B))){
        return null;
    }
    Set<TreeNode> parents = new HashSet<TreeNode>();
    while(A != null){
        parents.add(A);
        A = map.get(A);
    }
    while(!parents.contains(B)){
        B = map.get(B);
    }
    return B;
}
