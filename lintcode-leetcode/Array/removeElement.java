/** 172. Remove Element(lintcode) /172. Remove Element
 *  Given an array and a value, remove all occurrences of that value in place and return the new length.
 *  The order of elements can be changed, and the elements after the new length don't matter.
 */

/*  leetcode: Do not allocate extra space for another array, 
 *  you must do this by modifying the input array in-place with O(1) extra memory.
 */
//Method: 
public int removeElement(int[] nums, int val) {
    int index = nums.length-1;
    int i = 0;

    while(i <= index){ //这里判断条件是小于index而不是nums.length，因为index后面的值都会被提前置换到前置位置比较，后续不再需要比较了
        if(nums[i] == val){
            nums[i] = nums[index];
            index--;
        }
        else{
            i++;
        }
    }
    return index+1;
}

//My Method: [Accept in lintcode]
public int removeElement(int[] A, int elem) {
    // write your code here
    Arrays.sort(A);
    boolean remove = false;
    int elemnum = 0;
    int i = 0;
    while(i < A.length){
        while(i < A.length && A[i] == elem){
            remove = true;
            elemnum++;
            i++;
        }
        if(remove){
            break;
        }
        else{
            i++;
        }
    }
    if(i == A.length){
        return A.length - elemnum;
    }
    else{
        for(int j = 1;j <= elemnum; j++){
            A[i-j] = A[A.length-j];
        }
    }
    return A.length - elemnum;
}

