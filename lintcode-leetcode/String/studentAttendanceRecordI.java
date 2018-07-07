/** 1178. Student Attendance Record I(lintcode) / 551. Student Attendance Record I(leetcode)
 *      You are given a string representing an attendance record for a student. The record only contains 
 *   the following three characters:
 *           'A' : Absent.
 *           'L' : Late.
 *           'P' : Present.
 *      A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) 
 *   or more than two continuous 'L' (late).
 *      You need to return whether the student could be rewarded according to his attendance record.
 *
 *      Example: 1) Input: "PPALLP" ; Output: True
 *               2) Input: "PPALLL" ; Output: False
 */
 
 public boolean checkRecord(String s) {
    int A = 0;
    int L = 0;
    boolean reward = true;
    for(int i = 0; i < s.length(); i++){
        if(s.charAt(i) == 'A'){
            A++;
            if(A > 1){
                reward = false;
                break;
            }
        }
        if(s.charAt(i) == 'L'){
            L = 0;
            while(i < s.length() && s.charAt(i) == 'L'){
                L++;
                i++;
            }
            if(L > 2){
                reward = false;
                break;
            }
            i--;
        }
    }
    return reward;
 }
