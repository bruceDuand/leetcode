/*
 * @lc app=leetcode id=152 lang=java
 *
 * [152] Maximum Product Subarray
 */

// @lc code=start
class Solution {
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int cur_max = nums[0];
        int cur_min = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int temp = cur_max;
            cur_max = Math.max(Math.max(cur_max * nums[i], cur_min * nums[i]), nums[i]);
            cur_min = Math.min(Math.min(temp * nums[i], cur_min * nums[i]), nums[i]);

            res = Math.max(res, cur_max);
        }

        return res;
    }
}
// @lc code=end

// note:
// learn how to analyze problems, here analyze that for position i,
// cur_max value depends on last cur_max/cur_min, and current nums[i]
// then the code become much eaier to write