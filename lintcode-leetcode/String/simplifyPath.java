/** 421. Simplify Path(lintcode) / 71. Simplify Path(leetcode)
 *    Given an absolute path for a file (Unix-style), simplify it.
 *
 *    Example:  1) "/home/", => "/home"
 *              2) "/a/./b/../../c/", => "/c"
 *
 *    Corner Cases:
 *        1) Did you consider the case where path = "/../"?
 *           Ans: In this case, you should return "/".
 *        2) Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 *           Ans: In this case, you should ignore redundant slashes and return "/home/foo".
 */
 
 /* Method: Use stack
  *    此题主要在于理解Unix-style文件的路径结构，有以下三种情况：
  *     1）遇到"." 和 “”(即//)忽略，不做任何操作；
  *     2）遇到".."，是返回上级目录的操作，需要弹出当前目录，返回上一级（即弹出栈一次）
  *     3）遇到其他的，压栈
  */
  
  public String simplifyPath(String path) {
      // write your code here
      if(path == null || path.length() == 0){
          return "";
      }
      Stack<String> stk = new Stack<String>();
      String[] paths = path.split("/");

      for(int i = 0; i < paths.length; i++){
          switch(paths[i]){
              case ".":
              case "": break;
              case "..": if(!stk.isEmpty()){
                          stk.pop();
                      }
                      break;
              default: stk.push(paths[i]);
          }
      }
      
      if(stk.isEmpty()){
          return "/";
      }
      StringBuilder str = new StringBuilder();
      while(!stk.isEmpty()){
          str.insert(0, "/" + stk.pop());
      }
      return str.toString();
 }
 
 /* Note: 此题lintcode的test case不完善，switch中if写成while也能通过，
  * 没有检测如："/home/foo/.ssh/../.ssh2/authorized_keys/" —> "/home/foo/.ssh2/authorized_keys"的测试用例
  */    
