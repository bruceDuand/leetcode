# Lintcode notes

---

## 637 Valid Word Abbreviation

Given a **non-empty** string `word` and an abbreviation `abbr`, return whether the string matches with the given abbreviation.

A string such as `"word"` contains only the following valid abbreviations:

```
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
```

**Solution:**

This problem can be solved using two different pointers, one is for the string word, the other is for the string abbr. The point, or the most important idea behind is that: **while simultaneously considering chars and integers, like the case in this problem, in the while loop, we could separate the char cases and the integer cases using a if statement, this will make the solving process much more clear.**

**So, always distinguish different problem cases/classes, and solve them separately.**

We should also distinguish the corner cases clearly and coorectly, especially in these cases:

1. When we try to add the index of an array/string, very common in **while loop**, check if the index has reached the right/left boundary.



```java
public class Solution {
    /**
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while(i < word.length() & j < abbr.length()) {
            if(abbr.charAt(j) >= '0' & abbr.charAt(j) <= '9') {
                if(abbr.charAt(j) == '0')
                    return false;
                int sum = 0;
                while(abbr.charAt(j) >= '0' & abbr.charAt(j) <= '9') {
                    sum = 10*sum + (abbr.charAt(j++) - '0');
                    if(j == abbr.length()) //boundary case
                        break;
                }
                i += sum;
            }
            else
                if(word.charAt(i++) != abbr.charAt(j++))
                    return false;
        }
        
        return i == word.length() & j == abbr.length();
    }
    
}
```

---

## 156 Merge intervals

Given a set of intervals, returns the result that merges all the intervals that overlap

The problem is not hard, and the point is to be careful of the corner cases, sort the list at first, and compare the boundary conditions in the while loop.

**Solution:**

```java
/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
     
    class MyComparator implements Comparator {
        
        public int compare(Object a1, Object a2) {
            Interval i1 = (Interval)a1;
            Interval i2 = (Interval)a2;
            
            return i1.start - i2.start;
        }
    }
     
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        
        if(intervals.size() == 0 || intervals.size() == 1 || intervals == null)
            return intervals;
        
        MyComparator mc = new MyComparator();
        Collections.sort(intervals, mc);
        
        List<Interval> res = new ArrayList<>();
        
        int i = 0;
        while(i < intervals.size()) {
            int k = i + 1;
            if (k == intervals.size()) {
                res.add(new Interval(intervals.get(i).start, intervals.get(i).end));
                i = k;
                continue;
            }
            
            Interval curinterval = intervals.get(i);
            while(intervals.get(k).start <= curinterval.end) {
                Interval mergeinterval = intervals.get(k);
                if (mergeinterval.end > intervals.get(i).end) {
                    curinterval.end = mergeinterval.end;
                }
                k++;
                if(k == intervals.size())
                    break;
            }
            res.add(new Interval(curinterval.start, curinterval.end));
            i = k;

        }
        return res;
    }
}
```

---

## 512 Decode Ways

Given the encoding pattern, and find the number of possible ways that may decode the message.

```java
'A' -> 1
'B' -> 2
...
'Z' -> 26
```

**Solution:**

The special case the character "0", which can only be decoded as "10". One thing that we can learn from this problem is that when solving DP problem, **we can also divide a single sub-prolem into different cases, and get the number stored in dp[i] step by step through addition, we do not have to get the dp[i]'s value in a single step, of a if case.** The most important thing is to distinguish different cases clearly.

```java
public class Solution {
    /**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        // write your code here
        
        if (s == null || s.length() == 0)
            return 0;
        
        int m = s.length();
        int[] dp = new int[m+1];
        dp[0] = 1;
        
        for(int i=0; i<m; i++) {
            int sum = s.charAt(i) - '0';
            if (sum > 0 && sum <= 9) {
                dp[i+1] += dp[i]; 
            }
            
            if (i > 0) {
                sum = (s.charAt(i-1) - '0')*10 + s.charAt(i) - '0';
                if (sum >= 10 && sum <= 26) {
                    dp[i+1] += dp[i-1];
                }
            }
            
            if (dp[i] == 0)
                return 0;
        } 
     	 return dp[m];
    }
}
```

