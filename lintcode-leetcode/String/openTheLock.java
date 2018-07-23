/** 796. Open the Lock(lintcode)/752. Open the Lock(leetcode)
 *      You have a lock in front of you with 4 circular wheels. 
 *  Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. 
 *  The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. 
 *  Each move consists of turning one wheel one slot.
 *      The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *      You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 *  the wheels of the lock will stop turning and you will be unable to open it.
 *      Given a target representing the value of the wheels that will unlock the lock, return the minimum total 
 *  number of turns required to open the lock, or -1 if it is impossible.
 *
 *      Note: 1.The length of deadends will be in the range [1, 500].
 *            2.target will not be in the list deadends.
 *            3.Every string in deadends and the string target will be a string of 4 digits from the 10,000 
 *            possibilities '0000' to '9999'.
 *
 *      Example: 1)Given deadends = ["0201","0101","0102","1212","2002"], target = "0202" ; Return 6
 *               Explanation: A sequence of valid moves would be
 *                      "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 *               Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 *           because the wheels of the lock become stuck after the display becomes the dead end "0102".
 *
 *               2)Given deadends = ["8888"], target = "0009" ; Return 1
 *               Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
 */
 
 /* Method: BFS + Queue （类似与迷宫问题）
  *     从start状态“0000”开始，每个位的数字都可以有两种变化可能+1或者-1。然后把可能的变化/下一步状态放入queue队列中。
  *     (存入queue中时，需要检查是否在deadends和queue中已经存在了，已经存在就pass；都不存在才添加到queue中 - 用到set)
  *     同属于一层(level)的状态全部遍历完后，层数level才能更新+1.
  */
  
  public int openLock(String[] deadends, String target) {
    String start = "0000";
    Set<String> deads = new HashSet<String>();
    for(int i = 0; i < deadends.length; i++){
        if(deadends[i].equals(start) || deadends[i].equals(target)){
            return -1;
        }
        deads.add(deadends[i]);
    }
    if(start.equals(target)){
        return 0;
    }
    int[] move = {1,9};
    int level = 0;

    Queue<String> q = new LinkedList<String>();
    Set<String> qset = new HashSet<String>();
    q.offer(start);
    qset.add(start);
    while(!q.isEmpty()){
        int qsize = q.size();
        for(int x = 0; x < qsize; x++){// all nodes in this level
            start = q.poll();
            if(start.equals(target)){
                return level;
            }
            for(int i = 0; i < 4; i++){// each circular wheels
                for(int j = 0; j < 2; j++){// +1 or -1
                    StringBuilder newstate = new StringBuilder(start);
                    char n = (char)((newstate.charAt(i) - '0' + move[j]) % 10 + '0');
                    newstate.setCharAt(i,n);
                    if(!deads.contains(newstate.toString()) && !qset.contains(newstate.toString())){
                        qset.add(newstate.toString());
                        q.offer(newstate.toString());
                    }
                }
            }
        }
        level++;
    }      
    return -1;
 }
