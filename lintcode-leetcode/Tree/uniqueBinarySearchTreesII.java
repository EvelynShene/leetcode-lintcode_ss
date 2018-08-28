/** 95. Unique Binary Search Trees II (leetcode)/ 164. Unique Binary Search Trees II(lintcode)
 *      Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 *
 *      Example: Input: 3
 *               Output:
 *                 [
 *                   [1,null,3,2],
 *                   [3,2,null,1],
 *                   [3,1,null,null,2],
 *                   [2,1,3],
 *                   [1,null,2,null,3]
 *                 ]
 *               Explanation:
 *                   The above output corresponds to the 5 unique BST's shown below:
 *                          1         3     3      2      1
 *                           \       /     /      / \      \
 *                            3     2     1      1   3      2
 *                           /     /       \                 \
 *                          2     1         2                 3
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
 
 //My Method: Recursive
 class Solution {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        
        res = generate(1, n);
        return res;
    }
    
    public List<TreeNode> generate(int start, int end){
        if(end < start) {
            return new ArrayList<>();
        }
        if(end == start){
            List<TreeNode> r = new ArrayList<>();
            r.add(new TreeNode(start));
            return r;
        }
        
        List<TreeNode> res = new ArrayList<>();
        for(int i = start; i <= end; i++){
            
            List<TreeNode> left = generate(start, i - 1);
            List<TreeNode> right = generate(i + 1, end);
            
            if(left.size() == 0){
                for(int j = 0; j < right.size(); j++){
                    // should new a root here because it need to 
                    // be different for each tree
                    TreeNode root = new TreeNode(i);
                    root.right = right.get(j);
                    res.add(root);
                }
            }
            else if(right.size() == 0){
                for(int j = 0; j < left.size(); j++){
                    TreeNode root = new TreeNode(i);
                    root.left = left.get(j);
                    res.add(root);
                }
            }
            else{
                for(int j = 0; j < left.size(); j++){
                    for(int k = 0; k < right.size(); k++){
                        TreeNode root = new TreeNode(i);
                        root.left = left.get(j);
                        root.right = right.get(k);
                        res.add(root);
                    }
                }
            }   
        }
        return res;
    }
}