---

## 417 Valid Numbers

check if the input is a valid number string, the problem is tedious,we need to consider all the corner cases, and write all the if/else condition.(valid numbers including space at the beginning or the end of the input string)

```java
public class Solution {
    /**
     * @param s: the string that represents a number
     * @return: whether the string is a valid number
     */
    public boolean isNumber(String s) {
        // write your code here
        boolean num = false, numAfterE = true, exp = false, dot = false, sign = false;
        for(int i=0; i<s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (i < s.length()-1 && s.charAt(i+1) != ' ' && (num || exp || dot || sign) )
                    return false;
            }
            else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (i > 0 && s.charAt(i-1) != 'e' && s.charAt(i-1) != ' ')
                    return false;
                sign = true;
            }
            else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = true;
                numAfterE = true;
            }
            else if (s.charAt(i) == 'e') {
                if (exp || !num)
                    return false;
                exp = true;
                numAfterE = false;
            }
            else if(s.charAt(i) == '.') {
                if (dot || exp)
                    return false;
                dot = true;
            }
            else
                return false;
                
        }
        
        return num && numAfterE;
    }
}
```

---

## 654 Sparse Matrix Multiplication 

Find a more efficient way to calculate the result of sparse matrix multiplication result, the point is to judge if the current searching point in A equals to 0, then add up all the valid multiplication value

```java
public class Solution {
    /**
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // write your code here
        int[][] res = new int[A.length][B[0].length];
        for(int i=0; i<res.length; i++){
            for(int j=0; j<res[0].length; j++) {
                res[i][j] = 0;
            }
        }
        
        for(int i=0; i<A.length; i++) {
            for(int k=0; k<A[0].length; k++) {
                if(A[i][k] != 0)
                    for(int j=0;j<B[0].length;j++) {
                        if (B[k][j] != 0)
                            res[i][j] += A[i][k]*B[k][j];
                    }
            }
        }
        return res;
    }
}
```

---

## 645 Find the celebrity

**An interesting problem**, the problem requires to find the celebrity who is know to everyone else, but himself does not know anyone else.

The problem provides us with a knows(int a, int b) to check if a knows b, and want to make sure we call knows function as less as possible.

**Solution:**

To call knows function as less as possible, we should make full use of the return value. First thought is we have to traversal the entire number to see who could be a celebrity, the problem is, is it a 2d traversal, or a 1d traversal is enough.

Then it comes that , in the for loop, if a knows b, then a could not be a celebrity, but b may be, then we can move the pointer to b(denote b is a candidate) and continue to traverse, if b knows the next person, then b cannot be the celebrity, but c has the potential.

After the first traversal, we need to check again, since we ignored many cases before, but we have find the most potential person who could be celebrity, the only thing left is check if the candidate knows someone else, or someone else does not know the candidate.

```java
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    /**
     * @param n a party with n people
     * @return the celebrity's label or -1
     */
    public int findCelebrity(int n) {
        // Write your code here
        
        int res = 0;
        for(int i=0; i<n; i++) {
            if(knows(res, i)) {
                res = i;
            }
        }
        
        for(int i=0; i<n; i++) {
            if (res != i && (knows(res, i) || knows(i, res) == false))
                return -1;
        }
        
        return res;
    }
}
```

The call of knows function can be further decreased if we strore the verification result in a list or array, with a integer number M denote the length. Therefore in the second for loop, we can ignore the step of checking if the celebrity knows the people from index of M to n.

---

## 641 Missing Ranges

Given nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99, return a list of string contains missing ranges, the list looks like ["2", "4->49", "51->74", "76->99"]

The point in this problem is to consider corner cases:

1. The array maybe a null or the lengh is 0, but the lower and upper value still exists
2. The lower value may be the Integer.MINIMUM and the upper may be the Integer.Maximum, so the problem of overflow exists.

**Be aware of the corners cases when implementing +/- operation**, sometimes using long instead of int may solve the problem.

