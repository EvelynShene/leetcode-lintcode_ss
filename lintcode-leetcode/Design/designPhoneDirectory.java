/** 379. Design Phone Directory(leetcode) 
 *      Design a Phone Directory which supports the following operations:
 *           1) get: Provide a number which is not assigned to anyone.
 *           2) check: Check if a number is available or not.
 *           3) release: Recycle or release a number.
 *
 *      Example: // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 *                PhoneDirectory directory = new PhoneDirectory(3);
 *              // It can return any available phone number. Here we assume it returns 0.
 *                directory.get();
 *              // Assume it returns 1.
 *                directory.get();
 *              // The number 2 is available, so return true.
 *                directory.check(2);
 *              // It returns 2, the only number that is left.
 *                directory.get();
 *              // The number 2 is no longer available, so return false.
 *                directory.check(2);
 *              // Release number 2 back to the pool.
 *                directory.release(2);
 *              // Number 2 is available again, return true.
 *                directory.check(2);
 */

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
 
 //My Method:
 class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    Set<Integer> used;
    int maxNumber;
    int smallest_available_num;
    
    public PhoneDirectory(int maxNumbers) {
        used = new HashSet<Integer>();
        this.maxNumber = maxNumbers;
        smallest_available_num = 0;
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        while(used.contains(smallest_available_num)){
            smallest_available_num++;
        }
        if(smallest_available_num < maxNumber){
            used.add(smallest_available_num);
            smallest_available_num++;
            return smallest_available_num - 1;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(used.contains(number)){
            return false;
        }
        return true;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        used.remove(number);
        if(number < smallest_available_num){
            smallest_available_num = number;
        }
    }
}

//Method 2:
class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    Set<Integer> available;
    
    public PhoneDirectory(int maxNumbers) {
        available = new HashSet<Integer>();
        for(int i = 0; i < maxNumbers; i++){
            available.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(available.size() == 0){
            return -1;
        }
        int number = available.iterator().next();
        available.remove(number);
        return number;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return available.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        available.add(number);
    }
}
