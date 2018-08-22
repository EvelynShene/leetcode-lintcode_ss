/** 236. Lowest Common Ancestor of a Binary Tree(leetcode) / 88. Lowest Common Ancestor of a Binary Tree(lintcode)
 *      Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *      According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes 
 *  p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant
 *  of itself).”
 *
 *      Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *                       _______3______
 *                      /              \
 *                   ___5__          ___1__
 *                  /      \        /      \
 *                  6      _2       0       8
 *                        /  \
 *                        7   4
 *      Example: 1) Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *                  Output: 3
 *                      Explanation: The LCA of of nodes 5 and 1 is 3.
 *               2) Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 *                  Output: 5
 *                      Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself
 *                             according to the LCA definition.
 *
 *      Note: 1) All of the nodes' values will be unique.
 *            2) p and q are different and both values will exist in the binary tree.
 */
 
 /* Method 1: Recursive
  *     1) If the current (sub)tree contains both p and q, then the function result is their LCA. 
  *     2) If only one of them is in that subtree, then the result is that one of them. 
  *     3) If neither are in that subtree, the result is null.
  */
 public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if(root == null || p == root || q == root){
        return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if(left != null && right != null){
        return root;
    }
    if(left == null){
        return right;
    }
    return left;
 }
 
 /* Method 2: Iterative - HashMap + HashSet + Queue[slower than Method 1]
  *     1) Use HashMap<node, node's parent> to track their ancestors. 
  *     2) After we found both p and q and their parents, we create a set of p's ancestors. 
  *     3) Then we travel through q's ancestors, the first one appears in p's is our answer.
  */
 public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if(root == null || p == root || q == root){
        return root;
    }
    Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>(); // map<node, node's parent>
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);
    map.put(root,null);
    while(!map.containsKey(p) || !map.containsKey(q)){
        TreeNode cur = queue.poll();
        if(cur.left != null){
            queue.offer(cur.left);
            map.put(cur.left, cur);
        }
        if(cur.right != null){
            queue.offer(cur.right);
            map.put(cur.right, cur);
        }
    }
    Set<TreeNode> parents = new HashSet<TreeNode>();
    while(p != null){
        parents.add(p);
        p = map.get(p);
    }
    while(!parents.contains(q)){
        q = map.get(q);
    }
    return q;
 }
