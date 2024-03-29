/** 1134. Find Duplicate File in System(lintcode) / 609. Find Duplicate File in System(leetcode)
 *      Given a list of directory info including directory path, and all the files with contents in this directory,
 *   you need to find out all the groups of duplicate files in the file system in terms of their paths.
 *      A group of duplicate files consists of at least two files that have exactly the same content.
 *
 *      A single directory info string in the input list has the following format:
 *          "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
 *      It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, 
 *   respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory 
 *   is just the root directory.
 *
 *      The output is a list of group of duplicate file paths. For each group, it contains all the file paths of 
 *   the files that have the same content. A file path is a string that has the following format:
 *          "directory_path/file_name.txt"
 *
 *      Example: 
 *        Input:["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
 *        Output:["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
 *
 *      Note: 1.No order is required for the final output.
 *            2.You may assume the directory name, file name and file content only has letters and digits,
 *        and the length of file content is in the range of [1,50].
 *            3.The number of files given is in the range of [1,20000].
 *            4.You may assume no files or directories share the same name in the same directory.
 *            5.You may assume each given directory info represents a unique directory. 
 *        Directory path and file info are separated by a single blank space.
 *
 *      Follow-up beyond contest:
 *            1. Imagine you are given a real file system, how will you search files? DFS or BFS?
 *            2. If the file content is very large (GB level), how will you modify your solution?
 *            3. If you can only read the file by 1kb each time, how will you modify your solution?
 *            4. What is the time complexity of your modified solution? 
 *               What is the most time-consuming part and memory consuming part of it? How to optimize?
 *            5. How to make sure the duplicated files you find are not false positive?
 */
 
 /* My Method: Use HashMap
  *     Time complexity : O(n*x). n strings of average length x is parsed.
  *     Space complexity : O(n*x). map and list size grows up to n*x.
  */


 public List<List<String>> findDuplicate(String[] paths) {
      List<List<String>> groups = new ArrayList<List<String>>();
      if(paths == null || paths.length == 0){
          return groups;
      }

      Map<String, List<String>> map = new HashMap<String, List<String>>();
      for(int i = 0; i < paths.length; i++){
          String[] path_seg = paths[i].split(" ");
          // String dic = ; 
          for(int j = 1; j < path_seg.length; j++){
              int index = path_seg[j].indexOf('(');
              String content = path_seg[j].substring(index, path_seg[j].length()-1);
              if(!map.containsKey(content)){
                  map.put(content, new ArrayList<String>());
              }
              String p = path_seg[0] + "/" + path_seg[j].substring(0,index);
              List<String> l = map.get(content);
              l.add(p);
              map.put(content, l);
          }
      }
      for(Map.Entry<String, List<String>> entry: map.entrySet()){
          if(entry.getValue().size() >= 2)
              groups.add(entry.getValue());
      }
      return groups;
 }
