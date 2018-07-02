/** 715. Range Module(leetcode)
 *    A Range Module is a module that tracks ranges of numbers. 
 *    Your task is to design and implement the following interfaces in an efficient manner.
 *      1)addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. 
 *    Adding an interval that partially overlaps with currently tracked numbers should add any numbers 
 *    in the interval [left, right) that are not already tracked.
 *      2)queryRange(int left, int right) Returns true if and only if every real number in the interval [left, right) 
 *    is currently being tracked.
 *      3)removeRange(int left, int right) Stops tracking every real number currently being tracked in the interval [left, right).
 *    
 *    Note: - A half open interval [left, right) denotes all real numbers left <= x < right.
 *          - 0 < left < right < 10^9 in all calls to addRange, queryRange, removeRange.
 *          - The total number of calls to addRange in a single test case is at most 1000.
 *          - The total number of calls to queryRange in a single test case is at most 5000.
 *          - The total number of calls to removeRange in a single test case is at most 1000.
 *
 *    Example: addRange(10, 20): null
 *             removeRange(14, 16): null
 *             queryRange(10, 14): true (Every number in [10, 14) is being tracked)
 *             queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
 *             queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite the remove operation)
 */
 
 class RangeModule {
    TreeSet<Inteval> ranges;
    public RangeModule() {
        //do some initialization if necessary
        ranges = new TreeSet<Inteval>();
    }
    
    public void addRange(int left, int right) {
        //write your code here
        //得到所有right > left-1 的区间,即比[0,left-1)大的区间
        Iterator<Inteval> itr = ranges.tailSet(new Inteval(0,left-1)).iterator();
        while(itr.hasNext()){
            Inteval iv = itr.next();
            if(iv.left <= right){ // overlap
                left = Math.min(iv.left, left);
                right = Math.max(iv.right, right);
                itr.remove();
            }
            else{ // iv.left > right
                break;
            }
        }
        ranges.add(new Inteval(left,right));
    }
    
    public boolean queryRange(int left, int right) {
        //write your code here
        Inteval iv = ranges.higher(new Inteval(0,left)); // 返回比[0,left)大的第一个区间 （区间是排序了的）
        if(iv != null && iv.left <= left && iv.right >= right){
            return true;
        }
        return false;
    }
    
    public void removeRange(int left, int right) {
        //write your code here
        //得到所有right > left 的区间，即比[0,left)大的区间
        Iterator<Inteval> itr = ranges.tailSet(new Inteval(0,left)).iterator();
        ArrayList<Inteval> newiv = new ArrayList<Inteval>();
        while(itr.hasNext()){
            Inteval iv = itr.next();
            if(iv.left >= right){// no such inteval or overlap need to remove
                break;
            }
            if(iv.left < left){ 
                newiv.add(new Inteval(iv.left, left));
            }
            if(iv.right > right){
                newiv.add(new Inteval(right,iv.right));
            }
            itr.remove();
        }
        for(Inteval iv: newiv){
            ranges.add(iv);
        }
    }
}
class Inteval implements Comparable<Inteval>{
    int left;
    int right;
    
    public Inteval(int left, int right){
        this.left = left;
        this.right = right;
    }
    
    public int compareTo(Inteval o){
        if(this.right == o.right){
            return this.left - o.left;
        }
        return this.right - o.right;
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
