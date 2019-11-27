/*
 * @lc app=leetcode id=9 lang=java
 *
 * [9] Palindrome Number
 */

// @lc code=start
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reverted = 0;
        while (x > reverted) {
            reverted = 10 * reverted + x % 10;
            x = x / 10;
        }

        return x == reverted || x == reverted / 10;

    }
}
// @lc code=end
