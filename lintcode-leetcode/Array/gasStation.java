/** 187. Gas Station(lintcode) / 134. Gas Station(leetcode)
 *      There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *      You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to 
 *  its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 *      Return the starting gas station's index if you can travel around the circuit once in the clockwise 
 *  direction, otherwise return -1.
 *
 *      Note: 1) If there exists a solution, it is guaranteed to be unique.
 *            2) Both input arrays are non-empty and have the same length.
 *            3) Each element in the input arrays is a non-negative integer.
 *
 *      Example: 1) Given 4 gas stations with gas[i]=[1,1,3,1], and the cost[i]=[2,2,1,1]. Return 2.
 *                The starting gas station's index is 2.
 *               2) Given 6 gas stations with gas[i]=[2,1,1,2,3,4,0], and the cost[i]=[0,2,0,0,0,0,11]. Return 0. 
 *                The starting gas station's index is 0.
 *
 *      Challenge: O(n) time and O(1) extra space
 */
 
 //My Method: O(n^2) time and O(n) space complexity
 public int canCompleteCircuit(int[] gas, int[] cost) {
    int n = gas.length;
    if(n == 0){
        return -1;
    }
    int[] remain = new int[n];
    int start = 0;
    int sum = 0;
    for(int i = 0; i < n; i++){
        remain[i] = gas[i] - cost[i];
        sum += remain[i];
    }
    if(sum < 0){
        return -1;
    }
    sum = 0;
    for(int i = 0; i < n; i++){
        if(remain[i] >= 0){
            sum = remain[i];
            int j = (i+1) % n;
            while(j != i){
                sum += remain[j];
                if(sum < 0){
                    break;
                }
                j = (j+1) % n;
            }
            if(j == i){
                start = i;
                break;
            }
        }
    }
    return start;
 }
 //简化代码：O(1) space complexity, 但是运行比上述代码慢一点
 public int canCompleteCircuit(int[] gas, int[] cost) {
    int n = gas.length;
    int start = -1;
    for(int i = 0; i < n; i++){
        int sum = gas[i] - cost[i];
        if(sum >= 0){
            int j = (i + 1) % n;
            while(j != i){
                sum += gas[j] - cost[j];
                if(sum < 0) break;
                j = (j + 1) % n;
            }
            if(i == j){
                return i;
            }
        }
    }
    return start;
 }
 
 /* Method 2: Greedy Algorithm - O(n) time and O(1) space complexity
  *    油箱里的燃料会不断损耗和积累,我觉得从油箱燃料将会积累最多的起点（start）出发最好。如果有最优解
  *    当测试站台i是起点时，如果积累的油箱燃料出现负数，说明i不可能时起点，并且从i到当前积累站点j之间的站点都不可能是起点。
  *      因为：Sum(gas[x]- cost[x]) > 0, Sum(gas[x]- cost[x]) + (gas[j]- cost[j]) < 0 (i <= x < j)
  *      那么： Sum(gas[x]- cost[x]) + (gas[j]- cost[j]) < 0 在 i < x < j 时也一定成立；i->j之间的站点不可能是起点。
  */
 public int canCompleteCircuit(int[] gas, int[] cost) {
    int n = gas.length;
    int remain = 0;
    for(int i = 0; i < n; i++){
        remain += gas[i] - cost[i]; 
    }
    if(remain < 0){
        return -1;
    }

    int start = 0;
    remain = 0;
    for(int i = 0; i < n; i++){
        remain += gas[i] - cost[i];
        if(remain + gas[i] - cost[i] < 0){
            remain = 0;
            start = (i + 1) % n;
        }
    }
    return start;
 }
