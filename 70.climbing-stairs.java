/*
 * @lc app=leetcode id=70 lang=java
 *
 * [70] Climbing Stairs
 */

// @lc code=start
class Solution {
    public int climbStairs(int n) {
        if (n <= 2)
            return n;
        int i = 2;
        int j = 1;

        for (int k=2; k < n; k++) {
            int temp = i;
            i = i + j;
            j = temp;
        }

        return i;
    }
}
// @lc code=end

