/** 355. Design Twitter(leetcode)
 *      Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is 
 *  able to see the 10 most recent tweets in the user's news feed. Your design should support the following 
 *  methods:
 *       1) postTweet(userId, tweetId): Compose a new tweet.
 *       2) getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the 
 *      news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered 
 *      from most recent to least recent.
 *       3) follow(followerId, followeeId): Follower follows a followee.
 *       4) unfollow(followerId, followeeId): Follower unfollows a followee.
 *
 *      Example: Twitter twitter = new Twitter();
 *               // User 1 posts a new tweet (id = 5).
 *               twitter.postTweet(1, 5);
 *               // User 1's news feed should return a list with 1 tweet id -> [5].
 *               twitter.getNewsFeed(1);
 *               // User 1 follows user 2.
 *               twitter.follow(1, 2);
 *               // User 2 posts a new tweet (id = 6).
 *               twitter.postTweet(2, 6);
 *               // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 *               // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 *               twitter.getNewsFeed(1);
 *               // User 1 unfollows user 2.
 *               twitter.unfollow(1, 2);
 *               // User 1's news feed should return a list with 1 tweet id -> [5],
 *               // since user 1 is no longer following user 2.
 *               twitter.getNewsFeed(1);
 */

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
 
 //My Method:
 class Twitter {
    
    class Node{
        int tweet_id;
        int timestamp;
        Node next;
        Node pre;
        
        public Node(int tweet_id, int timestamp){
            this.tweet_id = tweet_id;
            this.timestamp = timestamp;
            next = null;
            pre = null;
        }
    }
    
    int timestamp;
    Map<Integer, Set<Integer>> follows; 
    Map<Integer, List<Node>> userFeed; 
    Map<Integer, Integer> feedSize;
    
    /** Initialize your data structure here. */
    public Twitter() {
        follows = new HashMap<>();
        userFeed = new HashMap<>();
        feedSize = new HashMap<>();
        timestamp = 0;
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Node newNode = new Node(tweetId, timestamp);
        Node head = null;
        Node tail = null;
        if(!userFeed.containsKey(userId)){
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.pre = head;
            List<Node> list = new ArrayList<>();
            list.add(head); 
            list.add(tail);
            userFeed.put(userId, list);
            feedSize.put(userId, 0);
        }
        else{
            head = userFeed.get(userId).get(0);
            tail = userFeed.get(userId).get(1);
        }
        newNode.pre = head;
        newNode.next = head.next;
        head.next.pre = newNode;
        head.next = newNode;
        if(feedSize.get(userId) == 10){//remove the last tweetId
            Node lastNode = tail.pre;
            lastNode.pre.next = tail;
            tail.pre = lastNode.pre;
        }
        else{
            feedSize.put(userId, feedSize.get(userId) + 1);
        }  
        timestamp++;
    }
    
    private Comparator<Node> nodeComparator = new Comparator<Node>(){
        public int compare(Node a, Node b){
            return b.timestamp - a.timestamp;
        }
    };
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Node> q = new PriorityQueue<Node>(10, nodeComparator);
        if(feedSize.containsKey(userId) && feedSize.get(userId) != 0){
            q.offer(userFeed.get(userId).get(0).next);
        }
        if(follows.containsKey(userId) && follows.get(userId).size() != 0){
            Iterator<Integer> itr = follows.get(userId).iterator();
            while(itr.hasNext()){
                int user = itr.next();
                if(feedSize.containsKey(user) && feedSize.get(user) != 0){
                    q.offer(userFeed.get(user).get(0).next);
                }
            }
        }
        
        while(!q.isEmpty() && res.size() < 10){
            Node node = q.poll();
            res.add(node.tweet_id);
            if(node.next.tweet_id != -1){//not tail
                q.offer(node.next);
            }
        }
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followerId == followeeId){
            return;
        }
        if(!follows.containsKey(followerId)){
            follows.put(followerId, new HashSet<>());
        }
        follows.get(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followerId == followeeId){
            return;
        }
        if(!follows.containsKey(followerId) || !follows.get(followerId).contains(followeeId)){
            return;
        }
        follows.get(followerId).remove(followeeId);
    }
}
