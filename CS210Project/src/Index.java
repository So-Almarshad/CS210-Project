public class Index {
//AVL class
    private AVLNode root;
    private int size;
    
    public Index(){// sheek 3ala line 75 to 82 for Node class , lines 94 106 142 150 155
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
}
