public class Index {
    private AVLNode root;
    private int size;
    
    public Index(){// lines 94 106 142 150 155
        this.root = null;
        this.size = 0;
    }
    

    public Index(AVLNode root) {
        this.root = root;
        this.size = 1;
    }
    /*GETTERS & SETTERS*/
    public AVLNode getRoot(){
        return this.root;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int newSize){
        this.size=newSize;
    }

    /*METHODS*/
    private int getBalance(AVLNode node){
        //method to check the heights of two children
            if (node == null) {
                return 0;
            }
            // if result is negative then the difference is to the right. Vice versa
            int leftHeight = (node.left != null) ? node.left.getHeight() : 0;
            int rightHeight = (node.right != null) ? node.right.getHeight() : 0;
            return leftHeight - rightHeight;
    }

    private AVLNode rotateRight(AVLNode y){
        //System.out.println("Rotating Right around: "+ y.getToken());
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        
        x.right = y;
        y.left = T2;
        
        y.updHeight();
        x.updHeight();
        
        return x;
    }
    private AVLNode rotateLeft(AVLNode x){
        //System.out.println("Rotating Left around: "+ x.getToken());
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        
        y.left = x;
        x.right = T2;
        
        x.updHeight();
        y.updHeight();
        
        return y;
    }
    
    
    private AVLNode balance(AVLNode no){ //no ==node
        if(no == null)
            return null;
        int balanceFactor = getBalance(no);
        //LL - Left Left Case
        if(balanceFactor > 1 && no.left != null && getBalance(no.left) >= 0)
            return rotateRight(no);
        //LR - Left Right Case (double rotation)
        if(balanceFactor > 1 && no.left != null && getBalance(no.left) < 0){
            no.left = rotateLeft(no.left);
            return rotateRight(no);
        }
        //RR - Right Right Case
        if(balanceFactor < -1 && no.right != null && getBalance(no.right) <= 0)
            return rotateLeft(no);
        //RL - Right Left Case (double rotation)
        if(balanceFactor < -1 && no.right != null && getBalance(no.right) > 0){
            no.right = rotateRight(no.right);
            return rotateLeft(no);
        }
        return no;
    }
    
    
    public boolean insert(AVLNode node){
        //Update heights
        try {
            AVLNode temp=this.search(root, node.getToken());
            if (temp!=null) {
                temp.incFreq();
                return true;
            }
            root=insert(node, root);
            size++;
            return true;
        } catch (Exception e) {
            System.err.println("Unable to insert node into AVL");
            return false;
        }
    }
    public AVLNode insert (AVLNode newNode, AVLNode treeNode){
        if (treeNode==null) {
            return newNode;
        }
        if (newNode.compareTo(treeNode)<0) {
            treeNode.left=insert(newNode, treeNode.left);
        }
        else if (newNode.compareTo(treeNode)>0) {
            treeNode.right=insert(newNode, treeNode.right);
        }
        else
            return treeNode;
        treeNode.updHeight();
        return balance(treeNode);
    }

    /* 
    public void insert(AVLNode newNode, AVLNode treeNode){
        if (newNode.compareTo(treeNode)<1) {
            if (treeNode.left==null) {
                treeNode.left=newNode;
                treeNode.updHeight();
                return;
            }
            insert(newNode, treeNode.left);
        }  
        else if (newNode.compareTo(treeNode)>1) {
            if (treeNode.right==null) {
                treeNode.right=newNode;
                treeNode.updHeight();
                return;
            }
            insert(newNode, treeNode.right);
        }
        else
            treeNode.incFreq();
    }*/

    public AVLNode Search(String token){
        AVLNode node = search(root, token);
        if (node != null) {
            return node;
        }
        return null; //not found
    }

    private AVLNode search(AVLNode node, String token){
        if (node == null) {
            return null; //not found
        }
        
        if (token.compareTo(node.getToken()) < 0) {//go left
            return search(node.left, token);
        } else if(token.compareTo(node.getToken()) > 0) {//go right
            return search(node.right, token);
        } else {
            return node; //found
        }
    }
    
    public boolean remove(String token){
        try {
                if (search(root, token)==null) {
                    return false;
                }
                root=remove(root, token);
                size--;
                return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    private AVLNode remove(AVLNode node, String token){
        if (node==null) {
            return null;
        }
        if (token.compareTo(node.getToken())<0) {
            node.left=remove(node.left, token);
        }
        else if (token.compareTo(node.getToken())>0) {
            node.right=remove(node.right,token);
        }
        else{
            // Check if it's a leaf node first (0 children)
            if (node.isLeaf()) {
                return null;
            }
            // Check if it has 1 child
            if (node.has1Child()) {
                if (node.left==null) {
                    return node.right;
                }
                else
                    return node.left;
            }
            // Has 2 children - replace with inorder successor
            node.copyVal(this.minValueNode(node.right));
            node.right=remove(node.right, node.getToken());
        }
        node.updHeight();
        return balance(node);
    }


    public void traverse(){
        if (isEmpty()) {
            return;
        }
        AVLNode iterator=root;
        traverse(iterator);
        System.out.println();
    }
    
    public void traverse(AVLNode node){
        //post order traversal
        if (node!=null) {
            traverse(node.left);
            traverse(node.right);
            System.out.print(node.getToken()+" ");   
        }
    }

    private AVLNode minValueNode(AVLNode node){
        //method to find the smallest value child of this node
        AVLNode current = node;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    public boolean isEmpty(){
        return size==0;
    }
    
    public void clear(){
        root = null;
        size = 0;
    }

    @Override
    public String toString(){
        return "Index{ size = "+size+", height = "+(root != null ? root.getHeight() : 0)+" } ";

    }
}
