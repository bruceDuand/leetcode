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

