/** 912. Best Meeting Point(leetcode) / 296. Best Meeting Point(lintcode - locked)
 *      A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid
 *  of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using
 *  Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 *      Example: Given three people living at (0,0), (0,4), and (2,2):
 *                       1 - 0 - 0 - 0 - 1
 *                       |   |   |   |   |
 *                       0 - 0 - 0 - 0 - 0
 *                       |   |   |   |   |
 *                       0 - 0 - 1 - 0 - 0
 *               The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 is 
 *          minimal. So return 6.
 */
 
 //Method 1: Brute Force
 public int minTotalDistance(int[][] grid) {
    if(grid == null || grid.length == 0){
        return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    List<int[]> homes = new ArrayList<int[]>();

    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(grid[i][j] == 1){
                int[] t = new int[2];
                t[0] = i;
                t[1] = j;
                homes.add(t);
            }
        }
    }

    int min = Integer.MAX_VALUE;
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            int dis = 0;
            for(int k = 0; k < homes.size(); k++){
                int[] t = homes.get(k);
                dis += Math.abs(t[0] - i) + Math.abs(t[1] - j);
                if(dis > min){
                    break;
                }
            }
            min = Math.min(dis, min);
        }
    }
    return min;
}

//Method 2: O(mn) time and O(m + n) space complexity [idea from https://www.cnblogs.com/grandyang/p/5291058.html]
public int minTotalDistance(int[][] grid) {
    if(grid == null || grid.length == 0){
        return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    List<Integer> rows = new ArrayList<>();
    List<Integer> cols = new ArrayList<Integer>();

    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(grid[i][j] == 1){
                rows.add(i);
                cols.add(j);
            }
        }
    }

    int min = 0;
    Collections.sort(cols);
    int i = 0; 
    int j = rows.size() - 1;
    while(i < j){
        min += rows.get(j) - rows.get(i) + cols.get(j) - cols.get(i);
        i++;
        j--;
    }
    return min;
}
