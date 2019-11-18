import javafx.application.Preloader;

/*
 * @lc app=leetcode id=106 lang=java
 *
 * [106] Construct Binary Tree from Inorder and Postorder Traversal
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0)
            return null;
        return buildTreeRec(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTreeRec(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        TreeNode node = new TreeNode(postorder[postEnd]);

        int pos = findPos(inorder, node.val, inStart, inEnd);
        int left_len = pos - inStart;
        int right_len = inEnd - pos;

        if (left_len > 0)
            node.left = buildTreeRec(inorder, inStart, inStart + left_len - 1, postorder, postStart,
                    postStart + left_len - 1);
        if (right_len > 0)
            node.right = buildTreeRec(inorder, inEnd - right_len + 1, inEnd, postorder, postEnd - right_len,
                    postEnd - 1);

        return node;

    }

    private int findPos(int[] inorder, int val, int start, int end) {
        for (int i = end; i >= start; i--) {
            if (inorder[i] == val)
                return i;
        }

        return -1;
    }
}
// @lc code=end
