# Dynamic Programming

---

## *Introduction to Algorithms* — book

The algorithm is used for finding optimized solution to particular problems, ususally these problems have multiple solutions, but we need to find the optimal one.

Normally, the steps to implement DP are as follows:

1. Characterize the structure of an optimal solution
2. Recursively define the value of an optimal solution
3. Compute the value of an optimal solution, typically in a bottom-up fashion
4. Construct an optimal solution from computed information

**The most important part is finding the optimal solutions in a bottom-up way.** Sometimes we need to maintain information in step 3 so that we can easily construct an optimal solution

**DP algorithm is applicable to problems when the final solutions incorportate optimal solutions to all the subproblems, then we say the problem has optimal substructure**



## Rod cutting

Given the price based on the length of rod as follows:

| length i     | 1   2   3   4   5     6     7     8     9     10 |
| ------------ | ------------------------------------------------ |
| **price pi** | **1   5   8   9   10   17   17   20   24   30**  |

 Then give a rod of length n, return the maximum benefit.

### Solutions: 

Find the maxmum solution of r1, r2, r3, …, r10, by implementing the formula: rn = max(pn, r<sub>1</sub>+r<sub>n-1</sub>, r<sub>2</sub>+r<sub>n-2</sub>, ... )

**Recursively top-down design**

The idea is similar to simple recursion method, pseudocode is as follows:

```
CUT-ROD(p, n)
if n == 0
	return 0
q = -inf
for i = 1 to n
	q = max(q, p[i] + CUT-ROD(p, n-i))
return q
```

The solution is inefficient because it will solve the same problems repeatedly, but DP uses additional memories to save the somputation time by storing these repeated values. **Typically, there are two ways to implement DP**, one is **top-down with memorization**, the other is **bottom-up method.** Bottom-up method especially put emphasis on the word "size", since solutions to all the problems depend on subproblems with small sizes, so it is possible to solve all the subproblems at first, but in many cases, **there still exists a comparison** using solutions to subproblems to find the current one.

**Top-down with Memorization**

```
Memorized-CUT-ROD(p, n)
let r[0...n] denote a new array stroring the info
initial r[i] = -inf
Memorized-CUT-ROD-AUX(p, n, r)

Memorized-CUT-ROD-AUX(p, n, r)
if r[n] >= 0
	return r[n]

if n == 0
	return 0
else
  q = -inf
  for i = 1 to n
    q = max(q, p[i] + Memorized-CUT-ROD-AUX(p, n-i, r))
   r[n] = q;
return q
```

**bottom-up method**

```
Bottom-up-CUT-ROD(p, n)
let r[0...n] denote a new array stroring the info
initial r[i] = -inf;
r[0] = 0;
for j = 1 to n
	q = -inf
  for i = 1 to n
    q = max(q, p[i] + r[j-i])
   r[j] = q;
return r[n]
```

If we want to reconstruct the solution, **we need another array s to store i in each loop.**

---

## 10. Regular Expression Matching

Requirements:

Given an input string and a re pattern, including "." and "*", and determine if the input string satisfies with the re pattern. The re pattern should matches with the entire string

```
'.' Matches any single character.
'*' Matches zero or more of the preceding element.
```

This problem can be converted into a dp problem, there are several facts that lead to this fact:

1. At each index, the match function returns true when all the values in the previous indexes match and the current one matches.
2. There are two strings, so we can use a 2D array to express the relationship of each iteration.

The difficult part in this problem is to distinguish all the cases clearly, the "*" sign functions when there are two characters in the string, therefore there is a total of 4 condtions need to be considered. (since we should check if the input string matches with the pattern, so we need to discuss these conditions based on the pattern string)

**Solution 1**: Recursion

pattern length:

0 - check whether input string is 0

1 - check whether input string matches with the single character in the pattern

2 - check if the second char in pattern is "*"

​	false - compare the first char in both string

​	true - check if the next string mathces with the pattern without "_*" sign, if not, continue using the current pattern to check the next input string(**next means the substring(1,end) in a loop**) - **In this step we assumes the current string matches with the "\*" sign**

​		   - the last case is when the first/current string matches 0 nums of "*", then the directly checking the next inpu string.

```java
/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        if (p.length() == 0)
            return s.isEmpty();

        if (p.length() == 1)
            return s.length() == 1 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');

        if (p.charAt(1) != '*') {
            if (s.isEmpty())
                return false;
            return (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
        }

        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s, p.substring(2)))
                return true;
            s = s.substring(1);
        }

        return isMatch(s, p.substring(2));

    }
}
// @lc code=end

```

**Solution 2**: Dynamic programming

Considering dp, let a boolean 2d array dp\[i]\[j] denote if the input string [0, i-1) matches with the pattern [0, j-1), therefore, we have **dp\[0]\[0] = true**, based on is fact, we have ththe solution to a middle index is calculated as follows:

```
dp[i][j] = dp[i-1][j-1] if p[j-1] != '*' and (p[j-1] == s[i-1] || p[j-1] == '.')
dp[i][j] = dp[i][j-2] if p[j-1] == '*' and the pattern repeated for 0 times;
dp[i][j] = dp[i-1][j] && (p[j-2] == s[i-1] || p[j-2] == '.')  if p[i-1] == '*' and the pattern repeated for multiple times
```

