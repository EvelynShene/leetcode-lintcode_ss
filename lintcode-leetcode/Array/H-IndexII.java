/** 275. H-Index II(leetcode)
 *      Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, 
 *   write a function to compute the researcher's h-index.
 *      According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers
 *   have at least h citations each, and the other N − h papers have no more than h citations each."
 *
 *      Note: If there are several possible values for h, the maximum one is taken as the h-index.
 *  
 *      Follow up:
 *       This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
 *       Could you solve it in logarithmic time complexity?
 *
 *      Example: Input: citations = [0,1,3,5,6] ; Output: 3 
 *        Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had 
 *                  received 0, 1, 3, 5, 6 citations respectively. 
 *                      Since the researcher has 3 papers with at least 3 citations each and the remaining 
 *                  two with no more than 3 citations each, her h-index is 3.
 */
 
 /* Method: 二分法 - O(logn) time complexity
  *    因为已经是升序排列的数组，所以可以用二分法。以citations = [0,1,3,5,6]为例子：(n = citations.length)
  *    1) 一开始left = 1，right = n,查看整个数组的中心元素：mid = (left + right) / 2.
  *    2)然后看这个中心元素mid文献的引用数目citations[mid-1](mid-1是因为数组下标0开始)
  *               与它右边的文献个数(citations.length - mid + 1)的大小关系（包括文献mid本身):
  *      a) citations[mid-1] >= citations.length - mid + 1 -> 右边的都满足，向左推进找更大的h-index
  *         eg: mid = 3, citations[3-1] = 3 >= 3; 从3开始，右边的三个文献的引用数都大于等于3
  *           (数组是升序，所以如果citations[3-1]>= 3, mid = 3 右边的citations[x] 肯定也大于等于 3)
  *      b) citation[mid-1] < citations.length - mid + 1 -> 右边的并不是都满足，向右继续找h-index
  *   
  */
 public int hIndex(int[] citations) {
    if(citations == null || citations.length == 0){
        return 0;
    }
    int left = 1;
    int right = citations.length;
    int h = 0;
    while(left <= right){
        int mid = (left + right) / 2;
        if(citations[mid-1] >= citations.length - mid + 1){
            right = mid - 1;
            h = citations.length - mid + 1;
        }
        else{
            left = mid + 1;
        }
    }
    return h;
 }
