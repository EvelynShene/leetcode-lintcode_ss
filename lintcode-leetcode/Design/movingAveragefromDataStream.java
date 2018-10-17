/** 346. Moving Average from Data Stream(leetcode) / 642. Moving Average from Data Stream(lintcode)
 *     Given a stream of integers and a window size, calculate the moving average of all integers in the 
 *  sliding window.
 *
 *     Example: MovingAverage m = new MovingAverage(3);
 *              m.next(1) = 1
 *              m.next(10) = (1 + 10) / 2
 *              m.next(3) = (1 + 10 + 3) / 3
 *              m.next(5) = (10 + 3 + 5) / 3
 */

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
 
//Method 1: Use ArrayList [more efficient than method 2]
class MovingAverage {

    /** Initialize your data structure here. */
    
    int window_size; 
    List<Integer> array;
    Double sum;
    int first_index;
    
    public MovingAverage(int size) {
        window_size = size;
        array = new ArrayList<>();
        sum = 0.0;
        first_index = 0;
    }

    public double next(int val) {
        array.add(val);
        sum += val;
        if(array.size() > window_size){
            sum -= array.get(first_index);
            first_index++;
        }
        if(array.size() < window_size){
            return sum / array.size();
        }
        else{
            return sum / window_size;
        }
    }
}

//Method 2: Use Queue/Deque
class MovingAverage {

    /** Initialize your data structure here. */
    int window_size; 
    Deque<Integer> q;
    int sum;
    
    public MovingAverage(int size) {
        window_size = size;
        q = new LinkedList<Integer>();
        sum = 0;
    }
    
    public double next(int val) {
        sum += val;
        if(q.size() == window_size){
            sum -= q.removeFirst();
        }
        q.addLast(val);
        return (double) sum / q.size();
    }
}
