# Depth First Search

The DFS algorithm is a recursive algorithm, it involves exhaustive searches for all the nodes by going ahead, if possible, else by backtracking. If there are no more nodes along the current path, you move backwards on the same path, and find another path to traverse. 

The algorithm can be implemented using stacks, and this is an **iterative way** of implementation:

1. Pick a starting node and push all the adjacent nodes into a stack.
2. Pop a node from the stack as the current working node, and push all the adjacent nodes of the current one into the stack.
3. Repeat the process, while marking the visited nodes to avoid infinite loop.

Also, the algorithm can implemented by **recursion**:

1. Pick the starting node.
2. Visit the current working node.
3. For all the adjacent nodes of the current working one, repeat step 2.

## 99. Recover Binary Search Tree

```
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.
```

There are many methods of solving the problem. 

### 1. Reucrsion i

The first is using recursion and the inorder traversal of the tree. **The inorder traversal is an extremely useful method of solving BST problem, since the inorder traversal is well ordered.** In this problem, only two elements are swapped, so inorder traversal can help to find the two elements.

```java
    public void recoverTree(TreeNode root) {
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
```

## 112. Path Sum

Given a BST and a target value, then check whether there exists a root-to-leaf path that the sum of every value equals to the target.

This is a very calssical problem, normally this kind of problem has two types, one is to find the existence, another is to find all the paths that meet the requirements. The solution to this problem is a standard DFS algorighm, like bellow:

```java
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
```

Since JAVA used value type for int, boolean type(maybe), so we need to set the global variable **found.**

## 113. Path Sum ii

This problem is the other type of 112, which requires a list to store all the paths, sine for other data types like list, map, arraylist, JAVA implemented reference type, so we do not need to use a global list, map or array. The rest of the code is very similar to 112, **but we need to be careful that after each recursion, we need to remove the last element, which we added before we call the recursive function.**

```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        if (root == null)
            return res;
        temp.add(root.val);
        checkPath(root, sum, 0, res, temp);
        return res;

    }

    private void checkPath(TreeNode node, int target, int sum, List<List<Integer>> res, List<Integer> temp) {
        sum = sum + node.val;
        if (sum == target && node.left == null && node.right == null) {
            res.add(new ArrayList(temp));
            return;
        }
        if (node.left != null) {
            temp.add(node.left.val);
            checkPath(node.left, target, sum, res, temp);
            temp.remove(temp.size() - 1);
        }
        if (node.right != null) {
            temp.add(node.right.val);
            checkPath(node.right, target, sum, res, temp);
            temp.remove(temp.size() - 1);
        }
        return;

    }
}
```

