//(lintcode) 14. First Position of Target

/** My Method:
 * @param nums: The integer array.
 * @param target: Target to find.
 * @return: The first position of target. Position starts from 0.
 */
public int binarySearch(int[] nums, int target) {
    // write your code here
    if(nums.length == 0)
        return -1;

    int l = 0, r = nums.length-1;
    int mid = 0;

    while(r >= l){
        mid = (r+l)/2;
        if(nums[mid] == target){
            while((mid-1>=0) && nums[mid-1] == target){
                mid--;
            }
            return mid;
        }
        else if(nums[mid] < target){
            l = mid + 1;
        }
        else{
            r = mid - 1;
        }

    }
    return -1;
}


/** Method 2 (from jiuzhang):
 * @param nums: The integer array.
 * @param target: Target to find.
 * @return: The first position of target. Position starts from 0.
 */
public int binarySearch(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
        return -1;
    }

    int start = 0, end = nums.length - 1;
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            end = mid;
        } else if (nums[mid] < target) {
            start = mid;
            // or start = mid + 1
        } else {
            end = mid;
            // or end = mid - 1
        }
    }

    if (nums[start] == target) {
        return start;
    }
    if (nums[end] == target) {
        return end;
    }
    return -1;
}

