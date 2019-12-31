import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode id=970 lang=java
 *
 * [970] Powerful Integers
 */

// @lc code=start
class Solution {

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        HashSet<Integer> set = new HashSet<>();
        for (int xv = 1; xv < bound; xv *= x) {
            for (int yv = 1; xv + yv <= bound; yv *= y) {
                set.add(xv + yv);
                if (y == 1)
                    break;
            }
            if (x == 1)
                break;
        }
        return new ArrayList<Integer>(set);

    }
}
// @lc code=end
