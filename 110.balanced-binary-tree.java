/*
 * @lc app=leetcode id=110 lang=java
 *
 * [110] Balanced Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    private boolean res = true;

    public boolean isBalanced(TreeNode root) {
        findDepth(root, 1);
        return res;
    }

    private int findDepth(TreeNode node, int depth) {
        if (node == null)
            return depth;

        int leftDepth = findDepth(node.left, depth + 1);
        int rightDepth = findDepth(node.right, depth + 1);
        if (rightDepth != leftDepth + 1 && leftDepth != rightDepth + 1 && leftDepth != rightDepth)
            res = false;

        return Math.max(leftDepth, rightDepth);
    }
}
// @lc code=end
