## problem 127

This first thought of this problem is using brute force method to make transformation from 'a' to 'z' on each digit of the word, and check if the word is in the word list, this idea is exactly what BFS does(doing a line search on the same level). While searching, if the transformed word is in the wordlist, then use a "visited" matrix to note that the word has already been used, since if the same word is visited later, it won't be the shortest path(BFS let the level go deeper and deeper, and the path become longer and longer).

Using a "visited" matrix let the path get rid of potetial circles while doing bfs, with the matrix, the first time that the endword is visited, the lenth of levels is the path length. **Be careful, when we do the search in a single level, which may contain several words, the path length will only increase by one, this requires a place to store all the words in that level, that's why a queue will help.**

## problem 126

The problem requires to return all the possible shortest length, the problem is compared to problem 127, is we used a "visited" matrix here, if two paths have the same length, and both use a same word, then one of them will be ignored, because we used the matrix to record if the word has been used, and ignore the second request to the word.

The solution to this problem is based on 127, the most important part is to record the parents of single word, with BFS, we can build a graph based on

```java
HashMap<String, List<String>> parents = new HashMap<String, List<String>>();
```

The BFS algorithm can help us find all the parents of a single node, and this time we won't consider the length of each path here, and leave the problem to the second part of the code to find all the minimum length.

After the parents map has been built, we can implement the  DFS algorithm to search all the paths, but the algorithm should start from the endWord, and look through the parents map to find the beginWord.

