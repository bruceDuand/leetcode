/*
 * @lc app=leetcode id=1071 lang=java
 *
 * [1071] Greatest Common Divisor of Strings
 */

// @lc code=start
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1))
            return "";

        if (str1.equals(str2))
            return str1;

        if (str1.length() > str2.length())
            return gcdOfStrings(str1.substring(str2.length()), str2);
        else
            return gcdOfStrings(str1, str2.substring(str1.length()));
    }
}
// @lc code=end
