import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 */

// @lc code=start
class Solution {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> subset = new ArrayList<Integer>();
        if (nums.length == 0)
            return res;

        Arrays.sort(nums);
        dfs(nums, 0, res, subset);
        return res;
    }

    public void dfs(int[] nums, int i, List<List<Integer>> res, List<Integer> subset) {
        List<Integer> copy = new ArrayList<Integer>(subset);
        res.add(copy);

        for (int k = i; k < nums.length; k++) {
            subset.add(nums[k]);
            dfs(nums, k + 1, res, subset);
            subset.remove(subset.size() - 1);
            while (k + 1 < nums.length && nums[k] == nums[k + 1])
                k++;
        }

    }
}
// @lc code=end