Based on the condtions above, the dp algorithms can be implemented as follows:

```java
/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        for (int i = 0; i < s.length(); i++)
            for (int j = 0; j < p.length(); j++)
                dp[i][j] = false;
        dp[0][0] = true;

        for (int i = 0; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (j > 1 && p.charAt(j - 1) == '*')
                    dp[i][j] = dp[i][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')
                            && dp[i - 1][j]);
                else
                    dp[i][j] = i > 0 && dp[i - 1][j - 1]
                            && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
            }
        }

        return dp[s.length()][p.length()];

    }
}
// @lc code=end

```

This is how a problem can be solved based on the dp algorithm, **all of the problems concerning solving the current one based on the (optimal) solution to the previous problem can attain help from DP. excellent!**

---

## 44. WildCard Matching

**similar to last problem, do make some notes!**

Requirements:

```
Given an input string (`s`) and a pattern (`p`), implement wildcard pattern matching with support for `'?'` and `'*'`.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
```



**Solution:**

```java
/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] isMatched = new boolean[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            for (int j = 0; j <= n; j++)
                isMatched[i][j] = false;
        isMatched[0][0] = true;

        for (int i = 1; i <= n; i++)
            if (p.charAt(i - 1) == '*')
                isMatched[0][i] = isMatched[0][i - 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*')
                    isMatched[i][j] = isMatched[i][j - 1] || isMatched[i - 1][j];
                else {
                    isMatched[i][j] = isMatched[i - 1][j - 1]
                            && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
                }

            }
        }
        return isMatched[m][n];
    }
}
// @lc code=end

```



---

## 32. Longest Valid Parenthesis

Given a string containing just the characters `'('` and `')'`, find the length of the longest valid (well-formed) parentheses substring.

example: "(()" -> length: 2

**Solution 1:**

The problem can be solved using DP, although it is not the optimal solution. We can 

**NOTE: In many cases, the reason why when implementing DP, we use a N+1 array/matrix DP is that thus we can ignore the  condtion of the first element, we can be sure that DP[0] is 0 or false since it is not in the input, and consider the first element in the input stream as other normal elements**, a great kind of thinking! But we need to remember that when discussing the real input, we should use i+1 to discuss the index of the DP array, which in many cases lead to a for loop with the start index be 1,instead of 0.  

The solution code using DP is as follows:

```java
/*
 * @lc app=leetcode id=32 lang=java
 *
 * [32] Longest Valid Parentheses
 */

// @lc code=start
class Solution {
    public int longestValidParentheses(String s) {
        int m = s.length();
        int[] dp = new int[m + 1]; //1
        int maxlen = 0;
        dp[0] = 0;

        for (int i = 1; i <= m; i++) {//2
            int j = i - 2 - dp[i - 1];//3
            if (j < 0 || s.charAt(j) == ')' || s.charAt(i - 1) == '(')
                dp[i] = 0;
            else {
                dp[i] = dp[i - 1] + 2 + dp[j];//4
                maxlen = Math.max(dp[i], maxlen);
            }
        }
        return maxlen;
    }
}
// @lc code=end
```

There are several places need to notice, for DP problems, we could consider some properties of the problem to have a more clear understanding of the problem.

1. **The meaning the DP states, ie. the meaning of each DP[i], in many cases, it relates to the index [0, i-1] of the input string or array** 
2. **The general term formula(iterative formula), for this problem, DP[i] = DP[i-1] + 2 + DP[j] if certain condition holds.**

## Remember these two properties!!

In this problem, DP[i] denote the length of the longest parenthesis series can be found that ends with s[i-1]—**important!**

In comment 4, the dp[j] just denote the path ends with s[j-1].

**Excellent ideas! We can also use stack to solve the problem.**

**Solution 2:**

**Stack is very useful when we need to pair two elements in the input steam, or match two elements but we do not know their positions.** In this problem, we need to find valid parenthesis, we could push the index of each '(' character in a stack. Every time there appears a ')' char, we find if the top of the stack has a matched '(', if there isn't, we know there apprears a ')' without previous matching '(', so the start index would be the index of this ')', then we continue to check the latter part of the input stream.

```java
import java.util.Stack;

/*
 * @lc app=leetcode id=32 lang=java
 *
 * [32] Longest Valid Parentheses
 */

// @lc code=start
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stk = new Stack<Integer>();
        int maxlen = 0, curlen = 0, start = -1;

        // Map - first: index; second: 0->'(', 1->')'
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stk.push(i);
            else {
                if (stk.isEmpty())
                    start = i;
                else {
                    stk.pop();
                    if (stk.isEmpty())
                        curlen = i - start;
                    else
                        curlen = i - stk.peek();
                    maxlen = Math.max(curlen, maxlen);
                }
            }
        }
        return maxlen;

    }
}
// @lc code=end
```

It turns out that, both is two method is O(n) in time complexity.

