/**  65. Median of two Sorted Arrays(lintcode) /  4. Median of two Sorted Arrays(leetcode)
 *     There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.
 *
 *     Example: 1) Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.
 *              2) Given A=[1,2,3] and B=[4,5], the median is 3.
 *
 *     Challenge: The overall run time complexity should be O(log (m+n)).
 */
 
 //Method 1: 遍历并合并数组 - O(m+n)
 public double findMedianSortedArrays(int[] A, int[] B) {
    // write your code here
    if(A == null|| A.length == 0){
        if(B == null || B.length == 0){
            return 0;
        }
        else{
            if(B.length % 2 != 0){
                return B[B.length/2];
            }
            else{
                return ((double)B[B.length/2] + B[B.length/2 - 1])/2;
            }
        }
    }
    else if(B == null || B.length == 0){ //A != null
        if(A.length % 2 != 0){
            return A[A.length/2];
        }
        else{
            return ((double)A[A.length/2] + A[A.length/2 - 1])/2;
        }
    }
    int median = 0;
    int m = A.length;
    int n = B.length;
    int mid = 0;
    if((m + n) % 2 == 0){
        mid = (m + n)/2;
    }
    else{
        mid = (m + n)/2 + 1;
    }
    int i = 0, j = 0;
    while(i < m && j < n){
        if(A[i] < B[j]){
            median = A[i];
            i++;
        }
        else{
            median = B[j];
            j++;
        }
        mid--;
        if(mid == 0){
            if((m + n) % 2 != 0){
                return median;
            }
            else{
                if(i < m && j < n){
                    return ((double)median + Math.min(A[i],B[j]))/2;
                }
                else if(i == m){
                    return ((double)median + B[j])/2;
                }
                else{
                    return ((double)median + A[i])/2;
                }
            }
        }
    }
    while(i < m){
        if(mid == 1){
            if((m + n) % 2 != 0){
                median = A[i];
            }  
            else{
                return ((double)A[i] + A[i+1])/2;
            }
            break;
        }
        i++;
        mid--;
    }
    while(j < n){
        if(mid == 1){
            if((m + n) % 2 != 0){
                median = B[j];
            }  
            else{
                return ((double)B[j] + B[j+1])/2;
            }
            break;
        }
        j++;
        mid--;
    }
    return median;
 }
 
 //Method 2: Divide & Conquer ( * )[from leetcode]
 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    if(n > m){   //数组1总是最短
        return findMedianSortedArrays(nums2,nums1);
    }
    int L1 = 0, L2 = 0, R1 = 0, R2 = 0, c1 = 0, c2 = 0, lo = 0, hi = 2*n;  //加了虚拟的'#'使得数组1是2*n长度
    while(lo <= hi)   //二分
    {
        c1 = (lo+hi)/2;  //c1是二分的结果
        c2 = m+n- c1;
        L1 = (c1 == 0)?Integer.MIN_VALUE:nums1[(c1-1)/2];   //map to original element
        R1 = (c1 == 2*n)?Integer.MAX_VALUE:nums1[c1/2];
        L2 = (c2 == 0)?Integer.MIN_VALUE:nums2[(c2-1)/2];
        R2 = (c2 == 2*m)?Integer.MAX_VALUE:nums2[c2/2];

        if(L1 > R2)
            hi = c1-1;
        else if(L2 > R1)
            lo = c1+1;
        else
            break;
    }
    return (Math.max(L1,L2)+ Math.min(R1,R2))/2.0;
}
