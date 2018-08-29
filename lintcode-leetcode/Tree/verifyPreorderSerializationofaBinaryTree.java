/** 331. Verify Preorder Serialization of a Binary Tree(leetcode) / 1066. Verify Preorder Serialization of a Binary Tree(lintcode)
 *     One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, 
 *  we record the node's value. If it is a null node, we record using a sentinel value such as #.
 *                  _9_
 *                 /   \
 *                3     2
 *               / \   / \
 *              4   1  #  6
 *             / \ / \   / \
 *             # # # #   # #
 *     For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#",
 *  where # represents a null node.
 *
 *     Given a string of comma separated values, verify whether it is a correct preorder traversal serialization 
 *  of a binary tree. Find an algorithm without reconstructing the tree.
 *
 *     Each comma separated value in the string must be either an integer or a character '#' representing null 
 *  pointer.
 *  
 *     You may assume that the input format is always valid, for example it could never contain two consecutive 
 *  commas such as "1,,3".
 *
 *     Example 1) Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 *                Output: true
 *             2) Input: "1,#"
 *                Output: false
 *             3) Input: "9,#,#,1"
 *                Output: false
 */
 
 /* Method 1:
  *     序列化后的数组特征有：
  *       1） 数字的个数总是比#号少一个
  *       2） 最后一个一定是#号
  *     在正确的序列里，任何一个位置i，在[0, i]范围内的#号数都不大于数字的个数的。所以使用计数器实现。
  */
 public boolean isValidSerialization(String preorder) {
    if(preorder == null || preorder.length() == 0){
        return false;
    }
    String[] vals = preorder.split(",");
    if(!vals[vals.length - 1].equals("#")){
        return false;
    }
    int count = 0;
    for(int i = 0; i < vals.length - 1; i++){
        if(!vals[i].equals("#")){
            count++;
        }
        else{
            count--;
        }
        if(count < 0){
            return false;
        }
    }
    if(count == 0)
        return true;
    return false;
 }
