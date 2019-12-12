import java.util.Stack;

/*
 * @lc app=leetcode id=32 lang=java
 *
 * [32] Longest Valid Parentheses
 */

// @lc code=start
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stk = new Stack<Integer>();
        int maxlen = 0, curlen = 0, start = -1;

        // Map - first: index; second: 0->'(', 1->')'
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stk.push(i);
            else {
                if (stk.isEmpty())
                    start = i;
                else {
                    stk.pop();
                    if (stk.isEmpty())
                        curlen = i - start;
                    else
                        curlen = i - stk.peek();
                    maxlen = Math.max(curlen, maxlen);
                }

            }
        }

        return maxlen;

    }
}
// @lc code=end