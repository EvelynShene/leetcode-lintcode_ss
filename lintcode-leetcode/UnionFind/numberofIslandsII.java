/** 434. Number of Islands II(lintcode) / 305. Number of Islands II(leetcode - locked)
 *       Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k). Originally, 
 *  the 2D matrix is all 0 which means there is only sea in the matrix. The list pair has k operator and each 
 *  operator has two integer A[i].x, A[i].y means that you can change the grid matrix[A[i].x][A[i].y] from sea to 
 *  island. Return how many island are there in the matrix after each operator.
 *
 *        Note: 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider 
 *    them in the same island. We only consider up/down/left/right adjacent.
 *
 *        Example: Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)]. return [1,1,2,2].
 */

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
 
 //My Method: 
 public class Solution {
    public static List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> res = new ArrayList<>();
        if(operators == null || operators.length == 0 || m == 0 || n == 0){
            return res;
        }
        
        int[][] sea = new int[n][m];
        int island_num = 0;
        int[] deta = {-1, 0, 1, 0, -1};
        for(Point op : operators){
            if(sea[op.x][op.y] != 0){
                res.add(island_num);
                continue;
            }
            Set<Integer> root = new HashSet<>();
            for(int i = 0; i < 4; i++){
                int x = op.x + deta[i];
                int y = op.y + deta[i + 1];
                if(x >= 0 && x < n && y >= 0 && y < m && sea[x][y] != 0){
                    int[] tmp = findRoot(x, y, sea);
                    root.add(sea[tmp[0]][tmp[1]]);
                }
            }
            sea[op.x][op.y] = op.x * m + op.y + 1; 
            for(Integer r: root){
            	sea[(r - 1) / m][(r - 1) % m] = op.x * m + op.y + 1;
            }
            
            island_num = island_num + 1 - root.size();
            res.add(island_num);
        }
        return res;
    }
    
    public static int[] findRoot(int row, int col, int[][] sea){
        int n = sea.length;
        int m = sea[0].length;
        
        int id = sea[row][col];
        
        while(id != row * m + col + 1){
            row = (id - 1) / m;
            col = (id - 1) % m;
            id = sea[row][col];
        }
        return new int[]{row, col};
    }
}

//Method 2: [from jiuzhang - https://www.jiuzhang.com/solution/number-of-islands-ii/]
public class Solution {
    /**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    int converttoId(int x, int y, int m){
        return x*m + y;
    }
    class UnionFind{
        HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
        UnionFind(int n, int m){
            for(int i = 0 ; i < n; i++) {
                for(int j = 0 ; j < m; j++) {
                    int id = converttoId(i,j,m);
                    father.put(id, id); 
                }
            }
        }
        int compressed_find(int x){
            int parent =  father.get(x);
            while(parent!=father.get(parent)) {
                parent = father.get(parent);
            }
            int temp = -1;
            int fa = x;
            while(fa!=father.get(fa)) {
                temp = father.get(fa);
                father.put(fa, parent) ;
                fa = temp;
            }
            return parent;
                
        }
        
        void union(int x, int y){
            int fa_x = compressed_find(x);
            int fa_y = compressed_find(y);
            if(fa_x != fa_y)
                father.put(fa_x, fa_y);
        }
    }
    
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // Write your code here
        List<Integer> ans = new ArrayList<Integer>();
        if(operators == null) {
            return ans;
        }
        
        int []dx = {0,-1, 0, 1};
        int []dy = {1, 0, -1, 0};
        int [][]island = new int[n][m];
       
        
        UnionFind uf = new UnionFind(n, m);
        int count = 0;
        
        for(int i = 0; i < operators.length; i++) {
            int x = operators[i].x;
            int y = operators[i].y;
            if(island[x][y] != 1) {
                count ++;
                island[x][y]  = 1;
                int id = converttoId(x,y , m);
                for(int j = 0 ; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if(0 <= nx && nx < n && 0 <= ny && ny < m && island[nx][ny] == 1) 
                    {
                        int nid = converttoId(nx, ny, m);
                        
                        int fa = uf.compressed_find(id);
                        int nfa = uf.compressed_find(nid);
                        if(fa != nfa) {
                            count--;
                            uf.union(id, nid);
                        }
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }
}
