/** 645. Find the Celebrity(lintcode) / 277. Find the Celebrity(leetcode)
 *      Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist 
 *  one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her 
 *  but he/she does not know any of them.
 *      Now you want to find out who the celebrity is or verify that there is not one. The only thing you are 
 *  allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. 
 *  You need to find out the celebrity (or verify there is not one) by asking as few questions as possible
 *  (in the asymptotic sense).
 *      You are given a helper function bool knows(a, b) which tells you whether A knows B. 
 *  Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
 *
 *      Note: There will be exactly one celebrity if he/she is in the party. 
 *            Return the celebrity's label if there is a celebrity in the party. 
 *            If there is no celebrity, return -1.
 *
 *      Example: Given n = 2
 *                  2 // next n * (n - 1) lines 
 *                  0 knows 1
 *                  1 does not know 0
 *               return 1 // 1 is celebrity
 */
 
 //My Method: O(n^2) time complexity
 public int findCelebrity(int n){
    if(n < 2){
        return n-1;
    }

    for(int i = 0; i < n; i++){
        int j = 0;
        for(; j < n; j++){
            if(i == j) continue;
            if(knows(i,j)) break;
        }
        if(j == n){// i doesn't know anyone
            int x = 0;
            for(; x < n; x++){ // check if all others know i
                if(x == i) continue;
                if(!knows(x,i)) break;
            }
            if(x == n){
               return i;
            }
        }
    }
    return -1;
 }
 
 //Method 2: - O(n) time complexity
 public int findCelebrity(int n) {
    // Write your code here
    if(n < 2){
        return n-1;
    }
    int celebrity = 0;//初始化 0 是celebrity
    for(int i = 1; i < n; i++){
        if(knows(celebrity, i)){// 如果celerity认识某人i,那么当前celerity肯定不是celerity
            celebrity = i;
        }
    }

    for(int i = 0; i < n; i++){
        if(i != celebrity && !knows(i,celebrity)){//有i不认识celerity
            return -1;
        }
        if(i != celebrity && knows(celebrity,i)){//celerity有认识的人i
            return -1;
        }
    }
    return celebrity;
 }
