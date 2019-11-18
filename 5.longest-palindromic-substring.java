import sun.print.resources.serviceui;

/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 */

// @lc code=start
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return s;
        
        String subs=null;
        int length = s.length();
        int maxlength = 0;
        boolean [][] dp = new boolean[length][length];

        for (int l=0; l<s.length(); l++) {
            for (int i=0; i<s.length()-l; i++) {
                int j = i + l;
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i+1][j-1])) {
                    dp[i][j] = true;

                    if (j - i + 1 > maxlength) {
                        maxlength = j - i + 1;
                        subs = s.substring(i, j+1);
                    }
                }
            }
        }

        return subs;

    }
}
// @lc code=end

