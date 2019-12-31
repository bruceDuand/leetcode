import java.util.Queue;

/*
 * @lc app=leetcode id=397 lang=java
 *
 * [397] Integer Replacement
 */

// @lc code=start
class Solution {
    public int integerReplacement(int n) {
        if (n == 1)
            return 0;
        if (n % 2 == 0)
            return 1 + integerReplacement(n / 2);
        else
            return 2 + Math.min(integerReplacement(n / 2 + 1), integerReplacement(n / 2));

    }

    public int integerReplacement2(int n) {
        if (n == 1)
            return 0;
        int res = 0;
        Queue<Long> q = new LinkedList<>();
        q.add((long) n);
        while (true) {
            res++;
            int qlen = q.size();
            for (int i = 0; i < qlen; i++) {
                long val = q.remove();
                if (val % 2 == 0) {
                    long valtemp = val / 2;
                    if (valtemp == 1)
                        return res;
                    q.add(valtemp);
                } else {
                    if (val - 1 == 1 || val + 1 == 1)
                        return res;
                    q.add(val + 1);
                    q.add(val - 1);
                }
            }
        }
    }
}
// @lc code=end
