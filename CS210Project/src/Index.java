public class Index {
//AVL class
    private AVLNode root;
    private int size;
    
    public Index(){// lines 94 106 142 150 155
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
    
    public void Insert(String token, String filename, int lineNumber) {
        root = Insert(root, token, filename, lineNumber);
    }
    //Starting Block 9
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
    }
    
    public SList Search(String token){
        AVLNode node = search(root, token);
        if (node != null) {
            return node.list;
        }
        return null; //not found
    }
    private AVLNode search(AVLNode node, String token){
        if (node == null) {
            return null; //not found
        }
        int comparizen = token.compareTo(node.token);
        if (comparizen <0) {//s left
            return search(node.left,token);
        }else if(comparizen <0){//s right
            return search(node.right,token);
        }else{
            return node; //found
        }
    }//Block 11
    public void Remove(String token){
        root = remove(root, token);
    }

    private AVLNode remove(AVLNode node, String token) {
        if (node == null) {
            return null; // tok not found
        }
        int campores = token.compareTo(node.token);
        if (campores <0) {//s left
            return remove(node.left,token);
        }else if(campores <0){//s right
            return remove(node.right,token);
        }else{// found then
            size--;
            //System.out.println("Removing node: "+node.token);
            if (node.left ==null || node.right == null) {
                AVLNode temp = (node.left !=null) ? node.left :node.right;
                if (temp == null) {
                    node == null; // no child
                }else{
                    node = temp;
                }
            }else{
                AVLNode temp = minValueNode(node.right);
                node.token = temp.token;
                node.frequency = temp.frequency;
                node.list = temp.list;
                node.right = remove(node.right,temp.token);
            }
        }
        if (node == null)return null;
        
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
    public int getSize(){
        return size;
    }
    public void clear(){
        root = null;
        size = 0;
    }
    @Override
    public String toString(){
        return "Index{ size = "+size+", height = "+(root != null ? root.height : 0)+" } ";
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
