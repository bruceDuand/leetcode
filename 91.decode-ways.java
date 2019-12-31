/*
 * @lc app=leetcode id=91 lang=java
 *
 * [91] Decode Ways
 */

// @lc code=start
class Solution {
    public int numDecodings(String s) {
        int m = s.length();
        int[] dp = new int[m + 1];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            if (s.charAt(i) > '0' && s.charAt(i) <= '9')
                dp[i + 1] += dp[i];
            if (i > 0) {
                int sum = (s.charAt(i - 1) - '0') * 10 + s.charAt(i) - '0';
                if (sum >= 10 && sum <= 26)
                    dp[i + 1] += dp[i - 1];
            }

            if (dp[i + 1] == 0)
                return 0;
        }

        return dp[m];
    }
}
// @lc code=end
