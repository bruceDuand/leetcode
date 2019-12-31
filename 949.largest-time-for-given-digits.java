import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode id=949 lang=java
 *
 * [949] Largest Time for Given Digits
 */

// @lc code=start
class Solution {
    public String largestTimeFromDigits(int[] A) {
        int res = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (i == j || j == k || k == i)
                        continue;
                    int m = 6 - i - j - k;
                    int hours = A[i] * 10 + A[j];
                    int minitues = A[k] * 10 + A[m];
                    if (hours < 24 && minitues < 60)
                        res = Math.max(res, hours * 60 + minitues);

                }
            }
        }

        return res >= 0 ? String.format("%02d:%02d", res / 60, res % 60) : "";
    }
}
// @lc code=end
