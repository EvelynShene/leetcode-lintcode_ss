/** 378. Kth Smallest Element in a Sorted Matrix(leetcode)/ 1272. Kth Smallest Element in a Sorted Matrix(lintcode)
 *      Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth 
 *  smallest element in the matrix.
 *
 *      Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 *      Example:
 *           matrix = [
 *             [ 1,  5,  9],
 *             [10, 11, 13],
 *             [12, 13, 15]
 *           ],
 *            k = 8,
 *          return 13.
 *
 *      Note: You may assume k is always valid, 1 ≤ k ≤ n2.
 */
 
 //Method 1: Merge sort
 class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length; 
        int n = matrix[0].length;
        List<Integer> res = new ArrayList<Integer>();
        
        for(int i = 0; i < m; i++){
            mergeSort(res, matrix[i]);
        }
        
        return res.get(k - 1);
    }
    
    public void mergeSort(List<Integer> res, int[] m){
        int i = 0;
        int j = 0;
        while(i < res.size() && j < m.length){
            if(m[j] < res.get(i)){
                res.add(i, m[j]);
                j++;
            }
            i++;
        }
        while(j < m.length){
            res.add(m[j]);
            j++;
        }
    }
}

// Method 2: Use MaxHeap
public int kthSmallest(int[][] matrix, int k) {
    int m = matrix.length; 
    int n = matrix[0].length;
    PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>(){
        public int compare(Integer o1, Integer o2){
            return o2 - o1;
        }
    });

    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(q.size() < k){
                q.offer(matrix[i][j]);
            }
            else{
                if(q.peek() > matrix[i][j]){
                    q.poll();
                    q.offer(matrix[i][j]);
                }
            }
        }
    }
    return q.peek();
}

//Method 3: Use Binary Search - [idea from https://www.cnblogs.com/grandyang/p/5727892.html]
public int kthSmallest(int[][] matrix, int k) {
    int m = matrix.length; 
    int n = matrix[0].length;
    int left = matrix[0][0];
    int right = matrix[m - 1][n - 1];

    while(left < right){
        int mid = left + (right - left) / 2;
        int count = 0;
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][n - 1] < mid){
                count += n;
            }
            else if(mid >= matrix[i][0]){
                for(int j = 0; j < n; j++){
                    if(mid < matrix[i][j]){
                        break;
                    }
                    count++;
                }
            }
        }
        if(count < k){
            left = mid + 1;
        }
        else{
            right = mid;
        }
    }

    return left;
}
