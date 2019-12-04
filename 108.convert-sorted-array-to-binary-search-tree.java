import javax.swing.tree.TreeNode;
/*
 * @lc app=leetcode id=108 lang=java
 *
 * [108] Convert Sorted Array to Binary Search Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;

        TreeNode root = new TreeNode(0);
        convertToBST(nums, root, 0, nums.length - 1);

        return root;

    }

    private void convertToBST(int[] nums, TreeNode node, int minind, int maxind) {
        int mid = (minind + maxind) / 2;
        node.val = nums[mid];

        if (mid != minind) {
            TreeNode left_node = new TreeNode(0);
            node.left = left_node;
            convertToBST(nums, left_node, minind, mid - 1);
        } else
            node.left = null;

        if (mid != maxind) {
            TreeNode right_node = new TreeNode(0);
            node.right = right_node;
            convertToBST(nums, right_node, mid + 1, maxind);
        } else
            node.right = null;
        return;

    }

}
// @lc code=end
