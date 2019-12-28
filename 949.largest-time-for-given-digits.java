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

        // int timemax = 0;
        // String res = "";
        // Queue<List<Integer>> cases = new LinkedList<List<Integer>>();
        // cases.add(new List(A[0]));
        // for (int i = 1; i < 4; i++) {
        // int qlen = cases.size();
        // for (int j = 0; j <= qlen; j++) {
        // List<Integer> temp = cases.remove();
        // for (int k = 0; k <= temp.size(); k++) {
        // temp.add(k, A[i]);
        // cases.add(temp);
        // }
        // }
        // }

        // int max = 23 * 60 + 59;
        // int qlen = cases.size();
        // for (int i = 0; i < qlen; i++) {
        // List<Integer> temp = cases.remove();
        // int time = (temp.get(0) * 10 + temp.get(1)) * 60 + (temp.get(2) * 10 +
        // temp.get(3));
        // if (time >= 0 && time <= max && time > timemax) {
        // timemax = time;
        // res = String.valueOf(temp.get(0) * 10 + temp.get(1)) + ":"
        // + String.valueOf(temp.get(2) * 10 + temp.get(3));
        // }
        // }s
    }
}
// @lc code=end