```java
public class Solution {
    /*
     * @param nums: a sorted integer array
     * @param lower: An integer
     * @param upper: An integer
     * @return: a list of its missing ranges
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // write your code here
        long llower = new Long(lower);
        long lupper = new Long(upper);
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            addInterval(res, llower-1, lupper+1);
            return res;
        }
            
        addInterval(res, llower-1, nums[0]);
        for (int i=0; i<nums.length-1; i++){
            addInterval(res, nums[i], nums[i+1]);
        }
        addInterval(res, nums[nums.length-1], lupper+1);
        
        return res;
    }
    
    private void addInterval(List<String> res, long a, long b) {
        if (b <= a+1)
            return;
        else if (b == a+2)
            res.add(String.valueOf(a+1));
        else
            res.add(String.valueOf(a+1)+"->"+String.valueOf(b-1));
    }
}
```

---

## 428 Pow(x, n)

Given the base and the power number, return the result. Simply using a for loop to calculate the result will cause running out of time.

**Solution:**

Using recursion is an acceptable answer, if n is an even number, then return half*half, if n is an odd number, then there is an additional x, and we should return half \* half \* x. The last case is when n is a negative number, then we shuld return the reciprocal, which is half \* half / x in each recursive step.

```java
public class Solution {
    /**
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {  
        if (n==0)
            return 1;
        double half = myPow(x, n/2);
        if (n%2 == 0)
            return half*half;
        if(n > 0)
            return half*half*x;
        else
            return half*half/x;     
    }
}
```

---

## 418 Integer to Roman

Given an integer, convert it to roman expression. The number is restricted from 1 to 3999, which makes the problem much easier.

For Roman expression, we got:

```
I - 1
V - 5
X - 10
L - 50
C - 100 
D - 500
M - 1000
```

**Solution:**

The special point of Roman expressio is it expresses 5 & 9 differently, so we should discuss them separately from other cases.

We can divide the number by 1, 10, 100, 1000, and consider the reuslt falls in which cases.

```java
public class Solution {
    /**
     * @param n: The integer
     * @return: Roman representation
     */
    public String intToRoman(int n) {
        // write your code here
        String res = "";
        
        String[] roman = {"M", "D", "C", "L", "X", "V", "I"};
        int[] value = {1000, 500, 100, 50, 10, 5, 1};
        
        for (int i=0; i<7; i=i+2) {
            int x = n / value[i];
            if (x < 4) {
                for (int j=1; j<=x; j++) res += roman[i];
            }else if (x == 4) {
                res += roman[i] + roman[i-1];
            }else if (x > 4 && x < 9) {
                res += roman[i-1];
                for(int j=6; j<=x; j++) res += roman[i];
            }else if (x == 9)
                res += roman[i] + roman[i-2];
            
            n = n % value[i];
        }
        
        return res;
    }
}
```

---

## Problems concerning anagrams

Can be solved using hash ideas, using a array of size 256(the size of all ascii code), and store the appereace of each char in the array.

---

## 639 Word Abbreiation

Given an array of n distinct non-empty strings, you need to generate **minimal** possible abbreviations for every word following rules below.

1. Begin with the first character and then the number of characters abbreviated, which followed by the last character.
2. If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
3. If the abbreviation doesn't make the word shorter, then keep it as original.

example:

```
Input:
["like","god","internal","me","internet","interval","intension","face","intrusion"]
Output:
["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
```

**Solution:**

This problem is some kind of interesting, The point is to find if there is two abbreviations that are the same, if there are, then we should update the abbreviations stored in the answer, therefore, we can use a hashmap to store the number of appereaces of each word. After the first iteration, we need a for loop and a round variable to store the depth that we have gone through.

