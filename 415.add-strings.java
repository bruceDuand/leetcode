/*
 * @lc app=leetcode id=415 lang=java
 *
 * [415] Add Strings
 */

// @lc code=start
class Solution {
    public String addStrings(String num1, String num2) {
        if (num1.length() > num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        String res = "";
        int l1 = num1.length();
        int l2 = num2.length();
        int carry = 0;
        int i = 0;
        for (; i < l2; i++) {
            int bv = num2.charAt(l2 - i - 1) - '0' + carry;
            if (i < l1)
                bv += num1.charAt(l1 - i - 1) - '0';
            if (bv > 9) {
                carry = 1;
                bv -= 10;
            } else
                carry = 0;

            res = (char) ('0' + bv) + res;
        }
        if (carry == 1)
            res = '1' + res;

        return res;
    }
}
// @lc code=end
