/** 362. Design Hit Counter
 *      Design a hit counter which counts the number of hits received in the past 5 minutes.
 *      Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are 
 *  being made to the system in chronological order (ie, the timestamp is monotonically increasing). 
 *  You may assume that the earliest timestamp starts at 1.
 *      It is possible that several hits arrive roughly at the same time.
 *
 *      Example: HitCounter counter = new HitCounter();
 *               // hit at timestamp 1.
 *               counter.hit(1);
 *               // hit at timestamp 2.
 *               counter.hit(2);
 *               // hit at timestamp 3.
 *               counter.hit(3);
 *               // get hits at timestamp 4, should return 3.
 *               counter.getHits(4);
 *               // hit at timestamp 300.
 *               counter.hit(300);
 *               // get hits at timestamp 300, should return 4.
 *               counter.getHits(300);
 *               // get hits at timestamp 301, should return 3.
 *               counter.getHits(301); 
 *
 *      Follow up: What if the number of hits per second could be very large? Does your design scale?
 */ 

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
 
 //My Method:
 class HitCounter {

    /** Initialize your data structure here. */
    int sum;
    Queue<Integer> hits;
    public HitCounter() {
        sum = 0;
        hits = new LinkedList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        while(!hits.isEmpty() && timestamp - hits.peek() >= 300){
            sum--;
            hits.poll();
        }
        sum++;
        hits.offer(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(!hits.isEmpty() && timestamp - hits.peek() >= 300){
            sum--;
            hits.poll();
        }
        return sum;
    }
}

/* Follow up:[from leetcode discussion] - O(1) hit()  O(s) getHits() 
 *    Idea: O(s) s is total seconds in given time interval, in this case 300.
 *          basic ideal is using buckets. 1 bucket for every second because we only need to keep the recent hits 
 *      info for 300 seconds. hit[] array is wrapped around by mod operation. Each hit bucket is associated with 
 *      times[] bucket which record current time. If it is not current time, it means it is 300s or 600s... ago 
 *      and need to reset to 1.
 */
class HitCounter {

    /** Initialize your data structure here. */
    int[] times;
    int[] hits;
    public HitCounter() {
        times = new int[300]; // 0 indicates 300 * n 
        hits = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int index = timestamp % 300;
        if(times[index] == timestamp){
            hits[index]++;
        }
        else{
            times[index] = timestamp;
            hits[index] = 1;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int sum = 0;
        for(int i = 0; i < 300; i++){
            if(timestamp - times[i] < 300){
                sum += hits[i];
            }
        }
        return sum;
    }
}
