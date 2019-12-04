import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.TreeNode;


/*
 * @lc app=leetcode id=99 lang=java
 *
 * [99] Recover Binary Search Tree
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

    public void recoverTree(TreeNode root){
        
    }

    public void recoverTree_1(TreeNode root) {
        if(root==null)
            return;

        List<TreeNode> inorder = new ArrayList<TreeNode>();
        inorderTraversal(root, inorder);
        TreeNode first = null;
        TreeNode second = null;
        for(int i=1; i<inorder.size(); i++){
            if(inorder.get(i).val > inorder.get(i-1).val)
                continue;
            if (first == null) first = inorder.get(i-1);
            second = inorder.get(i);
        }

        if(first == null) return;
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
        
    }

    public void inorderTraversal(TreeNode node, List<TreeNode> inorder){
        if(node==null)
            return;
        inorderTraversal(node.left, inorder);
        inorder.add(node);
        inorderTraversal(node.right, inorder);
    }
}
// @lc code=end

