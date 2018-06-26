/** 532. Reverse Pairs(lintcode)
 *    For an array A, if i < j, and A [i] > A [j], called (A [i], A [j]) is a reverse pair. return total of reverse pairs in A.
 *
 *    Example: Given A = [2, 4, 1, 3, 5] , (2, 1), (4, 1), (4, 3) are reverse pairs. return 3
 */
 
 //Method 1: Brute Force - O(n^2)
  public long reversePairs(int[] A) {
      // write your code here
      if(A == null || A.length < 2){
          return 0;
      }
      long p = 0L;

      for(int i = 0; i < A.length; i++){
          for(int j = i+1; j < A.length; j++){
              if(A[i] > A[j]){
                  p++;
              }
          }
      }
      return p;
  }
  
  /* Method 2: Merge Sort - O(nlogn)  [From jiuzhang]
   *    1.先从最小的单位开始比起，比如比较两个相邻的数字，很容易判断它们是否是一个逆序对，是的话，总逆序对个数加一，
   *  并将这两个数以正序排好，说明这两个数之间的逆序对个数已经计算完了。 
   *    2.再往上，两个已经排好序的小数组，这两个数组内部的逆序对都已经计算好了，此时要计算这两个数组之间的逆序对，比如[2,4]和[1，3]，利用归并排序：
   *      1）从后往前比较，先比较4和3，前面的4比后面的3大，因此存在逆序对，且4肯定比第二个数组中3及3之前的数都要大（因为此时的数组已排好序），
   *    那么逆序对就要多了2对。同时将4放入merge数组中，并指向下一个要比较的数2
   *      2）比较的两个数是2和3，前面的数2比后面的3小，不构成一个逆序对。将3放入merge数组中，并指向下一个要比较的数1
   *      3）比较2和1，前面的2比后面的1大，因此构成一个逆序对(2,1)。将2放入merge数组中。
   *      4) 最后将1放入数组中。
   *
   *    整个归并排序并计算逆序对数的过程是：
   *      1）把数组分隔成小的子数组
   *      2）统计出子数组内部的逆序对数目
   *      3）统计两个相邻的排好序的子数组之间的逆序对数目
   *      4）统计逆序的过程中，对数组进行归并排序。
   */
public class Solution {
    /**
     * @param A an array
     * @return total of reverse pairs
     */
    public long reversePairs(int[] A) {
        return mergeSort(A, 0, A.length - 1);
    }
    
    private int mergeSort(int[] A, int start, int end) {
        if (start >= end) {
            return 0;
        }
        
        int mid = (start + end) / 2;
        int sum = 0;
        sum += mergeSort(A, start, mid);
        sum += mergeSort(A, mid+1, end);
        sum += merge(A, start, mid, end);
        return sum;
    }
    
    private int merge(int[] A, int start, int mid, int end) {
        int[] temp = new int[A.length];
        int leftIndex = start;
        int rightIndex = mid + 1;
        int index = start;
        int sum = 0;
        
        while (leftIndex <= mid && rightIndex <= end) {
            if (A[leftIndex] <= A[rightIndex]) {
                temp[index++] = A[leftIndex++];
            } else {
                temp[index++] = A[rightIndex++];
                sum += mid - leftIndex + 1;
            }
        }
        while (leftIndex <= mid) {
            temp[index++] = A[leftIndex++];
        }
        while (rightIndex <= end) {
            temp[index++] = A[rightIndex++];
        }
        
        for (int i = start; i <= end; i++) {
            A[i] = temp[i];
        }
        
        return sum;
    }
}
