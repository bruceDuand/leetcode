import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode id=880 lang=java
 *
 * [880] Decoded String at Index
 */

// @lc code=start
class Solution {
    // the idea is first consider the position that could get the Kth value, then
    // decode back to find the target character
    // During the way back to find the cahracter, if the S[i] is a digit then, first
    // skip the number of repeatence by N/=(number of repeatence), and K%=N is the
    // rest part without repeatence
    // Next time if the K==0 or K==N, then the S[i] is the target value
    public String decodeAtIndex(String S, int K) {
        long N = 0L;
        int i = 0;
        for (i = 0; N < K; i++) {
            N = (S.charAt(i) >= '0' && S.charAt(i) <= '9') ? N * (S.charAt(i) - '0') : N + 1;
        }
        i--;
        while (true) {
            if (S.charAt(i) >= '0' && S.charAt(i) <= '9') {
                N /= (S.charAt(i) - '0');
                K %= N;
            } else {
                if (K % N == 0)
                    return "" + S.charAt(i);
                else
                    N--;
            }
            i--;
        }
    }

    public String decodeAtIndex2(String S, int K) {
        int i = 0;
        List<Character> cres = new ArrayList<>();

        while (i < S.length()) {
            if (S.charAt(i) >= 'a' && S.charAt(i) <= 'z') {
                cres.add(S.charAt(i));
            }

            if (S.charAt(i) >= '0' && S.charAt(i) <= '9') {
                int times = S.charAt(i) - '0' - 1;
                List<Character> tmp = new ArrayList(cres);
                for (int k = 0; k < times; k++) {
                    cres.addAll(tmp);
                }
            }
            i++;

            if (K <= cres.size()) {
                return cres.get(K - 1).toString();
            }
        }

        // char[] carr = new char[cres.size()];
        // for (int j = 0; j < cres.size(); j++)
        // carr[j] = cres.get(j);

        return cres.get(K - 1).toString();
    }
}
// @lc code=end
