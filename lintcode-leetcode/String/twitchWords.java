/** 1401. Twitch Words(lintcode)
 *      Our normal words do not have more than two consecutive letters. If there are three or more consecutive letters, 
 *  this is a tics. Now give a word, from left to right, to find out the starting point and ending point of all tics.
 *
 *      Note: The input string length is n, n <= 100000.
 *
 *      Example: 1) Given str = "whaaaaatttsup", return [[2,6],[7,9]].
 *                 Explanation: "aaaa" and "ttt" are twitching letters, and output their starting and ending points.
 *               2) Given str = "whooooisssbesssst", return [[2,5],[7,9],[12,15]].
 *                 Explanation: "ooo", "sss" and "ssss" are twitching letters, and output their starting and ending points.
 */ 
 
 public int[][] twitchWords(String str) {
    // Write your code here
    ArrayList<int[]> tics = new ArrayList<int[]>();
    if(str == null || str.length() == 0){
        return new int[0][0];
    }

    int start = 0;
    for(int i = 1; i < str.length(); i++){
        while(i < str.length() && str.charAt(start) == str.charAt(i)){
            i++;
        }
        if(i - start > 2){
            int[] t = new int[2];
            t[0] = start;
            t[1] = i - 1;
            tics.add(t);
        }
        start = i;
    }
    int[][] res = new int[tics.size()][2];
    for(int i = 0; i < tics.size(); i++){
        res[i][0] = tics.get(i)[0];
        res[i][1] = tics.get(i)[1];
    }
    return res;
 } 
