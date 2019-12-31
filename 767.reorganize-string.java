import java.util.HashMap;

/*
 * @lc app=leetcode id=767 lang=java
 *
 * [767] Reorganize String
 */

// @lc code=start
class Solution {
    public String reorganizeString(String S) {
        char[] res = new char[S.length()];
        HashMap<Integer, Character> map = new HashMap<>();

        int max = 0;
        int[] count = new int[26];
        for (int i = 0; i < S.length(); i++) {
            map.put(S.charAt(i) - 'a', S.charAt(i));
            count[S.charAt(i) - 'a']++;
            max = Math.max(max, count[S.charAt(i) - 'a']);
        }

        if (max > (S.length() + 1) / 2)
            return "";

        int odd = 0, even = 1;
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0 && count[i] < S.length() / 2 + 1 && even < S.length()) {
                res[even] = (char) ('a' + i);
                count[i]--;
                even += 2;
            }
            while (count[i] > 0) {
                res[odd] = (char) ('a' + i);
                count[i]--;
                odd += 2;
            }
        }
        String sres = new String(res);
        return sres;
    }
}
// @lc code=end