/** 945. Task Scheduler(lintcode)/ 621. Task Scheduler(leetcode)
 *     Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters 
 *  represent different tasks.Tasks could be done without original order. Each task could be done in one interval. 
 *  For each interval, CPU could finish one task or just be idle.
 *     However, there is a non-negative cooling interval n that means between two same tasks, 
 *  there must be at least n intervals that CPU are doing different tasks or just be idle.
 *     You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 *     Note: The number of tasks is in the range [1, 10000].
 *           The integer n is in the range [0, 100].
 *
 *     Example: Input: tasks = ["A","A","A","B","B","B"], n = 2 ; Output: 8
 *        Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 */
 
 /* Method: 找规律 [from leetcode]
  *  Idea: 找到出现次数最多的任务（例如任务A），以它来分块。若是出现次数最多的任务有多个（例如A,B），绑定在一起(AB)进行分块。这样分块的理由是：
  *    假定，A一定能够符合距离下一个A的冷却时间为n,那么跟在A后面的也一定符合。只要保证A符合就行了，其他任务的的符合要求都可以由A的符合推导出来。
  *  举例说明：AAAABBBEEFFGG 3; 其中任务A出现了4次，频率最高，n = 3 所以在每个A中间加入三个空位，如下：
  *     A---A---A---A
  *     AB--AB--AB--A   (加入B)
  *     ABE-ABE-AB--A   (加入E)
  *     ABEFABE-ABF-A   (加入F，每次尽可能填满或者是均匀填充)
  *     ABEFABEGABFGA   (加入G)
  *   再来看一个例子：ACCCEEE 2; 任务C和E都出现了三次，将CE看作一个整体，在中间加入一个位置即可(E已经占了一个空位)：
  *     CE-CE-CE
  *     CEACE-CE   (加入A)
  *    不难发现，模块的次数为任务最大次数减1，模块的长度为n+1，最后加上的字母个数为出现次数最多的任务（或任务组合），就可以得到最少需要的时间段。
  */   
  
  public int leastInterval(char[] tasks, int n) {
      // write your code here
      if(tasks == null || tasks.length == 0){
          return 0;
      }
      int[] letter = new int[26];
      int intervals = 0;
      // Arrays.sort(tasks);
      for(int i = 0; i < tasks.length; i++){
          letter[tasks[i]-'A']++;
      }
      Arrays.sort(letter);
      int most_freq = 0;
      for(most_freq = 25; most_freq > 0; most_freq--){
          if(letter[most_freq-1] != letter[most_freq]){
              break;
          }
      }
      most_freq = 26 - most_freq;
      
      /*   最后intervals取任务数和模块数算出来的值中的最大值是因为，有可能出现次数最多的任务组合的组合内个数就比n大。
       * 例如： ACCCEEE 1 ； 若只用公式，得到的结果是（3-1）* 2 + 2 = 6，而实际有7个任务。
       */
      intervals = Math.max(tasks.length, (letter[25] - 1)*(n + 1) + most_freq);

      return intervals;
  }
 

