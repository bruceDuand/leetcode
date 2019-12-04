import javax.swing.tree.TreeNode;
/*
 * @lc app=leetcode id=104 lang=java
 *
 * [104] Maximum Depth of Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int res = findMaxDepth(root, 0, 1);

        return res;
    }

    public int findMaxDepth(TreeNode node, int depth, int res) {
        if(node == null)
            return depth;

        int maxdepth1 = findMaxDepth(node.left, depth+1, res);
        int maxdepth2 = findMaxDepth(node.right, depth+1, res);
        
        return Math.max(maxdepth1, maxdepth2);
    }
}
// @lc code=end

