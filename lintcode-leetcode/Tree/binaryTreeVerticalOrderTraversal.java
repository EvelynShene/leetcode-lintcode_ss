/** 314. Binary Tree Vertical Order Traversal(leetcode - locked) / 651. Binary Tree Vertical Order Traversal(lintcode)
 *     Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, 
 *  column by column).
 *     If two nodes are in the same row and column, the order should be from left to right.
 *
 *     Example: 1) Given binary tree {3,9,20,#,#,15,7}
 *                      3
 *                     /\
 *                    /  \
 *                    9  20
 *                       /\
 *                      /  \
 *                     15   7
 *               Return its vertical order traversal as:
 *                  [[9],[3,15],[20],[7]]
 *
 *              2) Given binary tree {3,9,8,4,0,1,7}
 *                        3
 *                       /\
 *                      /  \
 *                      9   8
 *                     /\  /\
 *                    /  \/  \
 *                    4  01   7
 *               Return its vertical order traversal as:
 *                  [[4],[9],[3,0,1],[8],[7]]
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
 
 /* Method:
  *    思路：把根节点给个序号0，然后开始层序遍历，凡是左子节点则序号减1，右子节点序号加1，这样可以通过序号来把相同列的节点值放到一起，
  *      用一个TreeMap来建立序号和其对应的节点值的映射，用到两个queue，一个用来存节点用于层序遍历，一个用来存节点对应的序号。
  *      组成的pair。
  */
public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if(root == null){
        return res;
    }
    Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    Queue<Integer> seq = new LinkedList<Integer>();
    q.offer(root);
    seq.offer(0);

    while(!q.isEmpty()){
        TreeNode cur = q.poll();
        int i = seq.poll();
        if(!map.containsKey(i)){
            map.put(i, new ArrayList<Integer>());
        }
        map.get(i).add(cur.val);
        if(cur.left != null){
            q.offer(cur.left);
            seq.offer(i - 1);
        }
        if(cur.right != null){
            q.offer(cur.right);
            seq.offer(i + 1);
        }
    }

    for(int i = Collections.min(map.keySet()); i <= Collections.max(map.keySet()); i++){
        res.add(map.get(i));
    }
    return res;
}  
