package gessyca;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public void insert(int val) {
        root = insertRecursive(root, val);
    }

    private TreeNode insertRecursive(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }

        if (val < root.val) {
            root.left = insertRecursive(root.left, val);
        } else if (val > root.val) {
            root.right = insertRecursive(root.right, val);
        }

        return root;
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

    private void printTreeRecursive(TreeNode root, int depth) {
        if (root != null) {
            printTreeRecursive(root.right, depth + 1);
            for (int i = 0; i < depth; i++) {
                System.out.print("   ");
            }
            System.out.println(root.val);
            printTreeRecursive(root.left, depth + 1);
        }
    }


    public void searchNumber(int num, TreeNode root){
        if (root != null){
            if (num == root.val){
                System.out.println(num + " encontrado");
            }
            else if (num>root.val){
                searchNumber(num, root.right);
            }
            else{
                searchNumber(num, root.left);
            }
        }
        else{
            System.out.println(num + " não encontrado");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        int[] numbers = {15, 8, 2, 12, 23, 20, 30};
        for (int num : numbers) {
            tree.insert(num);
        }
        tree.insert(24);
        tree.printTree();
        tree.searchNumber(12, tree.root);
        tree.searchNumber(21, tree.root);
    }
}

