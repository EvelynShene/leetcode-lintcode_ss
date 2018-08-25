/** 297. Serialize and Deserialize Binary Tree(leetcode) / 7. Serialize and Deserialize Binary Tree(lintcode)
 *      Serialization is the process of converting a data structure or object into a sequence of bits so that it 
 *  can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed 
 *  later in the same or another computer environment.
 *      Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your 
 *  serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be 
 *  serialized to a string and this string can be deserialized to the original tree structure.
 *
 *      Example: You may serialize the following tree:
 *                       1
 *                      / \
 *                     2   3
 *                        / \
 *                       4   5
 *               as "[1,2,3,null,null,4,5]"
 *
 *       Note: Do not use class member/global/static variables to store states. 
 *             Your serialize and deserialize algorithms should be stateless.
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
 
//My Method:  BFS - O(n) time and space complexity
public class Codec {
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return null;
        }
        //list l 这段代码的目的是为了求root中节点的数目，这样在序列化时，就可以提前结束，省略q结尾部分的所有null节点
        List<TreeNode> l = new ArrayList<TreeNode>();
        l.add(root);
        int index = 0;
        while(index < l.size()){
            TreeNode cur = l.get(index);
            if(cur.left != null){
                l.add(cur.left);
            }
            if(cur.right != null){
                l.add(cur.right);
            }
            index++;
        }
        
        StringBuilder res = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        StringBuilder empty = new StringBuilder();
        q.offer(root);
        int count = 0;
        while(!q.isEmpty() && count <= l.size()){
            TreeNode cur = q.poll();
            if(cur != null){
                count++;
                res.append(empty.toString());
                empty = new StringBuilder();
                if(cur != root){
                    res.append(",");
                }
                res.append("" + cur.val);
                q.offer(cur.left);
                q.offer(cur.right);
                
            }
            else{
                empty.append(",null");
            }
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0){
            return null;
        }
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        int index = 1;
        TreeNode cur = root;
        while(index < values.length){
            if(!values[index].equals("null")){
                cur.left = new TreeNode(Integer.valueOf(values[index]));
                q.offer(cur.left);
            }
            index++;
            if(index < values.length && !values[index].equals("null")){
                cur.right = new TreeNode(Integer.valueOf(values[index]));
                q.offer(cur.right);
            }
            index++;
            cur = q.poll();
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
