/*
 * @lc app=leetcode id=120 lang=java
 *
 * [120] Triangle
 */

// @lc code=start
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size()==0)
            return 0;

        List<Integer> DP= new ArrayList<Integer>(triangle.get(triangle.size()-1));

        for(int i=triangle.size()-2; i>=0; i--)
            for(int j=0; j<=i; j++) {
                DP.set(j, Math.min(DP.get(j), DP.get(j+1))+triangle.get(i).get(j));
            }

        return DP.get(0);


    }
}
// @lc code=end

// This kind of DP changes the original matrix
// the bottom-up DP can do better than this
// if(triangle.size()==1)
//     return triangle.get(0).get(0);

// int m = triangle.size();

// for (int i = 1; i<m; i++) {
//     for(int j = 0; j<=i; j++) {
//         if (j == 0)
//             triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j));  
//         else if(j == i)
//             triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j-1));  
//         else
//             triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i-1).get(j-1), triangle.get(i-1).get(j)));
//     }
// }

// int minpath = triangle.get(m-1).get(0);
// for (int k = 0; k < m; k++) {
//     if(triangle.get(m-1).get(k)<minpath)
//         minpath = triangle.get(m-1).get(k);
// }
