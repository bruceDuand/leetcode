/*
 * @lc app=leetcode id=112 lang=java
 *
 * [112] Path Sum
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    private boolean found = false;

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        checkPath(root, sum, 0);
        return found;

    }

    private void checkPath(TreeNode node, int target, int temp) {
        temp = temp + node.val;
        if (temp == target && node.left == null && node.right == null) {
            found = true;
            return;
        }
        if (node.left != null)
            checkPath(node.left, target, temp);
        if (node.right != null)
            checkPath(node.right, target, temp);

        return;

    }
}
// @lc code=end
