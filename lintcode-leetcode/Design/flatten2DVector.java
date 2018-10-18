/** 251. Flatten 2D Vector(leetcode) / 601. Flatten 2D Vector(lintcode)
 *      Implement an iterator to flatten a 2d vector.
 *
 *      Example: Input: 2d vector =
 *                    [
 *                      [1,2],
 *                      [3],
 *                      [4,5,6]
 *                    ]
 *               Output: [1,2,3,4,5,6]
 *                  Explanation: By calling next repeatedly until hasNext returns false, 
 *                                the order of elements returned by next should be: [1,2,3,4,5,6].
 *
 *               Follow up:
 *                      As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
 
 //My Method: using only iterators
 public class Vector2D implements Iterator<Integer> {
    Iterator<List<Integer>> itr_outer;
    Iterator<Integer> itr_inner;
    public Vector2D(List<List<Integer>> vec2d) {
        itr_outer = vec2d.iterator();
        if(itr_outer.hasNext()){
            itr_inner = itr_outer.next().iterator();
        }
    }

    @Override
    public Integer next() {
        Integer next = null;
        while(next == null){
            if(itr_inner.hasNext()){
                next = itr_inner.next();
            }
            else if(itr_outer.hasNext()){
                itr_inner = itr_outer.next().iterator();
            }
        }
        return next;
    }

    @Override
    public boolean hasNext() {
        while((itr_inner != null && itr_inner.hasNext()) || itr_outer.hasNext()){
            if(itr_inner.hasNext()){
                return true;
            }
            else if(itr_outer.hasNext()){
                itr_inner = itr_outer.next().iterator();
            }
        }
        
        return false;
    }
}
