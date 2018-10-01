/** 399. Evaluate Division(leetcode)
 *      Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is 
 *  a real number (floating point number). Given some queries, return the answers. If the answer does not exist, 
 *  return -1.0.
 *
 *      Example: 1) Given a / b = 2.0, b / c = 3.0. 
 *                  queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
 *                  return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 *      The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>>
 *  queries , where equations.size() == values.size(), and the values are positive. This represents the equations.
 *  Return vector<double>.
 *
 *      According to the example above: equations = [ ["a", "b"], ["b", "c"] ], values = [2.0, 3.0],
 *            queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
 *      Note:
 *        The input is always valid. You may assume that evaluating the queries will result in no division by zero 
 *      and there is no contradiction.
 */
 
 //Method: [Idea from https://segmentfault.com/a/1190000008330883]
 class Solution {
    class Info{
        String den;
        double val;
        public Info(String den, double val){
            this.den = den;
            this.val = val;
        }
    }
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, List<Info>> edges = new HashMap<>();
        for(int i = 0; i < equations.length; i++){
            if(!edges.containsKey(equations[i][0])){
                edges.put(equations[i][0], new ArrayList<>());
            }
            edges.get(equations[i][0]).add(new Info(equations[i][1], values[i]));
            if(!edges.containsKey(equations[i][1])){
                edges.put(equations[i][1], new ArrayList<>());
            }
            edges.get(equations[i][1]).add(new Info(equations[i][0], 1 / values[i]));
        }
        
        double[] res = new double[queries.length];
        // Set<String> visited = new HashSet<>();
        for(int i = 0; i < queries.length; i++){
            res[i] = find(queries[i][0], queries[i][1], edges, 1, new HashSet<>());
        }
        return res;
    }
    
    public double find(String start, String end, Map<String, List<Info>> edges, double value, Set<String> visited){
        if(visited.contains(start)){
            return -1;
        }
        if(!edges.containsKey(start) || !edges.containsKey(end)){
            return -1;
        }
        if(start.equals(end)){
            return value;
        }
        visited.add(start);
        List<Info> list = edges.get(start);
        for(int i = 0; i < list.size(); i++){
            double res = find(list.get(i).den, end, edges, value * list.get(i).val, visited);
            if(res != -1){
                return res;
            }
        }
        visited.remove(start);
        return -1;
    }
}
