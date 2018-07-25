/** 118. Pascal's Triangle(leetcode)
 *     Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 *     Note: In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *      
 *     Example: Input: 5
 *              Output:
 *                  [
 *                       [1],
 *                      [1,1],
 *                     [1,2,1],
 *                    [1,3,3,1],
 *                   [1,4,6,4,1]
 *                  ]
 */
 
 //My Method: - O(n^2) time complexity
 public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> pascal = new ArrayList<List<Integer>>();
    if(numRows == 0){
        return pascal;
    }
    pascal.add(new ArrayList<Integer>(Arrays.asList(1)));
    for(int i = 2; i <= numRows; i++){
        List<Integer> pre_row = pascal.get(pascal.size()-1);
        List<Integer> row = new ArrayList<Integer>();
        for(int j = 0; j < i; j++){           
            if(j == 0 || j == i - 1){
                row.add(1);
            }
            else{
                row.add(pre_row.get(j-1)+pre_row.get(j));
            }
        }
        pascal.add(row);
    }
    return pascal;
 }
