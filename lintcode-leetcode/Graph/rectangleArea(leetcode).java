/** 223. Rectangle Area(leetcode)
 *    Find the total area covered by two rectilinear rectangles in a 2D plane.
 *    Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 *  
 *    Example: Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
 *             Output: 45
 *
 *    Note: Assume that the total area is never beyond the maximum possible value of int.
 */
 
 //My Method:找规律
public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    int area1 = (C - A) * (D - B);
    int area2 = (G - E) * (H - F);

    if(E >= C || G <= A || F >= D || H <= B){
        return area1 + area2;
    }
    int x = 0;
    int y = 0;
    if(E >= A){ // A <= E < C
        x = G < C? G - E: C - E;
        if(F > B){
            y = H < D? H - F: D - F;
        }
        else{ // F <= B && H > B
            y = H < D? H - B : D - B;
        }
    }
    else{ // A < G && E < A
        x = G < C? G - A: C - A;
        if(F > B){
            y = H < D? H - F: D - F;
        }
        else{ // F <= B && H > B
            y = H < D? H - B : D - B;
        }
    }
    return area1 + area2 - x * y;
}

/* Method 2: 
 *    idea: 求交集区域的长和宽也不难，由于交集都是在中间，
 *        所以交集横边的左端点：是两个矩形左顶点横坐标的较大值，右端点：是两个矩形右顶点的较小值，
 *        同理交集竖边的下端点：是两个矩形下顶点纵坐标的较大值，上端点：是两个矩形上顶点纵坐标的较小值。
 */
public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    int area1 = (C - A) * (D - B);
    int area2 = (G - E) * (H - F);

    if(E >= C || G <= A || F >= D || H <= B){
        return area1 + area2;
    }
    int x = 0;
    int y = 0;
    x = Math.min(C, G) - Math.max(A, E);
    y = Math.min(H, D) - Math.max(B, F);
    return area1 + area2 - x * y;
}
