/** 210. Course Schedule II(leetcode) / 616. Course Schedule II(lintcode)
 *      There are a total of n courses you have to take, labeled from 0 to n-1.
 *      Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which 
 *  is expressed as a pair: [0,1]
 *      Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you 
 *  should take to finish all courses.
 *      There may be multiple correct orders, you just need to return one of them. If it is impossible to finish
 *  all courses, return an empty array.
 *
 *      Example: Input: 4, [[1,0],[2,0],[3,1],[3,2]] ; Output: [0,1,2,3] or [0,2,1,3]
 *            Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
 *                        courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
 *                         So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 *      
 *      Note: 1) The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
 *                Read more about how a graph is represented.
 *            2) You may assume that there are no duplicate edges in the input prerequisites.
 */
 
 //My Method: BFS
 class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List[] graph = new ArrayList[numCourses];
        
        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<Integer>();
        }
        int[] indegree = new int[numCourses];
        int[] res = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++){
            int pre = prerequisites[i][1];
            int cur = prerequisites[i][0];
            graph[pre].add(cur);
            indegree[cur]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        int count = 0;
        int index = 0;
        while(!q.isEmpty()){
            int course = q.poll();
            res[count] = course;
            List<Integer> next = graph[course];
            for(int i = 0; i < next.size(); i++){
                indegree[next.get(i)]--;
                if(indegree[next.get(i)] == 0){
                    q.offer(next.get(i));
                }
            }
            count++;
        }
        if(count == numCourses){
            return res;
        }
        return new int[0];
    }
}
