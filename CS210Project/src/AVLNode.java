public class AVLNode {
    private String token ;
    private int height ;
    private int frequency ;
    private SList SingList;
    private AVLNode left;
    private AVLNode right;

    public AVLNode(String token, int height, int frequency, SList List, AVLNode left, AVLNode right) {
        this.token = token;
        this.height = 1; // Starting the node from h1
        this.frequency = 0; // Starting with 0 and increments
        this.SingList = List;
        this.left = null; // no children yet
        this.right = null;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public SList getSingList() {
        return SingList;
    }

    public void setSingList(SList SingList) {
        this.SingList = SingList;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }
    // Block 4
    
}
