/** 332. Reconstruct Itinerary(lintcode)
 *      Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], 
 *  reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the 
 *  itinerary must begin with JFK.
 *
 *      Note:1) If there are multiple valid itineraries, you should return the itinerary that has the smallest 
 *              lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a 
 *              smaller lexical order than ["JFK", "LGB"].
 *           2) All airports are represented by three capital letters (IATA code).
 *           3) You may assume all tickets form at least one valid itinerary.
 *
 *      Example: 1) Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 *                  Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *               2) Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 *                  Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 *                    Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *                                But it is larger in lexical order.
 *
 *      Note: 必须将所有的航线都连起来（本质是有向图的遍历问题）
 */
 
 //My Method 1: DFS
 class Solution {
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new ArrayList<String>();
        if(tickets == null || tickets.length == 0){
            return res;
        }
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(int i = 0; i < tickets.length; i++){
            if(!map.containsKey(tickets[i][0])){
                List<String> q = new ArrayList<String>();
                q.add(tickets[i][1]);
                map.put(tickets[i][0], q);
            }
            else{
                map.get(tickets[i][0]).add(tickets[i][1]);
            }
        }
        for(Map.Entry<String, List<String>> entry: map.entrySet()){
            List<String> q = entry.getValue();
            if(q.size() > 1){
                Collections.sort(q);
            }
        }
        res.add("JFK");
        dfs(res, map);
        return res;
    }
    
    public boolean dfs(List<String> res, Map<String, List<String>> map){
        if(map.size() == 0){
            return true;
        }
        String key = res.get(res.size() - 1);
        if(!map.containsKey(key)){
            return false;
        }
        List<String> q = map.get(key);
        if(q.size() == 1){
            res.add(q.get(0));
            map.remove(key);
            if(dfs(res, map)){
                return true;
            }
            else{
                map.put(key, q);
                res.remove(res.size() - 1);
            }
        }
        else{
            for(int i = 0; i < q.size(); i++){
                res.add(q.get(i));
                q.remove(i);
                if(dfs(res, map)){
                    return true;
                }
                else{
                    q.add(i, res.get(res.size() - 1));
                    res.remove(res.size() - 1);
                }
            }
        }
        return false;
    }
}
 
//Method 2: Find Eulerian path
class Solution {
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new ArrayList<String>();
        if(tickets == null || tickets.length == 0){
            return res;
        }
        Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
        for(int i = 0; i < tickets.length; i++){
            if(!map.containsKey(tickets[i][0])){
                PriorityQueue<String> q = new PriorityQueue<String>();
                map.put(tickets[i][0], q);
            }
            map.get(tickets[i][0]).add(tickets[i][1]);
        }
        dfs(res, map, "JFK");
        return res;
    }
    
    public void dfs(List<String> res, Map<String, PriorityQueue<String>> map, String airport){
        while(map.containsKey(airport) && !map.get(airport).isEmpty()){
            dfs(res, map, map.get(airport).poll());
        }
        res.add(0, airport);
    }   
}
