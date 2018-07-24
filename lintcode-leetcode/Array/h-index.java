/** 1304. H-Index(lintcode) / 274. H-Index(leetcode)
 *     Given an array of citations (each citation is a non-negative integer) of a researcher, 
 *  write a function to compute the researcher's h-index.
 *     According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers 
 *  have at least h citations each, and the other N − h papers have no more than h citations each."
 *
 *     Note: If there are several possible values for h, the maximum one is taken as the h-index.
 *
 *     Example: Input: citations = [3,0,6,1,5] ; Output: 3 
 *     Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and 
 *              each of them had received 3, 0, 6, 1, 5 citations respectively.
 *                  Since the researcher has 3 papers with at least 3 citations each and the remaining two 
 *              with no more than 3 citations each, her h-index is 3
 */
 
 //My Method: Sort - O(nlogn) time and O(1) space complexity
 public int hIndex(int[] citations) {
    if(citations == null || citations.length == 0){
        return 0;
    }

    Arrays.sort(citations);
    int h = 0;
    int len = citations.length;
    for(int i = len - 1; i >= 0; i--){
        if(citations[i] >= len - i){
            h++;
        }
        else{
            break;
        }
    }
    return h;
 }
 
 /* Method 2: 数组映射法  - O(n) time and O(n) space complexity
  *        不对数组排序，额外使用一个大小为N+1的数组cites。cites[i]表示有多少文章被引用了i次;
  *     如果一篇文章引用大于N次，我们就将其当为N次，因为H指数不会超过文章的总数。先将整个文献引用数组遍历一遍，构建cites数组。
  *     
  *        可以用一个变量求h，从高引用次的文章数累加下来：
  *     因为如果有x篇文章的引用大于等于3次，那引用大于等于2次的文章数量一定是x加上引用次数等于2次的文章数量。
  *／
 public int hIndex(int[] citations) {
    if(citations == null || citations.length == 0){
        return 0;
    }
    int len = citations.length;
    int[] cites = new int[len + 1];

    for(int i = 0; i < len; i++){
        if(citations[i] > len){
            cites[len]++;
        }
        else{
            cites[citations[i]]++;
        }
    }
    
    int sum = 0;
    for(int i = len; i > 0; i--){
        sum += cites[i];
        if(sum >= i){
            return i;
        }
    }
    return 0;
 }
