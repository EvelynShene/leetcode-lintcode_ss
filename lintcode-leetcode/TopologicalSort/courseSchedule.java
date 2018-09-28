/** 207. Course Schedule(leetcode) / 615. Course Schedule(lintcode)
 *      There are a total of n courses you have to take, labeled from 0 to n-1.
 *      Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which
 *  is expressed as a pair: [0,1]
 *      Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all 
 *  courses?
 *      
 *      Example: 1) Input: 2, [[1,0]]  ; Output: true
 *                  Explanation: There are a total of 2 courses to take. 
 *                              To take course 1 you should have finished course 0. So it is possible.
 *               2) Input: 2, [[1,0],[0,1]] ; Output: false
 *                  Explanation: There are a total of 2 courses to take. 
 *                            To take course 1 you should have finished course 0, and to take course 0 you should
 *                            also have finished course 1. So it is impossible.
 *
 *      Note: 1) The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
 *              Read more about how a graph is represented.
 *            2) You may assume that there are no duplicate edges in the input prerequisites.
 */
 
 /* Method 1: Use BFS - 等同问题是有向图检测环[https://blog.csdn.net/u012148952/article/details/51377962]
  *     Kahn's Algorithms (wiki)： BFS based， start from with vertices with 0 incoming edge，insert them into 
  *             list S，at the same time we remove all their outgoing edges，after that find new vertices with 0 
  *             incoming edges and go on. 
  *     [AC in leetcode but MLE in lintcode]
  */
 public boolean canFinish(int numCourses, int[][] prerequisites) {
    if(prerequisites == null || prerequisites.length == 0){
        return true;
    }
    int[] indegree = new int[numCourses];
    int[][] graph = new int[numCourses][numCourses];
    for(int i = 0; i < prerequisites.length; i++){
        int pre = prerequisites[i][1];
        int cur = prerequisites[i][0];
        if(graph[pre][cur] == 0){
            indegree[cur]++; // remove duplicate case
        }
        graph[pre][cur] = 1;
    }

    Queue<Integer> q = new LinkedList<Integer>(); // store the nodes which has in-degree 0
    for(int i = 0; i < numCourses; i++){
        if(indegree[i] == 0){
            q.offer(i);
        }
    }

    while(!q.isEmpty()){
        int course = q.poll();
        for(int i = 0; i < numCourses; i++){
            if(graph[course][i] != 0){
                indegree[i]--;
                if(indegree[i] == 0){
                    q.offer(i);
                }
            }
        }
    }
    for(int i = 0; i < numCourses; i++){
        if(indegree[i] != 0){
            return false;
        }
    }
    return true;
}

//Above method is MLE in lintcode, so use link to store graph
public boolean canFinish(int numCourses, int[][] prerequisites) {
    if(prerequisites == null || prerequisites.length == 0){
        return true;
    }
    int[] indegree = new int[numCourses];
    List[] graph = new ArrayList[numCourses];

    for (int i = 0;i < numCourses; i++){
        graph[i] = new ArrayList<Integer>();
    }

    for(int i = 0; i < prerequisites.length; i++){
        int pre = prerequisites[i][1];
        int cur = prerequisites[i][0];
        indegree[cur]++;
        graph[pre].add(cur);
    }

    Queue<Integer> q = new LinkedList<Integer>(); // store the nodes which has in-degree 0
    for(int i = 0; i < numCourses; i++){
        if(indegree[i] == 0){
            q.offer(i);
        }
    }
    int count = 0;
    while(!q.isEmpty()){
        int course = q.poll();
        count++;
        int n = graph[course].size();
        for(int i = 0; i < n; i++){
            int pointer = (int)graph[course].get(i);
            indegree[pointer]--;
            if (indegree[pointer] == 0) {
                q.offer(pointer);
            }
        }
    }
    return count == numCourses;
}

/** Method 2: Use DFS [AC in leetcode but TLE in lintcode]
 *      Tarjan's Algorithms (wiki)： DFS based， loop through each node of the graph in an arbitrary order，
 *  initiating a depth-first search that terminates when it hits any node that has already been visited since 
 *  the beginning of the topological sort or the node has no outgoing edges (i.e. a leaf node).
 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0){
            return true;
        }
        List[] graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i < prerequisites.length; i++){
            int pre = prerequisites[i][1];
            int cur = prerequisites[i][0];
            graph[pre].add(cur);
        }
        boolean[] visited = new boolean[numCourses];
        for(int i = 0; i < numCourses; i++){
            if(!dfs(i, graph, visited)){
                return false;
            }
        }
        return true;
    }
    
    public boolean dfs(int course, List[] graph, boolean[] visited){
        if(visited[course]){
            return false;
        }
        visited[course] = true;
        List<Integer> nextcourse = graph[course];
        for(int i = 0; i < nextcourse.size(); i++){
            if(!dfs(nextcourse.get(i), graph, visited)){
                return false;
            }
        }
        visited[course] = false;
        return true;
    }
}
