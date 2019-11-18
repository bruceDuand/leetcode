import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=119 lang=java
 *
 * [119] Pascal's Triangle II
 */

// @lc code=start
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer>> res = new ArrayList<Integer>>();
        if (rowIndex < 0)
            return res;

        for (int i = 1; i < rowIndex; i++) {
            if (i == 0 || i == rowIndex)
                    res.add(1);
            else
                res.set(i, res.get(i - 1) + res.get(i));
            }

            res.add(cur);
            pre = cur;
        }

    return res;
}}
// @lc code=end
