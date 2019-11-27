import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * @lc app=leetcode id=126 lang=java
 *
 * [126] Word Ladder II
 */

// @lc code=start
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<String>(wordList);
        List<List<String>> ladders = new ArrayList<List<String>>();
        HashMap<String, List<String>> parents = new HashMap<String, List<String>>();
        HashMap<String, Integer> distance = new HashMap<String, Integer>();

        if (!wordList.contains(endWord))
            return ladders;

        boolean found = bfsBuildGraph(beginWord, endWord, wordSet, parents, distance);

        // for (String str : parents.keySet()) {
        // System.out.print("new key: ");
        // System.out.println(str);
        // for (String mem : parents.get(str)) {
        // System.out.println(mem);
        // }

        // }

        if (found)
            dfsSearch(endWord, beginWord, endWord, wordSet, new ArrayList<String>(), parents, ladders);

        return ladders;
    }

    private void dfsSearch(String word, String beginWord, String endWord, Set<String> wordSet, List<String> path,
            HashMap<String, List<String>> parents, List<List<String>> res) {
        if (word.equals(beginWord)) {
            List<String> pathcopy = new ArrayList<String>(path);
            Collections.reverse(pathcopy);
            res.add(pathcopy);
            return;
        }

        if (word.equals(endWord))
            path.add(endWord);

        List<String> words = parents.get(word);
        for (String last_word : words) {
            System.out.println(last_word);
            path.add(last_word);
            dfsSearch(last_word, beginWord, endWord, wordSet, path, parents, res);
            path.remove(path.size() - 1);
        }
    }

    private boolean bfsBuildGraph(String beginWord, String endWord, Set<String> wordSet,
            HashMap<String, List<String>> parents, HashMap<String, Integer> distance) {
        Queue<String> q = new LinkedList<String>();
        q.add(beginWord);
        distance.put(beginWord, 0);

        boolean found = false;

        while (!q.isEmpty() && !found) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                String word = q.remove();
                List<String> next_words = findWords(word, wordSet);

                for (String next_word : next_words) {
                    if (next_word.equals(endWord))
                        found = true;

                    if (!distance.containsKey(next_word)) {
                        List<String> p = new ArrayList<>();
                        p.add(word);
                        parents.put(next_word, p);
                        distance.put(next_word, distance.get(word) + 1);
                        q.add(next_word);
                    } else if (distance.get(next_word) == distance.get(word) + 1) {
                        parents.get(next_word).add(word);
                    } else
                        ;
                }
            }
        }

        return found;
    }

    private List<String> findWords(String word, Set<String> wordSet) {
        List<String> res = new ArrayList<String>();

        char[] arr = word.toCharArray();
        for (int j = 0; j < arr.length; j++) {

            for (char c = 'a'; c <= 'z'; c++) {
                char temp = arr[j];
                if (c != arr[j])
                    arr[j] = c;

                String new_word = new String(arr);
                if (wordSet.contains(new_word)) {
                    // System.out.println(new_word);
                    res.add(new_word);
                }

                arr[j] = temp;
            }
        }

        return res;

    }
}
// @lc code=end

// "hot"
// "dog"
// ["hot","cog","dog","tot","hog","hop","pot","dot"]