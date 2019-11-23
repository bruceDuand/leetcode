import java.util.LinkedList;
import java.util.Queue;
import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=101 lang=java
 *
 * [101] Symmetric Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;

        Queue<TreeNode> q = new LinkedList<TreeNode>();

        q.add(root.left);
        q.add(root.right);

        while(!q.isEmpty()){
            TreeNode leftNode  = q.remove();
            TreeNode rightNode = q.remove();

            if (leftNode == null && rightNode == null)
                continue;
                
            if ((leftNode == null && rightNode != null) || (leftNode != null && rightNode == null)
                    || (rightNode.val != leftNode.val))
                return false;

            q.add(leftNode.right);
            q.add(rightNode.left);
            q.add(leftNode.left);
            q.add(rightNode.right);
        }

        return true;

    }


}
// @lc code=end

// The recursion method
// private boolean isSymmetric(TreeNode leftNode, TreeNode rightNode) {
//     if (leftNode == null && rightNode == null)
//         return true;
//     if ((leftNode == null && rightNode != null) || (leftNode != null && rightNode == null)
//             || (rightNode.val != leftNode.val))
//         return false;
//     return isSymmetric(leftNode.right, rightNode.left) && isSymmetric(leftNode.left, rightNode.right);
// }

// note
// it proves that the recursion method is much quicker than the BFS method
// In my opinion, the only characteristic of BFS is the implementation of queue