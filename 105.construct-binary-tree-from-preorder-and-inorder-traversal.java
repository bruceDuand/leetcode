import java.util.Stack;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=105 lang=java
 *
 * [105] Construct Binary Tree from Preorder and Inorder Traversal
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0)
            return null;
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] predorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        TreeNode node = new TreeNode(predorder[preStart]);

        int pos = findMax(inorder, node.val, inStart, inEnd);
        int left_len = pos - inStart;
        int right_len = inEnd - pos;

        if (left_len > 0)
            node.left = buildTree(predorder, preStart + 1, preStart + left_len, inorder, inStart,
                    inStart + left_len - 1);

        if (right_len > 0)
            node.right = buildTree(predorder, preEnd - right_len + 1, preEnd, inorder, inEnd - right_len + 1, inEnd);

        return node;
    }

    public int findMax(int[] inorder, int value, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == value)
                return i;
        }

        return -1;
    }
}
// @lc code=end

/*
 * 1. init a stack 2. for i in predorder 3. stack.push(inorder) 4. if inorder =
 * preorder 5. construct left tree of preorder 6.
 * 
 * the stack method is mor interesting than recursive, recursive method is too
 * slow, may need to optimize it
 * 
 */
