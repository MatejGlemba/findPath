class Node {
    private int x;
    private int y;
    Node parent;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
    }

    Node(int x, int y, Node parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
