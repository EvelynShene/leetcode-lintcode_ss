/** 547. Friend Circles(leetcode)
 *     There are N students in a class. Some of them are friends, while some are not. Their friendship is 
 *  transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an 
 *  indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
 *     Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, 
 *  then the ith and jth students are direct friends with each other, otherwise not. And you have to output the 
 *  total number of friend circles among all the students.
 *
 *     Example: 1) Input: 
 *                     [[1,1,0],
 *                      [1,1,0],
 *                      [0,0,1]]
 *                 Output: 2
 *                 Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
 *                        The 2nd student himself is in a friend circle. So return 2.
 *
 *              2) Input: 
 *                     [[1,0,0,1],
 *                      [0,1,1,0],
 *                      [0,1,1,0]
 *                      [1,0,0,1]]
 *                 Output: 1
 *
 *      Note: 1) N is in range [1,200].
 *            2) M[i][i] = 1 for all students.
 *            3) If M[i][j] = 1, then M[j][i] = 1.
 *
 *     【不能使用连通性DFS求解】
 */
 
 //My Method: Union Find
 class Solution {
    class UnionFind{
        int count;
        int[] ids;
        public UnionFind(int n){
            count = n;
            ids = new int[n];
            for(int i = 0; i < n; i++){
                ids[i] = i;
            }
        }
        
        public int count(){
            return count;
        }
        
        public int find(int a){
            while(ids[a] != a){
                a = ids[a];
            }
            return a;
        }
        
        public void union(int a, int b){
            int root_a = find(a);
            int root_b = find(b);
            if(root_a != root_b){
                ids[root_a] = root_b;
                count--;
            }
        }
    }
    
    public int findCircleNum(int[][] M) {
        if(M == null || M.length == 0){
            return 0;
        }
        
        int n = M.length;
        UnionFind f = new UnionFind(n);
        
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(M[i][j] == 1){
                    f.union(i, j);
                }
            }
        }        
        return f.count();
    } 
}
