/** 526. Load Balancer(lintcode)
 *    Implement a load balancer for web servers. It provide the following functionality:
 *      - Add a new server to the cluster => add(server_id).
 *      - Remove a bad server from the cluster => remove(server_id).
 *      - Pick a server in the cluster randomly with equal probability => pick().
 *    Example: 
 *     At beginning, the cluster is empty => {}.
 *      add(1)
 *      add(2)
 *      add(3)
 *      pick()
 *      >> 1         // the return value is random, it can be either 1, 2, or 3.
 *      pick()
 *      >> 2
 *      pick()
 *      >> 1
 *      pick()
 *      >> 3
 *      remove(1)
 *      pick()
 *      >> 2
 *      pick()
 *      >> 3
 *      pick()
 *      >> 3
 */
 
 public class LoadBalancer {
    List<Integer> list;
    HashMap<Integer,Integer> map;
    public LoadBalancer() {
        // do intialization if necessary
        list = new ArrayList<Integer>();
        map = new HashMap<Integer,Integer>();
    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        // write your code here
        int index = list.size();
        map.put(server_id,index);
        list.add(server_id);
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        // write your code here
        if(!map.containsKey(server_id)){
            return;
        }
        int rm_index = map.get(server_id);
        int size = list.size();
        list.set(rm_index,list.get(size-1));
        map.put(list.get(size-1),rm_index); //renew index of the element list.get(size-1)
        map.remove(server_id);
        list.remove(size-1);
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        // write your code here
        int size = list.size();
        Random r = new Random();
        int index = Math.abs(r.nextInt()) % size;
        return list.get(index);
    }
}
