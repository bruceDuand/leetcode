/*
 * @lc app=leetcode id=72 lang=java
 *
 * [72] Edit Distance
 */

// @lc code=start
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++)
            dp[i][0] = i;
        for (int i = 0; i <= n; i++)
            dp[0][i] = i;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word1.charAt(i) == word2.charAt(j))
                    dp[i + 1][j + 1] = dp[i][j];
                else
                    dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i][j + 1], dp[i + 1][j])) + 1;
            }
        }

        return dp[m][n];
    }

    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] mem = new int[m][n];

        int res = makeChange(word1, 0, word2, 0, mem);

        return res;

    }

    private int makeChange(String word1, int idx1, String word2, int idx2, int[][] mem) {
        if (idx1 == word1.length())
            return word2.length() - idx2;
        if (idx2 == word2.length())
            return word1.length() - idx1;
        if (mem[idx1][idx2] > 0)
            return mem[idx1][idx2];

        int res = 0;
        if (word1.charAt(idx1) == word2.charAt(idx2))
            return makeChange(word1, idx1 + 1, word2, idx2 + 1, mem);
        else {
            int insertDistance = makeChange(word1, idx1, word2, idx2 + 1, mem);
            int deleteDistance = makeChange(word1, idx1 + 1, word2, idx2, mem);
            int replaceDistance = makeChange(word1, idx1 + 1, word2, idx2 + 1, mem);
            res = Math.min(insertDistance, Math.min(deleteDistance, replaceDistance)) + 1;
        }
        mem[idx1][idx2] = res;
        return res;
    }
}
// @lc code=end
