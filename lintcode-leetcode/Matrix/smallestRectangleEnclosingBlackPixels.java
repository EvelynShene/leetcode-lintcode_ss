/** 600. Smallest Rectangle Enclosing Black Pixels(lintcode) / 302. Smallest Rectangle Enclosing Black Pixels(leetcode - locked)
 *      An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black 
 *  pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. 
 *  Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle
 *  that encloses all black pixels.
 *
 *      Example: For example, given the following image:
 *                       [
 *                         "0010",
 *                         "0110",
 *                         "0100"
 *                       ]
 *                  and x = 0, y = 2,
 *                Return 6.
 */
 
 //Method 1: BFS (Brute force)
 public int minArea(char[][] image, int x, int y) {
    if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
    }
    int m = image.length;
    int n = image[0].length;
    int[] deta = {-1, 0 , 1, 0, -1};
    int up = x, down = x, left = y, right = y;
    boolean[][] visited = new boolean[m][n];
    visited[x][y] = true;

    Queue<Integer> q = new LinkedList<Integer>();
    q.offer(x * n + y);

    while(!q.isEmpty()){
        int size = q.size();
        while(size > 0){
            int index = q.poll();
            for(int i = 0; i < 4; i++){
                int row = index / n + deta[i];
                int col = index % n + deta[i+1];
                if(row >= 0 && row < m && col >= 0 && col < n && image[row][col] == '1' && !visited[row][col]){
                    visited[row][col] = true;
                    q.offer(row * n + col);
                    up = Math.min(up,row);
                    down = Math.max(down, row);
                    left = Math.min(left, col);
                    right = Math.max(right, col);
                }
            }
            size--;
        }
    }
    return (down - up + 1) * (right - left + 1);
 }
 
 //Method 2: Binary Search
 public class Solution {
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        int m = image.length;
        int n = image[0].length;
        
        int left = findLeft(image, 0, y);
        int right = findRight(image, y, n - 1);
        int up = findUp(image, 0, x);
        int down = findDown(image, x, m - 1);
    
        return (down - up + 1) * (right - left + 1);
    }
    public boolean isEmptyCol(char[][] image, int col){
        for(int i = 0; i < image.length; i++){
            if(image[i][col] == '1'){
                return false;
            }
        }
        return true;
    }
    
    public boolean isEmptyRow(char[][] image, int row){
        for(int i = 0; i < image[0].length; i++){
            if(image[row][i] == '1'){
                return false;
            }
        }
        return true;
    }
    
    public int findLeft(char[][] image, int start, int end){
        while(start < end){
            int mid = start + (end - start) / 2;
            if(isEmptyCol(image, mid)){
                start = mid + 1;
            }
            else{
                end = mid;
            }
        }
        return start;
    }
    
    public int findRight(char[][] image, int start, int end){
        while(start <= end){
            int mid = start + (end - start) / 2;
            if(isEmptyCol(image, mid)){
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        return end;
    }
    
    public int findUp(char[][] image, int start, int end){
        while(start < end){
            int mid = start + (end - start) / 2;
            if(isEmptyRow(image, mid)){
                start = mid + 1;
            }
            else{
                end = mid;
            }
        }
        return start;
    }
    
    public int findDown(char[][] image, int start, int end){
        while(start <= end){
            int mid = start + (end - start) / 2;
            if(isEmptyRow(image, mid)){
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        return end;
    }
}
