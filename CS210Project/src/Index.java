public class Index {
//AVL class
    private AVLNode root;
    private int size;
    
    public Index(){// sheek 3ala line 75 for Node class
        this.root = null;
        this.size = 0;// enta lazem t3el 3lehom
    }
    

    public Index(AVLNode root, int size) {
        this.root = root;
        this.size = size;
    }
    private int height(AVLNode n){
        if (n == null) {
            return 0;
        }
        return n.height;
    }
    private int getBalance(AVLNode n){
            if (n == null) {
                return 0;
    }
            return height(n.getLeft())- height(n.getRight());
    }
    private AVLNode rotateRight(AVLNode y){
        //System.out.println("Rotating Right around: "+ y.getToken());
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        
        x.right = y;
        y.left = T2;
        
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        
        return x;
    }
    private AVLNode rotateLeft(AVLNode x){
        //System.out.println("Rotating Right around: "+ y.getToken());
        AVLNode y = x.left;
        AVLNode T2 = y.right;
        
        y.left = x;
        x.right = T2;
        
        x.height = Math.max(height(x.left), height(x.right))+ 1;
        y.height = Math.max(height(y.left), height(y.right))+ 1;
        
        return y;
    }
    
    
    private AVLNode balance(AVLNode no){ //no ==node
        if(no == null)
            return null;
        int balanceFactor = getBalance(no);
        //LL
        if(balanceFactor>1 && getBalance(no.left) >= 0)
            return rotateRight(no);
        //LR
        if(balanceFactor>1 && getBalance(no.left) < 0)
            return rotateRight(no);
        //RR
        if(balanceFactor>-1 && getBalance(no.right) <= 0)
            return rotateLeft(no);
        //RL
        if(balanceFactor>-1 && getBalance(no.right) > 0)
            return rotateLeft(no);
        return no;
    }
    
    //public void Insert(String token, String filename, int lineNumber) {
      //  root = Insert(root, token, filename, lineNumber);
    //}
    //Starting Block 9
}
