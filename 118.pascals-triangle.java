import java.util.List;
import java.util.ArrayList;

/*
 * @lc app=leetcode id=118 lang=java
 *
 * [118] Pascal's Triangle
 */

// @lc code=start
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows == 0)
            return res;

        ArrayList<Integer> pre = new ArrayList<Integer>();
        pre.add(1);
        res.add(pre);
        for (int i = 1; i < numRows; i++) {
            ArrayList<Integer> cur = new ArrayList<Integer>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i)
                    cur.add(1);
                else
                    cur.add(pre.get(j - 1) + pre.get(j));
            }

            res.add(cur);
            pre = cur;
        }

        return res;

    }
}
// @lc code=end
