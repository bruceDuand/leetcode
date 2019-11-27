/*
 * @lc app=leetcode id=7 lang=java
 *
 * [7] Reverse Integer
 */

// @lc code=start
class Solution {
    public int reverse(int x) {

        int res = 0;
        int p = x;
        while (p != 0) {
            int digit = p % 10;
            int newres = res * 10 + digit;
            if ((newres - digit) / 10 != res)
                return 0;
            res = newres;
            p = p / 10;
        }

        return res;
    }
}
// @lc code=end
