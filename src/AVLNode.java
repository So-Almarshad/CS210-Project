public class AVLNode implements Comparable<AVLNode>{
    private String token ;
    private int height;// height is the downward distance between a node and null node
    private int frequency ;

    private SList SLL;
    public AVLNode left;
    public AVLNode right;

    public AVLNode(String token, int height, int frequency, SList List) {
        this.token = token;
        this.height = height; 
        this.frequency = frequency; 
        this.SLL = List;
        this.left = null; 
        this.right = null;
    }

    public AVLNode(String token){
        this.token=token;
        height=1; // Starting the node from h1
        frequency=1; // Starting with 1 and increments
        SLL=new SList();
        left=null;// no children yet
        right=null;
    }

    /*GETTERS & SETTERS*/
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

    public SList getSLL() {
        return SLL; 
    }

    public void setSLL(SList SLL) {
        this.SLL = SLL; 
    }
    
    /*METHODS*/

    public boolean isLeaf(){ //checking if it is leaf ?
        return left == null && right == null;// helping also in removal
    }
    
    public boolean has1Child(){
        //method to check if a node has only 1 child
        if (left== null || right== null) 
            if (left!= null || right!=null) 
                return true;
        return false; 
    }
    
    public void updHeight(){//updateing height
        int LHeight = 0;
        if(left != null)
            LHeight = left.height;
        int RHeight = 0;
        if(right != null)
            RHeight = right.height;
        
        this.height =  Math.max(LHeight, RHeight) + 1 ;
    }

    public void incFreq(){
        this.setFrequency(frequency+1);
    }

    @Override
    public int compareTo(AVLNode o){//compare node's token to other token
        return this.token.compareToIgnoreCase(o.getToken());
    }
    /* 
    These methods are redundent compareTo is suffecient
    public boolean matches(String ot){//node same same node??
        return this.token.equals(ot);
    }

    public boolean isLess(String oth){//did the node come before another token alphabeticlly
        return this.token.compareTo(oth) < 0 ;
    }

    public boolean isGreater(String othe){//did the node come after another token alphabeticlly
        return this.token.compareTo(othe) > 0 ;
    }
    */
    public void copyVal(AVLNode node){
        //copy values of node into calling node
        // Only copy the data, NOT the structure (left/right pointers)
        this.SLL=node.SLL;
        this.frequency=node.frequency;
        this.token=node.token;
        // Do NOT copy height, left, or right - those should remain as is
    }
    @Override
    public String toString() {
        return "AVLNode{ Token = " + token + ", Height = " + height + ", Frequency = " + frequency + '}';
    }

}
