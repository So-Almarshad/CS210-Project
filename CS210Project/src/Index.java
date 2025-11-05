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
    /*
    Useless method can get the height of node using n.getHeight()
    private int height(AVLNode n){
        if (n == null) {
            return 0;
        }
        return n.getHeight();
    }*/
    private int getBalance(AVLNode node){
            if (node == null) {
                return 0;
            }
            // if result is negative then the difference is to the right. Vice versa
            return node.getLeft().getHeight()-node.getRight().getHeight();
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
    
    /*public void Insert(String token, String filename, int lineNumber) {
        root = insert(root, token, filename, lineNumber);
    }
    
    private AVLNode insert(AVLNode node, String token, String filename, int lineNumber){
        if (node === null) {//finding the free place,and create new
            AVLNode newNode = new AVLNode(token);
            newNode.list.Insert(filename, lineNumber);
            newNode.frequency =1;
            size++;
            //System.out.println("Created new node for: "+token);
            return newNode;
        }
        int comparizon = token.compareTo(node.token);//deciding right or left
        if (comparizon < 0 ) {//left token smaller
            node.left = insert(node.left, token, filename, lineNumber);
        }else if (comparizon > 0){//right token larger
            node.right = insert(node.right, token , filename, lineNumber);
        }else{//updating frequency & list, token is already there
            node.list.Insert(filename, lineNumber);
            node.frequency++;
            //System.out.println("Updated existing node: "+token + ", frequency: "+node.frequency);
            return node;
        }
        node.height= Math.max(height(node.left), height(node.right))+1;//height ++
        return balance(node);
    }*/
    
    public boolean insert(AVLNode node){
        //Update heights
        try {
            insert(node,this.getRoot());
            size++;
            return true;
        } catch (Exception e) {
            System.err.println("Unable to insert node into AVL");
            return false;
        }
    }
    
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
    }

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
        
        if (token.compareTo(node.getToken()) <0) {//s left
            return search(node.left,token);
        }else if(token.compareTo(node.getToken()) <0){//s right
            return search(node.right,token);
        }else{
            return node; //found
        }
    }
    public boolean remove(String token){
        //Update heights
        try {
            AVLNode temp=remove(root, token);
            if (temp!=null) {
                size--;
                return true;
            }
            
            System.out.println("Token not found");
            return false;
        } catch (Exception e) {
            System.err.println("Unable to remove token");
            return false;
        }
        
    }

    private AVLNode remove(AVLNode node, String token) {
        //Method needs some revision
        if (node == null) {
            return null; // token not found
        }
        
        if (token.compareTo(node.getToken()) <0) {//s left
            return remove(node.left,token);
        }
        else if(token.compareTo(node.getToken()) >0){//s right
            return remove(node.right,token);
        }
        else{// Token found
            if (node.isLeaf()) {
                node=null;
            }
            else if (node.has1Child()) {
                AVLNode temp = (node.left !=null) ? node.left :node.right;
                node=temp;
            }
            else{
                AVLNode temp = minValueNode(node.right);
                
                node.setToken(temp.getToken());
                node.setFrequency(temp.getFrequency());
                node.setSLL(temp.getSLL());
                node.right = remove(node.right,temp.getToken());
            }
        }
        
        
        //updating heihgt & balance
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return balance(node);
    }

    public String Traverse(){
        StringBuilder result = new StringBuilder();
        postOrderTravesal(root, result);
        return result.toString().trim();
    }

    private void postOrderTravesal(AVLNode node, StringBuilder result){
        if(node != null){
            postOrderTravesal(node.left, result);  // left subtree
            postOrderTravesal(node.right, result); // right subtree
            result.append(node.token).append(" "); //cuurrent node
        }
    }

    private AVLNode minValueNode(AVLNode node){
        AVLNode current = node;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    
    public void clear(){
        root = null;
        size = 0;
    }

    @Override
    public String toString(){
        return "Index{ size = "+size+", height = "+(root != null ? root.height : 0)+" } ";

    }
//AVL class
}
