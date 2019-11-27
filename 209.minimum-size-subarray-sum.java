/*
 * @lc app=leetcode id=209 lang=java
 *
 * [209] Minimum Size Subarray Sum
 */

// @lc code=start
class Solution {

    public int minSubArrayLen(int s, int[] nums) {
        int l = 0;
        int sumval = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sumval += nums[i];
            while (sumval >= s) {
                res = Math.min(res, i - l + 1);
                sumval -= nums[l++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

}
// @lc code=end

// 213\n[12,28,83,4,25,26,25,2,25,25,25,12]

// int l = 0;
// int r = 0;
// int sumval = 0;
// int res = Integer.MAX_VALUE;
// while (l < nums.length) {
// while (sumval < s && r < nums.length) {
// sumval += nums[r];
// r++;
// }
// if (sumval < s)
// break;
// res = Math.min(res, r - l);
// System.out.print(l);
// System.out.println(r);
// sumval -= nums[l];
// l++;
// }

// return res == Integer.MAX_VALUE ? 0 : res;