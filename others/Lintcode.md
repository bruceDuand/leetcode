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

