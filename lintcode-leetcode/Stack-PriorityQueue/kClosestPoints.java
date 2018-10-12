/** 612. K Closest Points(lintcode)
 *      Given some points and an origin point in two-dimensional space, find k points which are nearest to the 
 *  origin.
 *      Return these points sorted by distance, if they are same in distance, sorted by the x-axis, and if they 
 *  are same in the x-axis, sorted by y-axis.
 *
 *      Example: Given points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3 
 *               return [[1,1],[2,5],[4,4]]
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
    /**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    class Node{
        Point p;
        int distance;
        
        public Node(Point point, int distance){
            this.p = new Point(point.x, point.y);
            this.distance = distance;
        }
    }
    
    private Comparator<Node> nodeComparator = new Comparator<Node>(){
        public int compare(Node a, Node b){
            
            if(a.distance == b.distance){
                if(a.p.x == b.p.x){
                    return b.p.y - a.p.y;
                }
                return b.p.x - a.p.x;
            }
            return b.distance - a.distance; // b > a, b is in front of a
        }
    };
    
    public Point[] kClosest(Point[] points, Point origin, int k) {
        Point[] res = new Point[k];
        if(k == 0){
            return res;
        }
        
        PriorityQueue<Node> queue = new PriorityQueue<Node>(k, nodeComparator);
        for(Point p: points){
            int dis = (p.x - origin.x) * (p.x - origin.x) + (p.y - origin.y) * (p.y - origin.y);
            Node node = new Node(p, dis);
            if(queue.size() < k){
                queue.offer(node);
            }
            else{// queue.size() == k
                /*
                 * 1) if node.distance < queue.peek().distance, will return res > 0, need replace
                 * 2) if node.distance = queue.peek().distance:
                 *      if queue.peek().x > node.x || queue.peek().y > node.y , will return res > 0
                 *          need replace 
                 */
                if(nodeComparator.compare(node, queue.peek()) > 0){
                    queue.poll();
                    queue.offer(node);
                }
            }
        }
        
        for(int i = k - 1; i >= 0; i--){
            res[i] = queue.poll().p;
        }
        return res;
    }
}
