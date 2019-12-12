/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] isMatched = new boolean[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            for (int j = 0; j <= n; j++)
                isMatched[i][j] = false;
        isMatched[0][0] = true;

        for (int i = 1; i <= n; i++)
            if (p.charAt(i - 1) == '*')
                isMatched[0][i] = isMatched[0][i - 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*')
                    isMatched[i][j] = isMatched[i][j - 1] || isMatched[i - 1][j];
                else {
                    isMatched[i][j] = isMatched[i - 1][j - 1]
                            && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
                }

            }
        }

        return isMatched[m][n];
    }
}
// @lc code=end
