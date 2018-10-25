/** 406. Queue Reconstruction by Height(leetcode) / 1252. Queue Reconstruction by Height(lintcode)
 *      Suppose you have a random list of people standing in a queue. Each person is described by a pair of 
 *  integers (h, k), where h is the height of the person and k is the number of people in front of this person 
 *  who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
 *
 *      Note: The number of people is less than 1,100.
 *
 *      Example: Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *               Output:[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
 
 /* Method: - O(nlogn) time and O(n) space complexity
  *   1) 先按高度h对数组元素排序，如果高度一样，就按k从小到大排列
  *   2) 然后遍历数组，按k的值作为最终位置插入每个元素到数组中
  *  【首先按高度h排序后，每个元素的大于等于它高度的元素都会排列在它前面，
  *       然后k指示了有多少个高度大于等于它的元素在它前面，所以用k作为最终position插入数组相应的位置，最后得到的就是想要的最终数组】
  */
 class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if(people == null || people.length == 0){
            return new int[0][0];
        }
        int n = people.length;
        
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return a[1] - b[1];
                }
                return b[0] - a[0];
            }
        });
        List<int[]> tmp = new ArrayList<int[]>();
        for(int i = 0; i < n; i++){
            tmp.add(people[i][1], people[i]);
        }
        int[][] res = new int[n][2];
        for(int i = 0; i < n; i++){
            res[i][0] = tmp.get(i)[0];
            res[i][1] = tmp.get(i)[1];
        }
        return res;
    }
}
