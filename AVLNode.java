class AVLNode {
    double epsilon;
    AVLNode left, right;
    int height;
    int bal;
    QuadTreeNode quadNode; // Ajout de l'adresse du quadtree node

    public AVLNode(double epsilon, QuadTreeNode quadNode) {
        this.epsilon = epsilon;
        this.height = 1;
        this.bal = 0;
        this.left = null;
        this.right = null;
        this.quadNode = quadNode;
    }
}
