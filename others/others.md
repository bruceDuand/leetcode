## 767 Reorganize String

Given a string `S`, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result. If not possible, return the empty string.

example:

```
Input: S = "aab"
Output: "aba"
```

special case:

```
Input: "vvvlo"
Output: "vlvov"
```

**Solution:**

First count the number of appereances of each character, the special case happens when the string could be arranged and the char with largest value has a appereance of (N/2+1) times.

From this case, we know the char that  appears the most times should be arranged first(put this char once each two positions, then position pointer increase two each time),then the problem turns to how to make a sorted array, and at the same time keeps the number of appereance and the value that denotes character itself. 

The solution to the problem above is arrange the array like below:

```java
int[] counts = new int[26];
for (char c: S.toCharArray()) counts[c-'a'] += 100;
for (int i = 0; i < 26; ++i) counts[i] += i;
//Encoded counts[i] = 100*(actual count) + (i)
Arrays.sort(counts);
```

the first count denote the number of appearence, the second one denote which character it belongs to. After sorting, it is easy to find the character.

```java
class Solution {
    public String reorganizeString(String S) {
        int N = S.length();
        int[] counts = new int[26];
        for (char c: S.toCharArray()) counts[c-'a'] += 100;
        for (int i = 0; i < 26; ++i) counts[i] += i;
        //Encoded counts[i] = 100*(actual count) + (i)
        Arrays.sort(counts);

        char[] ans = new char[N];
        int t = 1;
        for (int code: counts) {
            int ct = code / 100;
            char ch = (char) ('a' + (code % 100));
            if (ct > (N+1) / 2) return "";
            for (int i = 0; i < ct; ++i) {
                if (t >= N) t = 0;
                ans[t] = ch;
                t += 2;
            }
        }

        return String.valueOf(ans);
    }
}
```

