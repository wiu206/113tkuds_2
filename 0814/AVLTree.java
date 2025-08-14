public class AVLTree {
    private AVLNode root;
    
    // 取得節點高度
    private int getHeight(AVLNode node) {
        return (node != null) ? node.height : 0;
    }
    
    // 插入節點
    public void insert(int data) {
        root = insertNode(root, data);
    }
    
    private AVLNode insertNode(AVLNode node, int data) {
        if (node == null) {
            return new AVLNode(data);
        }
        
        if (data < node.data) {
            node.left = insertNode(node.left, data);
        } else if (data > node.data) {
            node.right = insertNode(node.right, data);
        } else {
            return node; // 重複值不插入
        }
        
        node.updateHeight();
        
        int balance = node.getBalance();
        
        // Left Left
        if (balance > 1 && data < node.left.data) {
            return AVLRotations.rightRotate(node);
        }
        // Right Right
        if (balance < -1 && data > node.right.data) {
            return AVLRotations.leftRotate(node);
        }
        // Left Right
        if (balance > 1 && data > node.left.data) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }
        // Right Left
        if (balance < -1 && data < node.right.data) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }
        
        return node;
    }
    
    // 搜尋節點
    public boolean search(int data) {
        return searchNode(root, data);
    }
    
    private boolean searchNode(AVLNode node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        if (data < node.data) return searchNode(node.left, data);
        return searchNode(node.right, data);
    }
    
    // 找最小值節點
    private AVLNode findMin(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    // 刪除節點
    public void delete(int data) {
        root = deleteNode(root, data);
    }
    
    private AVLNode deleteNode(AVLNode node, int data) {
        if (node == null) return null;
        
        if (data < node.data) {
            node.left = deleteNode(node.left, data);
        } else if (data > node.data) {
            node.right = deleteNode(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    node = null;
                } else {
                    node.data = temp.data;
                    node.left = temp.left;
                    node.right = temp.right;
                    node.height = temp.height;
                }
            } else {
                AVLNode temp = findMin(node.right);
                node.data = temp.data;
                node.right = deleteNode(node.right, temp.data);
            }
        }
        
        if (node == null) return node;
        
        node.updateHeight();
        
        int balance = node.getBalance();
        
        // Left Left
        if (balance > 1 && node.left.getBalance() >= 0) {
            return AVLRotations.rightRotate(node);
        }
        // Left Right
        if (balance > 1 && node.left.getBalance() < 0) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }
        // Right Right
        if (balance < -1 && node.right.getBalance() <= 0) {
            return AVLRotations.leftRotate(node);
        }
        // Right Left
        if (balance < -1 && node.right.getBalance() > 0) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }
        
        return node;
    }
    
    // 驗證是否為有效的 AVL 樹
    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }
    
    private int checkAVL(AVLNode node) {
        if (node == null) return 0;
        
        int leftHeight = checkAVL(node.left);
        int rightHeight = checkAVL(node.right);
        
        if (leftHeight == -1 || rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    // 列印樹狀結構
    public void printTree() {
        printInOrder(root);
        System.out.println();
    }
    
    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            printInOrder(node.right);
        }
    }
    
    // ===== 測試用 main 方法 =====
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // 插入節點
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println("中序列印 (節點值(平衡因子))：");
        tree.printTree();

        // 搜尋
        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("搜尋 99: " + tree.search(99));

        // 刪除
        tree.delete(40);
        System.out.println("刪除 40 後：");
        tree.printTree();

        // 驗證 AVL
        System.out.println("是否為有效 AVL: " + tree.isValidAVL());
    }
}
