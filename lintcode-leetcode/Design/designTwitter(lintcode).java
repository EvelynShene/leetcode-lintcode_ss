/** 501. Design Twitter(lintcode)
 *      Implement a simple twitter. Support the following method:
 *         1) postTweet(user_id, tweet_text). Post a tweet.
 *         2) getTimeline(user_id). Get the given user's most recently 10 tweets posted by himself, order by 
 *        timestamp from most recent to least recent.
 *         3) getNewsFeed(user_id). Get the given user's most recently 10 tweets in his news feed 
 *        (posted by his friends and himself). Order by timestamp from most recent to least recent.)
 *         4) follow(from_user_id, to_user_id). from_user_id followed to_user_id.
 *         5) unfollow(from_user_id, to_user_id). from_user_id unfollowed to to_user_id.
 *
 *      Example: postTweet(1, "LintCode is Good!!!")
 *               >> 1
 *               getNewsFeed(1)
 *               >> [1]
 *               getTimeline(1)
 *               >> [1]
 *               follow(2, 1)
 *               getNewsFeed(2)
 *               >> [1]
 *               unfollow(2, 1)
 *               getNewsFeed(2)
 *               >> []
 */
 
 //My Method:
 /**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *     }
 * }
 */


public class MiniTwitter {
    class Node{
        Tweet tweet;
        int timestamp;
        Node next;
        Node pre;
        
        public Node(int timestamp){
            this.tweet = null;
            this.timestamp = timestamp;
            next = null;
            pre = null;
        }
        
        public Node(Tweet tweet, int timestamp){
            this.tweet = tweet;
            this.timestamp = timestamp;
            next = null;
            pre = null;
        }
    }
    
    Map<Integer, List<Node>> userFeed; // userFeed<user_id, tweets the user post>
    Map<Integer, Integer> feedSize; // feedSize<user_id, feed_size>
    Map<Integer, Set<Integer>> follows; 
    int timestamp;
    
    public MiniTwitter() {
        // do intialization if necessary
        userFeed = new HashMap<>();
        feedSize = new HashMap<>();
        follows = new HashMap<>();
        timestamp = 0;
    }

    /*
     * @param user_id: An integer
     * @param tweet_text: a string
     * @return: a tweet
     */
    public Tweet postTweet(int user_id, String tweet_text) {
        Node head = null;
        Node tail = null;
        if(!userFeed.containsKey(user_id)){
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.pre = head;
            List<Node> list = new ArrayList<>();
            list.add(head);
            list.add(tail);
            userFeed.put(user_id, list);
            feedSize.put(user_id, 0);
        }
        else{
            head = userFeed.get(user_id).get(0);
            tail = userFeed.get(user_id).get(1);
        }
        
        Tweet newTweet = Tweet.create(user_id, tweet_text);
        Node newNode = new Node(newTweet, timestamp);
        
        newNode.next = head.next;
        newNode.pre = head;
        head.next.pre = newNode;
        head.next = newNode;
        if(feedSize.get(user_id) == 10){// remove the lastNode
            Node lastNode = tail.pre;
            lastNode.pre.next = tail;
            tail.pre = lastNode.pre;
        }
        else{
            feedSize.put(user_id, feedSize.get(user_id) + 1);
        }
        timestamp++;
        return newTweet;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new feeds recently and sort by timeline
     */
    private Comparator<Node> nodeComparator = new Comparator<Node>(){
        public int compare(Node a, Node b){
            return b.timestamp - a.timestamp;
        }
    };
    
    public List<Tweet> getNewsFeed(int user_id) {
        // write your code here
        PriorityQueue<Node> q = new PriorityQueue<Node>(nodeComparator);
        if(feedSize.containsKey(user_id) && feedSize.get(user_id) != 0){
            q.offer(userFeed.get(user_id).get(0).next);
        }
        if(follows.containsKey(user_id) && follows.get(user_id).size() != 0){
            Iterator<Integer> itr = follows.get(user_id).iterator();
            while(itr.hasNext()){
                int user = itr.next();
                if(feedSize.containsKey(user) && feedSize.get(user) != 0){
                    q.offer(userFeed.get(user).get(0).next);
                }
            }
        }
        List<Tweet> res = new ArrayList<>();
        
        while(!q.isEmpty() && res.size() < 10){
            Node node = q.poll();
            res.add(node.tweet);
            if(node.next.timestamp != -1){
                q.offer(node.next);
            }
        }
        return res;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new posts recently and sort by timeline
     */
    public List<Tweet> getTimeline(int user_id) {
        // write your code here
        List<Tweet> res = new ArrayList<>();
        if(feedSize.containsKey(user_id) && feedSize.get(user_id) != 0){
            Node cur = userFeed.get(user_id).get(0).next;
            while(cur.timestamp != -1 && res.size() < 10){
                res.add(cur.tweet);
                cur = cur.next;
            }
        }
        return res;
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int from_user_id, int to_user_id) {
        // write your code here
        if(from_user_id == to_user_id){
            return;
        }
        if(!follows.containsKey(from_user_id)){
            follows.put(from_user_id, new HashSet<>());
        }
        follows.get(from_user_id).add(to_user_id);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int from_user_id, int to_user_id) {
        // write your code here
        if(from_user_id == to_user_id){
            return;
        }
        if(!follows.containsKey(from_user_id) || !follows.get(from_user_id).contains(to_user_id)){
            return;
        }
        follows.get(from_user_id).remove(to_user_id);
    }
}
