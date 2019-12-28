import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=654 lang=java
 *
 * [654] Maximum Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root = buildTree(nums, 0, nums.length - 1);
        return root;
    }

    private TreeNode buildTree(int[] nums, int min, int max) {
        if (min > max)
            return null;

        int ind = min;
        for (int i = min; i <= max; i++) {
            if (nums[i] > nums[ind])
                ind = i;
        }

        TreeNode node = new TreeNode(nums[ind]);
        TreeNode leftnode = buildTree(nums, min, ind - 1);
        TreeNode rightnode = buildTree(nums, ind + 1, max);
        node.right = rightnode;
        node.left = leftnode;
        return node;
    }
}
// @lc code=end