```java
public class Solution {
    /**
     * @param dict: an array of n distinct non-empty strings
     * @return: an array of minimal possible abbreviations for every word
     */
    public String[] wordsAbbreviation(String[] dict) {
        // write your code here
        
        String[] res = new String[dict.length];
        HashMap<String, String> strmap = new HashMap<String, String>(); 
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        int round = 1;
        
        for (int i=0; i<dict.length; i++) {
            String word = dict[i];
            String abbr = getAbbrev(word, 0, word.length()-1);
            strmap.put(word, abbr);
            if (count.containsKey(abbr))
                count.put(abbr, count.get(abbr)+1);
            else
                count.put(abbr, 1);
            res[i] = abbr;
        }
        
        while (true) {
            boolean valid = true;
            for (int i=0; i<dict.length; i++) {
                if (count.get(strmap.get(dict[i])) > 1) {
                    valid = false;
                    String word = dict[i];
                    String abbr = getAbbrev(word, round, word.length()-1);
                    strmap.put(word, abbr);
                    if (count.containsKey(abbr))
                        count.put(abbr, count.get(abbr)+1);
                    else
                        count.put(abbr, 1);
                    res[i] = abbr;
                    
                }
            }
            
            if (!valid) {
                round++;
            }
            else
                break;
        }
        return res;
        
    }
    
    private String getAbbrev(String word, int p, int q) {
        if (p >= word.length() || q >= word.length())
            return "";
        if (q-p <= 2)
            return word;
        else
            return word.substring(0, p+1)+String.valueOf(q-p-1)+word.substring(q, word.length());
    }
}
```

---

## 663 Walls and Gates

You are given a m x n 2D grid initialized with these three possible values.

```
-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a Gate, that room should remain filled with INF
```

In a short, update the points in the graph with the shortest jumps that it could reach a gate.

**Solution:**

This is a very standard BFS problem concerning graph, or 2D matrix, could be solved by Iterating on points deeper and deeper, while comparing if the number of jumps is minimum.

The thing needs to notice is the dirrection the point goes when we define the dx and dy array.

```java
public class Solution {
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    public void wallsAndGates(int[][] rooms) {
        // write your code here
        
        Queue<Integer> q = new LinkedList<Integer>();
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        for (int i=0; i<rooms.length; i++)
            for (int j=0; j<rooms[0].length; j++)
                if (rooms[i][j] == 0)
                    q.add(i*rooms[0].length+j);
                    
        while (!q.isEmpty()) {
            int qlen = q.size();
            for (int i=0; i<qlen; i++) {
                int pos = q.remove();
                int nx = pos / rooms[0].length;
                int ny = pos % rooms[0].length;
                
                for (int d=0; d<4; d++) {
                    int cx = nx + dx[d];
                    int cy = ny + dy[d];
                    if (cx == -1 || cx == rooms.length || cy == -1 || cy == rooms[0].length || rooms[cx][cy] == -1){
                        continue;
                    }
                    
                    if (rooms[cx][cy] > rooms[nx][ny]+1) {
                        rooms[cx][cy] = rooms[nx][ny]+1;
                        q.add(cx*rooms[0].length+cy);
                    }
                }
                
            }
        }
        
    }
}
```

---

## 598 Zombie in Matrix

Determine how many steps could change all people in the graph to zombie(a people could be changed to a zombie in one step if it is near a zombie) 

There are some changes(can be seen as a followup of the last problem), since normally, the queue can not be empty, so we need an additional variable people to determine the end of the while loop. In case the queue becomes empty, then the loop should also end, because this means that the rest people can not be changed into zombie due to some walls or obstycles are between people and zombie

```java
public class Solution {
    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    public int zombie(int[][] grid) {
        // write your code here
        int people = 0;
        int round = 0;
        Queue<Integer> q = new LinkedList<Integer>();
        int m = grid.length;
        int n = grid[0].length;
       
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};
        
        for (int i=0; i<m; i++)
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1)
                    q.add(i*n+j);
                else if(grid[i][j] == 0)
                    people++;
            }
            
        while(people > 0 && !q.isEmpty()) {
            int qlen = q.size();
            for (int i=0; i<qlen; i++) {
                int pos = q.remove();
                int nx = pos / n;
                int ny = pos % n;
                
                for(int d = 0; d<4; d++) {
                    int cx = nx + dx[d];
                    int cy = ny + dy[d];
                    if (cx == -1 || cx == m || cy == -1 || cy == n)
                        continue;
                    if (grid[cx][cy] == 0) {
                        grid[cx][cy] = 1;
                        q.add(cx*n+cy);
                        people--;
                    }
                }
            }
            round++;
        }
        
        if (q.isEmpty())
            return -1;
        return round;
        
    }
}
```

