import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * @lc app=leetcode id=127 lang=java
 *
 * [127] Word Ladder
 */

// @lc code=start
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        Queue<String> q1 = new LinkedList<String>();
        q1.add(beginWord);
        Queue<String> q2 = new LinkedList<String>();
        q2.add(endWord);

        HashSet<String> visited = new HashSet<String>();

        int count = 1;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.size() > q2.size()) {
                Queue<String> temp = q1;
                q1 = q2;
                q2 = temp;
            }

            int len = q1.size();
            for (int i = 0; i < len; i++) {
                String word = q1.remove();
                visited.add(word);

                char[] arr = word.toCharArray();
                for (int j = 0; j < arr.length; j++) {

                    for (char c = 'a'; c <= 'z'; c++) {
                        char temp = arr[j];
                        if (c != arr[j])
                            arr[j] = c;

                        String new_word = new String(arr);

                        if (q2.contains(new_word))
                            return count + 1;

                        if (wordList.contains(new_word) && !visited.contains(new_word)) {
                            q1.add(new_word);
                            visited.add(new_word);
                        }
                        arr[j] = temp;
                    }
                }

            }
            count++;

        }

        return 0;

    }
}

// @lc code=end

// find the difference between word == endword and word.equals(endword)
// and String a = new String(arr) and String a = arr.toString()
