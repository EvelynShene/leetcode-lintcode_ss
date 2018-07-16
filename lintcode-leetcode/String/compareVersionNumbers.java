/** 1352. Compare Version Numbers(lintcode)/ 165. Compare Version Numbers(leetcode)
 *      Compare two version numbers version1 and version2.
 *      If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 *
 *      You may assume that the version strings are non-empty and contain only digits and the . character.
 *      The . character does not represent a decimal point and is used to separate number sequences.
 *      For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level 
 *    revision of the second first-level revision.
 *
 *      Example: 1) 0.1 < 1.1 < 1.2 < 13.37
 *               2) 1.0 = 1 = 1.00
 */
 
 //My Method: 
 public int compareVersion(String version1, String version2) {
    String[] ver1 = version1.split("\\.");
    String[] ver2 = version2.split("\\.");
    int len1 = ver1.length;
    int len2 = ver2.length;
    int i = 0;

    while(i < len1 && i < len2){
        int v1 = Integer.valueOf(ver1[i]);
        int v2 = Integer.valueOf(ver2[i]);

        if(v1 > v2){
            return 1;
        }
        else if(v1 < v2){
            return -1;
        }
        i++;
    }
    while((i < len1 && Integer.valueOf(ver1[i]) == 0) ||(i < len2 && Integer.valueOf(ver2[i]) == 0)){
        i++;
    }
    if(i < len1){
        return 1;
    }
    if(i < len2){
        return -1;
    }
    return 0;
 }
 
 //简化代码
 public int compareVersion(String version1, String version2) {
    String[] ver1 = version1.split("\\.");
    String[] ver2 = version2.split("\\.");

    int len = Math.max(ver1.length,ver2.length);
    for(int i = 0; i < len; i++){
        int v1 = i < ver1.length? Integer.valueOf(ver1[i]): 0;
        int v2 = i < ver2.length? Integer.valueOf(ver2[i]): 0;

        if(v1 != v2){
            return v1 > v2? 1: -1;
        }
    }
    return 0;
 }
 
