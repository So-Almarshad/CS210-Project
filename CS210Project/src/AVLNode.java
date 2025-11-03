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
        return token; }

    public void setToken(String token) {
        this.token = token; }

    public int getHeight() {
        return height; }

    public void setHeight(int height) {
        this.height = height; }

    public int getFrequency() {
        return frequency; }

    public void setFrequency(int frequency) {
        this.frequency = frequency; }

    public SList getSingList() {
        return SingList; }

    public void setSingList(SList SingList) {
        this.SingList = SingList; }

    public AVLNode getLeft() {
        return left; }

    public void setLeft(AVLNode left) {
        this.left = left; }

    public AVLNode getRight() {
        return right; }

    public void setRight(AVLNode right) {
        this.right = right; }
    // Block 4
    public boolean isLeaf(){ //checking if it is leaf ?
        return left == null && right == null;// helping also in removal
    }
    
    public boolean has1Child(){//checking if it has children
        return ((left != null||left == null) && (right == null||right != null)); }//removaal
    
    public void updHeight(){//updateing height
        int LHeight = 0;
        if(left != null)
            LHeight = left.height;
        int RHeight = 0;
        if(right != null)
            RHeight = right.height;
        
        this.height =  Math.max(LHeight, RHeight) + 1 ;
    }
    public int compareTo(String o){//compare node to token//other token
        return this.token.compareTo(o);
    }
    public boolean matches(String ot){//node same same node??
        return this.token.equals(ot);
    }
    public boolean isLess(String oth){//did the node come before another token alphabeticlly
        return this.token.compareTo(oth) < 0 ;
    }
    public boolean isGreater(String othe){//did the node come after another token alphabeticlly
        return this.token.compareTo(othe) > 0 ;
    }
    
    

    @Override
    public String toString() {
        return "AVLNode{ Token = " + token + ", Height = " + height + ", Frequency = " + frequency + '}';
    }
}
