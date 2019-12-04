## problem 127

This first thought of this problem is using brute force method to make transformation from 'a' to 'z' on each digit of the word, then we need to check if the word is in the word list, this idea is exactly what BFS does(doing a line search on the same level and checking). While searching, if the transformed word is in the wordlist, then use a "visited" matrix to note that the word has already been used, since if the same word is visited later, it won't be the shortest path(BFS let the level go deeper and deeper, and the path become longer and longer, so the path that first find the word is the shortest path for the word).

Using a "visited" matrix let the path get rid of potetial circles while doing bfs, with the matrix, the first time that the endword is visited, the lenth of levels is the path length.

**Be careful, when we do the search in a single level, which may contain several words, the path length will only increase by one, this requires a place to store all the words in that level, that's why a queue helps.**

```java
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
```



## problem 126

The problem requires to return all the possible shortest length, the problem compared to problem 127, is we used a "visited" matrix here, if two paths have the same length, and both use a same word, then one of them will be ignored, because we used the matrix to record if the word has been used, and ignore the second request to the word.

The solution to this problem is based on 127, the most important part is to record the parents of single word, with BFS, we can build a graph based on

```java
HashMap<String, List<String>> parents = new HashMap<String, List<String>>();
```

The BFS algorithm can help us find all the parents of a single node, and this time we won't consider the length of each path here, and leave the problem to the second part of the code to find all the minimum length.

After the parents map has been built, we can implement the  DFS algorithm to search all the paths, but the algorithm should start from the endWord, and look through the parents map to find the beginWord.

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<String>(wordList);
        List<List<String>> ladders = new ArrayList<List<String>>();
        HashMap<String, List<String>> parents = new HashMap<String, List<String>>();
        HashMap<String, Integer> distance = new HashMap<String, Integer>();

        if (!wordList.contains(endWord))
            return ladders;

        boolean found = bfsBuildGraph(beginWord, endWord, wordSet, parents, distance);

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
```

