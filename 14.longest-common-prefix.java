/*
 * @lc app=leetcode id=14 lang=java
 *
 * [14] Longest Common Prefix
 */

// @lc code=start
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null)
            return "";

        String longestCommonPrefix = strs[0];

        for (int i = 1; i < strs.length; i++) {

            int j = 0;
            String current_string = strs[i];

            while (j < current_string.length() && j < longestCommonPrefix.length()
                    && current_string.charAt(j) == longestCommonPrefix.charAt(j))
                j++;

            if (j == 0)
                return "";

            longestCommonPrefix = current_string.substring(0, j);
        }

        return longestCommonPrefix;

    }
}
// @lc code=end
